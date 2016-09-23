package hh.hh;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.BooleanUtils;
import org.springframework.stereotype.Controller;

@Controller
public class SettingsService {
	
	public static final String USER_HOME = System.getProperty("user.home");
	public static final String HH_HOME = USER_HOME + File.separator + "hhelper";
	public static final String TESSDATA_SUBPATH = "tessdata";
	public static final String PROPERTIES_FILE_NAME = "hh.properties";

	public enum SettingsParam {
		FILE_OUTPUT(HH_HOME + File.separator + "ocr_temp"), //
		FILEWATCH_PATH(USER_HOME + File.separator + "Documents" + File.separator + "Heroes of the Storm"
				+ File.separator + "Screenshots"), //
		TESSDATA_PATH(HH_HOME), //
		PROXY_ENABLED(BooleanUtils.toStringYesNo(false)), //
		PROXY_URL("127.0.0.1"), //
		PROXY_PORT("80");
		private String defaultValue;

		private SettingsParam(String defaultValue) {
			this.defaultValue = defaultValue;
		}
	}

	private Properties properties;

	@PostConstruct
	private void createSettings() {
		initData();
		checkFolders();
		exportTessdata();
	}

	private void checkFolders() {

		File writeDirectory = new File(getTessdataPath() + File.separator + TESSDATA_SUBPATH);
		if (!writeDirectory.exists()) {
			writeDirectory.mkdirs();
		}

		writeDirectory = new File(getFileOutput());
		if (!writeDirectory.exists()) {
			writeDirectory.mkdirs();
		}

		writeDirectory = new File(getFilewatchRoot());
		if (!writeDirectory.exists()) {
			writeDirectory.mkdirs();
		}

	}

	public boolean isProxyEnabled() {
		return BooleanUtils.toBoolean(properties.getProperty(SettingsParam.PROXY_ENABLED.name()));
	}

	public String getFileOutput() {
		return properties.getProperty(SettingsParam.FILE_OUTPUT.name());
	}

	public String getFilewatchRoot() {
		return properties.getProperty(SettingsParam.FILEWATCH_PATH.name());
	}

	public String getProxyUrl() {
		return properties.getProperty(SettingsParam.PROXY_URL.name());
	}

	public int getProxyPort() {
		try {
			return Integer.valueOf(properties.getProperty(SettingsParam.PROXY_PORT.name()));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 80;
		}
	}

	public String getTessdataPath() {
		return properties.getProperty(SettingsParam.TESSDATA_PATH.name());
	}

	private void initData() {

		if (!new File(HH_HOME).exists()) {
			new File(HH_HOME).mkdirs();
		}

		File propertyFile = new File(HH_HOME + File.separator + PROPERTIES_FILE_NAME);
		if (!propertyFile.exists()) {
			Properties p = new Properties();
			for (SettingsParam s : SettingsParam.values()) {
				p.setProperty(s.name(), s.defaultValue);
			}

			try {
				p.store(new FileOutputStream(propertyFile), "initial load");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		properties = new Properties();

		try (FileInputStream in = new FileInputStream(propertyFile)) {
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void exportTessdata() {

		File outputFile = new File(
				getTessdataPath() + File.separator + TESSDATA_SUBPATH + File.separator + "bat.traineddata");
		if (outputFile.exists()) {
			return;
		}

		try (InputStream stream = SettingsService.class.getResourceAsStream("/tessdata/bat.traineddata");
				OutputStream resStreamOut = new FileOutputStream(outputFile);) {
			if (stream == null) {
				throw new Exception("Cannot get resource 'bat.traineddata' from Jar file.");
			}

			int readBytes;
			byte[] buffer = new byte[4096];
			while ((readBytes = stream.read(buffer)) > 0) {
				resStreamOut.write(buffer, 0, readBytes);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
