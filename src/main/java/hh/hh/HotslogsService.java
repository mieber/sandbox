package hh.hh;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import feign.Param;
import feign.RequestLine;

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
    }

    interface HotslogsApi {
        @RequestLine("GET /Players/{id}")
                String getHotsLogsPlayerDetailsByPlayerId(@Param("id") int id);

        @RequestLine("GET /Players/{region}/{battleTag}")
                String getHotsLogsPlayerDetailsByBattleTag(@Param("region") int region,
                        @Param("battleTag") String battleTag);

    }

    static class Contributor {
        String login;
        int contributions;
    }

    @RequestMapping(value = "/api/name/{name}", method = GET)
    public List<Integer> getHotsIds(@PathVariable String name) {
        System.out.println("HotslogsApi.getHotsIds()");
        List<Integer> result = new ArrayList<>();

        Document doc = Jsoup.parse(hotslogs.playerSearch(name));
        Elements links = doc.getElementsContainingOwnText("View Match History");

        for (Element link : links) {
            // <a title="View Match History" href="/Player/MatchHistory?PlayerID=6436392">View Match History</a>
            // 01234567890123456789012345678901234567890123456789012345678901234567890123456789
            // 00000000001111111111222222222233333333334444444444555555555566666666667777777777
            int end = link.toString().indexOf(">V");
            String id = link.toString().substring(66, end - 1);
            result.add(Integer.parseInt(id));

            System.out.println(id);
        }
        return result;
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
        // $grid.slice(0, isDefaultSort ? limit : undefined).each( function (index, ele) {
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
