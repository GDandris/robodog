package com.codecool.robodog2.controller;

import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.model.Skill;
import com.codecool.robodog2.service.SkillService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/skill")
public class SkillController {
    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping
    public List<Skill> listSkills(){
        return skillService.listSkills();
    }

    @PostMapping
    public void addSkill(@RequestBody Skill skill){
        skillService.addSkill(skill);
    }

    @GetMapping("/{id}")
    public Skill getSkillById(@PathVariable("id") Long id){
        return skillService.getSkill(id);
    }

    @PutMapping("/{id}")
    public void updateSkill(@PathVariable("id") Long id,  @RequestBody Skill skill ){
        skillService.updateSkill(skill,id);
    }

    @DeleteMapping("/{id}")
    public void deleteSkill(@PathVariable("id") Long id){
        skillService.deleteSkill(id);
    }

    @GetMapping("/trick/{id}")
    public List<Dog> getDogsWithSkill(@PathVariable("id") Long id){
        return skillService.getDogsWithSkill(id);
    }

    @GetMapping("/check/{dogId}/{trickId}")
    public Optional<Skill> getSpecificSkill(@PathVariable("dogId") long dogId, @PathVariable("trickId") long trickId){
        return skillService.getSpecificSkill(dogId, trickId);
    }
    @GetMapping("/name/{trick_name}/dog/{dogId}")
    public long getLevelOfSkill(@PathVariable("dogId") long dogId, @PathVariable("trick_name") String trickName) throws Exception {
        return skillService.getLevelofSkill(trickName, dogId);
    }
    @PutMapping("/name/{trick_name}/dog/{dogId}")
    public void levelUpSkill(@PathVariable("dogId") long dogId, @PathVariable("trick_name") String trickName) throws Exception {
        skillService.levelUpSkill(trickName, dogId);
    }
}
