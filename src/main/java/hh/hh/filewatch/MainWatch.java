package hh.hh.filewatch;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import hh.hh.Conf;

@Component
public class MainWatch {

    private WatchService service;

    private List<FileEventListener> listeners = new ArrayList<>();

    @PostConstruct
    public void setUp() {
        File dir = new File(Conf.FILEWATCH_ROOT);
        Path path = dir.toPath();
        System.out.println("Watching path: " + path);

        // We obtain the file system of the Path
        FileSystem fs = path.getFileSystem();

        // We create the new WatchService using the new try() block
        try {
            service = fs.newWatchService();

            // We register the path to the service
            // We watch for creation events
            path.register(service, ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
    public void addListener(FileEventListener listener) {
    	listeners.add(listener);
    }

    @SuppressWarnings("unchecked")
    @Scheduled(fixedRate = 5000)
    public void watchDirectoryPath() {

        System.out.print(".");

        WatchKey key = service.poll();
        if (key == null) {
            System.out.print("-");
            return;
        }

        // Dequeueing events
        Kind<?> kind = null;
        for (WatchEvent<?> watchEvent : key.pollEvents()) {
            // Get the type of the event
            kind = watchEvent.kind();
            if (OVERFLOW == kind) {
                continue; // loop
            } else if (ENTRY_CREATE == kind) {
                // A new Path was created
                Path newPath = ((WatchEvent<Path>) watchEvent).context();
                // Output
                System.out.println("New path created: " + newPath);
                for (FileEventListener listener : listeners) {
                	try {
                		listener.inform(Conf.FILEWATCH_ROOT + "/" + newPath.toString(), ENTRY_CREATE);
                	} catch (Exception e) {
                		e.printStackTrace();
                	}
				}
            } 
//            else if (ENTRY_MODIFY == kind) {
//                // modified
//                Path newPath = ((WatchEvent<Path>) watchEvent).context();
//                // Output
//                System.out.println("New path modified: " + newPath);
//                update.greet(newPath.toString());
//            }
        }
        key.reset();
        
        System.out.println("Done");
    }

}