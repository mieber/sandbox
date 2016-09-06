package hh.hh.hotslogs;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import feign.Param;
import feign.RequestLine;
import hh.hh.hotslogs.data.History;
import hh.hh.hotslogs.data.Player;

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
    
    @RequestMapping(value = "/api/history/{id}", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<History> getMatchHistory(@PathVariable String id) {
        System.out.println("HotslogsApi.getMatchHistory()");
        List<History> result = new ArrayList<>();
        Document doc = Jsoup.parse(hotslogs.history(id));
        Elements bodies = doc.getElementsByTag("tbody");

        for (Element tbody :  bodies) {

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
}
