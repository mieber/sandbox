package hh.hh.hotslogs;

import java.util.Iterator;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import hh.hh.hotslogs.data.History;
import hh.hh.hotslogs.data.Player;

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
    
    public static History getHistoryFromResultRow(Element tr) {
    	
    	History h = new History();
    	
    	Elements cells = tr.getElementsByTag("td");
        if (cells.size() < 13) {
            return null;
        }

        Iterator<Element> iterator = cells.iterator();
        
    	// @formatter:off
//    	<tr class="rgRow" id="__0" bgcolor="#4F4F4F">
//			<td class="details-control"><font color="#3BE33B">&nbsp;</font></td>
        Element td = iterator.next();
//			<td style="display:none;"><font color="#3BE33B">84551850</font></td>
        td = iterator.next();
        h.setId(td.text());
//			<td><font color="#3BE33B">Garden of Terror</font></td>
        td = iterator.next();
        h.setMap(td.text());
//			<td><font color="#3BE33B">00:21:23</font></td>
        td = iterator.next();
        h.setLength(td.text());
//			<td><font color="#3BE33B"><a title="Jaina" href="/Sitewide/HeroDetails?Hero=Jaina"><font color="#3BE33B">Jaina</font></a></font></td>
        td = iterator.next();
//			<td style="display:none;"><font color="#3BE33B">Jaina</font></td>
        td = iterator.next();
        h.setHero(td.text());
//			<td><font color="#3BE33B">8</font></td>
        td = iterator.next();
        try {
        	h.setLvl(Integer.parseInt(td.text()));
        } catch (NumberFormatException nfe) {
        	 h.setLvl(0);
        }
//			<td style="display:none;"><font color="#3BE33B">1</font></td>
        td = iterator.next();
        h.setWin("1".equals(td.text()));
//			<td><font color="#3BE33B">2014</font></td>
        td = iterator.next();
        h.setMmr(td.text());
//			<td><font color="#3BE33B">16</font></td>
        td = iterator.next();
        h.setMmrChange(td.text());
//			<td><font color="#3BE33B">9/5/2016 11:49:31 PM</font></td>
        td = iterator.next();
        h.setDate(td.text());
//			<td style="display:none;"><font color="#3BE33B">636087161710000000</font></td>
        td = iterator.next();
//			<td style="display:none;"><font color="#3BE33B"><a href="/Replays/ShareReplay?ReplayID="><font color="#3BE33B"></font></a>&nbsp;</font></td>
        td = iterator.next();
//			<td style="display:none;"><font color="#3BE33B"><a title="View Details" href="/Player/MatchDetails?ReplayID=84551850"><font color="#3BE33B">View Details</font></a></font></td>
        td = iterator.next();
//			<td style="display:none;"><font color="#3BE33B">Assassin</font></td>
        td = iterator.next();
//			<td style="display:none;"><font color="#3BE33B">Burst Damage</font></td>
        td = iterator.next();
//		</tr>
		//@formatter:on 
        

        return h;
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
