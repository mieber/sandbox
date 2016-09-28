package hh.hh;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hh.hh.ocr.J2DImageTool;

public class ScreenShotTest {

	List<Rectangle> rectanglesRight = new ArrayList<>();

	@Before
	public void pre() {
		rectanglesRight.add(new Rectangle(2380, 190, 172, 183));
		rectanglesRight.add(new Rectangle(2252, 416, 172, 183));
		rectanglesRight.add(new Rectangle(2380, 642, 172, 183));
		rectanglesRight.add(new Rectangle(2252, 868, 172, 183));
		rectanglesRight.add(new Rectangle(2380, 1094, 172, 183));
	}

	@Test
	public void test01() throws IOException {
		int good = 0;
		good += compare("Screenshot2016-08-18 21_33_27.jpg", "r01", new String[]{"Cokyno", "Agni", "ZaZa", "Monczek", "VlaVe"});
		good += compare("Screenshot2016-08-19 09_04_51.jpg", "r02", new String[]{"RamboRainer", "Sirkillalot", "Fraiyn", null, "sCarMute"});
		good += compare("Screenshot2016-08-19 22_23_42.jpg", "r03", new String[]{"cavalera", "cawa", "Mosfet", "Kerghan", "Snayper"});
		good += compare("Screenshot2016-08-19 22_55_48.jpg", "r04", new String[]{"Barsig", "Gürkchen", "Murat", "s3phster", "HellBreath"});
		good += compare("Screenshot2016-08-31 21_50_33.jpg", "r05", new String[]{"Glypost", "h0uZeR", "Lilie", "HE3AMETEH", "Kosa"});
		good += compare("Screenshot2016-09-01 22_17_05.jpg", "r06", new String[]{"Darkology", "Vecna", "FLakhur", "TooTsi", "BrakhMaar"});
		good += compare("Screenshot2016-09-04 17_52_25.jpg", "r07", new String[]{"TGLError", "TGLNewGen", "Puffy1337", "DrIrrenHouse", "Everlast"});
		good += compare("Screenshot2016-09-04 20_21_48.jpg", "r09", new String[]{"NOsFEARatu", "Petazeta", "Blaze", "Xanador", "akudgava"});
		good += compare("Screenshot2016-09-05 19_31_06.jpg", "r10", new String[]{"ShitWisard", "ViperRUS", null, "Drapi", "TorkarusCZ"});
		good += compare("Screenshot2016-09-05 20_01_59.jpg", "r11", new String[]{"Akwatik", "Mercury", "sagaff", "Hamabellone", "RatKing"});
		good += compare("Screenshot2016-09-05 20_29_49.jpg", "r12", new String[]{"Roomata", "Majesty", "Smesnoje", "Syndicate", "Copc"});
		good += compare("Screenshot2016-09-05 22_05_21.jpg", "r13", new String[]{"Cwz", "MadBird", "Alvarou71", "sdvd", "Bulbaman"});
		good += compare("Screenshot2016-09-05 22_36_22.jpg", "r14", new String[]{"Krautjuchter", "Botar", "Scar", "Ripcord", "SrbinDoKoske"});
		good += compare("Screenshot2016-09-05 23_07_53.jpg", "r16", new String[]{"Cataru", "Shéogorath", "Nunown", "Scar", "Snowi"});
		good += compare("Screenshot2016-09-05 23_09_53.jpg", "r17", new String[]{"Twyzeas", "Bamzz", "Unknown", "Lambofan", "starfoxik"});
		good += compare("Screenshot2016-09-06 00_12_02.jpg", "r18", new String[]{"Czarny", "Zander", "Ziggy69", "SalazarPT", "Daesu"});
		good += compare("Screenshot2016-09-06 00_44_33.jpg", "r19", new String[]{"Hebun", "Iblis", "BillamGrill", "Hackfleisch", "Ragnors"});
		good += compare("Screenshot2016-09-06 01_23_26.jpg", "r20", new String[]{"marfu", "WyDgeS", "Achille", "fabsen", "Hebun"});
		
		
		System.out.println("OVERALL CORRECT OCR: " + good);
		
		Assert.assertTrue("At least 80% of OCR should be valid.", good > 80);
	}
	

	private int compare(String file, String prefix, String[] expected) throws IOException {
		
		BufferedImage image = ImageIO.read(J2DImageTool.class.getResourceAsStream("/" + file));
		String[] names = J2DImageTool.extractNames(image, SettingsService.HH_HOME, SettingsService.HH_HOME).getEnemies();

		System.out.println("***** " + prefix + " / " + file + ":");
		System.out.println("Marker\tExpected\tActual");
		
		int good = 0;
		
		for (int i = 0; i < names.length; i++) {
			
			if (expected[i] == null || expected[i].equalsIgnoreCase(names[i])) {
				System.out.print("+\t");
				good++;
			} else {
				System.out.print("-\t");
			}
			System.out.println(expected[i] + "\t" + names[i]);
			
		}
		
		return good;

	}


}
