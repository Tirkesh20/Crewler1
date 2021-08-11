package com.tkey.Crawler.model;

import javax.persistence.*;

@Entity
public class CrawlUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50)
    public String url;

    public int depth;

    public CrawlUrl() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }

    public CrawlUrl(String crawUrl,int depth){
        this.url=crawUrl;
        this.depth=depth;
    }

}
