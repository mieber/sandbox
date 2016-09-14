package hh.hh.filewatch;

import java.nio.file.Path;
import java.nio.file.WatchEvent.Kind;

public interface FileEventListener {
	
	void inform(String filepath, Kind<Path> entryCreate);

}
