import java.util.HashMap;

/**
 * Created by chris on 8/12/16.
 */
public class Animal {
    //private String id;
    public String name;
    public String species;
    public String breed;
    public String description;
    public String color;
    //MedicalRecord medicalRecord = new MedicalRecord();

    public Animal(String name, String species, String breed, String description) {
        this.name = name;
        //this.medicalRecord.name = name;
        this.species = species;
        this.breed = breed;
        this.description = description;
    }

    /*
    public String getId() {
        return this.id;
    }

    protected void setId(String id) {
        this.id = id;
    }
    */

    @Override
    public String toString() {
       return this.name + ", " + this.species + ", " + this.breed;
    }

    public String toString(String verbose){
        return "Name:\t\t\t" + this.name + "\nSpecies:\t\t" + this.species +
                "\nBreed:\t\t\t" + this.breed + "\nDescription:\t" + this.description;
    }
}
