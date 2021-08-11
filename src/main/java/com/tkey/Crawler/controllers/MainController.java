package com.tkey.Crawler.controllers;

import com.tkey.Crawler.exceptions.IOException;
import com.tkey.Crawler.services.HtmlService;
import com.tkey.Crawler.services.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;
import java.util.List;

@Controller
@EnableWebMvc
public class MainController {
    // inject via application.properties
    @Value("${welcome.message}")
    private String message;

    private final HtmlService htmlService;
    private final List<String> tasks = Arrays.asList("a", "b", "c", "d", "e", "f", "g");
    private final ResultService resultService;
    @Autowired
    public MainController(HtmlService htmlService, ResultService resultService) {
        this.htmlService = htmlService;
        this.resultService = resultService;
    }

    @GetMapping("/")
    public String main(Model model) throws IOException {
        try {
            htmlService.search("https://en.wikipedia.org/wiki/Elon_Musk","Musk");
        }catch (IOException e){
            throw new IOException();
        }
        model.addAttribute("message", message);
        model.addAttribute("tasks", resultService.findAllSensors());
        return "welcome"; //view
    }

    // /hello?name=kotlin
    @GetMapping("/hello")
    public String mainWithParam(
            @RequestParam(name = "name", required = false, defaultValue = "") String name, Model model) {

        model.addAttribute("message", name);

        return "welcome"; //view
    }
}
