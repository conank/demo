package demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Pikachu on 9/2/2015.
 */

@Controller //@Controller will forward the returned string value to View parser by default
public class ViewController {
    @RequestMapping("/hello")
    public String hello() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }
}
