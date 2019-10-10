package pl.nogacz.tasks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaticWebPageController {
    @GetMapping(value = "/")
    public String index() {
        return "index";
    }
}
