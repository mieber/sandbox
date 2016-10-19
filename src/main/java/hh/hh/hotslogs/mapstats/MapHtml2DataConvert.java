package hh.hh.hotslogs.mapstats;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import hh.hh.hotslogs.TagHelper;
import hh.hh.storage.HeroMapStat;

public class MapHtml2DataConvert {

	public static void main(String... strings) throws FileNotFoundException, IOException {
		convert(IOUtils.toString(new FileInputStream(new File("c:/temp/out.html"))), System.currentTimeMillis());
	}

	public static final List<HeroMapStat> convert(String html, long ts) {

		Document document = Jsoup.parse(html);

		List<HeroMapStat> result = new ArrayList<>();
		result = doGenericStatsPart(result, document, ts);
		result = doMapsStatsPart(result, document, ts);
		
		for (HeroMapStat heroMapStat : result) {
			System.out.println(heroMapStat);
		}

		return result;
	}

	private static List<HeroMapStat> doGenericStatsPart(List<HeroMapStat> result, Document document, long ts) {
		Elements select = document.select("table[id^=DataTables_Table_]").select(".rgMasterTable").select(" > tbody > tr");
		for (Element e : select) {
			HeroMapStat stat = new HeroMapStat();
			stat.setTimestamp(ts);
			stat.setMap(MapStatistics.GENERIC_STATS);
			
			Iterator<Element> c = e.children().iterator();
//@formatter:off
//			<tr role="row" class="odd rgRow" id="__0"> 
//			 <td align="center"><img id="imgcolumn" src="//d1i1jxrdh2kvwy.cloudfront.net/Images/Heroes/Portraits/Gazlowe.png" style="height:40px;width:40px;"></td>
			Element td = c.next();
//			 <td><a title="Gazlowe" href="/Sitewide/HeroDetails?Hero=Gazlowe">Gazlowe</a></td>
			td = c.next();
			stat.setHero(TagHelper.getTextFromTdLink(td));
//			 <td>5582</td>
			td = c.next();
			stat.setGamesPlayed(TagHelper.getIntFromText(td.text()));
//			 <td>1104</td>
			td = c.next();
			stat.setGamesBanned(TagHelper.getIntFromText(td.text()));
//			 <td>21.3 %
//			  <div style="height: 4px; width: 21.318193308208475043662101300%; background-color: #60CDCD;"></div></td>
			td = c.next();
			stat.setPopularity(TagHelper.getDoubleFromPercentageText(td.text()));
//			 <td class="sorting_1">55.3 %
//			  <div style="height: 4px; width: 100%; background-color: #3BE33B;"></div></td>
			td = c.next();
			stat.setWinPercentage(TagHelper.getDoubleFromPercentageText(td.text()));
//			 <td style="color:#3BE33B;">0.3 %</td>
			td = c.next();
			stat.setWinChange(TagHelper.getDoubleFromPercentageText(td.text()));
//			 <td style="display:none;">Specialist</td>
			td = c.next();
//			 <td style="display:none;">Siege</td>
			td = c.next();
//			</tr>
//@formatter:on
			result.add(stat);
		}
		return result;
	}

	private static List<HeroMapStat> doMapsStatsPart(List<HeroMapStat> result, Document document, long ts) {

		Elements elements = document.select("tr[id^=ctl00_MainContent_RadGridMapStatistics_ctl00]");

		String map = "unknown";
		

		for (Element e : elements) {
			HeroMapStat stat = new HeroMapStat();
			stat.setTimestamp(ts);

			if (!e.hasAttr("role")) {
				map = e.select(" > td > a").get(0).text();
			} else {

				stat.setMap(map);
				Iterator<Element> c = e.children().iterator();
//@formatter:off
//				<tr role="row" class="even rgAltRow" id="ctl00_MainContent_RadGridMapStatistics_ctl00_ctl30_Detail90__8:0_49"> 
//				 <td><a title="Arthas" href="/Sitewide/HeroDetails?Hero=Arthas">Arthas</a></td>
				Element td = c.next();
				stat.setHero(TagHelper.getTextFromTdLink(td));
//				 <td>21</td>
				td = c.next();
				stat.setGamesPlayed(TagHelper.getIntFromText(td.text()));
//				 <td>1</td>
				td = c.next();
				stat.setGamesBanned(TagHelper.getIntFromText(td.text()));
//				 <td>9.1 %
				td = c.next();
				stat.setPopularity(TagHelper.getDoubleFromPercentageText(td.text()));
//				  <div style="height: 4px; width: 9.060552092609082813891362420%; background-color: #60CDCD;"></div></td>
//				 <td class="sorting_1">33.3 %
				td = c.next();
				stat.setWinPercentage(TagHelper.getDoubleFromPercentageText(td.text()));
//				  <div style="height: 4px; width: 16.666666666666666666666666680%; background-color: #3BE33B;"></div></td> 
//				</tr>
//@formatter:on

			}
			result.add(stat);
		}
		return result;
	}

}
