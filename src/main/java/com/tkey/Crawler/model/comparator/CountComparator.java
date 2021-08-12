package com.tkey.Crawler.model.comparator;

import com.tkey.Crawler.model.Emergencies;

import java.util.Comparator;

public class CountComparator implements Comparator<Emergencies> {


    @Override
    public int compare(Emergencies emergencies, Emergencies emergencies1) {

        if (emergencies.getCount()<emergencies1.getCount()){
            return -1;
        }else if (emergencies.getCount()>emergencies.getCount()){
            return 1;
        }

        return 0;
    }
}
