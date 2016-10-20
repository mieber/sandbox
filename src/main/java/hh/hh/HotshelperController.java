package hh.hh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.hh.handler.ScreenshotModel;

@Controller
@EnableAutoConfiguration
@EnableScheduling
@EnableCaching
@ComponentScan(basePackages = { "hh.hh" })
public class HotshelperController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	String home() {

		return "index";
	}

	@MessageMapping("/screenupdate")
	@SendTo("/topic/updates")
	public ScreenshotModel screenupdate(ScreenshotModel message) throws Exception {
		System.out.println("HotshelperController.screenupdate()");
		return message;
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(HotshelperController.class, args);
	}

}
