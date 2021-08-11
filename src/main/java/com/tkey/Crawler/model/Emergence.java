package com.tkey.Crawler.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Emergence implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "url",length = 50)
    private String url;

    @Column(name = "search_word",length = 50)
    private String word;

    @Column(name = "emergence")
    private int emergence;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getEmergence() {
        return emergence;
    }

    public void setEmergence(int emergence) {
        this.emergence = emergence;
    }

    public Emergence(){

    }

    public Emergence(String url, String word, int emergence) {
        this.url = url;
        this.word = word;
        this.emergence = emergence;
    }
}
