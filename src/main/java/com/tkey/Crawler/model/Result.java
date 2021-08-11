package com.tkey.Crawler.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Result implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private long count;
    private String url;

    public Result() {

    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Result(long count, String url) {
        this.count=count;
        this.url=url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return count == result.count && Objects.equals(url, result.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, url);
    }

    @Override
    public String toString() {
        return "Result{" +
                "count=" + count +
                ", url='" + url + '\'' +
                '}';
    }

}
