package hh.hh;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Assert;
import org.junit.Test;

import hh.hh.ocr.J2DImageTool;
import hh.hh.ocr.ScreenGrabResult;

public class HeroNameTest {
	
	@Test
	public void test01() throws IOException {
		int good = 0;
		//@formatter:off
		good += compare("hero_001.jpg", "r01", new String[]{"Sylvanas", "Zagara", "Sonya", "Xul", "Valla"}, new String[]{"Raynor", "Johanna", "E.T.C.", null, "Lt. Morales"});
		good += compare("hero_002.jpg", "r01", new String[]{"Li-Ming", "Jaina", "Artanis", "Rehgar", null}, new String[]{"Kerrigan", "E.T.C.", "Zagara", null, null});
		//@formatter:on
		System.out.println("OVERALL CORRECT OCR: " + good + " possible: 20");
		
		Assert.assertTrue("All hero OCR should be good.", good >= 20);
	}
	

	private int compare(String file, String prefix, String[] expectedAllyHeroes, String[] expectedEnemyHeroes) throws IOException {
		
		int good = 0;
		
		BufferedImage image = ImageIO.read(J2DImageTool.class.getResourceAsStream("/" + file));
		ScreenGrabResult result = J2DImageTool.extractNames(image, SettingsService.HH_HOME, SettingsService.HH_HOME);
		String[] enemies = result.getEnemyHeroes();
		String[] allies = result.getFriendHeroes();

		System.out.println("***** " + prefix + " / " + file + ":");
		
		System.out.println("ALLIES");
		System.out.println("Marker\tExpected\tActual");
		
		for (int i = 0; i < allies.length; i++) {
			
			if (expectedAllyHeroes[i] == null || expectedAllyHeroes[i].equalsIgnoreCase(allies[i])) {
				System.out.print("+\t");
				good++;
			} else {
				System.out.print("-\t");
			}
			System.out.println(expectedAllyHeroes[i] + "\t" + allies[i]);
			
		}
		
		System.out.println("ENEMIES");
		System.out.println("Marker\tExpected\tActual");
		for (int i = 0; i < enemies.length; i++) {
			
			if (expectedEnemyHeroes[i] == null || expectedEnemyHeroes[i].equalsIgnoreCase(enemies[i])) {
				System.out.print("+\t");
				good++;
			} else {
				System.out.print("-\t");
			}
			System.out.println(expectedEnemyHeroes[i] + "\t" + enemies[i]);
			
		}
		
		return good;

	}

}
