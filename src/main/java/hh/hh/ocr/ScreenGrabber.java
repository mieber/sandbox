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

import com.github.axet.lookup.Capture;

import hh.hh.Conf;
import hh.hh.ocr.TesseractHelper.OcrMode;

public class ScreenGrabber {

	private BufferedImage image;

	private ScreenGrabber(String path) {
		image = Capture.load(ScreenGrabber.class, path);
	}

	public static ScreenGrabber get(String path) {
		return new ScreenGrabber(path);
	}

	public ScreenGrabber crop(Rectangle rect) {
		image = image.getSubimage(rect.x, rect.y, rect.width, rect.height);
		return this;
	}

	public ScreenGrabber rotate(double angle) {
		Graphics2D g2d = image.createGraphics();
		double rotationRequired = Math.toRadians(angle);
		double locationX = image.getWidth() / 2;
		double locationY = image.getHeight() / 2;
		AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
		AffineTransformOp rotate = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		g2d.drawImage(rotate.filter(image, null), 0, 0, null);
		return this;
	}

	public ScreenGrabber monoInvert(int threshold, int darker, int lighter) {
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

	public ScreenGrabber write(boolean overwrite, String path, String name, String extension) {
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

	public ScreenGrabber invertImage() {

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

	public ScreenGrabber addCaseHint(String string, int additionalWidth) {

		BufferedImage t = new BufferedImage(image.getWidth() + additionalWidth, image.getHeight(), image.getType());
		Graphics2D g = t.createGraphics();
		g.drawImage(image, additionalWidth, 0, null);
		g.setFont(new Font("Metronic W01 SemiBold", Font.BOLD, 25));
		g.setColor(Color.WHITE);
		g.drawString(string, 3, image.getHeight() - 5);
		g.dispose();

		image = t;

		return this;
	}

	public static String[] extractNames(String pathToScreenshot) {

		String writePath = Conf.ROOT + "/src/main/resources/output";
		String prefix = "r";

		List<Rectangle> rectanglesRight = new ArrayList<>();
		rectanglesRight.add(new Rectangle(2380, 190, 172, 183));
		rectanglesRight.add(new Rectangle(2252, 416, 172, 183));
		rectanglesRight.add(new Rectangle(2380, 642, 172, 183));
		rectanglesRight.add(new Rectangle(2252, 868, 172, 183));
		rectanglesRight.add(new Rectangle(2380, 1094, 172, 183));

		String caseString = "Pa";

		String[] names = new String[5];

		for (int i = 0; i < 5; i++) {
			Rectangle r = rectanglesRight.get(i);
			//@formatter:off
            ScreenGrabber
                .get(pathToScreenshot)
                .crop(r)
                .rotate(30.5)
                .crop(new Rectangle(6, 62, 165, 32))
                .addCaseHint(caseString, 30)
                 // .monoInvert(threshold, darker, lighter)
                .write(true, writePath, prefix + i, "png");
            //@formatter:on

			String source = prefix + i + ".png";
			String target = prefix + i + ".tif";

			LeptonicaHelper.doMagic(writePath, source, target);

			PIX p = TesseractHelper.getPixFromPath(writePath + "/" + target);
			names[i] = TesseractHelper.getTextFromPicture(p, TesseractHelper.DEFAULT_LANG, OcrMode.ORIGINAL).trim()
					.substring(caseString.length() + 1);
		}

		return names;

	}

	public static void main(String... args) {

		List<Rectangle> rectanglesRight = new ArrayList<>();
		rectanglesRight.add(new Rectangle(2380, 190, 172, 183));
		rectanglesRight.add(new Rectangle(2252, 416, 172, 183));
		rectanglesRight.add(new Rectangle(2380, 642, 172, 183));
		rectanglesRight.add(new Rectangle(2252, 868, 172, 183));
		rectanglesRight.add(new Rectangle(2380, 1094, 172, 183));

		doitfirst(rectanglesRight, "/draft.png", Conf.ROOT + "/src/main/resources/output", "r", "bat",
				OcrMode.ORIGINAL);
		doitfirst(rectanglesRight, "/draft1.png", Conf.ROOT + "/src/main/resources/output", "r2", "bat",
				OcrMode.ORIGINAL);
		doitfirst(rectanglesRight, "/real.jpg", Conf.ROOT + "/src/main/resources/output", "r3", "bat",
				OcrMode.ORIGINAL);
		doitfirst(rectanglesRight, "/real2.jpg", Conf.ROOT + "/src/main/resources/output", "r4", "bat",
				OcrMode.ORIGINAL);

	}

	private static void doitfirst(List<Rectangle> rectanglesRight, String pathToScreenshot, String writePath,
			String namePrefix, String data, OcrMode mode) {

		doit(rectanglesRight, pathToScreenshot, writePath, namePrefix, data, 175, 0, 0, mode);
	}

	private static void doit(List<Rectangle> rs, String pathToScreenshot, String writePath, String namePrefix,
			String data, int threshold, int darker, int lighter, OcrMode mode) {

		String caseString = "Pa";

		String[] names = new String[5];

		for (int i = 0; i < 5; i++) {
			Rectangle r = rs.get(i);
			String filename = namePrefix + "_" + data + "_" + i + "_" + threshold + "_" + darker + "_" + lighter;
			//@formatter:off
            ScreenGrabber
                .get(pathToScreenshot)
                .crop(r)
                .rotate(30.5)
                .crop(new Rectangle(6, 62, 165, 32))
                .addCaseHint(caseString, 30)
                 // .monoInvert(threshold, darker, lighter)
                .write(true, writePath, filename, "png");
            //@formatter:on
			LeptonicaHelper.doMagic(writePath, filename + ".png", filename + ".tif");
			PIX p = TesseractHelper.getPixFromPath(writePath + "/" + filename + ".tif");
			names[i] = TesseractHelper.getTextFromPicture(p, data, mode).trim().substring(caseString.length() + 1);
		}

		System.out.println(threshold + "/" + darker + "/" + lighter + ": \t" + names[0] + "\t" + names[1] + "\t"
				+ names[2] + "\t" + names[3] + "\t" + names[4] + "\t(" + data + "/" + mode.name() + ")");

	}

}
