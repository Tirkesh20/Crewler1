package com.tkey.Crawler.model;

public class SearchTerm {
    private String url;
    private String word;
    private boolean top10;

    public SearchTerm(){

    }

    public SearchTerm(String url, String word) {
        this.url = url;
        this.word = word;
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



    public boolean isTop10() {
        return top10;
    }

    public void setTop10(boolean top10) {
        this.top10 = top10;
    }
}
