package hh.hh.handler;

import java.nio.file.Path;
import java.nio.file.WatchEvent.Kind;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hh.hh.filewatch.FileEventListener;
import hh.hh.filewatch.MainWatch;
import hh.hh.ocr.J2DImageTool;
import hh.hh.ocr.ScreenGrabResult;
import hh.hh.ui.ScreenUpdateController;

@Component
public class Reactor implements FileEventListener {

	@Autowired
	private ScreenUpdateController update;

	@Autowired
	private MainWatch fileWatcher;

	@PostConstruct
	private void setUp() {
		fileWatcher.addListener(this);
	}

	@Override
	public void inform(String filepath, Kind<Path> entryCreate) {

		ScreenGrabResult result = J2DImageTool.extractNames(filepath);
		update.update(result);
	}

	// TODO Auto-generated method stub

}
