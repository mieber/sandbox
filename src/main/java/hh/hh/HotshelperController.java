package hh.hh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
@ComponentScan(basePackages = { "hh.hh" })
public class HotshelperController {

    @RequestMapping("/")
    @ResponseBody
            String home() {

        return "This is the hots helper root page.";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(HotshelperController.class, args);
    }

}
