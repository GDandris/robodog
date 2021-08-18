package com.codecool.robodog2.service;

import com.codecool.robodog2.dao.SkillDAO;
import com.codecool.robodog2.dao.implementation.db.SkillJdbcDAO;
import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.model.Skill;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillService {
    private final SkillDAO skillDAO;

    public SkillService(SkillJdbcDAO skillJdbcDAO) {
        this.skillDAO = skillJdbcDAO;
    }

    public void addSkill(Skill skill){
        skillDAO.addSkill(skill);
    }
    public List<Skill> listSkills(){
        return skillDAO.listSkills();
    }
    public Skill getSkill(long id){
        return skillDAO.getSkill(id);
    }

    public void updateSkill(Skill skill, long id){
        skillDAO.updateSkill(skill, id);
    }
    public void deleteSkill(long id){
        skillDAO.deleteSkill(id);
    }
    public List<Dog> getDogsWithSkill(long trickId){
        return skillDAO.getDogsWithSkill(trickId);
    }
    public Optional<Skill> getSpecificSkill(long dogId, long trickId){
        return skillDAO.getSpecificSkill(dogId, trickId);
    }

    public long getLevelofSkill(String trickName, long dogId) throws Exception{
        return skillDAO.getLevelOfSkill(trickName, dogId);
    }

    public void levelUpSkill(String trickName, long dogId) throws Exception {
        skillDAO.levelUpSkill(trickName, dogId);
    }
}
