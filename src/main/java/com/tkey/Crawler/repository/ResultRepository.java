package com.tkey.Crawler.repository;

import com.tkey.Crawler.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  ResultRepository extends JpaRepository<Result,Long> {


}
