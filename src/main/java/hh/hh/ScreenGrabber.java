package hh.hh;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.github.axet.lookup.Capture;

public class ScreenGrabber {

    public void doIt(String path, Rectangle rect, String name) {

        BufferedImage image = Capture.load(ScreenGrabber.class, path);

        BufferedImage cropped = cropImage(image, rect);

        Graphics2D g2d = cropped.createGraphics();

        // The required drawing location
        int drawLocationX = 0;
        int drawLocationY = 0;

        double rotationRequired = Math.toRadians(30);
        double locationX = cropped.getWidth() / 2;
        double locationY = cropped.getHeight() / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        // Drawing the rotated image at the required drawing locations
        g2d.drawImage(op.filter(cropped, null), drawLocationX, drawLocationY, null);

        cropped = cropImage(cropped, new Rectangle(6, 60, 165, 37));

        try {
            File file = new File("E:\\vhp\\workspace_mars\\hh\\src\\main\\java\\hh\\hh\\resources\\output",
 name + ".png");
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

        s.doIt("resources/draft1.png", new Rectangle(2380, 190, 172, 183), "first");
        s.doIt("resources/draft1.png", new Rectangle(2252, 416, 172, 183), "second");
        s.doIt("resources/draft1.png", new Rectangle(2380, 642, 172, 183), "third");
        s.doIt("resources/draft1.png", new Rectangle(2252, 868, 172, 183), "fourth");
        s.doIt("resources/draft1.png", new Rectangle(2380, 1094, 172, 183), "fifth");

    }

}
