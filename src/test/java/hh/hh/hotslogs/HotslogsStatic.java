package hh.hh.hotslogs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Iterator;

import org.jsoup.nodes.Element;

import feign.Feign;
import feign.okhttp.OkHttpClient;
import hh.hh.hotslogs.HotslogsService.Hotslogs;
import hh.hh.storage.HeroWinStatistics;

public class HotslogsStatic {

	public static void main(String[] args) throws IOException {

		OkHttpClient client = new OkHttpClient(new okhttp3.OkHttpClient.Builder()
				.proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.206.246.20", 8080))).build());

		Hotslogs hl = Feign.builder().client(client).target(Hotslogs.class, "http://www.hotslogs.com/");

		String html = hl.playerRankings(2, "Gold");

		try (PrintWriter out = new PrintWriter("c:/temp/herorankings.html")) {
			out.println(html);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static void handleRow(Element tr, HeroWinStatistics stat) {
		//@formatter:off
//		<div class="tab-pane" id="winRateVsOtherHeroes"> 
		
//		<div class="tab-pane" id="winRateWithOtherHeroes">
		
//	       <div class="RadAjaxPanel" id="ctl00_MainContent_ctl00_MainContent_RadGridSitewideCharacterWinPercentVsOtherCharactersPanel"> 
//	        <div id="RadGridSitewideCharacterWinPercentVsOtherCharacters" class="RadGrid RadGrid_Black" style="width:100%;"> 
//	         <table class="rgMasterTable" id="" width="100%" style="table-layout:auto;empty-cells:show;"> 
//	          <colgroup> 
//	           <col> 
//	           ...
//	          </colgroup> 
//	          <thead> 
//	           <tr> 
//	            <th scope="col" class="rgHeader">&nbsp;</th>
//	            ...
//	           </tr> 
//	          </thead>
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
