package hh.hh;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.junit.Assert;
import org.junit.Test;

import hh.hh.ocr.J2DImageTool;
import hh.hh.ocr.ScreenGrabResult;
import hh.hh.ocr.SingleWordResult;

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
		List<SingleWordResult> enemies = result.getEnemyHeroes();
		List<SingleWordResult> allies = result.getFriendHeroes();

		System.out.println("***** " + prefix + " / " + file + ":");
		
		System.out.println("ALLIES");
		System.out.println("Marker\tExpected\tActual");
		
		for (int i = 0; i < allies.size(); i++) {
			
			if (expectedAllyHeroes[i] == null || expectedAllyHeroes[i].equalsIgnoreCase(allies.get(i).getText())) {
				System.out.print("+\t");
				good++;
			} else {
				System.out.print("-\t");
			}
			System.out.println(expectedAllyHeroes[i] + "\t" + allies.get(i).getText());
			
		}
		
		System.out.println("ENEMIES");
		System.out.println("Marker\tExpected\tActual");
		for (int i = 0; i < enemies.size(); i++) {
			
			if (expectedEnemyHeroes[i] == null || expectedEnemyHeroes[i].equalsIgnoreCase(enemies.get(i).getText())) {
				System.out.print("+\t");
				good++;
			} else {
				System.out.print("-\t");
			}
			System.out.println(expectedEnemyHeroes[i] + "\t" + enemies.get(i).getText());
			
		}
		
		return good;

	}

}
