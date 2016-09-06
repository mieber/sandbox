#!/bin/bash

# current directory
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

# log file for maven / test output
TESSERACT_DIR="e:/hh/dev/tesseract_3.05"
TRAIN_DIR="e:/hh/git/sandbox/train"

function initvars() {
	echo Initalizing...
	WORK_DIR="$TRAIN_DIR/work"
	RESULT_DIR="$TRAIN_DIR/result"
	LOG_FILE="$DIR/trainer.log"
	touch $LOG_FILE
	LOG= 2>&1 | tee "$LOG_FILE"
}

function clean() {
	echo Cleaning...
	if [ -d "$WORK_DIR" ]
	then
		echo Work dir already exists, cleaning
		rm -rf "$WORK_DIR"
		mkdir $WORK_DIR
    else
		echo Creating work dir
		mkdir $WORK_DIR
	fi

	if [ -d "$RESULT_DIR" ]
	then
		echo result dir already exists, cleaning
		rm -rf "$RESULT_DIR"
		mkdir $RESULT_DIR
    else
		echo Creating result dir
		mkdir $RESULT_DIR
	fi
}

function text2image() {
	echo Starting text2Image
	$TESSERACT_DIR/text2image.exe --text=$TRAIN_DIR/trainlangdata/testdata.txt --outputbase=$WORK_DIR/bat.Metronic_W01_Bold.exp0 --font='MetronicW01-Bold Bold' --fonts_dir= $LOG
	$TESSERACT_DIR/text2image.exe --text=$TRAIN_DIR/trainlangdata/testdata.txt --outputbase=$WORK_DIR/bat.Metronic_W01_Regular.exp0 --font='MetronicW01-Regular' --fonts_dir= $LOG
	# $TESSERACT_DIR/text2image.exe --text=$TRAIN_DIR/trainlangdata/testdata.txt --outputbase=$WORK_DIR/bat.Metronic_W01_Semibold.exp0 --font='MetronicW01-Semibold Semi-Bold' --fonts_dir= $LOG
}

function train() {
    echo Training...
    $TESSERACT_DIR/tesseract.exe $WORK_DIR/bat.Metronic_W01_Bold.exp0.tif $WORK_DIR/bat.Metronic_W01_Bold.exp0 box.train.stderr $LOG
 	$TESSERACT_DIR/tesseract.exe $WORK_DIR/bat.Metronic_W01_Regular.exp0.tif $WORK_DIR/bat.Metronic_W01_Regular.exp0 box.train.stderr $LOG
 	# $TESSERACT_DIR/tesseract.exe $WORK_DIR/bat.Metronic_W01_Semibold.exp0.tif $WORK_DIR/bat.Metronic_W01_Semibold.exp0 box.train.stderr $LOG
}

function unicharset() {
	echo Unicharset...
	# $TESSERACT_DIR/unicharset_extractor.exe -D $WORK_DIR $WORK_DIR/bat.Metronic_W01_Bold.exp0.box $WORK_DIR/bat.Metronic_W01_Regular.exp0.box $WORK_DIR/bat.Metronic_W01_Semibold.exp0.box $LOG
	$TESSERACT_DIR/unicharset_extractor.exe -D $WORK_DIR $WORK_DIR/bat.Metronic_W01_Bold.exp0.box $WORK_DIR/bat.Metronic_W01_Regular.exp0.box $LOG
	$TESSERACT_DIR/set_unicharset_properties.exe -U $WORK_DIR/unicharset -O $WORK_DIR/unicharset_props --script_dir=$TRAIN_DIR/trainlangdata $LOG
}

function mftraining() {
	echo mftraining...
	# $TESSERACT_DIR/mftraining.exe -D $RESULT_DIR -F $TRAIN_DIR/trainlangdata/font_properties -U $WORK_DIR/unicharset -O $RESULT_DIR/unicharset $WORK_DIR/bat.Metronic_W01_Bold.exp0.tr $WORK_DIR/bat.Metronic_W01_Semibold.exp0.tr $WORK_DIR/bat.Metronic_W01_Regular.exp0.tr $LOG
	$TESSERACT_DIR/mftraining.exe -D $RESULT_DIR -F $TRAIN_DIR/trainlangdata/font_properties -U $WORK_DIR/unicharset -O $RESULT_DIR/unicharset $WORK_DIR/bat.Metronic_W01_Bold.exp0.tr $WORK_DIR/bat.Metronic_W01_Regular.exp0.tr $LOG
}

function cntraining() {
	echo cntraining...
	# $TESSERACT_DIR/cntraining.exe -D $RESULT_DIR $WORK_DIR/bat.Metronic_W01_Bold.exp0.tr $WORK_DIR/bat.Metronic_W01_Semibold.exp0.tr $WORK_DIR/bat.Metronic_W01_Regular.exp0.tr $LOG
	$TESSERACT_DIR/cntraining.exe -D $RESULT_DIR $WORK_DIR/bat.Metronic_W01_Bold.exp0.tr $WORK_DIR/bat.Metronic_W01_Regular.exp0.tr $LOG
}

function addprefix() {
	echo adding prefix...
	for entry in `ls $RESULT_DIR`; do
    	mv "$RESULT_DIR/$entry" "$RESULT_DIR/bat.$entry"
	done
}

function combine() {
	echo combining...
	$TESSERACT_DIR/combine_tessdata.exe $RESULT_DIR/bat.
}

initvars
while test $# -gt 0
do
    case "$1" in
    	--clean)
    		clean
    	;;
        --text2image)
            text2image
        ;;
        --train)
            train
        ;;
        --unicharset)
        	unicharset
        ;;
        --mftraining)
        	mftraining
        ;;
        --cntraining)
        	cntraining
        ;;
        --addprefix)
        	addprefix
        ;;
        --combine)
        	combine
        ;;
        --full)
           clean
           text2image
           train
           unicharset
           mftraining
           cntraining
           addprefix
           combine
        ;;
        --help)
           echo "Possible params: "
           echo "	clean"
           echo "	text2image"
           echo "	train"
           echo "	unicharset"
           echo "	mftraining"
           echo "	cntraining"
           echo "	addprefix"
           echo "	combine"
        ;;
        *) echo "unknown option -- $1"
           echo "Try '--help' for more information."
        ;;
    esac
    shift
done
exit 0
