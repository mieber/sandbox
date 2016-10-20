package hh.hh.hotslogs.rankings;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import hh.hh.hotslogs.mapstats.MapStatistics.League;

public class RankingsNames {

	public static void main(String... strings) throws FileNotFoundException, IOException {

		Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		root.setLevel(Level.INFO);
		loadPage("C:\\Users\\msieber1\\hhelper\\seleniumdriver\\chromedriver.exe", League.Gold);

	}

	public static void loadPage(String driverPath, League... leagues) throws FileNotFoundException, IOException {

		System.setProperty("webdriver.chrome.driver", driverPath);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--lang=en");

		WebDriver driver = new ChromeDriver(options);
		driver.get("http://www.hotslogs.com//Rankings?Region=2&GameMode=4&League=Silver");

		try (FileWriter fw = new FileWriter("c:/temp/silver.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			
			for (int i = 0; i < 165; i++) {
				List<String> names = extractNames(driver.getPageSource());
				
				for (String n : names) {
					out.println(n);
				}
				
				openNextPage(driver);
			}
		} 

		driver.quit();
	}

	private static List<String> extractNames(String pageSource) {
		
		Document d = Jsoup.parse(pageSource);
		Elements rows = d.getElementById("ctl00_MainContent_RadGridRankings_ctl00").select("tbody > tr");
		
		List<String> names = new ArrayList<>();
		
		for (Element tr : rows) {
			handleRow(tr, names);
		}
		
		return names;
	}

	private static void handleRow(Element tr, List<String> names) {
		//@formatter:off
//		<tr class="rgRow" id="ctl00_MainContent_RadGridRankings_ctl00__22">
		Iterator<Element> c = tr.children().iterator();
//		<td style="display:none;">1433284</td>
		Element td = c.next();
//		<td style="display:none;"> </td>
		td = c.next();
//		<td>123</td>
		td = c.next();
//		<td><a title="Ren" href="/Player/Profile?PlayerID=1433284">Ren</a></td>
		td = c.next();
		names.add(td.text());
//		<td>274</td>
		td = c.next();
//		<td>2069</td>
		td = c.next();
//		<td><a title="View Match History" href="/Player/MatchHistory?PlayerID=1433284">View Match History</a></td>
		td = c.next();
//		</tr>
		//@formatter:on
	}

	private static void openNextPage(WebDriver driver) {

		WebElement button = driver.findElement(By.xpath("//button[@title='Next Page']"));
		button.click();

		new WebDriverWait(driver, 15).ignoring(NoSuchElementException.class).until(new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {

				// <div
				// id="MainContent_RadAjaxLoadingPanel1ctl00_MainContent_RadGridRankings"
				// class="RadAjax RadAjax_Black"
				// style="position: absolute; width: 902px; height: 3677px; left: 5px; top: 352px; text-align: center; z-index: 90000;">

				List<WebElement> els = driver.findElements(By.xpath("//div[@class='raDiv']"));
				if (els.size() == 2) {
					return null;
				}

				return Boolean.TRUE;

			}
		});

	}

}
