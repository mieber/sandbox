package hh.hh;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.bytedeco.javacpp.lept.PIX;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
@EnableScheduling
@ComponentScan(basePackages = { "hh.hh" })
public class HotshelperController {
    
    @Autowired
    private MainWatch watcher;
    
    @Autowired
    private HotslogsService hotslogs;

    @RequestMapping(value="/",method = RequestMethod.GET)
    String home() {

        return "index";
    }
    
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(3000); // simulated delay
        return new Greeting("Hello, " + message.getName() + "!");
    }
    

    @RequestMapping("/base")
    @ResponseBody
    String baseInfo() {

        ScreenGrabber.createNameImages("/draft.png", "E:/vhp/hh/sandbox/src/main/resources/output", "r", "png");

        String[] names = new String[5];
        for (int i = 0; i < 5; i++) {
            PIX p = TesseractHelper.getPixFromPath("E:/vhp/hh/sandbox/src/main/resources/output/r" + i + ".png");
            names[i] = TesseractHelper.getTextFromPicture(p, null).trim();
            System.out.println("E:/vhp/hh/sandbox/src/main/resources/output/r" + i + ".png: " + names[i]);
        }

        names[0] = "PandaAttack";
        names[1] = "Dunkelqwert";
        names[2] = "Rohan";
        names[3] = "TinyTina";
        names[4] = "xxHawkxx";

        Player[] foundPlayers = new Player[5];

        for (int i = 0; i < 5; i++) {
            String name = names[i];
            List<Player> players = hotslogs.getHotsIds(name);

            for (Player player : players) {
                System.out.println(name + ": " + player);
            }
            Player bestMatch = new PlayerFilter(players).getBestMatch();
            System.out.println("Best Match: " + bestMatch);
            foundPlayers[i] = bestMatch;

        }

        return Arrays.toString(foundPlayers);
    }

    @RequestMapping("/detail")
    @ResponseBody
    String detail() {

        new Player(4387231);
        
        // [Player [name=PandaAttack, id=4387231, mmr=1890, region=EU, numberOfGames=713], null, Player [name=Rohan, id=5201487, mmr=1720, region=EU, numberOfGames=1418], Player [name=TinyTina,
        // id=3792937, mmr=2561, region=EU, numberOfGames=1774], null]

        return null;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(HotshelperController.class, args);
    }

}
