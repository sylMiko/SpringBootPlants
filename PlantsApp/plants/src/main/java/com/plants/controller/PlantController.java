package com.plants.controller;

import com.plants.model.Plant;
import com.plants.service.PlantDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlantController {

    @Autowired
    private PlantDao plantDao;

    @PostMapping("/plant/save")
    public Plant save(@RequestBody Plant plant) {
        return plantDao.save(plant);
    }

    @GetMapping("/plant/allPlants")
    public List<Plant> getAllStudents() {

        return plantDao.getAllPlants();

    }

    @GetMapping("/plant/getPlantById/{id}")
    public ResponseEntity<Plant> getStudentById(@PathVariable long id) {
        Plant plant = plantDao.findById((int) id);
        return ResponseEntity.ok(plant);
    }


    @PutMapping("/plant/updateById/{id}")
    public Plant update(@RequestBody Plant plant, @PathVariable String id) {
        Plant plantEx = plantDao.findById(Integer.parseInt(id));
        plantEx.setName(plant.getName());
        plantEx.setFloweringTime(plant.getFloweringTime());
        plantEx.setMaturityTime(plant.getMaturityTime());
        plantEx.setPlantingDate(plant.getPlantingDate());

        return plantDao.save(plantEx);
    }

    @DeleteMapping("/plant/deleteById/{id}")
    public void delete(@PathVariable String id) {
        plantDao.deleteById(Integer.parseInt(id));
    }
}
