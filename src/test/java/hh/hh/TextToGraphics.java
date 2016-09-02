package hh.hh;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TextToGraphics {

    public static void write(char c, String fontName) {

        /*
         * Because font metrics is based on a graphics context, we need to create a small, temporary image so we can
         * ascertain the width and height of the final image
         */
        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = img.createGraphics();
        Font font = new Font(fontName, Font.BOLD, 21);
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();
        int width = fm.stringWidth(String.valueOf(c)) * 2;
        int height = fm.getHeight();
        g2d.dispose();

        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        g2d = img.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2d.setFont(font);
        fm = g2d.getFontMetrics();
        g2d.setBackground(new Color(32, 18, 31));
        g2d.clearRect(0, 0, width, height);
        g2d.setColor(new Color(203, 203, 203));
        // g2d.rotate(-0.60, 0, 10);
        g2d.drawString(String.valueOf(c), 0, fm.getAscent());

        g2d.dispose();

        try {

            // File file = new File("E:\\vhp\\workspace_mars\\hh\\src\\main\\java\\hh\\hh\\fonts\\generated",
            // "%" + String.format("%02x", (int) c) + ".png");
            File file = new File(Conf.ROOT + "/src/main/java/hh/hh/fonts/generated",
 c + ".png");
            if (file.exists()) {
                file.delete();
            }
            ImageIO.write(img, "png", file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {

        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Font font = Font.createFont(Font.TRUETYPE_FONT,
                    new File("E:\\vhp\\workspace_mars\\hh\\src\\main\\java\\hh\\hh\\resources", "OpenSans-Bold.ttf"));
            String fontName = font.getFontName();
            ge.registerFont(font);
            write('A', fontName);
            write('l', fontName);
            write('d', fontName);
            write('r', fontName);
            write('e', fontName);
            write('x', fontName);
            write('u', fontName);
            write('s', fontName);
            write('e', fontName);
            write('i', fontName);
            write('t', fontName);
            write('E', fontName);
            write('I', fontName);
            write('.', fontName);
        } catch (IOException | FontFormatException e) {
            // Handle exception
        }

    }

}