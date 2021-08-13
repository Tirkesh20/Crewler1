package com.tkey.Crawler;


import com.tkey.Crawler.exceptions.IOException;
import com.tkey.Crawler.services.HtmlService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
public class ServiceTest {

private final HtmlService htmlService;

    @Autowired
    public ServiceTest(HtmlService htmlService) {
        this.htmlService = htmlService;
    }

//    @Test
//    public void testIoExcepton(){
//        function(,);
//    }
//
//    private void function(IOException expected,Boolean array){
//        Boolean actual= searchServices.getPrimeNumbers(array);
//        assertEquals(expected,actual);
//    }
}
