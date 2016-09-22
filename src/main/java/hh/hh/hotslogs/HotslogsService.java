package hh.hh.hotslogs;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BinaryOperator;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import feign.Param;
import feign.RequestLine;
import hh.hh.hotslogs.data.History;
import hh.hh.hotslogs.data.HistoryResult;
import hh.hh.hotslogs.data.Player;
import hh.hh.hotslogs.data.Statistic;

@RestController
@RequestMapping("hh")
public class HotslogsService {

	@Autowired
	private Hotslogs hotslogs;

	@Autowired
	private HotslogsApi hotslogsapi;

	interface Hotslogs {
		@RequestLine("GET /PlayerSearch?Name={name}")
		String playerSearch(@Param("name") String name);

		@RequestLine("GET /Player/Profile?PlayerID={id}")
		String playerDetail(@Param("id") String id);

		@RequestLine("GET /Player/MatchHistory?PlayerID={id}")
		String history(@Param("id") String id);
	}

	interface HotslogsApi {
		@RequestLine("GET /Players/{id}")
		String getHotsLogsPlayerDetailsByPlayerId(@Param("id") int id);

		@RequestLine("GET /Players/{region}/{battleTag}")
		String getHotsLogsPlayerDetailsByBattleTag(@Param("region") int region, @Param("battleTag") String battleTag);
	}

	static class Contributor {
		String login;
		int contributions;
	}

