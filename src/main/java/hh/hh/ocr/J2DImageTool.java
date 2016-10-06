package hh.hh.ocr;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.bytedeco.javacpp.lept.PIX;

import hh.hh.ocr.TesseractHelper.OcrMode;

public class J2DImageTool {

	private BufferedImage image;

	private J2DImageTool(BufferedImage image) {
		this.image = image;
	}

	public static J2DImageTool get(BufferedImage image) {
		return new J2DImageTool(image);
	}

	public static J2DImageTool getFromClasspath(String path) {
		try {
			return new J2DImageTool(ImageIO.read(J2DImageTool.class.getResourceAsStream(path)));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public J2DImageTool crop(Rectangle rect) {
		image = image.getSubimage(rect.x, rect.y, rect.width, rect.height);
		return this;
	}

	public J2DImageTool rotate(double angle) {
		if (angle == 0) {
			return this;
		}
		Graphics2D g2d = image.createGraphics();
		double rotationRequired = Math.toRadians(angle);
		double locationX = image.getWidth() / 2;
		double locationY = image.getHeight() / 2;
		AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
		AffineTransformOp rotate = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		g2d.drawImage(rotate.filter(image, null), 0, 0, null);
		return this;
	}

	public J2DImageTool monoInvert(int threshold, int darker, int lighter) {
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				int rgba = image.getRGB(x, y);
				Color col = new Color(rgba, true);
				if (col.getRed() + col.getGreen() + col.getBlue() > threshold) {
					int r = 255 - col.getGreen() - darker;
					int g = 255 - col.getRed() - darker;
					int b = 255 - col.getBlue() - darker;

					col = new Color(r < 0 ? 0 : r, g < 0 ? 0 : g, b < 0 ? 0 : b);
					// col = new Color(0, 0, 0);
				} else {
					// col = new Color(255 - col.getRed(), 255 - col.getGreen(),
					// 255 - col.getBlue());
					int r = 255 - col.getGreen() + lighter;
					int g = 255 - col.getGreen() + lighter;
					int b = 255 - col.getGreen() + lighter;
					col = new Color(r > 255 ? 255 : r, g > 255 ? 255 : g, b > 255 ? 255 : b);
				}
				image.setRGB(x, y, col.getRGB());
			}
		}
		return this;
	}

	public BufferedImage getImage() {
		return image;
	}

