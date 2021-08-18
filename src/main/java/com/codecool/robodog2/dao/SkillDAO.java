package com.codecool.robodog2.dao;

import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.model.Skill;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface SkillDAO {

    void addSkill(Skill skill);
    List<Skill> listSkills();
    Skill getSkill(long id);
    void updateSkill(Skill skill, long id);
    void deleteSkill(long id);
    List<Dog> getDogsWithSkill(long trickId);
    Optional<Skill> getSpecificSkill(long dogId, long trickId);
    long getLevelOfSkill(String trickName, long dogId) throws Exception;
    void levelUpSkill(String trickName, long dogId) throws Exception;
}
