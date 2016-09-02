package hh.hh;

import static org.bytedeco.javacpp.lept.pixDestroy;
import static org.bytedeco.javacpp.lept.pixRead;

import java.io.UnsupportedEncodingException;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.lept.PIX;
import org.bytedeco.javacpp.tesseract;
import org.bytedeco.javacpp.tesseract.TessBaseAPI;

public class TesseractHelper {
    
    public static final String METRO_BLACK = "MetronicW01-Black";

	public static PIX getPixFromPath(String path) {
		PIX image = pixRead(path);
		return image;
	}
	
	public static String getTextFromPicture(PIX image)  {
	    return getTextFromPicture(image, METRO_BLACK);
	    
	}

	public static String getTextFromPicture(PIX image, String tessData)  {
		BytePointer outText;

		TessBaseAPI api = new TessBaseAPI();
		try {
            /*@formatter:off
                PSM_OSD_ONLY                0   Orientation and script detection only.
                PSM_AUTO_OSD                1   Automatic page segmentation with orientation and script detection. (OSD)
                PSM_AUTO_ONLY               2   Automatic page segmentation, but no OSD, or OCR.
                PSM_AUTO                    3   Fully automatic page segmentation, but no OSD.
                PSM_SINGLE_COLUMN           4   Assume a single column of text of variable sizes.
                PSM_SINGLE_BLOCK_VERT_TEXT  5   Assume a single uniform block of vertically aligned text.
                PSM_SINGLE_BLOCK            6   Assume a single uniform block of text. (Default.)
                PSM_SINGLE_LINE             7   Treat the image as a single text line.
                PSM_SINGLE_WORD             8   Treat the image as a single word.
                PSM_CIRCLE_WORD             9   Treat the image as a single word in a circle.
                PSM_SINGLE_CHAR             10  Treat the image as a single character.
                PSM_SPARSE_TEXT             11  Find as much text as possible in no particular order.
                PSM_SPARSE_TEXT_OSD         12  Sparse text with orientation and script det.
                PSM_RAW_LINE                13  Treat the image as a single text line, bypassing hacks that are Tesseract-specific
                @formatter:on
             */
            api.SetPageSegMode(tesseract.PSM_AUTO);
            
			if (api.Init("E:/vhp/hh/sandbox/src/main/resources", tessData) != 0) {
				System.err.println("Could not initialize tesseract.");
				System.exit(1);
			}

			// Open input image with leptonica library
			api.SetImage(image);

			if (image == null) {
				return null;
			}

			
			outText = api.GetUTF8Text();
//			BytePointer getUNLVText = api.GetUNLVText();
//			System.out.println(getUNLVText.getString());
//			getUNLVText.deallocate();
			String string;
            try {
                string = outText.getString("UTF8");
            } catch (UnsupportedEncodingException e) {
                string = outText.getString();
            }

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
	
	

}
