import com.theIronYard.Animal.AnimalsService;
import com.theIronYard.Animal.Animal;

import java.util.ArrayList;

/**
 * Created by chris on 8/12/16.
 */


public class MenuService {
    private DisplayService display = new DisplayService();
    private AnimalsService animals = new AnimalsService();



    public void addAnimal(){
        Animal newAnimal = display.promptForNewAnimal();
        animals.addAnimal(newAnimal);
    }

    public void listAnimals(){
        display.displayAnimals(animals);
    }

    public void viewAnimalDetails() {
        int choice;
        choice = display.promptForAnimalToView(animals, "view");
        display.displayAnimal(animals, choice);
    }

    public void editAnimal() {
        int choice = display.promptForAnimalToView(animals, "edit");
        Animal newData = display.promptForNewAnimalData(animals.getAnimal(choice));
        animals.editAnimal(newData, choice);
    }

    public void deleteAnimal() {
        int choice;

        choice = display.promptForAnimalToView(animals, "delete");
        if (animals.contains(choice))
        animals.removeAnimal(choice);
    }
}