package hh.hh;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Assert;
import org.junit.Test;

import hh.hh.ocr.J2DImageTool;
import hh.hh.ocr.ScreenGrabResult;

public class PlayerAndMapNameTest {

	@Test
	public void test01() throws IOException {
		int good = 0;
		//@formatter:off
		good += compare("player_001.jpg", "r01", new String[]{"PandaAttack", "GhostKebab", null, "Voxdeus", "KrokMic"}, new String[]{"Cokyno", "Agni", "ZaZa", "Monczek", "VlaVe"}, "INFERNAL SHRINES");
		good += compare("player_002.jpg", "r02", new String[]{"Tuonra", "PandaAttack", "LastUnicorn", "easy", "ARGONEES"}, new String[]{"RamboRainer", "Sirkillalot", "Fraiyn", null, "sCarMute"}, "TOWERS OF DOOM");
		good += compare("player_003.jpg", "r03", new String[]{"MisTikaL", "PandaAttack", "Soriakh", "Langemaudit", "Kyryus"}, new String[]{"cavalera", "cawa", "Mosfet", "Kerghan", "Snayper"}, "SKY TEMPLE");
		good += compare("player_004.jpg", "r04", new String[]{"Westerwave", "Sibu", "bemol", "koalex", "PandaAttack"}, new String[]{"Barsig", "Gürkchen", "Murat", "s3phster", "HellBreath"}, "DRAGON SHIRE");
		good += compare("player_005.jpg", "r05", new String[]{"PandaAttack", "spoff", "MrCandy87", "Apor", "Grav3"}, new String[]{"Glypost", "h0uZeR", "Lilie", "HE3AMETEH", "Kosa"}, "TOMB OF THE SPIDER QUEEN");
		good += compare("player_006.jpg", "r06", new String[]{"PandaAttack", "Roronoa", "FrankDS", "Tigrix", "KTyJLXy"}, new String[]{"Darkology", "Vecna", "FLakhur", "TooTsi", "BrakhMaar"}, "INFERNAL SHRINES");
		good += compare("player_007.jpg", "r07", new String[]{"Empi", "Tarantula", null, "PandaAttack", "Lop"}, new String[]{"TGLError", "TGLNewGen", "Puffy1337", "DrIrrenHouse", "Everlast"}, "GARDEN OF TERROR");
		good += compare("player_008.jpg", "r09", new String[]{"Lixiano", "Efka", "Aurin", "PandaAttack", "Tammo"}, new String[]{"NOsFEARatu", "Petazeta", "Blaze", "Xanador", "akudgava"}, "INFERNAL SHRINES");
		good += compare("player_009.jpg", "r10", new String[]{"alekson", "JohnLennon", "Ksandr163", "KOSM4S", "PandaAttack"}, new String[]{"ShitWisard", "ViperRUS", null, "Drapi", "TorkarusCZ"}, "DRAGON SHIRE");
		good += compare("player_010.jpg", "r11", new String[]{"michelangelo", "Grendi", "TopQualityUA", "KOSM4S", "PandaAttack"}, new String[]{"Akwatik", "Mercury", "sagaff", "Hamabellone", "RatKing"}, "CURSED HOLLOW");
		good += compare("player_011.jpg", "r12", new String[]{"PandaAttack", "Adelnor", null, "Falko", "OberstObst"}, new String[]{"Roomata", "Majesty", "Smesnoje", "Syndicate", "Copc"}, "INFERNAL SHRINES");
		good += compare("player_012.jpg", "r13", new String[]{"Hien", "Firv", "Cernuros", "PandaAttack", "Aelz"}, new String[]{"Cwz", "MadBird", "Alvarou71", "sdvd", "Bulbaman"}, "SKY TEMPLE");
		good += compare("player_013.jpg", "r14", new String[]{"Kanijo", "Sirlx", "PandaAttack", "Luke", "GM309"}, new String[]{"Krautjuchter", "Botar", "Scar", "Ripcord", "SrbinDoKoske"}, "TOWERS OF DOOM");
		good += compare("player_014.jpg", "r16", new String[]{"Isotop", "Poki", "PandaAttack", "Zealth", "Twyzeas"}, new String[]{"Cataru", "Shéogorath", "Nunown", "Scar", "Snowi"}, "GARDEN OF TERROR");
		good += compare("player_015.jpg", "r17", new String[]{"lumos", "PandaAttack", "va1ente", "Imperator", "HornBow"}, new String[]{"Twyzeas", "Bamzz", "Unknown", "Lambofan", "starfoxik"}, "CURSED HOLLOW");
		good += compare("player_016.jpg", "r18", new String[]{"PandaAttack", "Andryushkaus", "huzzler", "szept", "Sh33p"}, new String[]{"Czarny", "Zander", "Ziggy69", "SalazarPT", "Daesu"}, "TOMB OF THE SPIDER QUEEN");
		good += compare("player_017.jpg", "r19", new String[]{"Esartir", "KorzoN", "Jammy6565", "Sons1989", "PandaAttack"}, new String[]{"Hebun", "Iblis", "BillamGrill", "Hackfleisch", "Ragnors"}, "INFERNAL SHRINES");
		good += compare("player_018.jpg", "r20", new String[]{"Xellis", "PandaAttack", "AceSephiro", "Sayr", "DarthFox"}, new String[]{"marfu", "WyDgeS", "Achille", "fabsen", "Hebun"}, "GARDEN OF TERROR");
		//@formatter:on
		System.out.println("OVERALL CORRECT OCR: " + good + " possible: 200, wrong map gives 10 points penalty");
		
		Assert.assertTrue("At least 80% of OCR should be valid.", good > 160);
	}
	

	private int compare(String file, String prefix, String[] expectedAllies, String[] expectedEnemies, String map) throws IOException {
		
		int good = 0;
		
		BufferedImage image = ImageIO.read(J2DImageTool.class.getResourceAsStream("/" + file));
		ScreenGrabResult result = J2DImageTool.extractNames(image, SettingsService.HH_HOME, SettingsService.HH_HOME);
		String[] enemies = result.getEnemies();
		String[] allies = result.getFriends();

		System.out.println("***** " + prefix + " / " + file + ":");
		
		if (map.equals(result.getMap())) {
			System.out.println("MAP: " + map + "/" + result.getMap()) ;
		} else {
			System.out.println("!!! MAP: " + map + "/" + result.getMap()) ;
			good = - 10;
		}
		
		System.out.println("ALLIES");
		System.out.println("Marker\tExpected\tActual");
		
		for (int i = 0; i < allies.length; i++) {
			
			if (expectedAllies[i] == null || expectedAllies[i].equalsIgnoreCase(allies[i])) {
				System.out.print("+\t");
				good++;
			} else {
				System.out.print("-\t");
			}
			System.out.println(expectedAllies[i] + "\t" + allies[i]);
			
		}
		
		System.out.println("ENEMIES");
		System.out.println("Marker\tExpected\tActual");
		for (int i = 0; i < enemies.length; i++) {
			
			if (expectedEnemies[i] == null || expectedEnemies[i].equalsIgnoreCase(enemies[i])) {
				System.out.print("+\t");
				good++;
			} else {
				System.out.print("-\t");
			}
			System.out.println(expectedEnemies[i] + "\t" + enemies[i]);
			
		}
		
		return good;

	}


}
