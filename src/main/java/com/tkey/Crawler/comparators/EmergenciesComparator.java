package com.tkey.Crawler.comparators;

import com.tkey.Crawler.model.Emergencies;

import java.util.Comparator;

public class EmergenciesComparator implements Comparator<Emergencies> {
    @Override
    public int compare(Emergencies o1, Emergencies o2) {
        if (o1.getCount()< o2.getCount()) return 1;
        if (o1.getCount()>o2.getCount()) return -1;
        return 0;
    }
}
