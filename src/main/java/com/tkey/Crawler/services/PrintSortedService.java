package com.tkey.Crawler.services;

import com.tkey.Crawler.model.Emergencies;
import com.tkey.Crawler.model.comparator.CountComparator;
import com.tkey.Crawler.model.interfaces.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class PrintSortedService implements CSVPrinter {
    private final ResultService resultService;

    @Autowired
    public PrintSortedService(ResultService resultService) {
        this.resultService = resultService;
    }

    @Override
    public void print() throws IOException {
        Comparator<Emergencies> comparator = (p1, p2) -> (int) (p1.getCount() - p2.getCount());
        List<Emergencies> list = resultService.findAllResults();
        File file = new File("sorted.csv");
        if (!file.exists()) {
            file.createNewFile();
        }
        list.sort(comparator);
        List<Emergencies> sorted=list.stream().sorted(Comparator.comparingLong(Emergencies::getCount)).limit(10).collect(Collectors.toList());
        try (PrintWriter writer = new PrintWriter(file)) {
            StringBuilder sb = new StringBuilder();
            if (!list.isEmpty()) {
                sb.append("Url ");
                sb.append(" ,     ");
                sb.append("hits");
                sb.append('\n');
                writer.write(sb.toString());
                for (Emergencies o : sorted) {
                    writer.write(o.getUrl() + "," + o.getCount() + '\n');
                }
            }
        writer.write(sb.toString());

        }
    }
}
