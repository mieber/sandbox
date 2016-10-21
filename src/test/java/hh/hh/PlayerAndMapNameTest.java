package hh.hh;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hh.hh.ocr.J2DImageTool;
import hh.hh.ocr.ScreenGrabResult;
import hh.hh.ocr.SingleWordResult;

public class PlayerAndMapNameTest {

	private List<String> errors;

	private int possible = 0;

	private int good = 0;

	@Before
	public void setUp() {
		possible = 0;
		good = 0;
		errors = new ArrayList<>();
	}

	@After
	public void log() {

		System.out.println("OVERALL CORRECT OCR: " + good + " possible: " + possible + " -> " + (100 * good / possible)
				+ "%, wrong map gives 10 points penalty");

		if (errors.size() > 0) {
			System.out.println("**** Error Overview");
			System.out.println("Expected\tActual");
			for (String e : errors) {
				System.out.println(e);
			}
		}


	}

	public void assertQuality() {
		Assert.assertTrue("At least 90% of OCR should be valid.", (100 * good / possible) >= 90);
	}

	@Test
	public void test1920() throws IOException {
		//@formatter:off
		compare("player_1920_1080_001.jpg", new String[]{"PandaAttack", "NecROlanD", "WuCash", "Ania", "Serpinga"}, new String[]{"Alexjangdo", "anubshadar", "Finzolak", "Ironice", "Tartanka"}, "CURSED HOLLOW");
		//@formatter:on

		assertQuality();
	}

	@Test
	public void test2560() throws IOException {
		//@formatter:off
		compare("player_001.jpg",new String[]{"PandaAttack", "GhostKebab", null, "Voxdeus", "KrokMic"}, new String[]{"Cokyno", "Agni", "ZaZa", "Monczek", "VlaVe"}, "INFERNAL SHRINES");
		compare("player_002.jpg",new String[]{"Tuonra", "PandaAttack", "LastUnicorn", "easy", "ARGONEES"}, new String[]{"RamboRainer", "Sirkillalot", "Fraiyn", "Антик", "sCarMute"}, "TOWERS OF DOOM");
		compare("player_003.jpg",new String[]{"MisTikaL", "PandaAttack", "Soriakh", "Langemaudit", "Kyryus"}, new String[]{"cavalera", "cawa", "Mosfet", "Kerghan", "Snayper"}, "SKY TEMPLE");
		compare("player_004.jpg",new String[]{"Westerwave", "Sibu", "bemol", "koalex", "PandaAttack"}, new String[]{"Barsig", "Gürkchen", "Murat", "s3phster", "HellBreath"}, "DRAGON SHIRE");
		compare("player_005.jpg",new String[]{"PandaAttack", "spoff", "MrCandy87", "Apor", "Grav3"}, new String[]{"Glypost", "h0uZeR", "Lilie", "HE3AMETEH", "Kosa"}, "TOMB OF THE SPIDER QUEEN");
		compare("player_006.jpg",new String[]{"PandaAttack", "Roronoa", "FrankDS", "Tigrix", "KTyJLXy"}, new String[]{"Darkology", "Vecna", "FLakhur", "TooTsi", "BrakhMaar"}, "INFERNAL SHRINES");
		compare("player_007.jpg",new String[]{"Empi", "Tarantula", null, "PandaAttack", "Lop"}, new String[]{"TGLError", "TGLNewGen", "Puffy1337", "DrIrrenHouse", "Everlast"}, "GARDEN OF TERROR");
		compare("player_008.jpg",new String[]{"Lixiano", "Efka", "Aurin", "PandaAttack", "Tammo"}, new String[]{"NOsFEARatu", "Petazeta", "Blaze", "Xanador", "akudgava"}, "INFERNAL SHRINES");
		compare("player_009.jpg",new String[]{"alekson", "JohnLennon", "Ksandr163", "KOSM4S", "PandaAttack"}, new String[]{"ShitWisard", "ViperRUS", null, "Drapi", "TorkarusCZ"}, "DRAGON SHIRE");
		compare("player_010.jpg",new String[]{"michelangelo", "Grendi", "TopQualityUA", "KOSM4S", "PandaAttack"}, new String[]{"Akwatik", "Mercury", "sagaff", "Hamabellone", "RatKing"}, "CURSED HOLLOW");
		compare("player_011.jpg",new String[]{"PandaAttack", "Adelnor", null, "Falko", "OberstObst"}, new String[]{"Roomata", "Majesty", "Smesnoje", "Syndicate", "Copc"}, "INFERNAL SHRINES");
		compare("player_012.jpg",new String[]{"Hien", "Firv", "Cernuros", "PandaAttack", "Aelz"}, new String[]{"Cwz", "MadBird", "Alvarou71", "sdvd", "Bulbaman"}, "SKY TEMPLE");
		compare("player_013.jpg",new String[]{"Kanijo", "Sirlx", "PandaAttack", "Luke", "GM309"}, new String[]{"Krautjuchter", "Botar", "Scar", "Ripcord", "SrbinDoKoske"}, "TOWERS OF DOOM");
		compare("player_014.jpg",new String[]{"Isotop", "Poki", "PandaAttack", "Zealth", "Twyzeas"}, new String[]{"Cataru", "Shéogorath", "Nunown", "Scar", "Snowi"}, "GARDEN OF TERROR");
		compare("player_015.jpg",new String[]{"lumos", "PandaAttack", "va1ente", "Imperator", "HornBow"}, new String[]{"Twyzeas", "Bamzz", "Unknown", "Lambofan", "starfoxik"}, "CURSED HOLLOW");
		compare("player_016.jpg",new String[]{"PandaAttack", "Andryushkaus", "huzzler", "szept", "Sh33p"}, new String[]{"Czarny", "Zander", "Ziggy69", "SalazarPT", "Daesu"}, "TOMB OF THE SPIDER QUEEN");
		compare("player_017.jpg",new String[]{"Esartir", "KorzoN", "Jammy6565", "Sons1989", "PandaAttack"}, new String[]{"Hebun", "Iblis", "BillamGrill", "Hackfleisch", "Ragnors"}, "INFERNAL SHRINES");
		compare("player_018.jpg",new String[]{"Xellis", "PandaAttack", "AceSephiro", "Sayr", "DarthFox"}, new String[]{"marfu", "WyDgeS", "Achille", "fabsen", "Hebun"}, "GARDEN OF TERROR");
		compare("player_019.jpg",new String[]{"Seele", "Bioshoker", "PandaAttack", "WannaFly", "Geeman"}, new String[]{"Тирион", "Kani", "Liron", "eQx", "wino"}, "SKY TEMPLE");
		compare("player_020.jpg",new String[]{"Teophanus", "SleepyAsh", "PandaAttack", "Guanako", "GeorgeNaruto"}, new String[]{"iShares", "Hoghart", "PrideKinG", "Psychosis", "MitaKei"}, "INFERNAL SHRINES");
		//@formatter:on

		assertQuality();
	}