	@Cacheable("hotsIds")
	@RequestMapping(value = "/api/name/{name}", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Player> getHotsIds(@PathVariable String name) {
		System.out.println("HotslogsApi.getHotsIds()");
		List<Player> result = new ArrayList<>();
		Document doc = Jsoup.parse(hotslogs.playerSearch(name));
		Elements titles = doc.getElementsByTag("title");

		for (Element title : titles) {

			if (title.toString().contains("Player Search")) {
				handleResultTable(name, result, doc);

			} else {
				handleSingleResult(name, result, doc, title);
			}
		}
		for (Player player : result) {
			player.setNumberOfMatches(result.size());
		}

		return result;
	}

	@Cacheable("bestMatch")
	@RequestMapping(value = "/api/bestmatch/{name}", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Player getBestMatch(@PathVariable String name) {
		System.out.println("HotslogsApi.getBestMatch(): " + name);
		List<Player> players = getHotsIds(name);
		Player bestMatch = new PlayerFilter(players).getBestMatch();
		System.out.println("Best Match: " + bestMatch);

		return bestMatch;
	}

	@Cacheable("history")
	@RequestMapping(value = "/api/history/{id}", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public HistoryResult getMatchHistory(@PathVariable String id) {
		System.out.println("HotslogsApi.getMatchHistory()");

		List<History> result = createHistory(id);
		List<Statistic> stat = createStats(result);

		HistoryResult wrapper = new HistoryResult();
		wrapper.setRows(result);
		wrapper.setStatistics(stat);

		return wrapper;
	}

	@RequestMapping(value = "/api/details/{id}", method = GET)
	public List<String> getDetails(@PathVariable String id) {

		System.out.println("HotslogsApi.getDetails()");
		List<String> result = new ArrayList<>();

		Document doc = Jsoup.parse(hotslogs.playerDetail(id));
		Elements rows = doc.select("tr[id^=ctl00_MainContent_RadGridCharacterStatistics_ctl00]");

		for (Element element : rows) {
			result.add(element.toString());
		}

		// limit = parseInt(limit) || 5;
		// sort = sort || HOTS_LOGS_DEFAULT_SORT;
		// let heroes = [];
		// let $grid = $('div#heroStatistics table.rgMasterTable
		// tr[id^="ctl00_MainContent_RadGridCharacterStatistics"]');
		// let isDefaultSort = sort == HOTS_LOGS_DEFAULT_SORT;
		// // if sorting by the default, scan only the number of rows requested
		// // otherwise, scan all rows, then sort and limit
		// $grid.slice(0, isDefaultSort ? limit : undefined).each( function
		// (index, ele) {
		// let hero = {};
		// let $ele = $(ele);
		// let $tds = $ele.children('td');;
		// hero.name = $ele.find("a[title]").attr('title');
		// hero.level = parseInt($tds.eq(3).text());
		// hero.gamesPlayed = parseInt($tds.eq(4).text());
		// hero.winPercent = parseFloat($tds.eq(6).text()) || null;
		// hero.skill = getHeroSkill(hero);
		// heroes.push(hero);
		// });
		// if (!isDefaultSort) {
		// let direction = 1; // ascending
		// if (sort.startsWith('-')) {
		// // descending
		// sort = sort.substring(1);
		// direction = -1;
		// }
		// heroes.sort((a, b) => {
		// return direction * (a[sort] - b[sort]);
		// });
		// heroes = heroes.slice(0, limit);
		// }
		// return heroes;

		return result;
	}

	@RequestMapping(value = "/api/bt/{battleTag}", method = GET)
	public String getDetailsForBattleTag(@PathVariable String battleTag) {

		return hotslogsapi.getHotsLogsPlayerDetailsByBattleTag(2, battleTag.replace('#', '_'));
	}

	private List<Statistic> createStats(List<History> input) {

		int maxRecords = 200;

		// number of games per hero
		Map<String, Long> mappingHeroToNumberOfGames = input.stream().limit(maxRecords)
				.collect(Collectors.groupingBy(History::getHero, Collectors.counting()));

		// number of wins per hero
		Map<String, Long> mappingHeroToNumberOfWins = input.stream().limit(maxRecords).filter(History::isWin)
				.collect(Collectors.groupingBy(History::getHero, Collectors.counting()));

		// max hero lvl over all games
		Map<String, Optional<History>> mappingHeroToMaxLvl = input.stream().limit(maxRecords).collect(
				Collectors.groupingBy(History::getHero, Collectors.maxBy(Comparator.comparingInt(History::getLvl))));

		// sort desc by number of games
		Map<String, Long> sortedMappingHeroToNumberOfGames = new LinkedHashMap<>();
		mappingHeroToNumberOfGames.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed())
				.forEachOrdered(e -> sortedMappingHeroToNumberOfGames.put(e.getKey(), e.getValue()));

		List<Statistic> result = new ArrayList<>();
		int i = 0;
		for (Entry<String, Long> numberOfGamesPerHero : sortedMappingHeroToNumberOfGames.entrySet()) {
			// only create stat for top 6 heroes
			if (i++ >= 6) {
				break;
			}
			String heroName = numberOfGamesPerHero.getKey();
			Long numberOfGames = numberOfGamesPerHero.getValue();
			
			Statistic s = new Statistic();
			s.setHero(heroName);
			s.setNumber(numberOfGames);
			Optional<History> o = mappingHeroToMaxLvl.get(heroName);
			if (o.isPresent()) {
				s.setLvl(o.get().getLvl());
			} else {
				s.setLvl(0);
			}

			int sizeOfHistory = input.size();
			if (input.size() > maxRecords) {
				sizeOfHistory = maxRecords;
			}
			
			BigDecimal countOfGamesPerHero = new BigDecimal(numberOfGamesPerHero.getValue());
			BigDecimal size = new BigDecimal(sizeOfHistory);
			

			BigDecimal percentage = countOfGamesPerHero.divide(size, 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
			
			if (mappingHeroToNumberOfWins.get(heroName) != null) {
				BigDecimal wins = new BigDecimal(mappingHeroToNumberOfWins.get(heroName));
				BigDecimal winrate = wins.divide(countOfGamesPerHero, 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
				s.setWinrate(winrate.toString());
			} else {
				s.setWinrate("0");
			}

			s.setPercentage(percentage.toString());
			

			result.add(s);
		}

		return result;
	}

	private List<History> createHistory(String id) {
		List<History> result = new ArrayList<>();
		Document doc = Jsoup.parse(hotslogs.history(id));
		Elements bodies = doc.getElementsByTag("tbody");

		for (Element tbody : bodies) {

			Elements rows = tbody.getElementsByTag("tr");
			for (Element tr : rows) {
				History h = TagHelper.getHistoryFromResultRow(tr);
				if (h != null) {
					result.add(h);
				}
			}
		}
		return result;
	}

	private void handleSingleResult(String name, List<Player> result, Document doc, Element title) {
		Element form = doc.getElementById("ctl01");
		// <form method="post" action="./Profile?PlayerID=4387231" id="ctl01">
		String attr = form.attr("action");

		Player player = new Player(TagHelper.getPlayerIdFromLink(attr));
		player.setName(name);
		player.setRegion(TagHelper.getRegionFromTitle(title));
		player.setMmr(TagHelper.getMmrFromDetailPage(doc));
		player.setNumberOfGames(TagHelper.getNumberOfGamesFromDetailPage(doc));
		player.setNumberOfMatches(1);
		result.add(player);
	}

	private void handleResultTable(String name, List<Player> result, Document doc) {
		Elements tables = doc.getElementsByTag("tbody");
		for (Element tbody : tables) {
			Elements rows = tbody.getElementsByTag("tr");
			for (Element tr : rows) {
				// <tr class="rgRow" id="__0">
				Player player = TagHelper.getPlayerFromResultRow(tr, name);
				if (player != null) {
					result.add(player);
				}
			}
		}
	}
}
