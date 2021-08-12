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
    private  final int MAX_VISITED_PAGES=328;
    private final Set<String> visited;
    private Document document;
    private final Queue<CrawlUrl> pagesToVisit;

    @Autowired
    public HtmlService(ResultService resultService, Set<String> domainStorage) {
        this.resultService = resultService;
        this.pagesToVisit = new LinkedList<>();
        this.visited = new HashSet<>();
    }

    /**
     *
     * @param crawlUrl root url for searching
     * @param searchWord word that we are looking for in page
     * @return if no exception returns false
     * @throws com.tkey.Crawler.exceptions.IOException
     */
    public boolean search(String crawlUrl, String searchWord) throws com.tkey.Crawler.exceptions.IOException {
        if (pagesToVisit.isEmpty()){
            pagesToVisit.add(new CrawlUrl(crawlUrl,0));
        }
        System.out.println(visited.size());
        try {

            while (!pagesToVisit.isEmpty()){
                CrawlUrl currentUrl=  pagesToVisit.remove();
                System.out.println(visited.size());
                if (visited.size()>=MAX_VISITED_PAGES){
                    return true ;
                }
                if (!visited.contains(currentUrl.url)){
                    this.visited.add(currentUrl.url);
                    document = Jsoup.connect(currentUrl.url).timeout(0).ignoreHttpErrors(true).get();
                    Elements linksOnPage = document.select("a[href]");
                    long count=searchForWord(searchWord);
                    if (count>0){
                        resultService.addSensor(new Emergencies(count,currentUrl.url));
                    }
                    String href;
                    if (currentUrl.depth<MAX_DEPTH||visited.size()<MAX_VISITED_PAGES) {
                        for (Element link : linksOnPage) {
                            href = link.attr("abs:href");
                                 if (!isSubUrl(href)&&!visited.contains(href)) {
                                     pagesToVisit.add(new CrawlUrl(href, currentUrl.depth + 1));
                                 }
                             }
                         }
                    }
                }
        } catch (IOException e) {
            throw new com.tkey.Crawler.exceptions.IOException(e.getMessage());
        }
        return false;
    }

    /**
     * the function that checks if the given url is subUrl
     * example-https://en.wikipedia.org/wiki/Elon_Musk and https://en.wikipedia.org/wiki/Elon_Musk#mv-header are the same urls but
     * second url is just header of that page
     * @param url url
     * @return count of words found
     */
    public boolean isSubUrl (String url){
        String[] split = url.split("#");
        if (split.length>1){
            String givenRoot =split[1];
            return url.contains(givenRoot);
        }
        return false;
    }

    /**
     *
     * @param searchWord word thar we are looking for
     * @return number of words found on given page
     *
     */
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