package hh.hh.ocr;

public class TesseractOptions {

	public static final String edges_max_children_per_outline = "edges_max_children_per_outline"; // 10 Max number of children inside a character
																									// outline
	public static final String edges_max_children_layers = "edges_max_children_layers"; // 5 Max layers of nested children inside a character outline
	public static final String edges_children_per_grandchild = "edges_children_per_grandchild"; // 10 Importance ratio for chucking outlines
	public static final String edges_children_count_limit = "edges_children_count_limit"; // 45 Max holes allowed in blob
	public static final String edges_min_nonhole = "edges_min_nonhole"; // 12 Min pixels for potential char in box
	public static final String edges_patharea_ratio = "edges_patharea_ratio"; // 40 Max lensq/area for acceptable child outline
	public static final String textord_tabfind_show_images = "textord_tabfind_show_images"; // 0 Show image blobs
	public static final String textord_debug_tabfind = "textord_debug_tabfind"; // 0 Debug tab finding
	public static final String textord_debug_bugs = "textord_debug_bugs"; // 0 Turn on output related to bugs in tab finding
	public static final String textord_testregion_left = "textord_testregion_left"; // -1 Left edge of debug reporting rectangle
	public static final String textord_testregion_top = "textord_testregion_top"; // -1 Top edge of debug reporting rectangle
	public static final String textord_testregion_right = "textord_testregion_right"; // 2147483647 Right edge of debug rectangle
	public static final String textord_testregion_bottom = "textord_testregion_bottom"; // 2147483647 Bottom edge of debug rectangle
	public static final String textord_tabfind_show_partitions = "textord_tabfind_show_partitions"; // 0 Show partition bounds, waiting if >1
	public static final String devanagari_split_debuglevel = "devanagari_split_debuglevel"; // 0 Debug level for split shiro-rekha process.
	public static final String classify_num_cp_levels = "classify_num_cp_levels"; // 3 Number of Class Pruner Levels
	public static final String textord_skewsmooth_offset = "textord_skewsmooth_offset"; // 4 For smooth factor
	public static final String textord_skewsmooth_offset2 = "textord_skewsmooth_offset2"; // 1 For smooth factor
	public static final String textord_test_x = "textord_test_x"; // -2147483647 coord of test pt
	public static final String textord_test_y = "textord_test_y"; // -2147483647 coord of test pt
	public static final String textord_min_blobs_in_row = "textord_min_blobs_in_row"; // 4 Min blobs before gradient counted
	public static final String textord_spline_minblobs = "textord_spline_minblobs"; // 8 Min blobs in each spline segment
	public static final String textord_spline_medianwin = "textord_spline_medianwin"; // 6 Size of window for spline segmentation
	public static final String textord_max_blob_overlaps = "textord_max_blob_overlaps"; // 4 Max number of blobs a big blob can overlap
	public static final String textord_min_xheight = "textord_min_xheight"; // 10 Min credible pixel xheight
	public static final String textord_lms_line_trials = "textord_lms_line_trials"; // 12 Number of linew fits to do
	public static final String textord_tabfind_show_strokewidths = "textord_tabfind_show_strokewidths"; // 0 Show stroke widths
	public static final String oldbl_holed_losscount = "oldbl_holed_losscount"; // 10 Max lost before fallback line used
	public static final String textord_dotmatrix_gap = "textord_dotmatrix_gap"; // 3 Max pixel gap for broken pixed pitch
	public static final String textord_debug_block = "textord_debug_block"; // 0 Block to do debug on
	public static final String textord_pitch_range = "textord_pitch_range"; // 2 Max range test on pitch
	public static final String textord_words_veto_power = "textord_words_veto_power"; // 5 Rows required to outvote a veto
	public static final String textord_fp_chop_error = "textord_fp_chop_error"; // 2 Max allowed bending of chop cells
	public static final String pitsync_linear_version = "pitsync_linear_version"; // 6 Use new fast algorithm
	public static final String pitsync_fake_depth = "pitsync_fake_depth"; // 1 Max advance fake generation
	public static final String stream_filelist = "stream_filelist"; // 0 Stream a filelist from stdin
	public static final String edges_use_new_outline_complexity = "edges_use_new_outline_complexity"; // 0 Use the new outline complexity module
	public static final String edges_debug = "edges_debug"; // 0 turn on debugging for this module
	public static final String edges_children_fix = "edges_children_fix"; // 0 Remove boxy parents of char-like children
	public static final String equationdetect_save_bi_image = "equationdetect_save_bi_image"; // 0 Save input bi image
	public static final String equationdetect_save_spt_image = "equationdetect_save_spt_image"; // 0 Save special character image
	public static final String equationdetect_save_seed_image = "equationdetect_save_seed_image"; // 0 Save the seed image
	public static final String equationdetect_save_merged_image = "equationdetect_save_merged_image"; // 0 Save the merged image
	public static final String poly_debug = "poly_debug"; // 0 Debug old poly
	public static final String poly_wide_objects_better = "poly_wide_objects_better"; // 1 More accurate approx on wide things
	public static final String textord_debug_images = "textord_debug_images"; // 0 Use greyed image background for debug
	public static final String textord_debug_printable = "textord_debug_printable"; // 0 Make debug windows printable
	public static final String textord_tabfind_show_initial_partitions = "textord_tabfind_show_initial_partitions"; // 0 Show partition bounds
	public static final String textord_tabfind_show_reject_blobs = "textord_tabfind_show_reject_blobs"; // 0 Show blobs rejected as noise
	public static final String textord_tabfind_show_columns = "textord_tabfind_show_columns"; // 0 Show column bounds
	public static final String textord_tabfind_show_blocks = "textord_tabfind_show_blocks"; // 0 Show final block bounds
	public static final String textord_tabfind_find_tables = "textord_tabfind_find_tables"; // 1 run table detection
	public static final String devanagari_split_debugimage = "devanagari_split_debugimage"; // 0 Whether to create a debug image for split shiro-rekha
																							// process.
	public static final String wordrec_display_splits = "wordrec_display_splits"; // 0 Display splits
	public static final String textord_heavy_nr = "textord_heavy_nr"; // 0 Vigorously remove noise
	public static final String textord_show_initial_rows = "textord_show_initial_rows"; // 0 Display row accumulation
	public static final String textord_show_parallel_rows = "textord_show_parallel_rows"; // 0 Display page correlated rows
	public static final String textord_show_expanded_rows = "textord_show_expanded_rows"; // 0 Display rows after expanding
	public static final String textord_show_final_rows = "textord_show_final_rows"; // 0 Display rows after final fitting
	public static final String textord_show_final_blobs = "textord_show_final_blobs"; // 0 Display blob bounds after pre-ass
	public static final String textord_test_landscape = "textord_test_landscape"; // 0 Tests refer to land/port
	public static final String textord_parallel_baselines = "textord_parallel_baselines"; // 1 Force parallel baselines
	public static final String textord_straight_baselines = "textord_straight_baselines"; // 0 Force straight baselines
	public static final String textord_old_baselines = "textord_old_baselines"; // 1 Use old baseline algorithm
	public static final String textord_old_xheight = "textord_old_xheight"; // 0 Use old xheight algorithm
	public static final String textord_fix_xheight_bug = "textord_fix_xheight_bug"; // 1 Use spline baseline
	public static final String textord_fix_makerow_bug = "textord_fix_makerow_bug"; // 1 Prevent multiple baselines
	public static final String textord_debug_xheights = "textord_debug_xheights"; // 0 Test xheight algorithms
	public static final String textord_biased_skewcalc = "textord_biased_skewcalc"; // 1 Bias skew estimates with line length
	public static final String textord_interpolating_skew = "textord_interpolating_skew"; // 1 Interpolate across gaps
	public static final String textord_new_initial_xheight = "textord_new_initial_xheight"; // 1 Use test xheight mechanism
	public static final String textord_debug_blob = "textord_debug_blob"; // 0 Print test blob information
	public static final String textord_tabfind_show_initialtabs = "textord_tabfind_show_initialtabs"; // 0 Show tab candidates
	public static final String textord_tabfind_show_finaltabs = "textord_tabfind_show_finaltabs"; // 0 Show tab vectors
	public static final String textord_fp_chopping = "textord_fp_chopping"; // 1 Do fixed pitch chopping
	public static final String textord_force_make_prop_words = "textord_force_make_prop_words"; // 0 Force proportional word segmentation on all rows
	public static final String textord_chopper_test = "textord_chopper_test"; // 0 Chopper is being tested.
	public static final String textord_tabfind_only_strokewidths = "textord_tabfind_only_strokewidths"; // 0 Only run stroke widths
	public static final String textord_dump_table_images = "textord_dump_table_images"; // 0 Paint table detection output
	public static final String textord_show_tables = "textord_show_tables"; // 0 Show table regions
	public static final String textord_tablefind_show_mark = "textord_tablefind_show_mark"; // 0 Debug table marking steps in detail
	public static final String textord_tablefind_show_stats = "textord_tablefind_show_stats"; // 0 Show page stats used in table finding
	public static final String textord_tablefind_recognize_tables = "textord_tablefind_recognize_tables"; // 0 Enables the table recognizer for table
																											// layout and filtering.
	public static final String textord_tabfind_show_color_fit = "textord_tabfind_show_color_fit"; // 0 Show stroke widths
	public static final String textord_really_old_xheight = "textord_really_old_xheight"; // 0 Use original wiseowl xheight
	public static final String textord_oldbl_debug = "textord_oldbl_debug"; // 0 Debug old baseline generation
	public static final String textord_debug_baselines = "textord_debug_baselines"; // 0 Debug baseline generation
	public static final String textord_oldbl_paradef = "textord_oldbl_paradef"; // 1 Use para default mechanism
	public static final String textord_oldbl_split_splines = "textord_oldbl_split_splines"; // 1 Split stepped splines
	public static final String textord_oldbl_merge_parts = "textord_oldbl_merge_parts"; // 1 Merge suspect partitions
	public static final String oldbl_corrfix = "oldbl_corrfix"; // 1 Improve correlation of heights
	public static final String oldbl_xhfix = "oldbl_xhfix"; // 0 Fix bug in modes threshold for xheights
	public static final String textord_ocropus_mode = "textord_ocropus_mode"; // 0 Make baselines for ocropus
	public static final String textord_show_initial_words = "textord_show_initial_words"; // 0 Display separate words
	public static final String textord_show_new_words = "textord_show_new_words"; // 0 Display separate words
	public static final String textord_show_fixed_words = "textord_show_fixed_words"; // 0 Display forced fixed pitch words
	public static final String textord_blocksall_fixed = "textord_blocksall_fixed"; // 0 Moan about prop blocks
	public static final String textord_blocksall_prop = "textord_blocksall_prop"; // 0 Moan about fixed pitch blocks
	public static final String textord_blocksall_testing = "textord_blocksall_testing"; // 0 Dump stats when moaning
	public static final String textord_test_mode = "textord_test_mode"; // 0 Do current test
	public static final String textord_pitch_scalebigwords = "textord_pitch_scalebigwords"; // 0 Scale scores on big words
	public static final String textord_space_size_is_variable = "textord_space_size_is_variable"; // 0 If true, word delimiter spaces are assumed to
																									// have variable width, even though characters
																									// have fixed pitch.
	public static final String textord_all_prop = "textord_all_prop"; // 0 All doc is proportial text
	public static final String textord_debug_pitch_test = "textord_debug_pitch_test"; // 0 Debug on fixed pitch test
	public static final String textord_disable_pitch_test = "textord_disable_pitch_test"; // 0 Turn off dp fixed pitch algorithm
	public static final String textord_fast_pitch_test = "textord_fast_pitch_test"; // 0 Do even faster pitch algorithm
	public static final String textord_debug_pitch_metric = "textord_debug_pitch_metric"; // 0 Write full metric stuff
	public static final String textord_show_row_cuts = "textord_show_row_cuts"; // 0 Draw row-level cuts
	public static final String textord_show_page_cuts = "textord_show_page_cuts"; // 0 Draw page-level cuts
	public static final String textord_pitch_cheat = "textord_pitch_cheat"; // 0 Use correct answer for fixed/prop
	public static final String textord_blockndoc_fixed = "textord_blockndoc_fixed"; // 0 Attempt whole doc/block fixed pitch
	public static final String textord_restore_underlines = "textord_restore_underlines"; // 1 Chop underlines & put back
	public static final String gapmap_debug = "gapmap_debug"; // 0 Say which blocks have tables
	public static final String gapmap_use_ends = "gapmap_use_ends"; // 0 Use large space at start and end of rows
	public static final String gapmap_no_isolated_quanta = "gapmap_no_isolated_quanta"; // 0 Ensure gaps not less than 2quanta wide
	public static final String debug_file = "debug_file"; // File to send tprintf output to
	public static final String classify_font_name = "classify_font_name"; // UnknownFont Default font name to be used in training
	public static final String classify_training_file = "classify_training_file"; // MicroFeatures Training file
	public static final String edges_childarea = "edges_childarea"; // 0.5 Min area fraction of child outline
	public static final String edges_boxarea = "edges_boxarea"; // 0.875 Min area fraction of grandchild for box
	public static final String textord_tabvector_vertical_gap_fraction = "textord_tabvector_vertical_gap_fraction"; // 0.5 max fraction of mean blob
																													// width allowed for vertical gaps
																													// in vertical text
	public static final String textord_tabvector_vertical_box_ratio = "textord_tabvector_vertical_box_ratio"; // 0.5 Fraction of box matches required
																												// to declare a line vertical
	public static final String classify_pico_feature_length = "classify_pico_feature_length"; // 0.05 Pico Feature Length
	public static final String classify_cp_angle_pad_loose = "classify_cp_angle_pad_loose"; // 45 Class Pruner Angle Pad Loose
	public static final String classify_cp_angle_pad_medium = "classify_cp_angle_pad_medium"; // 20 Class Pruner Angle Pad Medium
	public static final String classify_cp_angle_pad_tight = "classify_cp_angle_pad_tight"; // 10 CLass Pruner Angle Pad Tight
	public static final String classify_cp_end_pad_loose = "classify_cp_end_pad_loose"; // 0.5 Class Pruner End Pad Loose
	public static final String classify_cp_end_pad_medium = "classify_cp_end_pad_medium"; // 0.5 Class Pruner End Pad Medium
	public static final String classify_cp_end_pad_tight = "classify_cp_end_pad_tight"; // 0.5 Class Pruner End Pad Tight
	public static final String classify_cp_side_pad_loose = "classify_cp_side_pad_loose"; // 2.5 Class Pruner Side Pad Loose
	public static final String classify_cp_side_pad_medium = "classify_cp_side_pad_medium"; // 1.2 Class Pruner Side Pad Medium
	public static final String classify_cp_side_pad_tight = "classify_cp_side_pad_tight"; // 0.6 Class Pruner Side Pad Tight
	public static final String classify_pp_angle_pad = "classify_pp_angle_pad"; // 45 Proto Pruner Angle Pad
	public static final String classify_pp_end_pad = "classify_pp_end_pad"; // 0.5 Proto Prune End Pad
	public static final String classify_pp_side_pad = "classify_pp_side_pad"; // 2.5 Proto Pruner Side Pad
	public static final String classify_norm_adj_midpoint = "classify_norm_adj_midpoint"; // 32 Norm adjust midpoint ...
	public static final String classify_norm_adj_curl = "classify_norm_adj_curl"; // 2 Norm adjust curl ...
	public static final String textord_spline_shift_fraction = "textord_spline_shift_fraction"; // 0.02 Fraction of line spacing for quad
	public static final String textord_spline_outlier_fraction = "textord_spline_outlier_fraction"; // 0.1 Fraction of line spacing for outlier
	public static final String textord_skew_ile = "textord_skew_ile"; // 0.5 Ile of gradients for page skew
	public static final String textord_skew_lag = "textord_skew_lag"; // 0.02 Lag for skew on row accumulation
	public static final String textord_linespace_iqrlimit = "textord_linespace_iqrlimit"; // 0.2 Max iqr/median for linespace
	public static final String textord_width_limit = "textord_width_limit"; // 8 Max width of blobs to make rows
	public static final String textord_chop_width = "textord_chop_width"; // 1.5 Max width before chopping
	public static final String textord_expansion_factor = "textord_expansion_factor"; // 1 Factor to expand rows by in expand_rows
	public static final String textord_overlap_x = "textord_overlap_x"; // 0.375 Fraction of linespace for good overlap
	public static final String textord_minxh = "textord_minxh"; // 0.25 fraction of linesize for min xheight
	public static final String textord_min_linesize = "textord_min_linesize"; // 1.25 * blob height for initial linesize
	public static final String textord_excess_blobsize = "textord_excess_blobsize"; // 1.3 New row made if blob makes row this big
	public static final String textord_occupancy_threshold = "textord_occupancy_threshold"; // 0.4 Fraction of neighbourhood
	public static final String textord_underline_width = "textord_underline_width"; // 2 Multiple of line_size for underline
	public static final String textord_min_blob_height_fraction = "textord_min_blob_height_fraction"; // 0.75 Min blob height/top to include blob top
																										// into xheight stats
	public static final String textord_xheight_mode_fraction = "textord_xheight_mode_fraction"; // 0.4 Min pile height to make xheight
	public static final String textord_ascheight_mode_fraction = "textord_ascheight_mode_fraction"; // 0.08 Min pile height to make ascheight
	public static final String textord_descheight_mode_fraction = "textord_descheight_mode_fraction"; // 0.08 Min pile height to make descheight
	public static final String textord_ascx_ratio_min = "textord_ascx_ratio_min"; // 1.25 Min cap/xheight
	public static final String textord_ascx_ratio_max = "textord_ascx_ratio_max"; // 1.8 Max cap/xheight
	public static final String textord_descx_ratio_min = "textord_descx_ratio_min"; // 0.25 Min desc/xheight
	public static final String textord_descx_ratio_max = "textord_descx_ratio_max"; // 0.6 Max desc/xheight
	public static final String textord_xheight_error_margin = "textord_xheight_error_margin"; // 0.1 Accepted variation
	public static final String classify_min_slope = "classify_min_slope"; // 0.414214 Slope below which lines are called horizontal
	public static final String classify_max_slope = "classify_max_slope"; // 2.41421 Slope above which lines are called vertical
	public static final String oldbl_xhfract = "oldbl_xhfract"; // 0.4 Fraction of est allowed in calc
	public static final String oldbl_dot_error_size = "oldbl_dot_error_size"; // 1.26 Max aspect ratio of a dot
	public static final String textord_oldbl_jumplimit = "textord_oldbl_jumplimit"; // 0.15 X fraction for new partition
	public static final String textord_underline_threshold = "textord_underline_threshold"; // 0.5 Fraction of width occupied
	public static final String textord_wordstats_smooth_factor = "textord_wordstats_smooth_factor"; // 0.05 Smoothing gap stats
	public static final String textord_width_smooth_factor = "textord_width_smooth_factor"; // 0.1 Smoothing width stats
	public static final String textord_words_width_ile = "textord_words_width_ile"; // 0.4 Ile of blob widths for space est
	public static final String textord_words_maxspace = "textord_words_maxspace"; // 4 Multiple of xheight
	public static final String textord_words_default_maxspace = "textord_words_default_maxspace"; // 3.5 Max believable third space
	public static final String textord_words_default_minspace = "textord_words_default_minspace"; // 0.6 Fraction of xheight
	public static final String textord_words_min_minspace = "textord_words_min_minspace"; // 0.3 Fraction of xheight
	public static final String textord_words_default_nonspace = "textord_words_default_nonspace"; // 0.2 Fraction of xheight
	public static final String textord_words_initial_lower = "textord_words_initial_lower"; // 0.25 Max initial cluster size
	public static final String textord_words_initial_upper = "textord_words_initial_upper"; // 0.15 Min initial cluster spacing
	public static final String textord_words_minlarge = "textord_words_minlarge"; // 0.75 Fraction of valid gaps needed
	public static final String textord_words_pitchsd_threshold = "textord_words_pitchsd_threshold"; // 0.04 Pitch sync threshold
	public static final String textord_words_def_fixed = "textord_words_def_fixed"; // 0.016 Threshold for definite fixed
	public static final String textord_words_def_prop = "textord_words_def_prop"; // 0.09 Threshold for definite prop
	public static final String textord_pitch_rowsimilarity = "textord_pitch_rowsimilarity"; // 0.08 Fraction of xheight for sameness
	public static final String words_initial_lower = "words_initial_lower"; // 0.5 Max initial cluster size
	public static final String words_initial_upper = "words_initial_upper"; // 0.15 Min initial cluster spacing
	public static final String words_default_prop_nonspace = "words_default_prop_nonspace"; // 0.25 Fraction of xheight
	public static final String words_default_fixed_space = "words_default_fixed_space"; // 0.75 Fraction of xheight
	public static final String words_default_fixed_limit = "words_default_fixed_limit"; // 0.6 Allowed size variance
	public static final String textord_words_definite_spread = "textord_words_definite_spread"; // 0.3 Non-fuzzy spacing region
	public static final String textord_spacesize_ratiofp = "textord_spacesize_ratiofp"; // 2.8 Min ratio space/nonspace
	public static final String textord_spacesize_ratioprop = "textord_spacesize_ratioprop"; // 2 Min ratio space/nonspace
	public static final String textord_fpiqr_ratio = "textord_fpiqr_ratio"; // 1.5 Pitch IQR/Gap IQR threshold
	public static final String textord_max_pitch_iqr = "textord_max_pitch_iqr"; // 0.2 Xh fraction noise in pitch
	public static final String textord_fp_min_width = "textord_fp_min_width"; // 0.5 Min width of decent blobs
	public static final String textord_fp_chop_snap = "textord_fp_chop_snap"; // 0.5 Max distance of chop pt from vertex
	public static final String textord_projection_scale = "textord_projection_scale"; // 0.2 Ding rate for mid-cuts
	public static final String textord_balance_factor = "textord_balance_factor"; // 1 Ding rate for unbalanced char cells
	public static final String textord_underline_offset = "textord_underline_offset"; // 0.1 Fraction of x to ignore
	public static final String gapmap_big_gaps = "gapmap_big_gaps"; // 1.75 xht multiplier
	public static final String pitsync_joined_edge = "pitsync_joined_edge"; // 0.75 Dist inside big blob for chopping
	public static final String pitsync_offset_freecut_fraction = "pitsync_offset_freecut_fraction"; // 0.25 Fraction of cut for free cuts
	public static final String ambigs_debug_level = "ambigs_debug_level"; // 0 Debug level for unichar ambiguities
	public static final String tessedit_single_match = "tessedit_single_match"; // 0 Top choice only from CP
	public static final String classify_debug_level = "classify_debug_level"; // 0 Classify debug level
	public static final String classify_norm_method = "classify_norm_method"; // 1 Normalization Method ...
	public static final String matcher_debug_level = "matcher_debug_level"; // 0 Matcher Debug Level
	public static final String matcher_debug_flags = "matcher_debug_flags"; // 0 Matcher Debug Flags
	public static final String classify_learning_debug_level = "classify_learning_debug_level"; // 0 Learning Debug Level:
	public static final String matcher_permanent_classes_min = "matcher_permanent_classes_min"; // 1 Min # of permanent classes
	public static final String matcher_min_examples_for_prototyping = "matcher_min_examples_for_prototyping"; // 3 Reliable Config Threshold
	public static final String matcher_sufficient_examples_for_prototyping = "matcher_sufficient_examples_for_prototyping"; // 5 Enable adaption even
																															// if the ambiguities have
																															// not been seen
	public static final String classify_adapt_proto_threshold = "classify_adapt_proto_threshold"; // 230 Threshold for good protos during adaptive
																									// 0-255
	public static final String classify_adapt_feature_threshold = "classify_adapt_feature_threshold"; // 230 Threshold for good features during
																										// adaptive 0-255
	public static final String classify_class_pruner_threshold = "classify_class_pruner_threshold"; // 229 Class Pruner Threshold 0-255
	public static final String classify_class_pruner_multiplier = "classify_class_pruner_multiplier"; // 15 Class Pruner Multiplier 0-255:
	public static final String classify_cp_cutoff_strength = "classify_cp_cutoff_strength"; // 7 Class Pruner CutoffStrength:
	public static final String classify_integer_matcher_multiplier = "classify_integer_matcher_multiplier"; // 10 Integer Matcher Multiplier 0-255:
	public static final String il1_adaption_test = "il1_adaption_test"; // 0 Don't adapt to i/I at beginning of word
	public static final String dawg_debug_level = "dawg_debug_level"; // 0 Set to 1 for general debug info, to 2 for more details, to 3 to see all the
																		// debug messages
	public static final String hyphen_debug_level = "hyphen_debug_level"; // 0 Debug level for hyphenated words.
	public static final String max_viterbi_list_size = "max_viterbi_list_size"; // 10 Maximum size of viterbi list.
	public static final String stopper_smallword_size = "stopper_smallword_size"; // 2 Size of dict word to be treated as non-dict word
	public static final String stopper_debug_level = "stopper_debug_level"; // 0 Stopper debug level
	public static final String tessedit_truncate_wordchoice_log = "tessedit_truncate_wordchoice_log"; // 10 Max words to keep in list
	public static final String fragments_debug = "fragments_debug"; // 0 Debug character fragments
	public static final String max_permuter_attempts = "max_permuter_attempts"; // 10000 Maximum number of different character choices to consider
																				// during permutation. This limit is especially useful when user
																				// patterns are specified, since overly generic patterns can result in
																				// dawg search exploring an overly large number of options.
	public static final String repair_unchopped_blobs = "repair_unchopped_blobs"; // 1 Fix blobs that aren't chopped
	public static final String chop_debug = "chop_debug"; // 0 Chop debug
	public static final String chop_split_length = "chop_split_length"; // 10000 Split Length
	public static final String chop_same_distance = "chop_same_distance"; // 2 Same distance
	public static final String chop_min_outline_points = "chop_min_outline_points"; // 6 Min Number of Points on Outline
	public static final String chop_seam_pile_size = "chop_seam_pile_size"; // 150 Max number of seams in seam_pile
	public static final String chop_inside_angle = "chop_inside_angle"; // -50 Min Inside Angle Bend
	public static final String chop_min_outline_area = "chop_min_outline_area"; // 2000 Min Outline Area
	public static final String chop_centered_maxwidth = "chop_centered_maxwidth"; // 90 Width of (smaller) chopped blobs above which we don't care
																					// that a chop is not near the center.
	public static final String chop_x_y_weight = "chop_x_y_weight"; // 3 X / Y length weight
	public static final String segment_adjust_debug = "segment_adjust_debug"; // 0 Segmentation adjustment debug
	public static final String wordrec_debug_level = "wordrec_debug_level"; // 0 Debug level for wordrec
	public static final String wordrec_max_join_chunks = "wordrec_max_join_chunks"; // 4 Max number of broken pieces to associate
	public static final String segsearch_debug_level = "segsearch_debug_level"; // 0 SegSearch debug level
	public static final String segsearch_max_pain_points = "segsearch_max_pain_points"; // 2000 Maximum number of pain points stored in the queue
	public static final String segsearch_max_futile_classifications = "segsearch_max_futile_classifications"; // 20 Maximum number of pain point
																												// classifications per chunk thatdid
																												// not result in finding a better word
																												// choice.
	public static final String language_model_debug_level = "language_model_debug_level"; // 0 Language model debug level
	public static final String language_model_ngram_order = "language_model_ngram_order"; // 8 Maximum order of the character ngram model
	public static final String language_model_viterbi_list_max_num_prunable = "language_model_viterbi_list_max_num_prunable"; // 10 Maximum number of
																																// prunable (those for
																																// which
																																// PrunablePath() is
																																// true) entries in
																																// each viterbi list
																																// recorded in
																																// BLOB_CHOICEs
	public static final String language_model_viterbi_list_max_size = "language_model_viterbi_list_max_size"; // 500 Maximum size of viterbi lists
																												// recorded in BLOB_CHOICEs
	public static final String language_model_min_compound_length = "language_model_min_compound_length"; // 3 Minimum length of compound words
	public static final String wordrec_display_segmentations = "wordrec_display_segmentations"; // 0 Display Segmentations
	public static final String tessedit_pageseg_mode = "tessedit_pageseg_mode"; // 6 Page seg mode: 0=osd only, 1=auto+osd, 2=auto, 3=col, 4=block,
																				// 5=line, 6=word, 7=char (Values from PageSegMode enum in
																				// publictypes.h)
	public static final String tessedit_ocr_engine_mode = "tessedit_ocr_engine_mode"; // 0 Which OCR engine(s) to run (Tesseract, Cube, both).
																						// Defaults to loading and running only Tesseract (no Cube,no
																						// combiner). Values from OcrEngineMode enum in
																						// tesseractclass.h)
	public static final String pageseg_devanagari_split_strategy = "pageseg_devanagari_split_strategy"; // 0 Whether to use the top-line splitting
																										// process for Devanagari documents while
																										// performing page-segmentation.
	public static final String ocr_devanagari_split_strategy = "ocr_devanagari_split_strategy"; // 0 Whether to use the top-line splitting process for
																								// Devanagari documents while performing ocr.
	public static final String bidi_debug = "bidi_debug"; // 0 Debug level for BiDi
	public static final String applybox_debug = "applybox_debug"; // 1 Debug level
	public static final String applybox_page = "applybox_page"; // 0 Page number to apply boxes from
	public static final String tessedit_bigram_debug = "tessedit_bigram_debug"; // 0 Amount of debug output for bigram correction.
	public static final String debug_noise_removal = "debug_noise_removal"; // 0 Debug reassignment of small outlines
	public static final String noise_maxperblob = "noise_maxperblob"; // 8 Max diacritics to apply to a blob
	public static final String noise_maxperword = "noise_maxperword"; // 16 Max diacritics to apply to a word
	public static final String debug_x_ht_level = "debug_x_ht_level"; // 0 Reestimate debug
	public static final String quality_min_initial_alphas_reqd = "quality_min_initial_alphas_reqd"; // 2 alphas in a good word
	public static final String tessedit_tess_adaption_mode = "tessedit_tess_adaption_mode"; // 39 Adaptation decision algorithm for tess
	public static final String tessedit_test_adaption_mode = "tessedit_test_adaption_mode"; // 3 Adaptation decision algorithm for tess
	public static final String paragraph_debug_level = "paragraph_debug_level"; // 0 Print paragraph debug info.
	public static final String cube_debug_level = "cube_debug_level"; // 0 Print cube debug info.
	public static final String tessedit_preserve_min_wd_len = "tessedit_preserve_min_wd_len"; // 2 Only preserve wds longer than this
	public static final String crunch_rating_max = "crunch_rating_max"; // 10 For adj length in rating per ch
	public static final String crunch_pot_indicators = "crunch_pot_indicators"; // 1 How many potential indicators needed
	public static final String crunch_leave_lc_strings = "crunch_leave_lc_strings"; // 4 Don't crunch words with long lower case strings
	public static final String crunch_leave_uc_strings = "crunch_leave_uc_strings"; // 4 Don't crunch words with long lower case strings
	public static final String crunch_long_repetitions = "crunch_long_repetitions"; // 3 Crunch words with long repetitions
	public static final String crunch_debug = "crunch_debug"; // 0 As it says
	public static final String fixsp_non_noise_limit = "fixsp_non_noise_limit"; // 1 How many non-noise blbs either side?
	public static final String fixsp_done_mode = "fixsp_done_mode"; // 1 What constitues done for spacing
	public static final String debug_fix_space_level = "debug_fix_space_level"; // 0 Contextual fixspace debug
	public static final String x_ht_acceptance_tolerance = "x_ht_acceptance_tolerance"; // 8 Max allowed deviation of blob top outside of font data
	public static final String x_ht_min_change = "x_ht_min_change"; // 8 Min change in xht before actually trying it
	public static final String superscript_debug = "superscript_debug"; // 0 Debug level for sub & superscript fixer
	public static final String suspect_level = "suspect_level"; // 99 Suspect marker level
	public static final String suspect_space_level = "suspect_space_level"; // 100 Min suspect level for rejecting spaces
	public static final String suspect_short_words = "suspect_short_words"; // 2 Don't suspect dict wds longer than this
	public static final String tessedit_reject_mode = "tessedit_reject_mode"; // 0 Rejection algorithm
	public static final String tessedit_image_border = "tessedit_image_border"; // 2 Rej blbs near image edge limit
	public static final String min_sane_x_ht_pixels = "min_sane_x_ht_pixels"; // 8 Reject any x-ht lt or eq than this
	public static final String tessedit_page_number = "tessedit_page_number"; // -1 -1 -> All pages , else specifc page to process
	public static final String tessdata_manager_debug_level = "tessdata_manager_debug_level"; // 0 Debug level for TessdataManager functions.
	public static final String tessedit_parallelize = "tessedit_parallelize"; // 0 Run in parallel where possible
	public static final String tessedit_ok_mode = "tessedit_ok_mode"; // 5 Acceptance decision algorithm
	public static final String segment_debug = "segment_debug"; // 0 Debug the whole segmentation process
	public static final String language_model_fixed_length_choices_depth = "language_model_fixed_length_choices_depth"; // 3 Depth of blob choice
																														// lists to explore when fixed
																														// length dawgs are on
	public static final String tosp_debug_level = "tosp_debug_level"; // 0 Debug data
	public static final String tosp_enough_space_samples_for_median = "tosp_enough_space_samples_for_median"; // 3 or should we use mean
	public static final String tosp_redo_kern_limit = "tosp_redo_kern_limit"; // 10 No.samples reqd to reestimate for row
	public static final String tosp_few_samples = "tosp_few_samples"; // 40 No.gaps reqd with 1 large gap to treat as a table
	public static final String tosp_short_row = "tosp_short_row"; // 20 No.gaps reqd with few cert spaces to use certs
	public static final String tosp_sanity_method = "tosp_sanity_method"; // 1 How to avoid being silly
	public static final String textord_max_noise_size = "textord_max_noise_size"; // 7 Pixel size of noise
	public static final String textord_baseline_debug = "textord_baseline_debug"; // 0 Baseline debug level
	public static final String textord_noise_sizefraction = "textord_noise_sizefraction"; // 10 Fraction of size for maxima
	public static final String textord_noise_translimit = "textord_noise_translimit"; // 16 Transitions for normal blob
	public static final String textord_noise_sncount = "textord_noise_sncount"; // 1 super norm blobs to save row
	public static final String use_definite_ambigs_for_classifier = "use_definite_ambigs_for_classifier"; // 0 Use definite ambiguities when running
																											// character classifier
	public static final String use_ambigs_for_adaption = "use_ambigs_for_adaption"; // 0 Use ambigs for deciding whether to adapt to a character
	public static final String allow_blob_division = "allow_blob_division"; // 1 Use divisible blobs chopping
	public static final String prioritize_division = "prioritize_division"; // 0 Prioritize blob division over chopping
	public static final String classify_enable_learning = "classify_enable_learning"; // 1 Enable adaptive classifier
	public static final String tess_cn_matching = "tess_cn_matching"; // 0 Character Normalized Matching
	public static final String tess_bn_matching = "tess_bn_matching"; // 0 Baseline Normalized Matching
	public static final String classify_enable_adaptive_matcher = "classify_enable_adaptive_matcher"; // 1 Enable adaptive classifier
	public static final String classify_use_pre_adapted_templates = "classify_use_pre_adapted_templates"; // 0 Use pre-adapted classifier templates
	public static final String classify_save_adapted_templates = "classify_save_adapted_templates"; // 0 Save adapted templates to a file
	public static final String classify_enable_adaptive_debugger = "classify_enable_adaptive_debugger"; // 0 Enable match debugger
	public static final String classify_nonlinear_norm = "classify_nonlinear_norm"; // 0 Non-linear stroke-density normalization
	public static final String disable_character_fragments = "disable_character_fragments"; // 1 Do not include character fragments in the results of
																							// the classifier
	public static final String classify_debug_character_fragments = "classify_debug_character_fragments"; // 0 Bring up graphical debugging windows
																											// for fragments training
	public static final String matcher_debug_separate_windows = "matcher_debug_separate_windows"; // 0 Use two different windows for debugging the
																									// matching: One for the protos and one for the
																									// features.
	public static final String classify_bln_numeric_mode = "classify_bln_numeric_mode"; // 0 Assume the input is numbers [0-9].
	public static final String load_system_dawg = "load_system_dawg"; // 1 Load system word dawg.
	public static final String load_freq_dawg = "load_freq_dawg"; // 1 Load frequent word dawg.
	public static final String load_unambig_dawg = "load_unambig_dawg"; // 1 Load unambiguous word dawg.
	public static final String load_punc_dawg = "load_punc_dawg"; // 1 Load dawg with punctuation patterns.
	public static final String load_number_dawg = "load_number_dawg"; // 1 Load dawg with number patterns.
	public static final String load_bigram_dawg = "load_bigram_dawg"; // 1 Load dawg with special word bigrams.
	public static final String use_only_first_uft8_step = "use_only_first_uft8_step"; // 0 Use only the first UTF8 step of the given string when
																						// computing log probabilities.
	public static final String stopper_no_acceptable_choices = "stopper_no_acceptable_choices"; // 0 Make AcceptableChoice() always return false.
																								// Useful when there is a need to explore all
																								// segmentations
	public static final String save_raw_choices = "save_raw_choices"; // 0 Deprecated- backward compatibility only
	public static final String segment_nonalphabetic_script = "segment_nonalphabetic_script"; // 0 Don't use any alphabetic-specific tricks.Set to
																								// true in the traineddata config file for scripts
																								// that are cursive or inherently fixed-pitch
	public static final String save_doc_words = "save_doc_words"; // 0 Save Document Words
	public static final String merge_fragments_in_matrix = "merge_fragments_in_matrix"; // 1 Merge the fragments in the ratings matrix and delete them
																						// after merging
	public static final String wordrec_no_block = "wordrec_no_block"; // 0 Don't output block information
	public static final String wordrec_enable_assoc = "wordrec_enable_assoc"; // 1 Associator Enable
	public static final String force_word_assoc = "force_word_assoc"; // 0 force associator to run regardless of what enable_assoc is.This is used for
																		// CJK where component grouping is necessary.
	public static final String fragments_guide_chopper = "fragments_guide_chopper"; // 0 Use information from fragments to guide chopping process
	public static final String chop_enable = "chop_enable"; // 1 Chop enable
	public static final String chop_vertical_creep = "chop_vertical_creep"; // 0 Vertical creep
	public static final String chop_new_seam_pile = "chop_new_seam_pile"; // 1 Use new seam_pile
	public static final String assume_fixed_pitch_char_segment = "assume_fixed_pitch_char_segment"; // 0 include fixed-pitch heuristics in char
																									// segmentation
	public static final String wordrec_skip_no_truth_words = "wordrec_skip_no_truth_words"; // 0 Only run OCR for words that had truth recorded in
																							// BlamerBundle
	public static final String wordrec_debug_blamer = "wordrec_debug_blamer"; // 0 Print blamer debug messages
	public static final String wordrec_run_blamer = "wordrec_run_blamer"; // 0 Try to set the blame for errors
	public static final String save_alt_choices = "save_alt_choices"; // 1 Save alternative paths found during chopping and segmentation search
	public static final String language_model_ngram_on = "language_model_ngram_on"; // 0 Turn on/off the use of character ngram model
	public static final String language_model_ngram_use_only_first_uft8_step = "language_model_ngram_use_only_first_uft8_step"; // 0 Use only the
																																// first UTF8 step of
																																// the given string
																																// when computing log
																																// probabilities.
	public static final String language_model_ngram_space_delimited_language = "language_model_ngram_space_delimited_language"; // 1 Words are
																																// delimited by space
	public static final String language_model_use_sigmoidal_certainty = "language_model_use_sigmoidal_certainty"; // 0 Use sigmoidal score for
																													// certainty
	public static final String tessedit_resegment_from_boxes = "tessedit_resegment_from_boxes"; // 0 Take segmentation and labeling from box file
	public static final String tessedit_resegment_from_line_boxes = "tessedit_resegment_from_line_boxes"; // 0 Conversion of word/line box file to
																											// char box file
	public static final String tessedit_train_from_boxes = "tessedit_train_from_boxes"; // 0 Generate training data from boxed chars
	public static final String tessedit_make_boxes_from_boxes = "tessedit_make_boxes_from_boxes"; // 0 Generate more boxes from boxed chars
	public static final String tessedit_dump_pageseg_images = "tessedit_dump_pageseg_images"; // 0 Dump intermediate images made during page
																								// segmentation
	public static final String tessedit_ambigs_training = "tessedit_ambigs_training"; // 0 Perform training for ambiguities
	public static final String tessedit_adaption_debug = "tessedit_adaption_debug"; // 0 Generate and print debug information for adaption
	public static final String applybox_learn_chars_and_char_frags_mode = "applybox_learn_chars_and_char_frags_mode"; // 0 Learn both character
																														// fragments (as is done in
																														// the special low exposure
																														// mode) as well as
																														// unfragmented characters.
	public static final String applybox_learn_ngrams_mode = "applybox_learn_ngrams_mode"; // 0 Each bounding box is assumed to contain ngrams. Only
																							// learn the ngrams whose outlines overlap horizontally.
	public static final String tessedit_display_outwords = "tessedit_display_outwords"; // 0 Draw output words
	public static final String tessedit_dump_choices = "tessedit_dump_choices"; // 0 Dump char choices
	public static final String tessedit_timing_debug = "tessedit_timing_debug"; // 0 Print timing stats
	public static final String tessedit_fix_fuzzy_spaces = "tessedit_fix_fuzzy_spaces"; // 1 Try to improve fuzzy spaces
	public static final String tessedit_unrej_any_wd = "tessedit_unrej_any_wd"; // 0 Don't bother with word plausibility
	public static final String tessedit_fix_hyphens = "tessedit_fix_hyphens"; // 1 Crunch double hyphens?
	public static final String tessedit_redo_xheight = "tessedit_redo_xheight"; // 1 Check/Correct x-height
	public static final String tessedit_enable_doc_dict = "tessedit_enable_doc_dict"; // 1 Add words to the document dictionary
	public static final String tessedit_debug_fonts = "tessedit_debug_fonts"; // 0 Output font info per char
	public static final String tessedit_debug_block_rejection = "tessedit_debug_block_rejection"; // 0 Block and Row stats
	public static final String tessedit_enable_bigram_correction = "tessedit_enable_bigram_correction"; // 1 Enable correction based on the word
																										// bigram dictionary.
	public static final String tessedit_enable_dict_correction = "tessedit_enable_dict_correction"; // 0 Enable single word correction based on the
																									// dictionary.
	public static final String enable_noise_removal = "enable_noise_removal"; // 1 Remove and conditionally reassign small outlines when they confuse
																				// layout analysis, determining diacritics vs noise
	public static final String debug_acceptable_wds = "debug_acceptable_wds"; // 0 Dump word pass/fail chk
	public static final String tessedit_minimal_rej_pass1 = "tessedit_minimal_rej_pass1"; // 0 Do minimal rejection on pass 1 output
	public static final String tessedit_test_adaption = "tessedit_test_adaption"; // 0 Test adaption criteria
	public static final String tessedit_matcher_log = "tessedit_matcher_log"; // 0 Log matcher activity
	public static final String test_pt = "test_pt"; // 0 Test for point
	public static final String paragraph_text_based = "paragraph_text_based"; // 1 Run paragraph detection on the post-text-recognition (more
																				// accurate)
	public static final String docqual_excuse_outline_errs = "docqual_excuse_outline_errs"; // 0 Allow outline errs in unrejection?
	public static final String tessedit_good_quality_unrej = "tessedit_good_quality_unrej"; // 1 Reduce rejection on good docs
	public static final String tessedit_use_reject_spaces = "tessedit_use_reject_spaces"; // 1 Reject spaces?
	public static final String tessedit_preserve_blk_rej_perfect_wds = "tessedit_preserve_blk_rej_perfect_wds"; // 1 Only rej partially rejected words
																												// in block rejection
	public static final String tessedit_preserve_row_rej_perfect_wds = "tessedit_preserve_row_rej_perfect_wds"; // 1 Only rej partially rejected words
																												// in row rejection
	public static final String tessedit_dont_blkrej_good_wds = "tessedit_dont_blkrej_good_wds"; // 0 Use word segmentation quality metric
	public static final String tessedit_dont_rowrej_good_wds = "tessedit_dont_rowrej_good_wds"; // 0 Use word segmentation quality metric
	public static final String tessedit_row_rej_good_docs = "tessedit_row_rej_good_docs"; // 1 Apply row rejection to good docs
	public static final String tessedit_reject_bad_qual_wds = "tessedit_reject_bad_qual_wds"; // 1 Reject all bad quality wds
	public static final String tessedit_debug_doc_rejection = "tessedit_debug_doc_rejection"; // 0 Page stats
	public static final String tessedit_debug_quality_metrics = "tessedit_debug_quality_metrics"; // 0 Output data to debug file
	public static final String bland_unrej = "bland_unrej"; // 0 unrej potential with no chekcs
	public static final String unlv_tilde_crunching = "unlv_tilde_crunching"; // 1 Mark v.bad words for tilde crunch
	public static final String hocr_font_info = "hocr_font_info"; // 0 Add font info to hocr output
	public static final String crunch_early_merge_tess_fails = "crunch_early_merge_tess_fails"; // 1 Before word crunch?
	public static final String crunch_early_convert_bad_unlv_chs = "crunch_early_convert_bad_unlv_chs"; // 0 Take out ~^ early?
	public static final String crunch_terrible_garbage = "crunch_terrible_garbage"; // 1 As it says
	public static final String crunch_pot_garbage = "crunch_pot_garbage"; // 1 POTENTIAL crunch garbage
	public static final String crunch_leave_ok_strings = "crunch_leave_ok_strings"; // 1 Don't touch sensible strings
	public static final String crunch_accept_ok = "crunch_accept_ok"; // 1 Use acceptability in okstring
	public static final String crunch_leave_accept_strings = "crunch_leave_accept_strings"; // 0 Don't pot crunch sensible strings
	public static final String crunch_include_numerals = "crunch_include_numerals"; // 0 Fiddle alpha figures
	public static final String tessedit_prefer_joined_punct = "tessedit_prefer_joined_punct"; // 0 Reward punctation joins
	public static final String tessedit_write_block_separators = "tessedit_write_block_separators"; // 0 Write block separators in output
	public static final String tessedit_write_rep_codes = "tessedit_write_rep_codes"; // 0 Write repetition char code
	public static final String tessedit_write_unlv = "tessedit_write_unlv"; // 0 Write .unlv output file
	public static final String tessedit_create_txt = "tessedit_create_txt"; // 0 Write .txt output file
	public static final String tessedit_create_hocr = "tessedit_create_hocr"; // 0 Write .html hOCR output file
	public static final String tessedit_create_pdf = "tessedit_create_pdf"; // 0 Write .pdf output file
	public static final String suspect_constrain_1Il = "suspect_constrain_1Il"; // 0 UNLV keep 1Il chars rejected
	public static final String tessedit_minimal_rejection = "tessedit_minimal_rejection"; // 0 Only reject tess failures
	public static final String tessedit_zero_rejection = "tessedit_zero_rejection"; // 0 Don't reject ANYTHING
	public static final String tessedit_word_for_word = "tessedit_word_for_word"; // 0 Make output have exactly one word per WERD
	public static final String tessedit_zero_kelvin_rejection = "tessedit_zero_kelvin_rejection"; // 0 Don't reject ANYTHING AT ALL
	public static final String tessedit_consistent_reps = "tessedit_consistent_reps"; // 1 Force all rep chars the same
	public static final String tessedit_rejection_debug = "tessedit_rejection_debug"; // 0 Adaption debug
	public static final String tessedit_flip_0O = "tessedit_flip_0O"; // 1 Contextual 0O O0 flips
	public static final String rej_trust_doc_dawg = "rej_trust_doc_dawg"; // 0 Use DOC dawg in 11l conf. detector
	public static final String rej_1Il_use_dict_word = "rej_1Il_use_dict_word"; // 0 Use dictword test
	public static final String rej_1Il_trust_permuter_type = "rej_1Il_trust_permuter_type"; // 1 Don't double check
	public static final String rej_use_tess_accepted = "rej_use_tess_accepted"; // 1 Individual rejection control
	public static final String rej_use_tess_blanks = "rej_use_tess_blanks"; // 1 Individual rejection control
	public static final String rej_use_good_perm = "rej_use_good_perm"; // 1 Individual rejection control
	public static final String rej_use_sensible_wd = "rej_use_sensible_wd"; // 0 Extend permuter check
	public static final String rej_alphas_in_number_perm = "rej_alphas_in_number_perm"; // 0 Extend permuter check
	public static final String tessedit_create_boxfile = "tessedit_create_boxfile"; // 0 Output text with boxes
	public static final String tessedit_write_images = "tessedit_write_images"; // 0 Capture the image from the IPE
	public static final String interactive_display_mode = "interactive_display_mode"; // 0 Run interactively?
	public static final String tessedit_override_permuter = "tessedit_override_permuter"; // 1 According to dict_word
	public static final String tessedit_use_primary_params_model = "tessedit_use_primary_params_model"; // 0 In multilingual mode use params model of
																										// the primary language
	public static final String textord_tabfind_show_vlines = "textord_tabfind_show_vlines"; // 0 Debug line finding
	public static final String textord_use_cjk_fp_model = "textord_use_cjk_fp_model"; // 0 Use CJK fixed pitch model
	public static final String poly_allow_detailed_fx = "poly_allow_detailed_fx"; // 0 Allow feature extractors to see the original outline
	public static final String tessedit_init_config_only = "tessedit_init_config_only"; // 0 Only initialize with the config file. Useful if the
																						// instance is not going to be used for OCR but say only for
																						// layout analysis.
	public static final String textord_equation_detect = "textord_equation_detect"; // 0 Turn on equation detector
	public static final String textord_tabfind_vertical_text = "textord_tabfind_vertical_text"; // 1 Enable vertical detection
	public static final String textord_tabfind_force_vertical_text = "textord_tabfind_force_vertical_text"; // 0 Force using vertical text page mode
	public static final String preserve_interword_spaces = "preserve_interword_spaces"; // 0 Preserve multiple interword spaces
	public static final String include_page_breaks = "include_page_breaks"; // 0 Include page separator string in output text after each image/page.
	public static final String textord_tabfind_vertical_horizontal_mix = "textord_tabfind_vertical_horizontal_mix"; // 1 find horizontal lines such as
																													// headers in vertical page mode
	public static final String load_fixed_length_dawgs = "load_fixed_length_dawgs"; // 1 Load fixed length dawgs (e.g. for non-space delimited
																					// languages)
	public static final String permute_debug = "permute_debug"; // 0 Debug char permutation process
	public static final String permute_script_word = "permute_script_word"; // 0 Turn on word script consistency permuter
	public static final String segment_segcost_rating = "segment_segcost_rating"; // 0 incorporate segmentation cost in word rating?
	public static final String permute_fixed_length_dawg = "permute_fixed_length_dawg"; // 0 Turn on fixed-length phrasebook search permuter
	public static final String permute_chartype_word = "permute_chartype_word"; // 0 Turn on character type (property) consistency permuter
	public static final String ngram_permuter_activated = "ngram_permuter_activated"; // 0 Activate character-level n-gram-based permuter
	public static final String permute_only_top = "permute_only_top"; // 0 Run only the top choice permuter
	public static final String use_new_state_cost = "use_new_state_cost"; // 0 use new state cost heuristics for segmentation state evaluation
	public static final String enable_new_segsearch = "enable_new_segsearch"; // 0 Enable new segmentation search path.
	public static final String textord_single_height_mode = "textord_single_height_mode"; // 0 Script has no xheight, so use a single mode
	public static final String tosp_old_to_method = "tosp_old_to_method"; // 0 Space stats use prechopping?
	public static final String tosp_old_to_constrain_sp_kn = "tosp_old_to_constrain_sp_kn"; // 0 Constrain relative values of inter and intra-word
																							// gaps for old_to_method.
	public static final String tosp_only_use_prop_rows = "tosp_only_use_prop_rows"; // 1 Block stats to use fixed pitch rows?
	public static final String tosp_force_wordbreak_on_punct = "tosp_force_wordbreak_on_punct"; // 0 Force word breaks on punct to break long lines in
																								// non-space delimited langs
	public static final String tosp_use_pre_chopping = "tosp_use_pre_chopping"; // 0 Space stats use prechopping?
	public static final String tosp_old_to_bug_fix = "tosp_old_to_bug_fix"; // 0 Fix suspected bug in old code
	public static final String tosp_block_use_cert_spaces = "tosp_block_use_cert_spaces"; // 1 Only stat OBVIOUS spaces
	public static final String tosp_row_use_cert_spaces = "tosp_row_use_cert_spaces"; // 1 Only stat OBVIOUS spaces
	public static final String tosp_narrow_blobs_not_cert = "tosp_narrow_blobs_not_cert"; // 1 Only stat OBVIOUS spaces
	public static final String tosp_row_use_cert_spaces1 = "tosp_row_use_cert_spaces1"; // 1 Only stat OBVIOUS spaces
	public static final String tosp_recovery_isolated_row_stats = "tosp_recovery_isolated_row_stats"; // 1 Use row alone when inadequate cert spaces
	public static final String tosp_only_small_gaps_for_kern = "tosp_only_small_gaps_for_kern"; // 0 Better guess
	public static final String tosp_all_flips_fuzzy = "tosp_all_flips_fuzzy"; // 0 Pass ANY flip to context?
	public static final String tosp_fuzzy_limit_all = "tosp_fuzzy_limit_all"; // 1 Don't restrict kn->sp fuzzy limit to tables
	public static final String tosp_stats_use_xht_gaps = "tosp_stats_use_xht_gaps"; // 1 Use within xht gap for wd breaks
	public static final String tosp_use_xht_gaps = "tosp_use_xht_gaps"; // 1 Use within xht gap for wd breaks
	public static final String tosp_only_use_xht_gaps = "tosp_only_use_xht_gaps"; // 0 Only use within xht gap for wd breaks
	public static final String tosp_rule_9_test_punct = "tosp_rule_9_test_punct"; // 0 Don't chng kn to space next to punct
	public static final String tosp_flip_fuzz_kn_to_sp = "tosp_flip_fuzz_kn_to_sp"; // 1 Default flip
	public static final String tosp_flip_fuzz_sp_to_kn = "tosp_flip_fuzz_sp_to_kn"; // 1 Default flip
	public static final String tosp_improve_thresh = "tosp_improve_thresh"; // 0 Enable improvement heuristic
	public static final String textord_no_rejects = "textord_no_rejects"; // 0 Don't remove noise blobs
	public static final String textord_show_blobs = "textord_show_blobs"; // 0 Display unsorted blobs
	public static final String textord_show_boxes = "textord_show_boxes"; // 0 Display unsorted blobs
	public static final String textord_noise_rejwords = "textord_noise_rejwords"; // 1 Reject noise-like words
	public static final String textord_noise_rejrows = "textord_noise_rejrows"; // 1 Reject noise-like rows
	public static final String textord_noise_debug = "textord_noise_debug"; // 0 Debug row garbage detector
	public static final String m_data_sub_dir = "m_data_sub_dir"; // tessdata/ Directory for data files
	public static final String tessedit_module_name = "tessedit_module_name"; // libtesseract.lib Module colocated with tessdata dir
	public static final String classify_learn_debug_str = "classify_learn_debug_str"; // Class str to debug learning
	public static final String user_words_file = "user_words_file"; // A filename of user-provided words.
	public static final String user_words_suffix = "user_words_suffix"; // A suffix of user-provided words located in tessdata.
	public static final String user_patterns_file = "user_patterns_file"; // A filename of user-provided patterns.
	public static final String user_patterns_suffix = "user_patterns_suffix"; // A suffix of user-provided patterns located in tessdata.
	public static final String output_ambig_words_file = "output_ambig_words_file"; // Output file for ambiguities found in the dictionary
	public static final String word_to_debug = "word_to_debug"; // Word for which stopper debug information should be printed to stdout
	public static final String word_to_debug_lengths = "word_to_debug_lengths"; // Lengths of unichars in word_to_debug
	public static final String tessedit_char_blacklist = "tessedit_char_blacklist"; // Blacklist of chars not to recognize
	public static final String tessedit_char_whitelist = "tessedit_char_whitelist"; // Whitelist of chars to recognize
	public static final String tessedit_char_unblacklist = "tessedit_char_unblacklist"; // List of chars to override tessedit_char_blacklist
	public static final String tessedit_write_params_to_file = "tessedit_write_params_to_file"; // Write all parameters to the given file.
	public static final String applybox_exposure_pattern = "applybox_exposure_pattern"; // .exp Exposure value follows this pattern in the image
																						// filename. The name of the image files are expected to be in
																						// the form [lang].[fontname].exp[num].tif
	public static final String chs_leading_punct = "chs_leading_punct"; // ('`" Leading punctuation
	public static final String chs_trailing_punct1 = "chs_trailing_punct1"; // ).,;:?! 1st Trailing punctuation
	public static final String chs_trailing_punct2 = "chs_trailing_punct2"; // )'`" 2nd Trailing punctuation
	public static final String outlines_odd = "outlines_odd"; // %| Non standard number of outlines
	public static final String outlines_2 = "outlines_2"; // ij!?%":; Non standard number of outlines
	public static final String numeric_punctuation = "numeric_punctuation"; // ., Punct. chs expected WITHIN numbers
	public static final String unrecognised_char = "unrecognised_char"; // | Output char for unidentified blobs
	public static final String ok_repeated_ch_non_alphanum_wds = "ok_repeated_ch_non_alphanum_wds"; // -?*= Allow NN to unrej
	public static final String conflict_set_I_l_1 = "conflict_set_I_l_1"; // Il1[] Il1 conflict set
	public static final String file_type = "file_type"; // .tif Filename extension
	public static final String tessedit_load_sublangs = "tessedit_load_sublangs"; // List of languages to load with this one
	public static final String page_separator = "page_separator"; // Page separator (default is form feed control character)
	public static final String classify_char_norm_range = "classify_char_norm_range"; // 0.2 Character Normalization Range ...
	public static final String classify_min_norm_scale_x = "classify_min_norm_scale_x"; // 0 Min char x-norm scale ...
	public static final String classify_max_norm_scale_x = "classify_max_norm_scale_x"; // 0.325 Max char x-norm scale ...
	public static final String classify_min_norm_scale_y = "classify_min_norm_scale_y"; // 0 Min char y-norm scale ...
	public static final String classify_max_norm_scale_y = "classify_max_norm_scale_y"; // 0.325 Max char y-norm scale ...
	public static final String classify_max_rating_ratio = "classify_max_rating_ratio"; // 1.5 Veto ratio between classifier ratings
	public static final String classify_max_certainty_margin = "classify_max_certainty_margin"; // 5.5 Veto difference between classifier certainties
	public static final String matcher_good_threshold = "matcher_good_threshold"; // 0.125 Good Match (0-1)
	public static final String matcher_reliable_adaptive_result = "matcher_reliable_adaptive_result"; // 0 Great Match (0-1)
	public static final String matcher_perfect_threshold = "matcher_perfect_threshold"; // 0.02 Perfect Match (0-1)
	public static final String matcher_bad_match_pad = "matcher_bad_match_pad"; // 0.15 Bad Match Pad (0-1)
	public static final String matcher_rating_margin = "matcher_rating_margin"; // 0.1 New template margin (0-1)
	public static final String matcher_avg_noise_size = "matcher_avg_noise_size"; // 12 Avg. noise blob length
	public static final String matcher_clustering_max_angle_delta = "matcher_clustering_max_angle_delta"; // 0.015 Maximum angle delta for prototype
																											// clustering
	public static final String classify_misfit_junk_penalty = "classify_misfit_junk_penalty"; // 0 Penalty to apply when a non-alnum is vertically out
																								// of its expected textline position
	public static final String rating_scale = "rating_scale"; // 1.5 Rating scaling factor
	public static final String certainty_scale = "certainty_scale"; // 20 Certainty scaling factor
	public static final String tessedit_class_miss_scale = "tessedit_class_miss_scale"; // 0.00390625 Scale factor for features not used
	public static final String classify_adapted_pruning_factor = "classify_adapted_pruning_factor"; // 2.5 Prune poor adapted results this much worse
																									// than best result
	public static final String classify_adapted_pruning_threshold = "classify_adapted_pruning_threshold"; // -1 Threshold at which
																											// classify_adapted_pruning_factor starts
	public static final String classify_character_fragments_garbage_certainty_threshold = "classify_character_fragments_garbage_certainty_threshold"; // -3
																																						// Exclude
																																						// fragments
																																						// that
																																						// do
																																						// not
																																						// look
																																						// like
																																						// whole
																																						// characters
																																						// from
																																						// training
																																						// and
																																						// adaption
	public static final String speckle_large_max_size = "speckle_large_max_size"; // 0.3 Max large speckle size
	public static final String speckle_rating_penalty = "speckle_rating_penalty"; // 10 Penalty to add to worst rating for noise
	public static final String xheight_penalty_subscripts = "xheight_penalty_subscripts"; // 0.125 Score penalty (0.1 = 10%) added if there are
																							// subscripts or superscripts in a word, but it is
																							// otherwise OK.
	public static final String xheight_penalty_inconsistent = "xheight_penalty_inconsistent"; // 0.25 Score penalty (0.1 = 10%) added if an xheight is
																								// inconsistent.
	public static final String segment_penalty_dict_frequent_word = "segment_penalty_dict_frequent_word"; // 1 Score multiplier for word matches which
																											// have good case andare frequent in the
																											// given language (lower is better).
	public static final String segment_penalty_dict_case_ok = "segment_penalty_dict_case_ok"; // 1.1 Score multiplier for word matches that have good
																								// case (lower is better).
	public static final String segment_penalty_dict_case_bad = "segment_penalty_dict_case_bad"; // 1.3125 Default score multiplier for word matches,
																								// which may have case issues (lower is better).
	public static final String segment_penalty_ngram_best_choice = "segment_penalty_ngram_best_choice"; // 1.24 Multipler to for the best choice from
																										// the ngram model.
	public static final String segment_penalty_dict_nonword = "segment_penalty_dict_nonword"; // 1.25 Score multiplier for glyph fragment
																								// segmentations which do not match a dictionary word
																								// (lower is better).
	public static final String segment_penalty_garbage = "segment_penalty_garbage"; // 1.5 Score multiplier for poorly cased strings that are not in
																					// the dictionary and generally look like garbage (lower is
																					// better).
	public static final String stopper_nondict_certainty_base = "stopper_nondict_certainty_base"; // -2.5 Certainty threshold for non-dict words
	public static final String stopper_phase2_certainty_rejection_offset = "stopper_phase2_certainty_rejection_offset"; // 1 Reject certainty offset
	public static final String stopper_certainty_per_char = "stopper_certainty_per_char"; // -0.5 Certainty to add for each dict char above small word
																							// size.
	public static final String stopper_allowable_character_badness = "stopper_allowable_character_badness"; // 3 Max certaintly variation allowed in a
																											// word (in sigma)
	public static final String doc_dict_pending_threshold = "doc_dict_pending_threshold"; // 0 Worst certainty for using pending dictionary
	public static final String doc_dict_certainty_threshold = "doc_dict_certainty_threshold"; // -2.25 Worst certainty for words that can be inserted
																								// into thedocument dictionary
	public static final String wordrec_worst_state = "wordrec_worst_state"; // 1 Worst segmentation state
	public static final String tessedit_certainty_threshold = "tessedit_certainty_threshold"; // -2.25 Good blob limit
	public static final String chop_split_dist_knob = "chop_split_dist_knob"; // 0.5 Split length adjustment
	public static final String chop_overlap_knob = "chop_overlap_knob"; // 0.9 Split overlap adjustment
	public static final String chop_center_knob = "chop_center_knob"; // 0.15 Split center adjustment
	public static final String chop_sharpness_knob = "chop_sharpness_knob"; // 0.06 Split sharpness adjustment
	public static final String chop_width_change_knob = "chop_width_change_knob"; // 5 Width change adjustment
	public static final String chop_ok_split = "chop_ok_split"; // 100 OK split limit
	public static final String chop_good_split = "chop_good_split"; // 50 Good split limit
	public static final String segsearch_max_char_wh_ratio = "segsearch_max_char_wh_ratio"; // 2 Maximum character width-to-height ratio
	public static final String language_model_ngram_small_prob = "language_model_ngram_small_prob"; // 1e-06 To avoid overly small denominators use
																									// this as the floor of the probability returned
																									// by the ngram model.
	public static final String language_model_ngram_nonmatch_score = "language_model_ngram_nonmatch_score"; // -40 Average classifier score of a
																											// non-matching unichar.
	public static final String language_model_ngram_scale_factor = "language_model_ngram_scale_factor"; // 0.03 Strength of the character ngram model
																										// relative to the character classifier
	public static final String language_model_ngram_rating_factor = "language_model_ngram_rating_factor"; // 16 Factor to bring log-probs into the
																											// same range as ratings when multiplied
																											// by outline length
	public static final String language_model_penalty_non_freq_dict_word = "language_model_penalty_non_freq_dict_word"; // 0.1 Penalty for words not
																														// in the frequent word
																														// dictionary
	public static final String language_model_penalty_non_dict_word = "language_model_penalty_non_dict_word"; // 0.15 Penalty for non-dictionary words
	public static final String language_model_penalty_punc = "language_model_penalty_punc"; // 0.2 Penalty for inconsistent punctuation
	public static final String language_model_penalty_case = "language_model_penalty_case"; // 0.1 Penalty for inconsistent case
	public static final String language_model_penalty_script = "language_model_penalty_script"; // 0.5 Penalty for inconsistent script
	public static final String language_model_penalty_chartype = "language_model_penalty_chartype"; // 0.3 Penalty for inconsistent character type
	public static final String language_model_penalty_font = "language_model_penalty_font"; // 0 Penalty for inconsistent font
	public static final String language_model_penalty_spacing = "language_model_penalty_spacing"; // 0.05 Penalty for inconsistent spacing
	public static final String language_model_penalty_increment = "language_model_penalty_increment"; // 0.01 Penalty increment
	public static final String noise_cert_basechar = "noise_cert_basechar"; // -8 Hingepoint for base char certainty
	public static final String noise_cert_disjoint = "noise_cert_disjoint"; // -1 Hingepoint for disjoint certainty
	public static final String noise_cert_punc = "noise_cert_punc"; // -3 Threshold for new punc char certainty
	public static final String noise_cert_factor = "noise_cert_factor"; // 0.375 Scaling on certainty diff from Hingepoint
	public static final String quality_rej_pc = "quality_rej_pc"; // 0.08 good_quality_doc lte rejection limit
	public static final String quality_blob_pc = "quality_blob_pc"; // 0 good_quality_doc gte good blobs limit
	public static final String quality_outline_pc = "quality_outline_pc"; // 1 good_quality_doc lte outline error limit
	public static final String quality_char_pc = "quality_char_pc"; // 0.95 good_quality_doc gte good char limit
	public static final String test_pt_x = "test_pt_x"; // 100000 xcoord
	public static final String test_pt_y = "test_pt_y"; // 100000 ycoord
	public static final String tessedit_reject_doc_percent = "tessedit_reject_doc_percent"; // 65 %rej allowed before rej whole doc
	public static final String tessedit_reject_block_percent = "tessedit_reject_block_percent"; // 45 %rej allowed before rej whole block
	public static final String tessedit_reject_row_percent = "tessedit_reject_row_percent"; // 40 %rej allowed before rej whole row
	public static final String tessedit_whole_wd_rej_row_percent = "tessedit_whole_wd_rej_row_percent"; // 70 Number of row rejects in whole word
																										// rejectswhich prevents whole row rejection
	public static final String tessedit_good_doc_still_rowrej_wd = "tessedit_good_doc_still_rowrej_wd"; // 1.1 rej good doc wd if more than this
																										// fraction rejected
	public static final String quality_rowrej_pc = "quality_rowrej_pc"; // 1.1 good_quality_doc gte good char limit
	public static final String crunch_terrible_rating = "crunch_terrible_rating"; // 80 crunch rating lt this
	public static final String crunch_poor_garbage_cert = "crunch_poor_garbage_cert"; // -9 crunch garbage cert lt this
	public static final String crunch_poor_garbage_rate = "crunch_poor_garbage_rate"; // 60 crunch garbage rating lt this
	public static final String crunch_pot_poor_rate = "crunch_pot_poor_rate"; // 40 POTENTIAL crunch rating lt this
	public static final String crunch_pot_poor_cert = "crunch_pot_poor_cert"; // -8 POTENTIAL crunch cert lt this
	public static final String crunch_del_rating = "crunch_del_rating"; // 60 POTENTIAL crunch rating lt this
	public static final String crunch_del_cert = "crunch_del_cert"; // -10 POTENTIAL crunch cert lt this
	public static final String crunch_del_min_ht = "crunch_del_min_ht"; // 0.7 Del if word ht lt xht x this
	public static final String crunch_del_max_ht = "crunch_del_max_ht"; // 3 Del if word ht gt xht x this
	public static final String crunch_del_min_width = "crunch_del_min_width"; // 3 Del if word width lt xht x this
	public static final String crunch_del_high_word = "crunch_del_high_word"; // 1.5 Del if word gt xht x this above bl
	public static final String crunch_del_low_word = "crunch_del_low_word"; // 0.5 Del if word gt xht x this below bl
	public static final String crunch_small_outlines_size = "crunch_small_outlines_size"; // 0.6 Small if lt xht x this
	public static final String fixsp_small_outlines_size = "fixsp_small_outlines_size"; // 0.28 Small if lt xht x this
	public static final String superscript_worse_certainty = "superscript_worse_certainty"; // 2 How many times worse certainty does a superscript
																							// position glyph need to be for us to try classifying it
																							// as a char with a different baseline?
	public static final String superscript_bettered_certainty = "superscript_bettered_certainty"; // 0.97 What reduction in badness do we think
																									// sufficient to choose a superscript over what
																									// we'd thought. For example, a value of 0.6 means
																									// we want to reduce badness of certainty by at
																									// least 40%
	public static final String superscript_scaledown_ratio = "superscript_scaledown_ratio"; // 0.4 A superscript scaled down more than this is
																							// unbelievably small. For example, 0.3 means we expect
																							// the font size to be no smaller than 30% of the text
																							// line font size.
	public static final String subscript_max_y_top = "subscript_max_y_top"; // 0.5 Maximum top of a character measured as a multiple of x-height above
																			// the baseline for us to reconsider whether it's a subscript.
	public static final String superscript_min_y_bottom = "superscript_min_y_bottom"; // 0.3 Minimum bottom of a character measured as a multiple of
																						// x-height above the baseline for us to reconsider whether
																						// it's a superscript.
	public static final String suspect_rating_per_ch = "suspect_rating_per_ch"; // 999.9 Don't touch bad rating limit
	public static final String suspect_accept_rating = "suspect_accept_rating"; // -999.9 Accept good rating limit
	public static final String tessedit_lower_flip_hyphen = "tessedit_lower_flip_hyphen"; // 1.5 Aspect ratio dot/hyphen test
	public static final String tessedit_upper_flip_hyphen = "tessedit_upper_flip_hyphen"; // 1.8 Aspect ratio dot/hyphen test
	public static final String rej_whole_of_mostly_reject_word_fract = "rej_whole_of_mostly_reject_word_fract"; // 0.85 if >this fract
	public static final String min_orientation_margin = "min_orientation_margin"; // 7 Min acceptable orientation margin
	public static final String textord_tabfind_vertical_text_ratio = "textord_tabfind_vertical_text_ratio"; // 0.5 Fraction of textlines deemed
																											// vertical to use vertical page mode
	public static final String textord_tabfind_aligned_gap_fraction = "textord_tabfind_aligned_gap_fraction"; // 0.75 Fraction of height used as a
																												// minimum gap for aligned blobs.
	public static final String bestrate_pruning_factor = "bestrate_pruning_factor"; // 2 Multiplying factor of current best rate to prune other
																					// hypotheses
	public static final String segment_reward_script = "segment_reward_script"; // 0.95 Score multipler for script consistency within a word. Being a
																				// 'reward' factor, it should be <= 1. Smaller value implies bigger
																				// reward.
	public static final String segment_reward_chartype = "segment_reward_chartype"; // 0.97 Score multipler for char type consistency within a word.
	public static final String segment_reward_ngram_best_choice = "segment_reward_ngram_best_choice"; // 0.99 Score multipler for ngram permuter's
																										// best choice (only used in the Han script
																										// path).
	public static final String heuristic_segcost_rating_base = "heuristic_segcost_rating_base"; // 1.25 base factor for adding segmentation cost into
																								// word rating.It's a multiplying factor, the larger
																								// the value above 1, the bigger the effect of
																								// segmentation cost.
	public static final String heuristic_weight_rating = "heuristic_weight_rating"; // 1 weight associated with char rating in combined cost ofstate
	public static final String heuristic_weight_width = "heuristic_weight_width"; // 1000 weight associated with width evidence in combined cost of
																					// state
	public static final String heuristic_weight_seamcut = "heuristic_weight_seamcut"; // 0 weight associated with seam cut in combined cost of state
	public static final String heuristic_max_char_wh_ratio = "heuristic_max_char_wh_ratio"; // 2 max char width-to-height ratio allowed in
																							// segmentation
	public static final String segsearch_max_fixed_pitch_char_wh_ratio = "segsearch_max_fixed_pitch_char_wh_ratio"; // 2 Maximum character
																													// width-to-height ratio for
																													// fixed-pitch fonts
	public static final String tosp_old_sp_kn_th_factor = "tosp_old_sp_kn_th_factor"; // 2 Factor for defining space threshold in terms of space and
																						// kern sizes
	public static final String tosp_threshold_bias1 = "tosp_threshold_bias1"; // 0 how far between kern and space?
	public static final String tosp_threshold_bias2 = "tosp_threshold_bias2"; // 0 how far between kern and space?
	public static final String tosp_narrow_fraction = "tosp_narrow_fraction"; // 0.3 Fract of xheight for narrow
	public static final String tosp_narrow_aspect_ratio = "tosp_narrow_aspect_ratio"; // 0.48 narrow if w/h less than this
	public static final String tosp_wide_fraction = "tosp_wide_fraction"; // 0.52 Fract of xheight for wide
	public static final String tosp_wide_aspect_ratio = "tosp_wide_aspect_ratio"; // 0 wide if w/h less than this
	public static final String tosp_fuzzy_space_factor = "tosp_fuzzy_space_factor"; // 0.6 Fract of xheight for fuzz sp
	public static final String tosp_fuzzy_space_factor1 = "tosp_fuzzy_space_factor1"; // 0.5 Fract of xheight for fuzz sp
	public static final String tosp_fuzzy_space_factor2 = "tosp_fuzzy_space_factor2"; // 0.72 Fract of xheight for fuzz sp
	public static final String tosp_gap_factor = "tosp_gap_factor"; // 0.83 gap ratio to flip sp->kern
	public static final String tosp_kern_gap_factor1 = "tosp_kern_gap_factor1"; // 2 gap ratio to flip kern->sp
	public static final String tosp_kern_gap_factor2 = "tosp_kern_gap_factor2"; // 1.3 gap ratio to flip kern->sp
	public static final String tosp_kern_gap_factor3 = "tosp_kern_gap_factor3"; // 2.5 gap ratio to flip kern->sp
	public static final String tosp_ignore_big_gaps = "tosp_ignore_big_gaps"; // -1 xht multiplier
	public static final String tosp_ignore_very_big_gaps = "tosp_ignore_very_big_gaps"; // 3.5 xht multiplier
	public static final String tosp_rep_space = "tosp_rep_space"; // 1.6 rep gap multiplier for space
	public static final String tosp_enough_small_gaps = "tosp_enough_small_gaps"; // 0.65 Fract of kerns reqd for isolated row stats
	public static final String tosp_table_kn_sp_ratio = "tosp_table_kn_sp_ratio"; // 2.25 Min difference of kn & sp in table
	public static final String tosp_table_xht_sp_ratio = "tosp_table_xht_sp_ratio"; // 0.33 Expect spaces bigger than this
	public static final String tosp_table_fuzzy_kn_sp_ratio = "tosp_table_fuzzy_kn_sp_ratio"; // 3 Fuzzy if less than this
	public static final String tosp_fuzzy_kn_fraction = "tosp_fuzzy_kn_fraction"; // 0.5 New fuzzy kn alg
	public static final String tosp_fuzzy_sp_fraction = "tosp_fuzzy_sp_fraction"; // 0.5 New fuzzy sp alg
	public static final String tosp_min_sane_kn_sp = "tosp_min_sane_kn_sp"; // 1.5 Don't trust spaces less than this time kn
	public static final String tosp_init_guess_kn_mult = "tosp_init_guess_kn_mult"; // 2.2 Thresh guess - mult kn by this
	public static final String tosp_init_guess_xht_mult = "tosp_init_guess_xht_mult"; // 0.28 Thresh guess - mult xht by this
	public static final String tosp_max_sane_kn_thresh = "tosp_max_sane_kn_thresh"; // 5 Multiplier on kn to limit thresh
	public static final String tosp_flip_caution = "tosp_flip_caution"; // 0 Don't autoflip kn to sp when large separation
	public static final String tosp_large_kerning = "tosp_large_kerning"; // 0.19 Limit use of xht gap with large kns
	public static final String tosp_dont_fool_with_small_kerns = "tosp_dont_fool_with_small_kerns"; // -1 Limit use of xht gap with odd small kns
	public static final String tosp_near_lh_edge = "tosp_near_lh_edge"; // 0 Don't reduce box if the top left is non blank
	public static final String tosp_silly_kn_sp_gap = "tosp_silly_kn_sp_gap"; // 0.2 Don't let sp minus kn get too small
	public static final String tosp_pass_wide_fuzz_sp_to_context = "tosp_pass_wide_fuzz_sp_to_context"; // 0.75 How wide fuzzies need context
	public static final String textord_blob_size_bigile = "textord_blob_size_bigile"; // 95 Percentile for large blobs
	public static final String textord_noise_area_ratio = "textord_noise_area_ratio"; // 0.7 Fraction of bounding box for noise
	public static final String textord_blob_size_smallile = "textord_blob_size_smallile"; // 20 Percentile for small blobs
	public static final String textord_initialx_ile = "textord_initialx_ile"; // 0.75 Ile of sizes for xheight guess
	public static final String textord_initialasc_ile = "textord_initialasc_ile"; // 0.9 Ile of sizes for xheight guess
	public static final String textord_noise_sizelimit = "textord_noise_sizelimit"; // 0.5 Fraction of x for big t count
	public static final String textord_noise_normratio = "textord_noise_normratio"; // 2 Dot to norm ratio for deletion
	public static final String textord_noise_syfract = "textord_noise_syfract"; // 0.2 xh fract height error for norm blobs
	public static final String textord_noise_sxfract = "textord_noise_sxfract"; // 0.4 xh fract width error for norm blobs
	public static final String textord_noise_hfract = "textord_noise_hfract"; // 0.015625 Height fraction to discard outlines as speckle noise
	public static final String textord_noise_rowratio = "textord_noise_rowratio"; // 6 Dot to norm ratio for deletion
	public static final String textord_blshift_maxshift = "textord_blshift_maxshift"; // 0 Max baseline shift
	public static final String textord_blshift_xfraction = "textord_blshift_xfraction"; // 9.99 Min size of baseline shift

}
