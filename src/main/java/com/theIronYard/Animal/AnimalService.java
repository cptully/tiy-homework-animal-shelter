package com.theIronYard.Animal;

import java.util.ArrayList;

/**
 * Created by chris on 8/12/16.
 */
public class AnimalService {
    // properties
    private AnimalRepository animalRepo;

    /**
     * The AnimalService class constructor requires an AnimalRepository object
     *
     * @param animalRepo an AnimalRepository object
     */
    public AnimalService(AnimalRepository animalRepo) {
        this.animalRepo = animalRepo;
    }

    /****************************
     * methods                  *
     ****************************/

    /**
     * listAnimals
     *
     * @return ArrayList&lt;String&gt; of string representations of animals
     */
    public ArrayList<String> listAnimals() { return animalRepo.list(); }

    /**
     * getAnimal gets the animal object for the submitted integer index
     * @param index - the object to be returned
     * @return an Animal object
     */
    public Animal getAnimal(int index) {return animalRepo.get(index); }

    /**
     * addAnimal adds the supplied Animal to the AnimalRepository
     *
     * @param newAnimal Animal to be added
     */
    public void addAnimal(Animal newAnimal) { animalRepo.add(newAnimal); }

    /**
     * editAnimal passes the edited animal to AnimalRepository
     *
     * @param animal the edited animal
     * @param index the index in the repository of the animal to update
     */
    public void editAnimal(Animal animal, int index) {
        animalRepo.editAnimal(animal, index);
    }

    /**
     * editAnimal overloads editAnimal(animal, index) to accept the animal data
     * as string values.
     *
     * @param index the index of the animal to update
     * @param name the animal's name - ignored
     * @param species the animal's speiecs
     * @param breed the animal's breed
     * @param color - color or description of the animal's coat
     * @param description - description of the animal or behavioral traits
     */
    public void editAnimal(int index, String name, String species, String breed, String color, String description) {
        animalRepo.editAnimal(index, name, species, breed, color, description);
    }

    /**
     * removeAnimal removes the selected animal from the repository
     *
     * @param index the zero based index of the animal to remove
     */
    public void removeAnimal(int index) {
        animalRepo.remove(index);
    }

    public boolean contains(Animal animal) { return animalRepo.contains(animal); }

    public boolean contains(int index) { return animalRepo.contains(index); }
}
