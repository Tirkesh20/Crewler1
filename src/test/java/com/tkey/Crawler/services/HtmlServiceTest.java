package com.tkey.Crawler.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class HtmlServiceTest {

    private final HtmlService htmlService;

    @Autowired
    HtmlServiceTest(HtmlService htmlService) {
        this.htmlService = htmlService;
    }

    @Test
    void testWhenNotSubUrl() {
    String word="https://en.wikipedia.org/wiki/Elon_Musk";
    test(false,word);
    }

    @Test
    void testWhenSubUrl() {
        String word="https://en.wikipedia.org/wiki/Elon_Musk#mv-head";
        test(true,word);
    }

    private void test(boolean expected, String word) {
        boolean actual = htmlService.isSubUrl(word);
         Assertions.assertEquals(expected, actual);
    }


    @org.junit.Test(expected = NullPointerException.class)
    public void searchForWordWhenDocumentIsEmpty() {
        String word="musk";
        long num=1;
        testSearch(num,word);
    }

    private void testSearch(long expected, String word) {
        long actual = htmlService.searchForWord(word);
        Assertions.assertEquals(expected, actual);
    }
}