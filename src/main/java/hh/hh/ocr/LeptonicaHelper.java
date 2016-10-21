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
	private static final float usmFract = 0.2f;
	private static final boolean doOtsuBinarize = true;


	public static int doMagic(String path, String sourceFile, String targetFile, int usmHalfWidth) {
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
			
			//@formatter:off
			/*!
			00915  *  pixUnsharpMaskingGray()
			00916  *
			00917  *      Input:  pixs (8 bpp; no colormap)
			00918  *              halfwidth  ("half-width" of smoothing filter)
			00919  *              fract  (fraction of edge added back into image)
			00920  *      Return: pixd, or null on error
			00921  *
			00922  *  Notes:
			00923  *      (1) We use symmetric smoothing filters of odd dimension,
			00924  *          typically use sizes of 3, 5, 7, etc.  The @halfwidth parameter
			00925  *          for these is (size - 1)/2; i.e., 1, 2, 3, etc.
			00926  *      (2) The fract parameter is typically taken in the range:
			00927  *          0.2 < fract < 0.7
			00928  *      (3) Returns a clone if no sharpening is requested.
			00929  */
			//@formatter:on
			
			pixs = lept.pixUnsharpMaskingGray(pixs, usmHalfWidth, usmFract);
		}

		if (doOtsuBinarize) {
			/* binarize */
			//@formatter:off
			
			/* 
			pixOtsuAdaptiveThreshold()

		      Input:  pixs (8 bpp)
		              sx, sy (desired tile dimensions; actual size may vary)
		              smoothx, smoothy (half-width of convolution kernel applied to
		                                threshold array: use 0 for no smoothing)
		              scorefract (fraction of the max Otsu score; typ. 0.1;
		                          use 0.0 for standard Otsu)
		              &pixth (<optional return> array of threshold values
		                      found for each tile)
		              &pixd (<optional return> thresholded input pixs, based on
		                     the threshold array)
		      Return: 0 if OK, 1 on error

		  Notes:
		      (1) The Otsu method finds a single global threshold for an image.
		          This function allows a locally adapted threshold to be
		          found for each tile into which the image is broken up.
		      (2) The array of threshold values, one for each tile, constitutes
		          a highly downscaled image.  This array is optionally
		          smoothed using a convolution.  The full width and height of the
		          convolution kernel are (2 * @smoothx + 1) and (2 * @smoothy + 1).
		      (3) The minimum tile dimension allowed is 16.  If such small
		          tiles are used, it is recommended to use smoothing, because
		          without smoothing, each small tile determines the splitting
		          threshold independently.  A tile that is entirely in the
		          image bg will then hallucinate fg, resulting in a very noisy
		          binarization.  The smoothing should be large enough that no
		          tile is only influenced by one type (fg or bg) of pixels,
		          because it will force a split of its pixels.
		      (4) To get a single global threshold for the entire image, use
		          input values of @sx and @sy that are larger than the image.
		          For this situation, the smoothing parameters are ignored.
		      (5) The threshold values partition the image pixels into two classes:
		          one whose values are less than the threshold and another
		          whose values are greater than or equal to the threshold.
		          This is the same use of 'threshold' as in pixThresholdToBinary().
		      (6) The scorefract is the fraction of the maximum Otsu score, which
		          is used to determine the range over which the histogram minimum
		          is searched.  See numaSplitDistribution() for details on the
		          underlying method of choosing a threshold.
		      (7) This uses enables a modified version of the Otsu criterion for
		          splitting the distribution of pixels in each tile into a
		          fg and bg part.  The modification consists of searching for
		          a minimum in the histogram over a range of pixel values where
		          the Otsu score is within a defined fraction, @scorefract,
		          of the max score.  To get the original Otsu algorithm, set
		          @scorefract == 0. */
			//@formatter:on
			
			
			status = lept.pixOtsuAdaptiveThreshold(pixs, otsuX, otsuY, otsuSmoothX, otsuSmoothY, otsuScoreFrac, null,
					pixs);
		}
		
		status = lept.pixWriteImpliedFormat(path + "/" + targetFile, pixs, 0, 0);
		
		lept.pixDestroy(pixs);

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
