package hh.hh.handler;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.WatchEvent.Kind;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hh.hh.SettingsService;
import hh.hh.filewatch.FileEventListener;
import hh.hh.filewatch.MainWatch;
import hh.hh.ocr.J2DImageTool;
import hh.hh.ocr.ScreenGrabResult;
import hh.hh.ocr.SingleWordResult;
import hh.hh.storage.DBController;
import hh.hh.ui.ScreenUpdateController;

@Component
public class Reactor implements FileEventListener {

	@Autowired
	private ScreenUpdateController update;

	@Autowired
	private MainWatch fileWatcher;

	@Autowired
	private SettingsService settings;

	@Autowired
	private DBController db;

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

		ScreenGrabResult r = J2DImageTool.extractNames(bufferedImage, settings.getFileOutput(),
				settings.getTessdataPath());

		ScreenshotModel model = new ScreenshotModel();
		model.setMap(r.getMap().getText());
		model.setEnemies(getTexts(r.getEnemies()));
		model.setEnemyHeroes(filterHeroes(getTexts(r.getEnemyHeroes())));
		model.setFriends(getTexts(r.getFriends()));
		model.setFriendHeroes(filterHeroes(getTexts(r.getFriendHeroes())));

		update.update(model);
	}

	private List<String> filterHeroes(List<String> heroes) {
		List<String> allHeroNames = db.getAllHeroNames();
		for (Iterator<String> i = heroes.iterator(); i.hasNext();) {
			String heroName = (String) i.next();
			if (!containsCaseInsensitive(heroName, allHeroNames)) {
				i.remove();
			}
		}
		return heroes;
	}

	private boolean containsCaseInsensitive(String search, List<String> list) {
		for (String string : list) {
			if (string.equalsIgnoreCase(search)) {
				return true;
			}
		}
		return false;
	}

	private static List<String> getTexts(List<SingleWordResult> r) {
		return r.stream().map(SingleWordResult::getText).collect(Collectors.toList());
	}

}
