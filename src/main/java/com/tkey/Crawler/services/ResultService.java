package com.tkey.Crawler.services;

import com.tkey.Crawler.model.Result;
import com.tkey.Crawler.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {

    private final ResultRepository repository;

    @Autowired
    public ResultService(ResultRepository repository) {
        this.repository = repository;
    }


    public Result addSensor(Result sensor){
        return repository.save(sensor);
    }

    public List<Result> findAllSensors(){
        return repository.findAll();
    }

    public Result update(Result sensor){
        return repository.save(sensor);
    }


}
