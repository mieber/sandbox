package hh.hh.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import hh.hh.handler.ScreenshotModel;

@Controller
public class ScreenUpdateController {

    private SimpMessagingTemplate template;

    @Autowired
    public ScreenUpdateController(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void update(ScreenshotModel update) {
        this.template.convertAndSend("/topic/updates", update);
    }

}