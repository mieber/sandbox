package hh.hh.ocr;

import static org.bytedeco.javacpp.lept.pixDestroy;
import static org.bytedeco.javacpp.lept.pixRead;

import java.io.UnsupportedEncodingException;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.lept.PIX;
import org.bytedeco.javacpp.tesseract;
import org.bytedeco.javacpp.tesseract.StringGenericVector;
import org.bytedeco.javacpp.tesseract.TessBaseAPI;

import hh.hh.Conf;

public class TesseractHelper {

	/**
	 * https://code.google.com/archive/p/tesseract-ocr-extradocs/wikis/Cube.wiki
	 */
	public enum OcrMode {

		/*
		 * | tessedit_ocr_engine_mode | Description | |:--------------------------------|:----------------| | 0 | Use original Tesseract recognition
		 * engine | | 1 | Use cube recognition engine | | 2 | Use both engines, automatically choosing whichever appears to give better results |
		 */

		ORIGINAL(tesseract.OEM_TESSERACT_ONLY), CUBE(tesseract.OEM_CUBE_ONLY), AUTO(
				tesseract.OEM_TESSERACT_CUBE_COMBINED);

		private int type;

		OcrMode(int type) {
			this.type = type;
		}

		int type() {
			return type;
		}

	}

	public static final String DEFAULT_LANG = "bat";

	public static PIX getPixFromPath(String path) {
		PIX image = pixRead(path);
		return image;
	}

	public static String getTextFromPicture(PIX image) {
		return getTextFromPicture(image, DEFAULT_LANG, OcrMode.ORIGINAL);

	}

	public static String getTextFromPicture(PIX image, String tessData, OcrMode mode) {
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
            
            @formatter:on*/

			/*@formatter:off
			
			StringGenericVector vars_vec = new StringGenericVector(2);
			vars_vec.addPut(new tesseract.STRING(TesseractOptions.load_system_dawg));
			vars_vec.addPut(new tesseract.STRING(TesseractOptions.load_freq_dawg));
			StringGenericVector vars_values = new StringGenericVector(2);
			vars_values.addPut(new tesseract.STRING("false"));
			vars_values.addPut(new tesseract.STRING("false"));
			
			@formatter:on*/

			if (api.Init(Conf.ROOT + "/src/main/resources", tessData, mode.type()) != 0) { // , new byte[]{}, 2, vars_vec, vars_values, false) != 0) {
				System.err.println("Could not initialize tesseract.");
				System.exit(1);
			}

			api.SetPageSegMode(tesseract.PSM_SINGLE_LINE);
			// api.SetVariable("tessedit_ocr_engine_mode", "" + mode.type());

			// https://github.com/tesseract-ocr/tesseract/wiki/ImproveQuality
			// api.SetVariable(TesseractOptions.load_system_dawg, "F");
			// api.SetVariable(TesseractOptions.load_freq_dawg, "F");
			// api.SetVariable(TesseractOptions.segment_penalty_garbage, "0.0");
			// api.SetVariable(TesseractOptions.language_model_penalty_case, "0.0");
			// api.SetVariable(TesseractOptions.language_model_penalty_non_dict_word, "0.0");
			// api.SetVariable(TesseractOptions.language_model_penalty_non_freq_dict_word, "0.0");
			//
			// api.SetVariable(TesseractOptions.language_model_penalty_non_freq_dict_word, "0.0");
			//
			// api.SetVariable(TesseractOptions.wordrec_debug_level, "100");
			//
			//
			// api.SetVariable(TesseractOptions.user_words_suffix, "userwords");
			// api.SetVariable(TesseractOptions.user_words_file, "e:/hh/git/sandbox/src/main/resources/tessdata/deu.userwords");

			// api.SetVariable("tessedit_char_blacklist", "I");
			// api.SetVariable("tessedit_enable_doc_dict", "false");
			// api.SetVariable("tessedit_rejection_debug", "true");

			// Open input image with leptonica library
			api.SetImage(image);

			if (image == null) {
				return null;

			}

			outText = api.GetUTF8Text();
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
