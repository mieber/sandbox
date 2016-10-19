package hh.hh.hotslogs.mapstats;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

public class MapStatistics {
//	private static final Logger logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
	
	public static final String GENERIC_STATS = "Generic";

	public enum League {
		Master(0), Diamond(1), Platinum(2), Gold(3), Silver(4), Bronze(5);
		final public int order;

		private League(int order) {
			this.order = order;
		}
	}

	public static void main(String... strings) throws FileNotFoundException, IOException {
		
		Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		root.setLevel(Level.INFO);
		String page = loadMapStatisticsPage("C:\\Users\\msieber1\\hhelper\\geckodriver\\chromedriver.exe", League.Gold,
				League.Silver);
		IOUtils.write(page, new FileOutputStream(new File("C:/temp/out.html")));
	}

	public static String loadMapStatisticsPage(String driverPath, League... leagues) {
		
		System.setProperty("webdriver.chrome.driver",  driverPath);
		ChromeOptions options = new ChromeOptions();
	    options.addArguments("--lang=en");
		
		WebDriver driver = new ChromeDriver(options);
		driver.get("http://www.hotslogs.com/Sitewide/HeroAndMapStatistics");

		selectLeagues(driver, leagues);
		
		openAllMapRows(driver);

		String pageSource = driver.getPageSource();
		driver.quit();
		return pageSource;
	}
	
