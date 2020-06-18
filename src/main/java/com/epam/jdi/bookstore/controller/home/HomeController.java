package com.epam.jdi.bookstore.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class HomeController {
    @RequestMapping(value = "/swagger-ui")
    public String index() {
        System.out.println("swagger-ui.html");
        return "redirect:swagger-ui.html";
    }

    @RequestMapping(value = "/")
    public String openApi() {
        return "redirect:swagger-ui-openapi.html";
    }
}
