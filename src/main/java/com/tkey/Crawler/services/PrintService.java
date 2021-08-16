package com.tkey.Crawler.services;

import com.tkey.Crawler.model.Emergencies;

import com.tkey.Crawler.model.interfaces.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrintService implements CSVPrinter {
    private List<Emergencies> sortedList;
    private final ResultService resultService;


    @Autowired
    public PrintService(ResultService resultService) {
        this.resultService = resultService;
        sortedList=new LinkedList();
    }

    /**
     *
     * @throws IOException
     */
    @Override
    public void print() throws IOException {
        List<Emergencies> list=resultService.findAllResults();
        File file=new File("test.csv");
           if(!file.exists()) {
            file.createNewFile();
            }
             try (PrintWriter writer = new PrintWriter(file)) {
             StringBuilder sb = new StringBuilder();
                if (!list.isEmpty()) {
                     sb.append("Url ");
                     sb.append(" ,     ");
                     sb.append("hits");
                     sb.append('\n');
                     writer.write(sb.toString());
                    for (Emergencies o : list) {
                    writer.write(o.getUrl() + "," + o.getCount() + '\n');
                    }
                    List<Emergencies> sorted=sort();
                    long total=sorted.stream().mapToLong(Emergencies::getCount).sum();
                    sb.append("Top10 ");
                    sb.append(" ,  ");
                    sb.append("hits total");
                    sb.append('\n');
                    writer.write(sb.toString());
                    for (Emergencies o : sorted) {
                    writer.write(o.getUrl() + "," + o.getCount() +"  "+ total+'\n');
                    }
                }
            }catch(FileNotFoundException e){
                System.out.println(e.getMessage());
        }
    }

    public List<Emergencies>  sort(){
        List<Emergencies> list=resultService.findAllResults();
        Comparator<Emergencies> comparator = (p1, p2) -> (int) ( p2.getCount() - p1.getCount());
        list.sort(comparator);
        List<Emergencies> sorted=list.stream().limit(10).collect(Collectors.toList());
        return sorted;
    }
}
