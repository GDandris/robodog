package com.codecool.robodog2.service;

import com.codecool.robodog2.dao.DogDAO;
import com.codecool.robodog2.dao.implementation.db.DogJdbcDao;
import com.codecool.robodog2.model.Breed;
import com.codecool.robodog2.model.Dog;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class DogService {

    private final DogDAO dogDAO;

    private final String[] names =  {"Bella",
            "Luna",
            "Lucy",
            "Daisy",
            "Lola",
            "Sadie",
            "Molly",
            "Bailey",
            "Stella",
            "Maggie",
            "Max",
            "Charlie",
            "Cooper",
            "Buddy",
            "Milo",
            "Bear",
            "Rocky",
            "Duke",
            "Tucker",
            "Jack"
    };

    public DogService(DogJdbcDao dogJdbcDao) {
        this.dogDAO = dogJdbcDao;
    }

    public Dog createRandomDog(){
        Random random = new Random();
        String name = getRandomName();
        Breed breed = Breed.randomBreed();
        int age = random.nextInt(15);
        return new Dog(breed,name,age);
    }

    public String getRandomName(){
        Random random = new Random();
        return names[random.nextInt(names.length)];
    }

    public void addDog(Dog dog){
        dogDAO.addDog(dog);
    }

    public List<Dog> dogs(){
        return dogDAO.listDogs();
    }

    public void updateDog(Long id,Dog dog){
        dogDAO.updateDog(dog,id);
    }
    public void deleteDog(Long id){
        dogDAO.deleteDog(id);
    }
    public Dog getDog( Long id){
        return dogDAO.getDog(id);
    }
    public void addRandomDog(){
        dogDAO.addDog(createRandomDog());
    }
    public Long addDogAndReturnId(Dog dog){
        return dogDAO.addDogAndReturnId(dog);
    }

}
