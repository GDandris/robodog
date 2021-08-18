package com.codecool.robodog2.controller;

import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.service.DogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dog")
public class DogController {

    private final DogService dogService;

    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    @PostMapping("/add")
    public void addDog(@RequestBody Dog dog){
        dogService.addDog(dog);
    }

    @GetMapping
    public List<Dog> dogs(){
        return dogService.dogs();
    }

    @GetMapping("/{id}")
    public Dog getDogById(@PathVariable("id") Long id){
        return dogService.getDog(id);
    }
    @PutMapping("/{id}")
    public void updateDog(@PathVariable("id") Long id,@RequestBody Dog dog){
        dogService.updateDog(id,dog);
    }
    @DeleteMapping("/{id}")
    public void deleteDog(@PathVariable("id") Long id){
        dogService.deleteDog(id);
    }
    @PostMapping("/add/id")
    public Long addDogAndReturnId(@RequestBody Dog dog){
        return dogService.addDogAndReturnId(dog);
    }
    @GetMapping("/random")
    public void addRandomDog(){
        dogService.addRandomDog();
    }
}
