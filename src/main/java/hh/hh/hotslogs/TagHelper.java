package hh.hh.hotslogs;

import java.util.Iterator;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TagHelper {

    public static String getRegionFromTitle(Element title) {
        // <title>
        // Heroes of the Storm (HotS): EU Profile: PandaAttack
        // </title>

        String text = title.text();
        int start = text.toString().indexOf("(HotS): ") + 8;
        int end = text.toString().indexOf(" Profile:");

        return text.substring(start, end);
    }

    public static Player getPlayerFromResultRow(Element tr, String name) {
        Elements cells = tr.getElementsByTag("td");
        if (cells.size() < 7) {
            return null;
        }

        Iterator<Element> iterator = cells.iterator();
        // @formatter:off
        // <td style="display:none;">4387231</td>
        Element td = iterator.next();
        Player player = new Player(TagHelper.getIntFromText(td.text()));
        player.setName(name);
        // <td style="display:none;">8/9/2016 6:44:51 AM</td>
        td = iterator.next();
        // <td>EU</td>
        td = iterator.next();
        player.setRegion(td.text());
        // <td>
        //    <img title="HOTS Logs Supporter since 8/9/2016" class="imageGoldStar" src="//d1i1jxrdh2kvwy.cloudfront.net/Images/GoldStar.png" />
        //    <a title="PandaAttack" href="/Player/Profile?PlayerID=4387231">PandaAttack</a>
        // </td>
        td = iterator.next();
        // <td>1890</td>
        td = iterator.next();
        player.setMmr(TagHelper.getIntFromText(td.text()));
        // <td>713</td>
        td = iterator.next();
        player.setNumberOfGames(TagHelper.getIntFromText(td.text()));
        // <td>
        // <a title="View Match History" href="/Player/MatchHistory?PlayerID=4387231">View Match History</a>
        // </td>
        td = iterator.next();
        // </tr>
        // @formatter:on

        return player;
    }

    public static int getIntFromText(String number) {
        try {
            return Integer.parseInt(number.trim());
        } catch (NumberFormatException nfe) {
            return 0;
        }
    }

    public static int getPlayerIdFromLink(String link) {
        int start = link.toString().indexOf("PlayerID=") + 9;
        int end = link.length();
        String id = link.toString().substring(start, end);
        return getIntFromText(id);
    }

    public static int getMmrFromDetailPage(Document doc) {
        /*
         @formatter:off
        <tbody>
        <tr class="rgRow" id="ctl00_MainContent_RadGridGeneralInformation_ctl00__0">
            <td>Hero League</td><td><img id="divLeagueImage" src="//d1i1jxrdh2kvwy.cloudfront.net/Images/Leagues/3.png" style="width: 20px;">&nbsp;<span>Gold 13219 (MMR:&nbsp;1894)</span></td>
        </tr><tr class="rgAltRow" id="ctl00_MainContent_RadGridGeneralInformation_ctl00__1">
            <td>Quick Match</td><td><img id="divLeagueImage" src="//d1i1jxrdh2kvwy.cloudfront.net/Images/Leagues/2.png" style="width: 20px;">&nbsp;<span>Platinum 33752 (MMR:&nbsp;2132)</span></td>
        </tr><tr class="rgRow" id="ctl00_MainContent_RadGridGeneralInformation_ctl00__2">
            <td>Combined Hero Level</td><td>219</td>
        </tr><tr class="rgAltRow" id="ctl00_MainContent_RadGridGeneralInformation_ctl00__3">
            <td>Total Games Played</td><td>713</td>
        </tr><tr class="rgRow" id="ctl00_MainContent_RadGridGeneralInformation_ctl00__4">
            <td>Total Time Played</td><td>10 Days, 15 Hours, 42 Minutes</td>
        </tr>
        </tbody>  
        @formatter:on
        */

        int mmr = 0;
        Element tr = doc.getElementById("ctl00_MainContent_RadGridGeneralInformation_ctl00__0");
        Elements spans = tr.getElementsByTag("span");
        for (Element span : spans) {
            String text = span.text();
            if (text.contains("MMR:")) {
                mmr = getIntFromText(text.substring(text.indexOf("MMR:") + 5, text.indexOf(")")));
            }
        }

        return mmr;
    }

    public static int getNumberOfGamesFromDetailPage(Document doc) {
        /*
        @formatter:off
       <tbody>
       <tr class="rgRow" id="ctl00_MainContent_RadGridGeneralInformation_ctl00__0">
           <td>Hero League</td><td><img id="divLeagueImage" src="//d1i1jxrdh2kvwy.cloudfront.net/Images/Leagues/3.png" style="width: 20px;">&nbsp;<span>Gold 13219 (MMR:&nbsp;1894)</span></td>
       </tr><tr class="rgAltRow" id="ctl00_MainContent_RadGridGeneralInformation_ctl00__1">
           <td>Quick Match</td><td><img id="divLeagueImage" src="//d1i1jxrdh2kvwy.cloudfront.net/Images/Leagues/2.png" style="width: 20px;">&nbsp;<span>Platinum 33752 (MMR:&nbsp;2132)</span></td>
       </tr><tr class="rgRow" id="ctl00_MainContent_RadGridGeneralInformation_ctl00__2">
           <td>Combined Hero Level</td><td>219</td>
       </tr><tr class="rgAltRow" id="ctl00_MainContent_RadGridGeneralInformation_ctl00__3">
           <td>Total Games Played</td><td>713</td>
       </tr><tr class="rgRow" id="ctl00_MainContent_RadGridGeneralInformation_ctl00__4">
           <td>Total Time Played</td><td>10 Days, 15 Hours, 42 Minutes</td>
       </tr>
       </tbody>  
       @formatter:on
       */
        int games = 0;
        Elements es = doc.getElementsContainingOwnText("Total Games Played");
        for (Element e : es) {
            System.out.println(e);
            
            Element nextElementSibling = e.nextElementSibling();
            String text = nextElementSibling.text();
            games = getIntFromText(text);
        }

        return games;
    }

}
