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
import java.util.ArrayList;
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

	public J2DImageTool addCaseHint(String string, boolean needsInversion, int additionalWidth) {

		BufferedImage t = new BufferedImage(image.getWidth() + additionalWidth, image.getHeight(), image.getType());
		Graphics2D g = t.createGraphics();
		g.drawImage(image, additionalWidth, 0, null);
		g.setFont(new Font("Metronic W01 SemiBold", Font.BOLD, 25));
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
		g.drawString(string, 3, image.getHeight() - 5);

		g.dispose();

		image = t;

		return this;
	}

	public static ScreenGrabResult extractNames(String pathToScreenshot, String outputPath, String tessDataPath) {
		System.out.println("Going to read: " + pathToScreenshot);

		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(new File(pathToScreenshot));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return extractNames(bufferedImage, outputPath, tessDataPath);
	}

	public static ScreenGrabResult extractNames(BufferedImage bufferedImage, String outputPath, String tessDataPath) {

		String[] enemies = extractEnemyNames(bufferedImage, outputPath, tessDataPath);
		String[] allies = extractAllyNames(bufferedImage, outputPath, tessDataPath);
		String map = extractMapName(bufferedImage, outputPath, tessDataPath);

		ScreenGrabResult result = new ScreenGrabResult();
		result.setEnemies(enemies);
		result.setFriends(allies);
		result.setMap(map);

		return result;

	}

	private static String extractMapName(BufferedImage bufferedImage, String outputPath, String tessDataPath) {

		// upper left: 746,9
		// size: 1128 x 42

		// public Rectangle(int x, int y, int width, int height)
		Rectangle r = new Rectangle(746, 9, 1128, 42);

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

		String name = TesseractHelper
				.getTextFromPicture(p, TesseractHelper.MAP_LANG, OcrMode.ORIGINAL, tessDataPath).trim();
        
		return name;
	}

	private static String[] extractAllyNames(BufferedImage bufferedImage, String outputPath, String tessDataPath) {
		String prefix = "l";

		List<Rectangle> rs = new ArrayList<>();
		rs.add(new Rectangle(0, 190, 172, 183));
		rs.add(new Rectangle(132, 416, 172, 183));
		rs.add(new Rectangle(0, 642, 172, 183));
		rs.add(new Rectangle(132, 868, 172, 183));
		rs.add(new Rectangle(0, 1094, 172, 183));

		String caseString = "0p";

		String[] names = new String[5];

		for (int i = 0; i < 5; i++) {
			Rectangle r = rs.get(i);
			//@formatter:off
            J2DImageTool
                .get(bufferedImage)
                .crop(r)
                .rotate(-30.5)
                .crop(new Rectangle(6, 54, 158, 32))
                .write(true, outputPath, prefix + i, "png");
            //@formatter:on

			String source = prefix + i + ".png";
			String target = prefix + i + ".tif";

			boolean needsInversion = LeptonicaHelper.needsInversion(outputPath, source);

			//@formatter:off
            try {
				J2DImageTool
					.get(ImageIO.read(new File(outputPath + "/" + source)))
					.addCaseHint(caseString, needsInversion, 40)
					.write(true, outputPath, prefix + i, "png");
			} catch (IOException e) {
				e.printStackTrace();
			}
            //@formatter:on

			LeptonicaHelper.doMagic(outputPath, source, target);

			PIX p = TesseractHelper.getPixFromPath(outputPath + "/" + target);

			String name = TesseractHelper
					.getTextFromPicture(p, TesseractHelper.DEFAULT_LANG, OcrMode.ORIGINAL, tessDataPath).trim();

			if (name != null && name.length() > caseString.length() + 1) {
				names[i] = name.substring(caseString.length() + 1).trim();
			} else {
				names[i] = null;
			}
		}
		return names;
	}

	private static String[] extractEnemyNames(BufferedImage bufferedImage, String outputPath, String tessDataPath) {
		String prefix = "r";

		List<Rectangle> rectanglesRight = new ArrayList<>();
		rectanglesRight.add(new Rectangle(2380, 190, 172, 183));
		rectanglesRight.add(new Rectangle(2252, 416, 172, 183));
		rectanglesRight.add(new Rectangle(2380, 642, 172, 183));
		rectanglesRight.add(new Rectangle(2252, 868, 172, 183));
		rectanglesRight.add(new Rectangle(2380, 1094, 172, 183));

		String caseString = "0p";

		String[] names = new String[5];

		for (int i = 0; i < 5; i++) {
			Rectangle r = rectanglesRight.get(i);
			//@formatter:off
            J2DImageTool
                .get(bufferedImage)
                .crop(r)
                .rotate(30.5)
                .crop(new Rectangle(6, 62, 165, 32))
//                .addCaseHint(caseString, 40)
                 // .monoInvert(threshold, darker, lighter)
                .write(true, outputPath, prefix + i, "png");
            //@formatter:on

			String source = prefix + i + ".png";
			String target = prefix + i + ".tif";

			boolean needsInversion = LeptonicaHelper.needsInversion(outputPath, source);

			//@formatter:off
            try {
				J2DImageTool
					.get(ImageIO.read(new File(outputPath + "/" + source)))
					.addCaseHint(caseString, needsInversion, 40)
					.write(true, outputPath, prefix + i, "png");
			} catch (IOException e) {
				e.printStackTrace();
			}
            //@formatter:on

			LeptonicaHelper.doMagic(outputPath, source, target);

			PIX p = TesseractHelper.getPixFromPath(outputPath + "/" + target);

			String name = TesseractHelper
					.getTextFromPicture(p, TesseractHelper.DEFAULT_LANG, OcrMode.ORIGINAL, tessDataPath).trim();

			if (name != null && name.length() > caseString.length() + 1) {
				names[i] = name.substring(caseString.length() + 1).trim();
			} else {
				names[i] = null;
			}
		}
		return names;
	}

}
