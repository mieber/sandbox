package hh.hh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {

    private SimpMessagingTemplate template;

    @Autowired
    public GreetingController(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void greet(String greeting) {
         Greeting greet = new Greeting("Hello, " + greeting + "!");
        this.template.convertAndSend("/topic/greetings", greet);
    }

}