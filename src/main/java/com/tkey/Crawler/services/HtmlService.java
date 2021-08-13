package com.tkey.Crawler.services;

import com.tkey.Crawler.model.CrawlUrl;
import com.tkey.Crawler.model.Emergencies;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class HtmlService {
    private static final int MAX_DEPTH = 8;
    private final ResultService resultService;
    private  final int MAX_VISITED_PAGES=10000;
    private final Set<String> visited;
    private Document document;
    private final Queue<CrawlUrl> pagesToVisit;
    private final Set<String> domainStorage;

    @Autowired
    public HtmlService(ResultService resultService, Set<String> domainStorage) {
        this.resultService = resultService;
        this.domainStorage = new HashSet<>();
        this.pagesToVisit = new LinkedList<>();
        this.visited = new HashSet<>();
    }

    public void search(String crawlUrl, String searchWord) throws com.tkey.Crawler.exceptions.IOException {
        pagesToVisit.add(new CrawlUrl(crawlUrl,0));
        try {
            while (!pagesToVisit.isEmpty()){
                CrawlUrl currentUrl=  pagesToVisit.remove();
                    this.visited.add(currentUrl.url);
                System.out.println(visited.size());
                    System.out.println(pagesToVisit.size());
                    System.out.println(domainStorage.size());
                    if (visited.size()==MAX_VISITED_PAGES){
                        System.out.println("end");
                    return;
                }
                document = Jsoup.connect(currentUrl.url).get();
                Elements linksOnPage = document.select("a[href]");
                long count=searchForWord(searchWord);
                if (count>0){
                    resultService.addSensor(new Emergencies(count,currentUrl.url));
                }
                System.out.println(currentUrl.url+" "+count+" "+currentUrl.depth);
                String href;
                    if (currentUrl.depth<MAX_DEPTH||visited.size()<MAX_VISITED_PAGES) {
                         for (Element link : linksOnPage) {
                              href=link.attr("abs:href");
                                if (!isSubUrl(href)){
                                    if ( !visited.contains(href)&&!domainStorage.contains(href)) {
                                    pagesToVisit.add(new CrawlUrl(href, currentUrl.depth + 1));
                                    domainStorage.add(href);
                                    }
                                }
                         }
                     }
                }
        } catch (IOException e) {
            throw new com.tkey.Crawler.exceptions.IOException(e.getMessage());
        }
    }

    public boolean isSubUrl (String url){
        String[] split = url.split("#");
        if (split.length>1){
            String givenRoot =split[1];
            return url.contains(givenRoot);
        }
        return false;
    }

    public  long searchForWord(String searchWord) {
        if(this.document == null) {
            System.out.println("ERROR! Call crawl() before performing analysis on the document");
            throw new NullPointerException();
        }
        String bodyText = this.document.body().text();
        Matcher matcher = Pattern.compile ( "\\b+"+searchWord+"\\b", Pattern.CASE_INSENSITIVE).matcher(bodyText);
        return matcher.results().count();
    }

}