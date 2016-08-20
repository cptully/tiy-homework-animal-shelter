package com.theIronYard.Animal;

import java.util.ArrayList;

/**
 * Created by chris on 8/12/16.
 */
public class AnimalsService {
    private ArrayList<Animal> animalList = new ArrayList<>(10);

    private static final boolean USE_DEMO_DATA = true;

    public AnimalsService() {

        //test data
        if (USE_DEMO_DATA)

        {
            //AnimalsService animals = new AnimalsService();
            addAnimal("Shadow", "dog", "border collie", "black and white", "energetic and friendly; liked to chase balls");
            addAnimal("Mia", "cat", "domestic short hair", "calico", "skittish");
            addAnimal("Rags", "cat", "domestic short hair", "black and white", "likes to hunt");
        }

    }

    public ArrayList<String> listAnimals() {
        ArrayList<String> animalListString = new ArrayList<>();

        for (Animal animal : animalList) {
            animalListString.add(animal.toString());
        }
        return animalListString;
    }

    public Animal getAnimal(int index) {
        return animalList.get(index);
    }

    public void addAnimal(Animal newAnimal) {
        animalList.add(newAnimal);
    }

    public void addAnimal(String name, String species, String breed, String color, String description) {
        Animal newAnimal = new Animal(name, species, breed, color, description);
        animalList.add(newAnimal);
    }

    public void editAnimal(int index, String name, String species, String breed, String color, String description) {
        Animal currentAnimal = animalList.get(index);

        currentAnimal.name = name;
        currentAnimal.species = species;
        currentAnimal.breed = breed;
        currentAnimal.color = color;
        currentAnimal.description = description;
    }

    public void editAnimal(Animal animal, int index) {
        if (! animal.name.isEmpty()) {animalList.get(index).name = animal.name;}
        if (! animal.species.isEmpty()) {animalList.get(index).species = animal.species;}
        if (! animal.breed.isEmpty()) {animalList.get(index).breed = animal.breed;}
        if (! animal.color.isEmpty()) {animalList.get(index).color = animal.color;}
        if (! animal.description.isEmpty()) {animalList.get(index).description = animal.description;}
    }

    public void removeAnimal(int index) {
        if ((index > 0) && (index < animalList.size())) {
            animalList.remove(index);
        }
    }

    public boolean contains(Animal animal) {
        return animalList.contains(animal);
    }

    public boolean contains(int index) {
        if ((index > 0) && (index < animalList.size())) {
            return animalList.contains(animalList.get(index));
        }
        return false;
    }

}