	private void compare(String file, String[] expectedAllies, String[] expectedEnemies, String map)
			throws IOException {

		BufferedImage image = ImageIO.read(J2DImageTool.class.getResourceAsStream("/" + file));
		ScreenGrabResult result = J2DImageTool.extractNames(image,
				SettingsService.SettingsParam.FILE_OUTPUT.getDefaultValue(), SettingsService.HH_HOME);
		List<SingleWordResult> enemies = result.getEnemies();
		List<SingleWordResult> allies = result.getFriends();

		System.out.println("***** " + file + ":");

		if (map.equals(result.getMap().getText())) {
			System.out.println("MAP: " + map + "/" + result.getMap());
		} else {
			errors.add(map + "/" + result.getMap());
			System.out.println("!!! MAP: " + map + "/" + result.getMap());
			good -= 10;
		}

		System.out.println("ALLIES");
		compareNames(expectedAllies, allies);

		System.out.println("ENEMIES");
		compareNames(expectedEnemies, enemies);

	}

	private void compareNames(String[] expected, List<SingleWordResult> actual) {
		System.out.println("Marker\tExpected\tActual");
		for (int i = 0; i < actual.size(); i++) {

			if (expected[i] == null) {
				System.out.print(".\t");
			} else if (expected[i].equalsIgnoreCase(actual.get(i).getText())) {
				System.out.print("+\t");
				good++;
				possible++;
			} else {
				System.out.print("-\t");
				errors.add(expected[i] + "\t" + actual.get(i));
				possible++;
			}
			System.out.println(expected[i] + "\t" + actual.get(i));
		}
	}

}
