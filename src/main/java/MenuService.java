import com.theIronYard.Animal.AnimalRepository;
import com.theIronYard.Animal.AnimalService;
import com.theIronYard.Animal.Animal;

import java.nio.file.Paths;

/**
 * Created by chris on 8/12/16.
 */


class MenuService {
    private DisplayService display; // = new DisplayService();
    //private AnimalRepository dataStore; // = new AnimalRepository();
    private AnimalService animals; // = new AnimalService(dataStore);

    /**
     * The MenuService constructor requires both DisplayService and
     * AnimalService parameters that it uses in coordinating the
     * menu selected activities.
     *
     * @param display DisplayService object for interfacing to the display
     * @param animals AnimalService object for interfacing to the Animal Repository
     */
    public MenuService(DisplayService display, AnimalService animals) {
        this.display = display;
        this.animals = animals;
    }

    /**
     * Calls the main menu display function and returns the user's choice.
     *
     * @return int value representing the user's choice
     */
    int showMenu() {
        return display.promptForMainMenuSelection();
    }

    void addAnimal() {
        Animal newAnimal = display.promptForNewAnimal();
        animals.addAnimal(newAnimal);
    }

    void listAnimals(){
        display.displayAnimals(animals);
    }

    void viewAnimalDetails() {
        int choice;
        choice = display.promptForAnimalToView(animals, "view");
        display.displayAnimal(animals, choice);
    }

    void editAnimal() {
        int choice = display.promptForAnimalToView(animals, "edit");
        Animal newData = display.promptForNewAnimalData(animals.getAnimal(choice));
        animals.editAnimal(newData, choice);
    }

    void deleteAnimal() {
        int choice;

        choice = display.promptForAnimalToView(animals, "delete");
        if (animals.contains(choice))
        animals.removeAnimal(choice);
    }
}