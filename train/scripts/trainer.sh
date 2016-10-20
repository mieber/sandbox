#!/bin/bash

# current directory
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

# log file for maven / test output
TESSERACT_DIR="e:/hh/dev/tesseract_3.05"
TRAIN_DIR="e:/hh/git/sandbox/train"
#TRAIN_DIR="F:/dev/git/sandbox/train"

FONT[0]="Metronic_W01_Bold"
FONT[1]="Metronic_W01_Regular"
FONT_NAME[0]="MetronicW01-Bold Bold"
FONT_NAME[1]="MetronicW01-Regular"

OUTPUT_LANG="hero"
LANG_DATA="testdata.hero.txt"

# OPTIONAL! Leave empty -> ="" if you dont want to use it. It will skip the step wordlist.
WORDLIST="wordlistfile.hero.txt"

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
	
	for ((i=0;i<${#FONT[@]};++i)); do
		$TESSERACT_DIR/text2image.exe --text=$TRAIN_DIR/trainlangdata/$LANG_DATA --outputbase=$WORK_DIR/$OUTPUT_LANG.${FONT[i]}.exp0 --font="${FONT_NAME[i]}" --fonts_dir= $LOG
	done
}

function train() {
    echo Training...
    for ((i=0;i<${#FONT[@]};++i)); do
    	$TESSERACT_DIR/tesseract.exe $WORK_DIR/$OUTPUT_LANG.${FONT[i]}.exp0.tif $WORK_DIR/$OUTPUT_LANG.${FONT[i]}.exp0 box.train.stderr $LOG
    done
}

function unicharset() {
	echo Unicharset...
	
	local tmp=""
	for ((i=0;i<${#FONT[@]};++i)); do
		tmp+="$WORK_DIR/$OUTPUT_LANG.${FONT[i]}.exp0.box "
	done
	
	#tmp e.g. = $WORK_DIR/bat.Metronic_W01_Bold.exp0.box $WORK_DIR/bat.Metronic_W01_Regular.exp0.box $WORK_DIR/bat.Metronic_W01_Semibold.exp0.box
	$TESSERACT_DIR/unicharset_extractor.exe -D $WORK_DIR $tmp $LOG
	$TESSERACT_DIR/set_unicharset_properties.exe -U $WORK_DIR/unicharset -O $WORK_DIR/unicharset_props --script_dir=$TRAIN_DIR/trainlangdata $LOG
}

function mftraining() {
	echo mftraining...
	
	local tmp=""
	for ((i=0;i<${#FONT[@]};++i)); do
		tmp+="$WORK_DIR/$OUTPUT_LANG.${FONT[i]}.exp0.tr "
	done
	
	#tmp e.g. $RESULT_DIR/unicharset $WORK_DIR/bat.Metronic_W01_Bold.exp0.tr $WORK_DIR/bat.Metronic_W01_Semibold.exp0.tr $WORK_DIR/bat.Metronic_W01_Regular.exp0.tr
	$TESSERACT_DIR/mftraining.exe -D $RESULT_DIR -F $TRAIN_DIR/trainlangdata/font_properties -U $WORK_DIR/unicharset -O $RESULT_DIR/unicharset $tmp $LOG
}

function cntraining() {
	echo cntraining...
	
	local tmp=""
	for ((i=0;i<${#FONT[@]};++i)); do
		tmp+="$WORK_DIR/$OUTPUT_LANG.${FONT[i]}.exp0.tr "
	done
	#tmp e.g. $WORK_DIR/bat.Metronic_W01_Bold.exp0.tr $WORK_DIR/bat.Metronic_W01_Semibold.exp0.tr $WORK_DIR/bat.Metronic_W01_Regular.exp0.tr
	$TESSERACT_DIR/cntraining.exe -D $RESULT_DIR $tmp $LOG
}

function addprefix() {
	echo adding prefix...
	for entry in `ls $RESULT_DIR`; do
    	mv "$RESULT_DIR/$entry" "$RESULT_DIR/$OUTPUT_LANG.$entry"
	done
}

function wordlist() {
	if [ -z "$WORDLIST" ]; then
		echo wordlist file not set. skipping this step...
	fi
	if [ -n "$WORDLIST" ]; then
		echo creating word-dawg file...
		$TESSERACT_DIR/wordlist2dawg.exe $TRAIN_DIR/trainlangdata/$WORDLIST $RESULT_DIR/$OUTPUT_LANG.word-dawg $WORK_DIR/unicharset
	fi
}

function combine() {
	echo combining...
	$TESSERACT_DIR/combine_tessdata.exe $RESULT_DIR/$OUTPUT_LANG.
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
        --wordlist)
        	wordlist
       	;;
        --full)
           clean
           text2image
           train
           unicharset
           mftraining
           cntraining
           addprefix
           wordlist
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
           echo "   wordlist"
           echo "	combine"
        ;;
        *) echo "unknown option -- $1"
           echo "Try '--help' for more information."
        ;;
    esac
    shift
done
exit 0
