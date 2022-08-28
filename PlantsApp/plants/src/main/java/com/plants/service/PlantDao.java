package com.plants.service;

import com.plants.model.Plant;
import com.plants.model.PlantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PlantDao {
    @Autowired
    private PlantsRepository repository;


    public List<Plant> getAllPlants() {
        List<Plant> plants = new ArrayList<Plant>();
        Streamable.of(repository.findAll()).forEach(plants::add);
        return plants;
    }

    public Plant findById(int parseInt) {

        return repository.findById(parseInt).get();
    }

    public Plant save(Plant plant) {
        return repository.save(plant);
    }

    public void delete(Plant plant) {
        repository.delete(plant);
    }

    public void deleteById(int parseInt) {
        repository.deleteById(parseInt);
    }


}
