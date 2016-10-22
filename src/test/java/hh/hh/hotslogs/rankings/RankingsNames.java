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
		loadPage("C:\\Users\\msieber1\\hhelper\\seleniumdriver\\chromedriver.exe", League.Diamond);

	}

	public static void loadPage(String driverPath, League... leagues) throws FileNotFoundException, IOException {

		System.setProperty("webdriver.chrome.driver", driverPath);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--lang=en");

		WebDriver driver = new ChromeDriver(options);
		
		for (League league : leagues) {
			System.out.println("Starting to load league: " + league.name());
			
			driver.get("http://www.hotslogs.com//Rankings?Region=2&GameMode=4&League=" + league.name());
			
			int numberOfPages = getNumberOfPages(driver);
			
			System.out.println("Number of Pages for league: " + numberOfPages);

			try (FileWriter fw = new FileWriter("c:/temp/" + league.name() + ".txt", true);
					BufferedWriter bw = new BufferedWriter(fw);
					PrintWriter out = new PrintWriter(bw)) {

				for (int i = 1; i <= numberOfPages; i++) {
					
					if (i % 25 == 0) {
						System.out.println("Page " + i + " of " + numberOfPages);
					}
					
					List<String> names = extractNames(driver.getPageSource());
					for (String n : names) {
						out.println(n);
					}

					openNextPage(driver);
				}
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

	private static int getNumberOfPages(WebDriver driver) {
		// <div class="rgWrap rgInfoPart">
		//  <strong>16371</strong> items in <strong>164</strong> pages
		// </div>
		List<WebElement> es = driver.findElements(By.className("rgInfoPart"));
		for (WebElement e : es) {
			String text = e.getText();
			// 17610 items in 177 pages
			String search = " items in ";
			int start = text.indexOf(search) + search.length();
			int end = text.indexOf(" pages");
			
			String pages = text.substring(start, end);
			
			System.out.println(text);
			System.out.println("Pages >" + pages + "<");
			
			try {
				return Integer.parseInt(pages);
			} catch (NumberFormatException e1) {
				System.out.println(pages + " is not a valid number.");
			}
		}
		
		throw new RuntimeException("The page structure changed.");
	}

}
