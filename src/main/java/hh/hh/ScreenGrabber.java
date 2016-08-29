package hh.hh;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.github.axet.lookup.Capture;

public class ScreenGrabber {

	public void doIt(String path, Rectangle rect, String name) {

		BufferedImage image = Capture.load(ScreenGrabber.class, path);

		image = cropImage(image, rect);

		Graphics2D g2d = image.createGraphics();

		// The required drawing location
		int drawLocationX = 0;
		int drawLocationY = 0;

		double rotationRequired = Math.toRadians(30);
		double locationX = image.getWidth() / 2;
		double locationY = image.getHeight() / 2;
		AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
		AffineTransformOp rotate = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

		// Drawing the rotated image at the required drawing locations
		g2d.drawImage(rotate.filter(image, null), drawLocationX, drawLocationY, null);

		image = cropImage(image, new Rectangle(6, 60, 165, 37));
		image = monoInvertImage(image);

		try {
			File file = new File("E:/vhp/hh/sandbox/src/main/resources/output", name + ".png");
			if (file.exists()) {
				file.delete();
			}
			ImageIO.write(image, "png", file);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	private BufferedImage cropImage(BufferedImage src, Rectangle rect) {
		BufferedImage dest = src.getSubimage(rect.x, rect.y, rect.width, rect.height);
		return dest;
	}

	private void invertImage(BufferedImage input) {

		for (int x = 0; x < input.getWidth(); x++) {
			for (int y = 0; y < input.getHeight(); y++) {
				int rgba = input.getRGB(x, y);
				Color col = new Color(rgba, true);
				col = new Color(255 - col.getRed(), 255 - col.getGreen(), 255 - col.getBlue());
				input.setRGB(x, y, col.getRGB());
			}
		}

	}

	private BufferedImage monoInvertImage(BufferedImage input) {
		for (int x = 0; x < input.getWidth(); x++) {
			for (int y = 0; y < input.getHeight(); y++) {
				int rgba = input.getRGB(x, y);
				Color col = new Color(rgba, true);
				int MONO_THRESHOLD = 250;
				int DARKER = 110;
				int LIGHTER = 50;
				if (col.getRed() + col.getGreen() + col.getBlue() > MONO_THRESHOLD) {
					int r = 255 - col.getGreen() - DARKER;
					int g = 255 - col.getRed() - DARKER;
					int b = 255 - col.getBlue() - DARKER;

					col = new Color(r < 0 ? 0 : r, g < 0 ? 0 : g, b < 0 ? 0 : b);
					// col = new Color(0, 0, 0);
				} else {
					// col = new Color(255 - col.getRed(), 255 - col.getGreen(),
					// 255 - col.getBlue());

					int r = 255 - col.getGreen() + LIGHTER;
					int g = 255 - col.getGreen() + LIGHTER;
					int b = 255 - col.getGreen() + LIGHTER;
					col = new Color(r > 255 ? 255 : r, g > 255 ? 255 : g, b > 255 ? 255 : b);
				}
				input.setRGB(x, y, col.getRGB());
			}
		}
		
		return input;

	}

	public static void main(String... args) {

		ScreenGrabber s = new ScreenGrabber();

		s.doIt("/draft1.png", new Rectangle(2380, 190, 172, 183), "b_first");
		s.doIt("/draft1.png", new Rectangle(2252, 416, 172, 183), "b_second");
		s.doIt("/draft.png", new Rectangle(2380, 642, 172, 183), "b_third");
		s.doIt("/draft.png", new Rectangle(2252, 868, 172, 183), "b_fourth");
		s.doIt("/draft.png", new Rectangle(2380, 1094, 172, 183), "b_fifth");

	}

}
