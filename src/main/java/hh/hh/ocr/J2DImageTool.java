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

import org.apache.commons.io.IOUtils;
import org.bytedeco.javacpp.lept.PIX;

import com.github.axet.lookup.Capture;

import hh.hh.Conf;
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
		BufferedImage image = Capture.load(J2DImageTool.class, path);
		return new J2DImageTool(image);
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
	
	public static ScreenGrabResult extractNames(String pathToScreenshot) {
		return extractNames(pathToScreenshot, "r");
		
	}

	public static ScreenGrabResult extractNames(String pathToScreenshot, String prefix) {

		String writePath = Conf.ROOT + "/src/main/resources/output";

		List<Rectangle> rectanglesRight = new ArrayList<>();
		rectanglesRight.add(new Rectangle(2380, 190, 172, 183));
		rectanglesRight.add(new Rectangle(2252, 416, 172, 183));
		rectanglesRight.add(new Rectangle(2380, 642, 172, 183));
		rectanglesRight.add(new Rectangle(2252, 868, 172, 183));
		rectanglesRight.add(new Rectangle(2380, 1094, 172, 183));

		String caseString = "Pa";

		String[] names = new String[5];
		
		System.out.println("Going to read: " + pathToScreenshot);
		
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(new File(pathToScreenshot));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

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
                .write(true, writePath, prefix + i, "png");
            //@formatter:on
            
            String source = prefix + i + ".png";
			String target = prefix + i + ".tif";
            
            boolean needsInversion = LeptonicaHelper.needsInversion(writePath, source);

            
            
            //@formatter:off
            try {
				J2DImageTool
					.get(ImageIO.read(new File(writePath + "/" + source)))
					.addCaseHint(caseString, needsInversion, 40)
					.write(true, writePath, prefix + i, "png");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            //@formatter:on

			LeptonicaHelper.doMagic(writePath, source, target);
			
			PIX p = TesseractHelper.getPixFromPath(writePath + "/" + target);
			
			String name = TesseractHelper.getTextFromPicture(p, TesseractHelper.DEFAULT_LANG, OcrMode.ORIGINAL).trim();
			
			if (name != null && name.length() > caseString.length() + 1 ) {
				names[i] = name.substring(caseString.length() + 1);
			} else {
				names[i] = null;
			}
		}
		
		ScreenGrabResult result = new ScreenGrabResult();
		result.setEnemies(names);

		return result;

	}

}
