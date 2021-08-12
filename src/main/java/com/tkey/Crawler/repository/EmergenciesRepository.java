package com.tkey.Crawler.repository;

import com.tkey.Crawler.model.Emergencies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmergenciesRepository extends JpaRepository<Emergencies,Long> {


}
