package com.codecool.robodog2.service;

import com.codecool.robodog2.dao.PedigreeDAO;
import com.codecool.robodog2.dao.implementation.db.PedigreeJdbcDAO;
import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.model.Pedigree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class PedigreeService {

    @Autowired
    DogService dogService;

    private final PedigreeDAO pedigreeDAO;

    public PedigreeService(PedigreeJdbcDAO pedigreeDAO) {
        this.pedigreeDAO = pedigreeDAO;
    }

    public void addPedigree(Pedigree pedigree){
        pedigreeDAO.addPedigree(pedigree);
    }
    List<Pedigree> listPedigrees(){
        return pedigreeDAO.listPedigrees();
    }
    Pedigree getPedigree(long id){
        return pedigreeDAO.getPedigree(id);
    }
    public void updatePedigree(long id, Pedigree pedigree){
        pedigreeDAO.updatePedigree(id, pedigree);
    }
    public void deletePedigree(long id){
        pedigreeDAO.deletePedigree(id);
    }
    public void makePuppy(long momId, long dadId){
        Random random = new Random();
        int breedRandom = random.nextInt(2);
        Dog puppy = new Dog(breedRandom == 0? dogService.getDog(momId).getBreed(): dogService.getDog(dadId).getBreed(), dogService.getRandomName(), 0);
        pedigreeDAO.makePuppy(momId,dadId,puppy);
    }
    public Dog getMom(long dogId){
        return pedigreeDAO.getMom(dogId);
    }
    public Dog getDad(long dogId){
        return pedigreeDAO.getDad(dogId);
    }
    public List<Dog> getFamily(long dogId){
        return pedigreeDAO.getFamily(dogId);
    }
    public List<Dog>  getSiblings(long dogId){
        return pedigreeDAO.getSiblings(dogId);
    }
}
