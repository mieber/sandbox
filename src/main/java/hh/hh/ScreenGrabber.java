package hh.hh;

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
//		image = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_BINARY);

		BufferedImage cropped = cropImage(image, rect);

		Graphics2D g2d = cropped.createGraphics();
		
		// The required drawing location
		int drawLocationX = 0;
		int drawLocationY = 0;

		double rotationRequired = Math.toRadians(30);
		double locationX = cropped.getWidth() / 2;
		double locationY = cropped.getHeight() / 2;
		AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
		AffineTransformOp rotate = new AffineTransformOp(tx, AffineTransformOp.TYPE_BICUBIC);

		// Drawing the rotated image at the required drawing locations
		g2d.drawImage(rotate.filter(cropped, null), drawLocationX, drawLocationY, null);

		cropped = cropImage(cropped, new Rectangle(6, 60, 165, 37));
		
		

		try {
			File file = new File("E:/vhp/hh/sandbox/src/main/resources/output", name + ".png");
			if (file.exists()) {
				file.delete();
			}
			ImageIO.write(cropped, "png", file);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	private BufferedImage cropImage(BufferedImage src, Rectangle rect) {
		BufferedImage dest = src.getSubimage(rect.x, rect.y, rect.width, rect.height);
		return dest;
	}
	

	public static void main(String... args) {

		ScreenGrabber s = new ScreenGrabber();

		s.doIt("/draft.png", new Rectangle(2380, 190, 172, 183), "b_first");
		s.doIt("/draft.png", new Rectangle(2252, 416, 172, 183), "b_second");
		s.doIt("/draft.png", new Rectangle(2380, 642, 172, 183), "b_third");
		s.doIt("/draft.png", new Rectangle(2252, 868, 172, 183), "b_fourth");
		s.doIt("/draft.png", new Rectangle(2380, 1094, 172, 183), "b_fifth");

	}

}
