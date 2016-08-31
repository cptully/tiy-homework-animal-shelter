package com.theIronYard.Animal;


/**
 * Created by chris on 8/12/16.
 */
public class Animal {
    //private String id;
    private String name;
    private String species;
    private String breed;
    private String description;
    private String color;

    /**
     * Animal constructor which creates a new animal with the specified properties.
     *
     * @param name String
     * @param species String
     * @param breed String
     * @param color String
     * @param description String
     */
    public Animal(String name, String species, String breed, String color, String description) {
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.color = color;
        this.description = description;
    }

    /**
     * Animal no arguments constructor creates a blank animal.
     */
    public Animal() {
        this.name = "";
        this.species = "";
        this.breed = "";
        this.color = "";
        this.description = "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Animal animal = (Animal) o;

        if (!getName().equals(animal.getName())) return false;
        if (!getSpecies().equals(animal.getSpecies())) return false;
        if (!getBreed().equals(animal.getBreed())) return false;
        if (!getDescription().equals(animal.getDescription())) return false;
        return getColor().equals(animal.getColor());

    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getSpecies().hashCode();
        result = 31 * result + getBreed().hashCode();
        result = 31 * result + getDescription().hashCode();
        result = 31 * result + getColor().hashCode();
        return result;
    }

    public String getName(){ return this.name; }
    public String getSpecies(){ return this.species; }
    public String getBreed(){ return this.breed; }
    public String getDescription(){ return this.description; }
    public String getColor(){ return this.color; }

    public void setName(String name){ this.name = name; }
    public void setSpecies(String species){ this.species = species; }
    public void setBreed(String breed){ this.breed = breed; }
    public void setDescription(String description){ this.description = description; }
    public void setColor(String color){ this.color = color; }


    @Override
    /**
     * Overrides the toString method and provides a simple one line string
     * representation od the Animal.
     */
    public String toString() {
       return this.name + ", " + this.species + ", " + this.breed + ", " + this.color;
    }

    /**
     * toString(verbose) Overloads the toString method with an option to
     * display the full details of the Animal. Including the description.
     *
     * @param verbose String, ignored, but it's presence triggers this
     *                version of the toString method.
     * @return String
     */
    public String toString(String verbose){
        return "Name:\t\t\t" + this.name + "\nSpecies:\t\t" + this.species +
                "\nBreed:\t\t\t" + this.breed + "\nColor:\t\t\t" + this.color + "\nDescription:\t" + this.description;
    }
}
