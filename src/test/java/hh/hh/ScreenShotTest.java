package hh.hh;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import org.bytedeco.javacpp.lept.PIX;
import org.junit.Before;
import org.junit.Test;

import hh.hh.ocr.LeptonicaHelper;
import hh.hh.ocr.J2DImageTool;
import hh.hh.ocr.TesseractHelper;
import hh.hh.ocr.TesseractHelper.OcrMode;

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
	public void test01() {
		compare("Screenshot2016-08-18 21_33_27.jpg", "r01", new String[]{"Cokyno", "Agni", "ZaZa", "Monczek", "VlaVe"});
	}

	@Test
	public void test02() {
		compare("Screenshot2016-08-19 09_04_51.jpg", "r02", new String[]{"RamboRainer", "Sirkillalot", "Fraiyn", null, "sCarMute"});
	}

	@Test
	public void test03() {
		compare("Screenshot2016-08-19 22_23_42.jpg", "r03", new String[]{"cavalera", "cawa", "Mosfet", "Kerghan", "Snayper"});
	}

	@Test
	public void test04() {
		compare("Screenshot2016-08-19 22_55_48.jpg", "r04", new String[]{"Barsig", "Gürkchen", "Murat", "s3phster", "HellBreath"});
	}

	@Test
	public void test05() {
		compare("Screenshot2016-08-31 21_50_33.jpg", "r05", new String[]{"Glypost", "h0uZeR", "Lilie", "HE3AMETEH", "Kosa"});
	}

	@Test
	public void test06() {
		compare("Screenshot2016-09-01 22_17_05.jpg", "r06", new String[]{"Darkology", "Vecna", "FLakhur", "TooTsi", "BrakhMaar"});
	}

	@Test
	public void test07() {
		compare("Screenshot2016-09-04 17_52_25.jpg", "r07", new String[]{"TGLError", "TGLNewGen", "Puffy1337", "DrIrrenHouse", "Everlast"});
	}

	@Test
	public void test09() {
		compare("Screenshot2016-09-04 20_21_48.jpg", "r09", new String[]{"NOsFEARatu", "Petazeta", "Blaze", "Xanador", "akudgava"});
	}
	
	@Test
	public void test10() {
		compare("Screenshot2016-09-05 19_31_06.jpg", "r10", new String[]{"ShitWisard", "ViperRUS", null, "Drapi", "TorkarusCZ"});
	}
	
	@Test
	public void test11() {
		compare("Screenshot2016-09-05 20_01_59.jpg", "r11", new String[]{"Akwatik", "Mercury", "sagaff", "Hamabellone", "RatKing"});
	}
	
	@Test
	public void test12() {
		compare("Screenshot2016-09-05 20_29_49.jpg", "r12", new String[]{"Roomata", "Majesty", "Smesnoje", "Syndicate", "Copc"});
	}
	
	@Test
	public void test13() {
		compare("Screenshot2016-09-05 22_05_21.jpg", "r13", new String[]{"Cwz", "MadBird", "Alvarou71", "sdvd", "Bulbaman"});
	}
	
	@Test
	public void test14() {
		compare("Screenshot2016-09-05 22_36_22.jpg", "r14", new String[]{"Krautjuchter", "Botar", "Scar", "Ripcord", "SrbinDoKoske"});
	}
	
	
	@Test
	public void test16() {
		compare("Screenshot2016-09-05 23_07_53.jpg", "r16", new String[]{"Cataru", "Shéogorath", "Nunown", "Scar", "Snowi"});
	}
	
	@Test
	public void test17() {
		compare("Screenshot2016-09-05 23_09_53.jpg", "r17", new String[]{"Twyzeas", "Bamzz", "Unknown", "Lambofan", "starfoxik"});
	}
	
	@Test
	public void test18() {
		compare("Screenshot2016-09-06 00_12_02.jpg", "r18", new String[]{"Czarny", "Zander", "Ziggy69", "SalazarPT", "Daesu"});
	}
	
	@Test
	public void test19() {
		compare("Screenshot2016-09-06 00_44_33.jpg", "r19", new String[]{"Hebun", "Iblis", "BillamGrill", "Hackfleisch", "Ragnors"});
	}
	
	@Test
	public void test20() {
		compare("Screenshot2016-09-06 01_23_26.jpg", "r20", new String[]{"marfu", "WyDgeS", "Achille", "fabsen", "Hebun"});
	}
	

	private void compare(String file, String prefix, String[] expected) {
		String[] names = J2DImageTool.extractNames("/" + file, prefix).getEnemies();

		System.out.println("***** " + prefix + " / " + file + ":");
		System.out.println("Marker\tExpected\tActual");
		
		for (int i = 0; i < names.length; i++) {
			if (expected[i] == null || expected[i].equals(names[i])) {
				System.out.print("+\t");
			} else {
				System.out.print("-\t");
			}
			System.out.println(expected[i] + "\t" + names[i]);
		}

	}


}
