package com.theIronYard.Animal;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Created by chris on 8/20/16.
 */
public class AnimalRepository {
    // properties
    private ArrayList<Animal> animalList = new ArrayList<>(10);
    private Path path = Paths.get("./animalDatabase.json");

    private static final boolean USE_DEMO_DATA = false;

    // constructor
    public AnimalRepository() {

        //load test data
        if (USE_DEMO_DATA) {
            //AnimalService animals = new AnimalService();
            animalList.add(new Animal("Shadow", "dog", "border collie", "black and white", "energetic and friendly; liked to chase balls"));
            animalList.add(new Animal("Mia", "cat", "domestic short hair", "calico", "skittish"));
            animalList.add(new Animal("Rags", "cat", "domestic short hair", "black and white", "likes to hunt"));
        } else {
            readDB();
        }
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        if ((path != null) && (!path.toString().equals(""))) this.path = path;
    }

    // methods used by other Animal Service
    Animal get(int index) {
        return animalList.get(index);
    }

    boolean add(Animal animal) {
        boolean newAnimal =  animalList.add(animal);
        writeDB();
        return newAnimal;
    }

    int size() { return animalList.size(); }

    Animal remove(int index) {
        if ((index >= 0) && (index < animalList.size())) {
            Animal newAnimal = animalList.remove(index);
            writeDB();
            return newAnimal;
        }
        return new Animal();
    }

    boolean contains(int index) {
        if ((index > 0) && (index < animalList.size())) {
            return animalList.contains(animalList.get(index));
        }
        return false;
    }

    boolean contains(Animal animal) { return animalList.contains(animal); }

//    int size() {return animalList.size();}

    ArrayList<String> list() {
        ArrayList<String> animalListString = new ArrayList<>();

        ListIterator it =  animalList.listIterator();

        while (it.hasNext())
            animalListString.add(it.next().toString());

        return animalListString;
    }

    public void editAnimal(int index, String name, String species, String breed, String color, String description) {
        Animal currentAnimal = animalList.get(index);

        //currentAnimal.setName(name);
        currentAnimal.setSpecies(species);
        currentAnimal.setBreed(breed);
        currentAnimal.setColor(color);
        currentAnimal.setDescription(description);

        writeDB();
    }

    public void editAnimal(Animal animal, int index) {
        // if (! animal.name.isEmpty()) {animalList.get(index).name = animal.name;}
        if (! animal.getSpecies().isEmpty()) {animalList.get(index).setSpecies(animal.getSpecies());}
        if (! animal.getBreed().isEmpty()) {animalList.get(index).setBreed(animal.getBreed());}
        if (! animal.getColor().isEmpty()) {animalList.get(index).setColor(animal.getColor());}
        if (! animal.getDescription().isEmpty()) {animalList.get(index).setDescription(animal.getDescription());}

        writeDB();
    }

    // persistence methods
    private void writeDB(){
        // Write out the animalList as a json file
        ArrayList<String> json = new ArrayList<>();
        json.add(new Gson().toJson(animalList));
        try {
            Files.write(path, json);
        } catch(IOException ex) {
            System.out.println("error writing file");
        }
    }

    private void readDB() {
        try {
            String json = new String(Files.readAllBytes(path));

            // this describes the structure of data in the JSON string. Note the generics
            Type listType = new TypeToken<ArrayList<Animal>>(){}.getType();

            // convert the JSON back to a map of arrays of recipes
            animalList = new Gson().fromJson(json, listType);

        } catch(IOException ex) {
            System.out.println("error writing file");
        }

    }
}
