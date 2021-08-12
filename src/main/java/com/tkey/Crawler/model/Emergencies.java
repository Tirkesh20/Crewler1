package com.tkey.Crawler.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Emergencies implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private long count;
    private String url;

    public Emergencies() {

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

    public Emergencies(long count, String url) {
        this.count=count;
        this.url=url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emergencies emergencies = (Emergencies) o;
        return count == emergencies.count && Objects.equals(url, emergencies.url);
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
