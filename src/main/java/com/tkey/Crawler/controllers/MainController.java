package com.tkey.Crawler.controllers;


import com.tkey.Crawler.model.SearchTerm;
import com.tkey.Crawler.services.HtmlService;
import com.tkey.Crawler.services.PrintService;
import com.tkey.Crawler.services.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
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

    @PostMapping("/")
    public String main( SearchTerm searchTerm,Model model)  {
                    try {
                        htmlService.search(searchTerm.getUrl(),searchTerm.getWord());
                        printService.print();
                    } catch (IOException | com.tkey.Crawler.exceptions.IOException e) {
                        e.printStackTrace();
                    }
                    if (searchTerm.isTop10()){
                        model.addAttribute("tasks", printService.sort());

                    }else
                        model.addAttribute("tasks", resultService.findAllResults());

        return "results"; //view
    }

    @GetMapping("/search")
     public  String smallRequest(Model model){
        model.addAttribute("searchterm", new SearchTerm());


        return "welcome";
    }


    }


