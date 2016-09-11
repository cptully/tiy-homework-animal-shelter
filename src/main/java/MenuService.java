import com.theIronYard.Animal.AnimalService;
import com.theIronYard.Animal.Animal;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by chris on 8/12/16.
 */


class MenuService {
    private DisplayService displayService; // = new DisplayService();
    //private AnimalRepository dataStore; // = new AnimalRepository();
    private AnimalService animalService; // = new AnimalService(dataStore);

    /**
     * The MenuService constructor requires both DisplayService and
     * AnimalService parameters that it uses in coordinating the
     * menu selected activities.
     *
     * @param displayService DisplayService object for interfacing to the displayService
     * @param animalService AnimalService object for interfacing to the Animal Repository
     */
    public MenuService(DisplayService displayService, AnimalService animalService) {
        this.displayService = displayService;
        this.animalService = animalService;
    }

    /**
     * Calls the main menu displayService function and returns the user's choice.
     *
     * @return int value representing the user's choice
     */
    int showMenu() {
        return displayService.promptForMainMenuSelection();
    }

    void addAnimal() throws SQLException {
        ArrayList<String> types = animalService.getValidAnimalTypes();
        ArrayList<String> breeds = animalService.getValidAnimalBreeds();
        Animal newAnimal = displayService.promptForNewAnimal(types, breeds);
        animalService.addAnimal(newAnimal);
    }

    void listAnimals() throws SQLException {
        displayService.displayAnimals(animalService);
    }

    void viewAnimalDetails() throws SQLException {
        int choice;
        choice = displayService.promptForAnimalToView(animalService, "view");
        displayService.displayAnimal(animalService, choice);
    }

    void editAnimal() throws SQLException {
        int choice = displayService.promptForAnimalToView(animalService, "edit");
        ArrayList<String> types = animalService.getValidAnimalTypes();
        ArrayList<String> breeds = animalService.getValidAnimalBreeds();
        Animal newData = displayService.promptForNewAnimalData(animalService.getAnimal(choice), types, breeds);
        animalService.editAnimal(newData);
    }

    void deleteAnimal() throws SQLException {
        int choice;

        choice = displayService.promptForAnimalToView(animalService, "delete");
        if (animalService.contains(choice))
        animalService.removeAnimal(choice);
    }
}