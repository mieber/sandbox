package hh.hh.hotslogs.herostats;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import hh.hh.hotslogs.TagHelper;
import hh.hh.storage.HeroWinStatistics;

public class HeroHtml2DataConvert {

	public static final List<HeroWinStatistics> convert(String hero, String html, long ts) {

		List<HeroWinStatistics> result = new ArrayList<>();
		Document doc = Jsoup.parse(html);

		Elements rows = doc.getElementById("winRateVsOtherHeroes").select("tbody > tr");
		for (Element tr : rows) {
			HeroWinStatistics stat = new HeroWinStatistics();
			stat.setTimestamp(ts);
			stat.setThisHero(hero);
			stat.setWith(false);
			handleRow(tr, stat);
			result.add(stat);
		}

		rows = doc.getElementById("winRateWithOtherHeroes").select("tbody > tr");
		for (Element tr : rows) {
			HeroWinStatistics stat = new HeroWinStatistics();
			stat.setTimestamp(ts);
			stat.setThisHero(hero);
			stat.setWith(true);
			handleRow(tr, stat);
			result.add(stat);
		}

		return result;
	}

	private static void handleRow(Element tr, HeroWinStatistics stat) {
		//@formatter:off
//		<div class="tab-pane" id="winRateVsOtherHeroes"> 
//		<div class="tab-pane" id="winRateWithOtherHeroes">
//      ...
//	          <tbody> 
//	           <tr class="rgRow" id="__0">
			Iterator<Element> c = tr.children().iterator();
//	            <td align="center"><img id="imgcolumn" src="//d1i1jxrdh2kvwy.cloudfront.net/Images/Heroes/Portraits/Murky.png" height="40" width="40"></td>
			Element td = c.next();
//	            <td><a title="Murky" href="/Sitewide/HeroDetails?Hero=Murky">Murky</a></td>
			td = c.next();
			stat.setOtherHero(td.text());
//	            <td>57
//	             <div style="height: 4px; width: 1.6642547033285094066570188100%; background-color: #60CDCD;"></div></td>
			td = c.next();
			stat.setPlayed(TagHelper.getIntFromText(td.text()));
//	            <td>56.1 %
//	             <div style="height: 4px; width: 100%; background-color: #3BE33B;"></div></td>
			td = c.next();
			stat.setWin(TagHelper.getDoubleFromPercentageText(td.text()));
//	            <td style="display:none;">Specialist</td>
			td = c.next();
//	            <td style="display:none;">Utility</td> 
			td = c.next();
//	           </tr>
		//@formatter:on
	}

}
