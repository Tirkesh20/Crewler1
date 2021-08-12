package com.tkey.Crawler.controllers;

import com.tkey.Crawler.exceptions.IOException;
import com.tkey.Crawler.services.HtmlService;
import com.tkey.Crawler.services.PrintService;
import com.tkey.Crawler.services.PrintSortedService;
import com.tkey.Crawler.services.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@EnableWebMvc
public class MainController {
    // inject via application.properties
    @Value("${welcome.message}")
    private String message;
    private String url;
    private String word;
    private final HtmlService htmlService;
    private final ResultService resultService;
    private final PrintService printService;
    private final PrintSortedService printSortedService;
    @Autowired
    public MainController(HtmlService htmlService, ResultService resultService, PrintService printService, PrintSortedService printSortedService) {
        this.htmlService = htmlService;
        this.resultService = resultService;
        this.printService = printService;
        this.printSortedService = printSortedService;
    }

    @PostMapping ("/welcome")
    public String main(Model model) throws java.io.IOException {
        try {
            System.out.println(this.url);
            htmlService.search(this.url,this.word);
            printService.print();
        }catch (java.io.IOException e){
            e.getMessage();
        }
        printService.print();
        model.addAttribute("message", resultService.findAllResults());
        return "results"; //view
    }

//    https://en.wikipedia.org/wiki/Elon_Musk
    // /hello?name=kotlin
    @GetMapping ("/")
    public String mainWithParam(HttpServletRequest request,
                                HttpServletResponse response) {

         this.url = request.getParameter("username");
         this.word=request.getParameter("searchword");
        System.out.println(this.url);
        System.out.println(word);
        return "welcome"; //view
    }
}
