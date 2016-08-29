package hh.hh;

import static org.bytedeco.javacpp.lept.pixDestroy;
import static org.bytedeco.javacpp.lept.pixRead;
import static org.junit.Assert.assertTrue;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.lept.PIX;
import org.bytedeco.javacpp.tesseract.TessBaseAPI;
import org.springframework.stereotype.Component;

@Component
public class TesseractComponent {

	public String getTextFromPicture(String path) throws Exception {
		BytePointer outText;

		TessBaseAPI api = new TessBaseAPI();
		try {
			// Initialize tesseract-ocr with English, without specifying
			// tessdata
			// path

			if (api.Init("E:/vhp/hh/sandbox/src/main/resources", "ENG") != 0) {
				System.err.println("Could not initialize tesseract.");
				System.exit(1);
			}

			// Open input image with leptonica library
			PIX image = pixRead(path);
			api.SetImage(image);

			if (image == null) {
				return null;
			}

			// Get OCR result
			outText = api.GetUTF8Text();
			String string = outText.getString();

			// Destroy used object and release memory
			api.End();
			outText.deallocate();
			pixDestroy(image);
			api.close();
			return string;
		} finally {
			try {
				api.close();
			} finally {

			}
		}
	}

	public static void main(String... args) throws Exception {
		TesseractComponent component = new TesseractComponent();
		String p = component.getTextFromPicture("E:/vhp/hh/sandbox/src/main/resources/output/a_first.png");
		System.out.println(p);
		p = component.getTextFromPicture("E:/vhp/hh/sandbox/src/main/resources/output/a_second.png");
		System.out.println(p);
		p = component.getTextFromPicture("E:/vhp/hh/sandbox/src/main/resources/output/a_third.png");
		System.out.println(p);
		p = component.getTextFromPicture("E:/vhp/hh/sandbox/src/main/resources/output/a_fourth.png");
		System.out.println(p);
		p = component.getTextFromPicture("E:/vhp/hh/sandbox/src/main/resources/output/a_fifth.png");
		System.out.println(p);
	}

}
