package com.plants.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PlantsRepository extends CrudRepository<Plant, Integer> {

}
