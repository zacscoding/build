package demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @GitHub : https://github.com/zacscoding
 */
@Controller
public class RouteController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/{path:[^\\\\.]*}")
    public String redirect() {
        return "forward:/";
    }
}
