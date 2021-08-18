package com.codecool.robodog2.controller;


import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.model.Trick;
import com.codecool.robodog2.service.TrickService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trick")
public class TrickController {

    private final TrickService trickService;

    public TrickController(TrickService trickService) {
        this.trickService = trickService;
    }

    @PostMapping("/add")
    public void addTrick(@RequestBody Trick trick){
        trickService.addTrick(trick);
    }

    @GetMapping
    public List<Trick> tricks(){
        return trickService.listTricks();
    }

    @GetMapping("/{id}")
    public Trick getTrick(@PathVariable("id") Long id){
        return trickService.getTrick(id);
    }
    @PutMapping("/{id}")
    public void updateTrick(@PathVariable("id") Long id,@RequestBody Trick trick){
        trickService.updateTrick(trick,id);
    }
    @DeleteMapping("/{id}")
    public void deleteTrick(@PathVariable("id") Long id){
        trickService.deleteTrick(id);
    }
}
