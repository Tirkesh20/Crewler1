package com.tkey.Crawler.controllers;


import com.tkey.Crawler.services.HtmlService;
import com.tkey.Crawler.services.PrintService;
import com.tkey.Crawler.services.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;

@Controller
@EnableWebMvc
public class MainController {

    private final HtmlService htmlService;
    private final ResultService resultService;
    private final PrintService printService;
    @Autowired
    public MainController(HtmlService htmlService, ResultService resultService, PrintService printService) {
        this.htmlService = htmlService;
        this.resultService = resultService;
        this.printService = printService;
    }

    @GetMapping("/")
    public String main(Model model) throws com.tkey.Crawler.exceptions.IOException {
        try {
            htmlService.search("https://en.wikipedia.org/wiki/Elon_Musk","Musk");
            printService.print();
        }catch (IOException e){
            e.getMessage();
        }
        model.addAttribute("tasks", resultService.findAllResults());

        return "welcome"; //view
    }


}
