package com.codecool.robodog2.controller;

import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.model.Pedigree;
import com.codecool.robodog2.service.PedigreeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PedigreeController {
    private final PedigreeService pedigreeService;

    public PedigreeController(PedigreeService pedigreeService) {
        this.pedigreeService = pedigreeService;
    }

    @GetMapping("/dog/{dog_id}/pedigree")
    public List<Dog> getFamily(@PathVariable("dog_id") long dogId){
        return pedigreeService.getFamily(dogId);
    }

    @PostMapping("/dog/{dog_id}/pedigree")
    public void addPedigree(@PathVariable("dog_id") long dogId, @RequestBody Pedigree pedigree){
        pedigree.setPuppyId(dogId);
        pedigreeService.addPedigree(pedigree);
    }

    @GetMapping("/dog/puppy")
    public void makePuppy(@RequestParam("momId") long momId, @RequestParam("dadId") long dadId){
        pedigreeService.makePuppy(momId,dadId);
    }

    @GetMapping("/dog/{dog_id}/pedigree/dad")
    public Dog getDad(@PathVariable("dog_id") long dogId){
        return pedigreeService.getDad(dogId);
    }
    @GetMapping("/dog/{dog_id}/pedigree/mom")
    public Dog getMom(@PathVariable("dog_id") long dogId){
        return pedigreeService.getMom(dogId);
    }
    @GetMapping("/dog/{dog_id}/pedigree/siblings")
    public List<Dog> getSiblings(@PathVariable("dog_id") long dogId){
        return pedigreeService.getSiblings(dogId);
    }
}
