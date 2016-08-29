package hh.hh;

import java.io.File;

import com.github.axet.lookup.Capture;
import com.github.axet.lookup.OCR;

public class OCRTest {

    static public void main(String[] args) {

        OCR l = new OCR(0.50f);

        // will go to com/github/axet/lookup/fonts folder and load all font
        // familys (here is only font_1 family in this library)
        l.loadFontsDirectory(OCRTest.class, new File("fonts"));

        String str = "";

        // recognize using all familys set
        str = l.recognize(Capture.load(OCRTest.class, "resources/output/first.png"), "generated");
        System.out.println("1: " + str);

    }
}