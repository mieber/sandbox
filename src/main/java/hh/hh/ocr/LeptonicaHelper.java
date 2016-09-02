package hh.hh;

import org.bytedeco.javacpp.lept;
import org.bytedeco.javacpp.lept.*;

public class LeptonicaHelper {

    public static final int LEPT_TRUE = 1;
    public static final int LEPT_FALSE = 0;
    public static final int LEPT_OK = 0;
    public static final int LEPT_ERROR = 1;

    public static void doMagic(String path, String sourceFile, String targetFile) {
// @formatter:off
//        int main(int argc, char *argv[])
//        {
//          char source_file[MAX_FILE_LEN] = "in.png";
//          char dest_file[MAX_FILE_LEN] = "out.png";
//
//          Negate_enum perform_negate = NEGATE_AUTO;
//          l_float32 dark_bg_threshold = 0.5f; /* From 0.0 to 1.0, with 0 being all white and 1 being all black */
//
//          int perform_scale = LEPT_TRUE;
//          l_float32 scale_factor = 3.5f;
//
//          int perform_unsharp_mask = LEPT_TRUE;
//          l_int32 usm_halfwidth = 5;
//          l_float32 usm_fract = 2.5f;
//
//          int perform_otsu_binarize = LEPT_TRUE;
//          l_int32 otsu_sx = 2000;
//          l_int32 otsu_sy = 2000;
//          l_int32 otsu_smoothx = 0;
//          l_int32 otsu_smoothy = 0;
//          l_float32 otsu_scorefract = 0.0f;
//
//          Remove_furigana_enum remove_furigana = REMOVE_FURIGANA_NO;
//
          int status = LEPT_ERROR;
          float border_avg = 0.0f;
          PIX pixs = null;
//          char *ext = NULL;
//
//          /* Get args.
//          leptonica_util.exe in.png out.png  2 0.5  1 3.5  1 5 2.5  1 2000 2000 0 0 0.0  1 */
        /*
        leptonica_util.exe 
        in.png  captureFile
        out.png  ocrInputFile
        2 negateArg perform_negate
        0.5  - dark_bg_threshold
        1 performScaleArg perform_scale
        3.5  scaleFactor scale_factor
        1 ocrPreProcessing perform_unsharp_mask
        5 - usm_halfwidth
        2.5  - usm_fract
        1 ocrPreProcessing perform_otsu_binarize
        2000 - otsu_sx
        2000 -otsu_sy
        0 -otsu_smoothx
        0 -otsu_smoothy
        0.0 -  otsu_scorefract
        1 stripFurigana remove_furigana
        */
        //  %captureFile%  %ocrInputFile%  %negateArg% 0.5  %performScaleArg% %scaleFactor%  %ocrPreProcessing% 5 2.5  %ocrPreProcessing% 2000 2000 0 0 0.0  %stripFurigana%, , Hide
//          if (argc >= 17)
//          {
// @formatter:on

        int perform_negate = 2;
        float dark_bg_threshold = 0.5f;
        boolean perform_scale = true;
        float scale_factor = 3.5f;
        boolean perform_unsharp_mask = true;
        int usm_halfwidth = 5;
        float usm_fract = 2.5f;
        boolean perform_otsu_binarize = true;
        int otsu_sx = 2000;
        int otsu_sy = 2000;
        int otsu_smoothx = 0;
        int otsu_smoothy = 0;
        float otsu_scorefract = 0.0f;
        int remove_furigana = 0;

// @formatter:off
//            strcpy_s(source_file, MAX_FILE_LEN, argv[1]);
//            strcpy_s(dest_file, MAX_FILE_LEN, argv[2]);
//
//            perform_negate = atoi(argv[3]);
//            dark_bg_threshold = (float)atof(argv[4]);
//
//            perform_scale = atoi(argv[5]);
//            scale_factor = (float)atof(argv[6]);
//
//            perform_unsharp_mask = atoi(argv[7]);
//            usm_halfwidth = atoi(argv[8]);
//            usm_fract = (float)atof(argv[9]);
//
//            perform_otsu_binarize = atoi(argv[10]);
//            otsu_sx = atoi(argv[11]);
//            otsu_sy = atoi(argv[12]);
//            otsu_smoothx = atoi(argv[13]);
//            otsu_smoothy = atoi(argv[14]);
//            otsu_scorefract = (float)atof(argv[15]);
//
//            remove_furigana = atoi(argv[16]);
//          }
//
//          /* Read in source image */
          pixs = lept.pixRead(path + "/" + sourceFile);
//
//          if (pixs == NULL)
//          {
//            return 1;
//          }
//
//          /* Convert to grayscale */
          pixs = lept.pixConvertRGBToGray(pixs, 0.0f, 0.0f, 0.0f);
//
//          if (pixs == NULL)
//          {
//            return 2;
//          }
//
//          if (perform_negate == NEGATE_YES)
//          {
//            /* Negate image */
//            pixInvert(pixs, pixs);
//
//            if (pixs == NULL)
//            {
//              return 3;
//            }
//          }
//          else if (perform_negate == NEGATE_AUTO)
//          {
            PIX otsu_pixs = new PIX();

            status = lept.pixOtsuAdaptiveThreshold(pixs, otsu_sx, otsu_sy, otsu_smoothx, otsu_smoothy, otsu_scorefract, null, otsu_pixs);
//
//            if (status != LEPT_OK)
//            {
//              return 4;
//            }
//
//            /* Get the average intensity of the border pixels,
//            with average of 0.0 being completely white and 1.0 being completely black. */
            border_avg =  lept.pixAverageOnLine(otsu_pixs, 0, 0, otsu_pixs.w() - 1, 0, 1);                               /* Top */
            border_avg += lept.pixAverageOnLine(otsu_pixs, 0, otsu_pixs.h() - 1, otsu_pixs.w() - 1, otsu_pixs.h() - 1, 1); /* Bottom */
            border_avg += lept.pixAverageOnLine(otsu_pixs, 0, 0, 0, otsu_pixs.h() - 1, 1);                               /* Left */
            border_avg += lept.pixAverageOnLine(otsu_pixs, otsu_pixs.w() - 1, 0, otsu_pixs.w() - 1, otsu_pixs.h() - 1, 1); /* Right */
            border_avg /= 4.0f;
//
            lept.pixDestroy(otsu_pixs);
//
//            /* If background is dark */
            if (border_avg > dark_bg_threshold)
            {
              /* Negate image */
                lept.pixInvert(pixs, pixs);

//              if (pixs == NULL)
//              {
//                return 5;
//              }
            }
//          }
//
//          if (perform_scale)
//          {
//            /* Scale the image (linear interpolation) */
            pixs = lept.pixScaleGrayLI(pixs, scale_factor, scale_factor);
//
//            if (pixs == NULL)
//            {
//              return 6;
//            }
//          }
        
        
//
          if (perform_unsharp_mask)
          {
//            /* Apply unsharp mask */
            pixs = lept.pixUnsharpMaskingGray(pixs, usm_halfwidth, usm_fract);
//
//            if (pixs == NULL)
//            {
//              return 7;
//            }
          }
        
//
          if (perform_otsu_binarize)
          {
            /* Binarize */
            status = lept.pixOtsuAdaptiveThreshold(pixs, otsu_sx, otsu_sy, otsu_smoothx, otsu_smoothy, otsu_scorefract, null, pixs);
//
//            if (status != LEPT_OK)
//            {
//              return 8;
//            }
//
//            /* Remove furigana? */
//            if (remove_furigana == REMOVE_FURIGANA_VERTICAL)
//            {
//              status = erase_furigana_vertical(pixs, scale_factor);
//
//              if (status != LEPT_OK)
//              {
//                return 9;
//              }
//            }
//            else if (remove_furigana == REMOVE_FURIGANA_HORIZONTAL)
//            {
//              status = erase_furigana_horizontal(pixs, scale_factor);
//
//              if (status != LEPT_OK)
//              {
//                return 10;
//              }
//            }
//          }
//
//          /* Get extension of output image */
//          status = splitPathAtExtension(dest_file, NULL, &ext);
//
//          if (status != LEPT_OK)
//          {
//            return 11;
//          }
//
//          /* pixWriteImpliedFormat() doesn't recognize PBM/PGM/PPM extensions */
//          if ((strcmp(ext, ".pbm") == 0) || (strcmp(ext, ".pgm") == 0) || (strcmp(ext, ".ppm") == 0))
//          {
//            /* Write the image to file as a PNM */
//            status = pixWrite(dest_file, pixs, IFF_PNM);
//          }
//          else
//          {
//            /* Write the image to file */
            status = lept.pixWriteImpliedFormat(path + "/" + targetFile, pixs, 0, 0);
//          }
//
//          if (status != LEPT_OK)
//          {
//            return 12;
//          }
//
//          /* Free image data */
          lept.pixDestroy(pixs);
//
//          return 0;
//
        } /* main */
// @formatter:on
    }

}