	private static void openAllMapRows(WebDriver driver) {
		//@formatter:off
//		<table class="rgMasterTable" id="ctl00_MainContent_RadGridMapStatistics_ctl00" style="width:100%;table-layout:auto;empty-cells:show;">
//			<colgroup>
//				<col style="width:36px">
//				<col>
//				<col>
//				<col>
//				<col>
//			</colgroup>
//			<thead>
//				<tr>
//					<th scope="col" class="rgHeader rgExpandCol">&nbsp;</th><th scope="col" class="rgHeader">&nbsp;</th><th scope="col" class="rgHeader rgSorted"><a onclick="Telerik.Web.UI.Grid.Sort($find('ctl00_MainContent_RadGridMapStatistics_ctl00'), 'MapNameLocalized'); return false;" title="Click here to sort" href="javascript:__doPostBack('ctl00$MainContent$RadGridMapStatistics$ctl00$ctl02$ctl00$ctl00','')">Map Name</a>&nbsp;<button type="button" name="ctl00$MainContent$RadGridMapStatistics$ctl00$ctl02$ctl00$ctl01" value="Sorted asc" onclick="javascript:__doPostBack('ctl00$MainContent$RadGridMapStatistics$ctl00$ctl02$ctl00$ctl01','')" title="Sorted asc" class="t-button rgActionButton rgSortAsc"><span class="t-font-icon rgIcon rgSortAscIcon"></span></button></th><th scope="col" class="rgHeader"><a onclick="Telerik.Web.UI.Grid.Sort($find('ctl00_MainContent_RadGridMapStatistics_ctl00'), 'GamesPlayed'); return false;" title="Click here to sort" href="javascript:__doPostBack('ctl00$MainContent$RadGridMapStatistics$ctl00$ctl02$ctl00$ctl02','')">Games Played</a></th><th scope="col" class="rgHeader"><a onclick="Telerik.Web.UI.Grid.Sort($find('ctl00_MainContent_RadGridMapStatistics_ctl00'), 'AverageLength'); return false;" title="Click here to sort" href="javascript:__doPostBack('ctl00$MainContent$RadGridMapStatistics$ctl00$ctl02$ctl00$ctl03','')">Average Length</a></th>
//				</tr>
//			</thead><tbody>
//			<tr class="rgRow" id="ctl00_MainContent_RadGridMapStatistics_ctl00__0">
//				<td class="rgExpandCol"><button type="button" name="ctl00$MainContent$RadGridMapStatistics$ctl00$ctl04$GECBtnExpandColumn" value="Expand" onclick="javascript:__doPostBack('ctl00$MainContent$RadGridMapStatistics$ctl00$ctl04$GECBtnExpandColumn','')" id="ctl00_MainContent_RadGridMapStatistics_ctl00_ctl04_GECBtnExpandColumn" title="Expand" class="t-button rgActionButton rgExpand"><span class="t-font-icon rgIcon rgExpandIcon"></span></button></td><td style="width:100px;" align="center"><img id="ctl00_MainContent_RadGridMapStatistics_ctl00_ctl04_imgcolumn" src="//d1i1jxrdh2kvwy.cloudfront.net/Images/Maps/BraxisHoldout.png" style="width:75px;"></td><td class="rgSorted"><a title="Braxis Holdout" href="http://hotscounters.com/#/map/BraxisHoldout">Braxis Holdout</a></td><td>16664</td><td>00:19:02</td>
//			</tr><tr class="rgAltRow" id="ctl00_MainContent_RadGridMapStatistics_ctl00__1">
//				<td class="rgExpandCol"><button type="button" name="ctl00$MainContent$RadGridMapStatistics$ctl00$ctl07$GECBtnExpandColumn" value="Expand" onclick="javascript:__doPostBack('ctl00$MainContent$RadGridMapStatistics$ctl00$ctl07$GECBtnExpandColumn','')" id="ctl00_MainContent_RadGridMapStatistics_ctl00_ctl07_GECBtnExpandColumn" title="Expand" class="t-button rgActionButton rgExpand"><span class="t-font-icon rgIcon rgExpandIcon"></span></button></td><td style="width:100px;" align="center"><img id="ctl00_MainContent_RadGridMapStatistics_ctl00_ctl07_imgcolumn" src="//d1i1jxrdh2kvwy.cloudfront.net/Images/Maps/CursedHollow.png" style="width:75px;"></td><td class="rgSorted"><a title="Cursed Hollow" href="http://hotscounters.com/#/map/CursedHollow">Cursed Hollow</a></td><td>16637</td><td>00:20:16</td>
//			</tr><tr class="rgRow" id="ctl00_MainContent_RadGridMapStatistics_ctl00__2">
//				<td class="rgExpandCol"><button type="button" name="ctl00$MainContent$RadGridMapStatistics$ctl00$ctl10$GECBtnExpandColumn" value="Expand" onclick="javascript:__doPostBack('ctl00$MainContent$RadGridMapStatistics$ctl00$ctl10$GECBtnExpandColumn','')" id="ctl00_MainContent_RadGridMapStatistics_ctl00_ctl10_GECBtnExpandColumn" title="Expand" class="t-button rgActionButton rgExpand"><span class="t-font-icon rgIcon rgExpandIcon"></span></button></td><td style="width:100px;" align="center"><img id="ctl00_MainContent_RadGridMapStatistics_ctl00_ctl10_imgcolumn" src="//d1i1jxrdh2kvwy.cloudfront.net/Images/Maps/InfernalShrines.png" style="width:75px;"></td><td class="rgSorted"><a title="Infernal Shrines" href="http://hotscounters.com/#/map/InfernalShrines">Infernal Shrines</a></td><td>16354</td><td>00:20:41</td>
//			</tr><tr class="rgAltRow" id="ctl00_MainContent_RadGridMapStatistics_ctl00__3">
//				<td class="rgExpandCol"><button type="button" name="ctl00$MainContent$RadGridMapStatistics$ctl00$ctl13$GECBtnExpandColumn" value="Expand" onclick="javascript:__doPostBack('ctl00$MainContent$RadGridMapStatistics$ctl00$ctl13$GECBtnExpandColumn','')" id="ctl00_MainContent_RadGridMapStatistics_ctl00_ctl13_GECBtnExpandColumn" title="Expand" class="t-button rgActionButton rgExpand"><span class="t-font-icon rgIcon rgExpandIcon"></span></button></td><td style="width:100px;" align="center"><img id="ctl00_MainContent_RadGridMapStatistics_ctl00_ctl13_imgcolumn" src="//d1i1jxrdh2kvwy.cloudfront.net/Images/Maps/SkyTemple.png" style="width:75px;"></td><td class="rgSorted"><a title="Sky Temple" href="http://hotscounters.com/#/map/SkyTemple">Sky Temple</a></td><td>16282</td><td>00:19:14</td>
//			</tr><tr class="rgRow" id="ctl00_MainContent_RadGridMapStatistics_ctl00__4">
//				<td class="rgExpandCol"><button type="button" name="ctl00$MainContent$RadGridMapStatistics$ctl00$ctl16$GECBtnExpandColumn" value="Expand" onclick="javascript:__doPostBack('ctl00$MainContent$RadGridMapStatistics$ctl00$ctl16$GECBtnExpandColumn','')" id="ctl00_MainContent_RadGridMapStatistics_ctl00_ctl16_GECBtnExpandColumn" title="Expand" class="t-button rgActionButton rgExpand"><span class="t-font-icon rgIcon rgExpandIcon"></span></button></td><td style="width:100px;" align="center"><img id="ctl00_MainContent_RadGridMapStatistics_ctl00_ctl16_imgcolumn" src="//d1i1jxrdh2kvwy.cloudfront.net/Images/Maps/TomboftheSpiderQueen.png" style="width:75px;"></td><td class="rgSorted"><a title="Tomb of the Spider Queen" href="http://hotscounters.com/#/map/TomboftheSpiderQueen">Tomb of the Spider Queen</a></td><td>16660</td><td>00:21:05</td>
//			</tr><tr class="rgAltRow" id="ctl00_MainContent_RadGridMapStatistics_ctl00__5">
//				<td class="rgExpandCol"><button type="button" name="ctl00$MainContent$RadGridMapStatistics$ctl00$ctl19$GECBtnExpandColumn" value="Expand" onclick="javascript:__doPostBack('ctl00$MainContent$RadGridMapStatistics$ctl00$ctl19$GECBtnExpandColumn','')" id="ctl00_MainContent_RadGridMapStatistics_ctl00_ctl19_GECBtnExpandColumn" title="Expand" class="t-button rgActionButton rgExpand"><span class="t-font-icon rgIcon rgExpandIcon"></span></button></td><td style="width:100px;" align="center"><img id="ctl00_MainContent_RadGridMapStatistics_ctl00_ctl19_imgcolumn" src="//d1i1jxrdh2kvwy.cloudfront.net/Images/Maps/TowersofDoom.png" style="width:75px;"></td><td class="rgSorted"><a title="Towers of Doom" href="http://hotscounters.com/#/map/TowersofDoom">Towers of Doom</a></td><td>16391</td><td>00:19:18</td>
//			</tr>
//		</tbody>
//	</table>
//@formatter:on

		List<WebElement> buttons = driver.findElements(
				By.xpath("//table[@id='ctl00_MainContent_RadGridMapStatistics_ctl00']/tbody/tr/td/button"));
		List<String> buttonNames = buttons.stream().map(e -> e.getAttribute("name")).collect(Collectors.toList());
		for (String buttonName : buttonNames) {
			final int sizeBeforeClick = driver
					.findElements(By.xpath("//table[@id='ctl00_MainContent_RadGridMapStatistics_ctl00']/tbody/tr"))
					.size();

			WebElement findElement = driver.findElement(By.name(buttonName));
			findElement.click();

			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(new ExpectedCondition<Integer>() {

				@Override
				public Integer apply(WebDriver driver) {
					int currentSize = driver
							.findElements(
									By.xpath("//table[@id='ctl00_MainContent_RadGridMapStatistics_ctl00']/tbody/tr"))
							.size();

					if (currentSize > sizeBeforeClick) {
						return currentSize;
					}
					return null;
				}
			});

		}
	}
	
	private static void selectLeagues(WebDriver driver, League... leagues) {
		if (leagues == null || leagues.length == 0) {
			return;
		}
		WebElement leagueButton = driver.findElement(By.xpath("//div[@id='ComboBoxLeague']/span/button"));
		leagueButton.click();

		WebElement dropDown = new WebDriverWait(driver, 15).until(new ExpectedCondition<WebElement>() {

			@Override
			public WebElement apply(WebDriver driver) {
				WebElement element = driver.findElement(By.xpath("//div[@id='ComboBoxLeague_DropDown']"));
				String attribute = element.getAttribute("style");
				if (!attribute.contains("transition-duration")) {
					return element;
				}
				return null;
			}
		});

		List<WebElement> findElements = dropDown.findElements(By.xpath(".//*/li"));
		for (League league : leagues) {
			WebElement webElement = findElements.get(league.order);
			webElement.click();
		}

		leagueButton.click();

		new WebDriverWait(driver, 15).until(new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				try {
					driver.findElement(
							By.xpath("//div[@id='MainContent_RadAjaxLoadingPanel1MainContent_divControlContainer']"));
					return null;
				} catch (NoSuchElementException e1) {
					return true;
				}
			}
		});
	}

	
}
