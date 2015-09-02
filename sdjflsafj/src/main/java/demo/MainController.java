package demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Pikachu on 8/9/15.
 */
@RestController
public class MainController {
    @RequestMapping("/")
    public String index() {
        return "Proudly handcrafted by " +
                "<a href='http://netgloo.com/en'>netgloo</a> :)";
    }

}
