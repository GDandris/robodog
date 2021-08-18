package com.codecool.robodog2.dao;
import com.codecool.robodog2.model.Dog;
import com.codecool.robodog2.model.Pedigree;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PedigreeDAO {

    void addPedigree(Pedigree pedigree);
    List<Pedigree> listPedigrees();
    Pedigree getPedigree(long id);
    void updatePedigree(long id, Pedigree pedigree);
    void deletePedigree(long id);
    List<Dog> getFamily(long dogId);
    void makePuppy(long momId,long dadId,Dog puppy);
    Dog getMom(long dogId);
    Dog getDad(long dogId);
    List<Dog>  getSiblings(long dogId);

}
