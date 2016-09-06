package hh.hh.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ScreenUpdateController {

    private SimpMessagingTemplate template;

    @Autowired
    public ScreenUpdateController(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void greet(String greeting, String[] enemies) {
         ScreenUpdate greet = new ScreenUpdate("Hello, " + greeting + "!", "TEST2", enemies);
        this.template.convertAndSend("/topic/updates", greet);
    }

}