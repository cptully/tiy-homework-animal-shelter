package com.theIronYard.Animal;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Created by chris on 8/20/16.
 */
public class AnimalRepository {
    private ArrayList<Animal> animalList = new ArrayList<>(10);

    private static final boolean USE_DEMO_DATA = true;

    public AnimalRepository() {

        //load test data
        if (USE_DEMO_DATA) {
            //AnimalService animals = new AnimalService();
            animalList.add(new Animal("Shadow", "dog", "border collie", "black and white", "energetic and friendly; liked to chase balls"));
            animalList.add(new Animal("Mia", "cat", "domestic short hair", "calico", "skittish"));
            animalList.add(new Animal("Rags", "cat", "domestic short hair", "black and white", "likes to hunt"));
        }
    }

    Animal get(int index) { return animalList.get(index); }

    boolean add(Animal animal) { return animalList.add(animal); }

    int size() { return animalList.size(); }

    Animal remove(int index) { return animalList.remove(index); }

    boolean contains(int index) {
        if ((index > 0) && (index < animalList.size())) {
            return animalList.contains(animalList.get(index));
        }
        return false;
    }

    boolean contains(Animal animal) { return animalList.contains(animal); }

    ArrayList<String> list() {
        ArrayList<String> animalListString = new ArrayList<>();

        ListIterator it =  animalList.listIterator();

        while (it.hasNext())
            animalListString.add(it.next().toString());

        return animalListString;
    }
}
