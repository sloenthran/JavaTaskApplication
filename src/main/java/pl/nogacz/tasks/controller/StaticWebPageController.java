package pl.nogacz.tasks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class StaticWebPageController {
    @GetMapping(value = "/")
    public String index(Map<String, Object> model) {
        model.put("variable", "My Thymeleaf variable");
        model.put("one", 1);
        model.put("two", 2);
        return "index";
    }
}