	public J2DImageTool write(boolean overwrite, String path, String name, String extension) {
		try {
			File file = new File(path, name + "." + extension);
			if (overwrite && file.exists()) {
				file.delete();
			}
			ImageIO.write(image, extension, file);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return this;
	}

	public J2DImageTool invertImage() {

		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				int rgba = image.getRGB(x, y);
				Color col = new Color(rgba, true);
				col = new Color(255 - col.getRed(), 255 - col.getGreen(), 255 - col.getBlue());
				image.setRGB(x, y, col.getRGB());
			}
		}
		return this;
	}

	public J2DImageTool addCaseHint(String string, boolean needsInversion, int additionalWidth, int fontSize, int fontOffset) {

		BufferedImage t = new BufferedImage(image.getWidth() + additionalWidth, image.getHeight(), image.getType());
		Graphics2D g = t.createGraphics();
		g.drawImage(image, additionalWidth, 0, null);
		g.setFont(new Font("Segoe UI", Font.BOLD, fontSize));
		Color front;
		Color back;
		if (needsInversion) {
			front = Color.WHITE;
			back = Color.BLACK;
		} else {
			front = Color.BLACK;
			back = Color.WHITE;
		}
		g.setColor(back);
		g.fillRect(0, 0, additionalWidth, image.getHeight());
		g.setColor(front);
		g.drawString(string, 3, image.getHeight() - fontOffset);

		g.dispose();

		image = t;

		return this;
	}

	public static ScreenGrabResult extractNames(BufferedImage bufferedImage, String outputPath, String tessDataPath) {

		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();

		Resolution2Rectangle resInfo = Resolution2Rectangle.get(width, height);

		String[] enemies = extractEnemyNames(bufferedImage, resInfo, outputPath, tessDataPath);
		String[] allies = extractAllyNames(bufferedImage, resInfo, outputPath, tessDataPath);
		String[] enemyHeroes = extractEnemyHeroes(bufferedImage, resInfo, outputPath, tessDataPath);
		String[] allyHeroes = extractAllyHeroes(bufferedImage, resInfo, outputPath, tessDataPath);
		String map = extractMapName(bufferedImage, resInfo, outputPath, tessDataPath);

		J2DImageTool.get(bufferedImage).write(true, outputPath, "test", "png");

		ScreenGrabResult result = new ScreenGrabResult();
		result.setEnemies(enemies);
		result.setFriends(allies);
		result.setEnemyHeroes(validateHeroNames(enemyHeroes));
		result.setFriendHeroes(validateHeroNames(allyHeroes));
		result.setMap(map);

		return result;

	}

	private static String[] validateHeroNames(String[] heroes) {

		for (int i = 0; i < heroes.length; i++) {
			String hero = heroes[i];
			if (hero == null || hero.isEmpty() || hero.length() < 3) {
				heroes[i] = null;
			}
		}

		return heroes;
	}

	private static String extractMapName(BufferedImage bufferedImage, Resolution2Rectangle res, String outputPath,
			String tessDataPath) {

		// public Rectangle(int x, int y, int width, int height)
		Rectangle r = res.getMapRectangle();

		//@formatter:off
		J2DImageTool
			.get(bufferedImage)
			.crop(r)
			.write(true, outputPath, "map", "png");
		//@formatter:on

		//@formatter:off
        try {
			J2DImageTool
				.get(ImageIO.read(new File(outputPath + "/" + "map.png")))
				.write(true, outputPath, "map", "png");
		} catch (IOException e) {
			e.printStackTrace();
		}
        //@formatter:on

		LeptonicaHelper.doMagic(outputPath, "map.png", "map.tif");

		PIX p = TesseractHelper.getPixFromPath(outputPath + "/" + "map.tif");

		String name = TesseractHelper.getTextFromPicture(p, TesseractHelper.MAP_LANG, OcrMode.ORIGINAL, tessDataPath)
				.trim();

		return name;
	}

	private static String[] extractAllyNames(BufferedImage bufferedImage, Resolution2Rectangle res, String outputPath,
			String tessDataPath) {

		String prefix = "l";
		String caseString = "Ly";
		double rotation = res.getAllyRotation();
		Rectangle rectangle = res.getAllySubRectangle();
		List<Rectangle> rs = res.getAllyRectangles();

		return extractNames(bufferedImage, outputPath, tessDataPath, prefix, caseString, rotation, rectangle, rs,
				TesseractHelper.DEFAULT_LANG, res.getFontSize(), res.getFontOffset());
	}

	private static String[] extractEnemyNames(BufferedImage bufferedImage, Resolution2Rectangle res, String outputPath,
			String tessDataPath) {

		String prefix = "r";
		String caseString = "Ly";
		double rotation = res.getEnemyRotation();
		Rectangle rectangle = res.getEnemySubRectangle();
		List<Rectangle> rs = res.getEnemyRectangles();

		return extractNames(bufferedImage, outputPath, tessDataPath, prefix, caseString, rotation, rectangle, rs,
				TesseractHelper.DEFAULT_LANG, res.getFontSize(), res.getFontOffset());
	}

	private static String[] extractAllyHeroes(BufferedImage bufferedImage, Resolution2Rectangle res, String outputPath,
			String tessDataPath) {

		String prefix = "hl";
		Rectangle rectangle = res.getAllyHeroSubRectangle();
		List<Rectangle> rs = res.getAllyRectangles();

		return extractNames(bufferedImage, outputPath, tessDataPath, prefix, null, 0, rectangle, rs,
				TesseractHelper.HERO_LANG, res.getFontSize(), res.getFontOffset());
	}

	private static String[] extractEnemyHeroes(BufferedImage bufferedImage, Resolution2Rectangle res, String outputPath,
			String tessDataPath) {

		String prefix = "hr";
		Rectangle rectangle = res.getEnemyHeroSubRectangle();
		List<Rectangle> rs = res.getEnemyRectangles();

		return extractNames(bufferedImage, outputPath, tessDataPath, prefix, null, 0, rectangle, rs,
				TesseractHelper.HERO_LANG, res.getFontSize(), res.getFontOffset());
	}

	private static String[] extractNames(BufferedImage bufferedImage, String outputPath, String tessDataPath,
			String prefix, String caseString, double rotation, Rectangle rectangle, List<Rectangle> rs, String lang,
			int fontSize, int fontOffset) {

		String[] names = new String[5];

		for (int i = 0; i < 5; i++) {
			Rectangle r = rs.get(i);
			//@formatter:off
            J2DImageTool
                .get(bufferedImage)
                .crop(r)
                .rotate(rotation)
                .crop(rectangle)
                .write(true, outputPath, prefix + i, "png");
            //@formatter:on

			String source = prefix + i + ".png";
			String target = prefix + i + ".tif";

			if (!(caseString == null || caseString.isEmpty())) {

				boolean needsInversion = LeptonicaHelper.needsInversion(outputPath, source);

				//@formatter:off
	            try {
					J2DImageTool
						.get(ImageIO.read(new File(outputPath + "/" + source)))
						.addCaseHint(caseString, needsInversion, 40, fontSize, fontOffset)
						.write(true, outputPath, prefix + i, "png");
				} catch (IOException e) {
					e.printStackTrace();
				}
	            //@formatter:on
			}

			LeptonicaHelper.doMagic(outputPath, source, target);

			PIX p = TesseractHelper.getPixFromPath(outputPath + "/" + target);

			String name = TesseractHelper.getTextFromPicture(p, lang, OcrMode.ORIGINAL, tessDataPath).trim();

			if (name == null) {
				names[i] = null;
			} else if (caseString == null || caseString.isEmpty()) {
				names[i] = name.trim();
			} else if (name.length() > caseString.length() + 1) {
				if (name.substring(0, caseString.length()).contains(" ")) {
					names[i] = name.substring(caseString.length() + 1).trim();
				} else {
					names[i] = name.substring(caseString.length()).trim();
				}
			}
		}
		return names;
	}

}
