package com.tkey.Crawler.services;

import com.tkey.Crawler.model.Emergencies;
import com.tkey.Crawler.repository.EmergenciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {

    private final EmergenciesRepository repository;

    @Autowired
    public ResultService(EmergenciesRepository repository) {
        this.repository = repository;
    }


    public void add(Emergencies emergencies){
        repository.save(emergencies);
    }

    public List<Emergencies> findAllResults(){
        return repository.findAll();
    }


}
