package com.codecool.robodog2.dao.implementation.mem;

import com.codecool.robodog2.dao.DogDAO;
import com.codecool.robodog2.model.Dog;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class DogMemDao implements DogDAO {
    List<Dog> dogs;

    public DogMemDao() {
        this.dogs = new ArrayList<>();
    }

    @Override
    public void addDog(Dog dog) {
        dogs.add(dog);
    }

    @Override
    public List<Dog> listDogs() {
        return dogs;
    }

    @Override
    public Dog getDog(long id) {
        return dogs.stream()
                .filter(dog -> dog.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void updateDog(Dog dog, long id) {
        for(Dog dogToUpdate : dogs){
            if (dogToUpdate.getId() == id){
                dogToUpdate = dog;
            }
        }
    }

    @Override
    public void deleteDog(long id) {
        dogs.removeIf(dog -> dog.getId() == id);
    }

    @Override
    public long addDogAndReturnId(Dog dog) {
        addDog(dog);
        return dogs.get(dogs.size()-1).getId();
    }
}
