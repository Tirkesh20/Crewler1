package com.tkey.Crawler.model;

import javax.persistence.*;

public class CrawlUrl {

    private Long id;

    public String url;

    public int depth;


    public CrawlUrl(String crawUrl,int depth){
        this.url=crawUrl;
        this.depth=depth;
    }

}
