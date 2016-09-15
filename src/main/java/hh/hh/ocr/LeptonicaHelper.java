package hh.hh.ocr;

import org.bytedeco.javacpp.lept;
import org.bytedeco.javacpp.lept.*;

public class LeptonicaHelper {

	/**
	 * I go a lot of this from https://github.com/sourcedog/Wurm-Unlimited-Bot/blob/master/lib/Capture2Text/SourceCode/leptonica_util/leptonica_util.c
	 * Which is distributed under GPL. I basically took the calls that I need, adjusted and tweaked a little and removed the rest.
	 */

	public static final int LEPT_TRUE = 1;
	public static final int LEPT_FALSE = 0;
	public static final int LEPT_OK = 0;
	public static final int LEPT_ERROR = 1;

	private static final int otsuX = 2000;
	private static final int otsuY = 2000;
	private static final int otsuSmoothX = 0;
	private static final int otsuSmoothY = 0;
	private static final float otsuScoreFrac = 0.0f;
	private static final float blackBackThreshold = 0.5f;

	private static final float scaling = 3.5f;
	private static final boolean doUnsharp = true;
	private static final int usmHalfWidth = 3;
	private static final float usmFract = 2.5f;
	private static final boolean doOtsuBinarize = true;

	public static int doMagic(String path, String sourceFile, String targetFile) {
		int status = LEPT_ERROR;
		PIX pixs = null;
		// read source img
		pixs = lept.pixRead(path + "/" + sourceFile);
		// to grayscale
		pixs = lept.pixConvertRGBToGray(pixs, 0.0f, 0.0f, 0.0f);
		PIX otsu_pixs = new PIX();

		status = lept.pixOtsuAdaptiveThreshold(pixs, otsuX, otsuY, otsuSmoothX, otsuSmoothY, otsuScoreFrac, null,
				otsu_pixs);
		// average intensity of the border - 0.0 white 1.0 black
		float border_avg = lept.pixAverageOnLine(otsu_pixs, 0, 0, otsu_pixs.w() - 1, 0, 1); /* top */
		border_avg += lept.pixAverageOnLine(otsu_pixs, 0, otsu_pixs.h() - 1, otsu_pixs.w() - 1, otsu_pixs.h() - 1,
				1); /* bottom */
		border_avg += lept.pixAverageOnLine(otsu_pixs, 0, 0, 0, otsu_pixs.h() - 1, 1); /* Left */
		border_avg += lept.pixAverageOnLine(otsu_pixs, otsu_pixs.w() - 1, 0, otsu_pixs.w() - 1, otsu_pixs.h() - 1,
				1); /* right */
		border_avg /= 4.0f;
		lept.pixDestroy(otsu_pixs);
		// backgr is black
		if (border_avg > blackBackThreshold) {
			lept.pixInvert(pixs, pixs);
		}
		// scale the image (linear interp)
		pixs = lept.pixScaleGrayLI(pixs, scaling, scaling);

		if (doUnsharp) {
			pixs = lept.pixUnsharpMaskingGray(pixs, usmHalfWidth, usmFract);
		}

		if (doOtsuBinarize) {
			/* binarize */
			status = lept.pixOtsuAdaptiveThreshold(pixs, otsuX, otsuY, otsuSmoothX, otsuSmoothY, otsuScoreFrac, null,
					pixs);
			status = lept.pixWriteImpliedFormat(path + "/" + targetFile, pixs, 0, 0);
			lept.pixDestroy(pixs);
		}

		return status;
	}

	public static boolean needsInversion(String writePath, String source) {

		PIX pixs = lept.pixRead(writePath + "/" + source);

		pixs = lept.pixConvertRGBToGray(pixs, 0.0f, 0.0f, 0.0f);

		PIX otsu_pixs = new PIX();

		lept.pixOtsuAdaptiveThreshold(pixs, otsuX, otsuY, otsuSmoothX, otsuSmoothY, otsuScoreFrac, null, otsu_pixs);

		float border_avg = lept.pixAverageOnLine(otsu_pixs, 0, 0, otsu_pixs.w() - 1, 0, 1); /* Top */
		border_avg += lept.pixAverageOnLine(otsu_pixs, 0, otsu_pixs.h() - 1, otsu_pixs.w() - 1, otsu_pixs.h() - 1,
				1); /* Bottom */
		border_avg += lept.pixAverageOnLine(otsu_pixs, 0, 0, 0, otsu_pixs.h() - 1, 1); /* Left */
		border_avg += lept.pixAverageOnLine(otsu_pixs, otsu_pixs.w() - 1, 0, otsu_pixs.w() - 1, otsu_pixs.h() - 1,
				1); /* Right */
		border_avg /= 4.0f;
		lept.pixDestroy(otsu_pixs);
		lept.pixDestroy(pixs);

		return border_avg > blackBackThreshold;
	}

}
