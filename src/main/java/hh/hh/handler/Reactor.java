package hh.hh.handler;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.WatchEvent.Kind;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hh.hh.SettingsService;
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
	
	@Autowired
	private SettingsService settings;
	
	@PostConstruct
	private void setUp() {
		fileWatcher.addListener(this);
	}

	@Override
	public void inform(String filepath, Kind<Path> entryCreate) {
		
		System.out.println("Going to read: " + filepath);
		
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(new File(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}

		ScreenGrabResult result = J2DImageTool.extractNames(bufferedImage, settings.getFileOutput(), settings.getTessdataPath());
		update.update(result);
	}

}
