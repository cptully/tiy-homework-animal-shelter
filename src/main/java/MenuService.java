import com.theIronYard.Animal.*;

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

    int manageDB() {
        return displayService.promptForDbManagementSelection();
    }

    int searchDB() {
        return displayService.promptForSearchMethod();
    }

    void addAnimal() throws SQLException {
        ArrayList<AnimalType> types = animalService.getValidAnimalTypes();
        ArrayList<AnimalBreed> breeds = animalService.getValidAnimalBreeds();
        Animal newAnimal = displayService.promptForNewAnimal(types, breeds);
        animalService.addAnimal(newAnimal);
    }

    void listAnimals() throws SQLException {
        displayService.displayAnimals(animalService);
    }

    void viewAnimalDetails() throws SQLException {
        int animalId;
        animalId = displayService.promptForAnimalToView(animalService, "view");
        Animal animal = animalService.getAnimal(animalId);

        displayService.displayAnimal(animalService, animalId);
        int noteChoice = displayService.promptForNoteEdit();
        switch (noteChoice) {
            case 1:         // add note
                displayService.promptForNewNote(animalService, animal);
                break;
            case 2:         // delete note
                int deleteId = displayService.promptForNoteToDelete(animal);
                if (deleteId != 0) {
                    animalService.removeNote(animalId, deleteId);
                }
                break;
            default:        // return to main menu
                break;
        }
    }

    void editAnimal() throws SQLException {
        int choice = displayService.promptForAnimalToView(animalService, "edit");
        ArrayList<AnimalType> types = animalService.getValidAnimalTypes();
        ArrayList<AnimalBreed> breeds = animalService.getValidAnimalBreeds();
        Animal newData = displayService.promptForNewAnimalData(animalService.getAnimal(choice), types, breeds);
        animalService.editAnimal(newData);
    }

    void deleteAnimal() throws SQLException {
        int choice;

        choice = displayService.promptForAnimalToView(animalService, "delete");
        if (animalService.contains(choice)) {
            if (displayService.promptForRemove(animalService.getAnimal(choice).getName()).equals("y")) {
                animalService.removeAnimal(choice);
            }
        }
    }

    void addBreed() throws SQLException {
        AnimalBreed breed = displayService.promptForNewBreed(animalService);
        animalService.addBreed(breed);
    }

    void editBreed() throws SQLException {
        int breedId = displayService.promptForBreedToEdit(animalService);
        AnimalBreed animalBreed = animalService.getBreed(breedId);
        animalBreed = displayService.promptForNewBreedData(animalBreed, animalService);
        animalService.editBreed(animalBreed);
    }

    // TODO: 9/13/16 add a way to delete a breed that is in use
    void deleteBreed() throws SQLException {
        int breedId = displayService.promptForBreedToDelete(animalService);
        int count = animalService.deleteBreed(breedId, false);
        if (count != 0) {
            AnimalBreed animalBreed = animalService.getBreed(breedId);
            displayService.deleteError("The " + animalBreed.getName() + " breed cannot be deleted because it " +
                    "is used by " + count + " animals.");
        }
    }

    void addType() throws SQLException {
        AnimalType animalType = new AnimalType(displayService.promptForNewType());
        animalService.addType(animalType);
    }

    void editType() throws SQLException {
        AnimalType animalType = displayService.promptForAnimalTypeToEdit(animalService);
        AnimalType newType = displayService.promptForNewTypeName(animalType);
        newType.setTypeId(animalType.getTypeId());
        animalService.editType(newType);
    }

    // TODO: 9/13/16 add a away to delete a type that is in use
    void deleteType() throws SQLException {
        int typeId = displayService.promptForTypeToDelete(animalService);
        int count = animalService.deleteType(typeId);
        if (count != 0) {
            AnimalType animalType = animalService.getType(typeId);
            displayService.deleteError("The " + animalType.getTypeName() + " type cannot be deleted because" +
                    " it is used by " + count + "animals.");
        }
    }

    public String searchByName(AnimalService animalService) throws SQLException {
        String name = displayService.promptForNameToSearch();
        return animalService.searchByName(name);
    }

    public int searchByType(AnimalService animalService) throws SQLException {
        int typeId = displayService.promptForTypeToSearch(animalService);
        return animalService.searchByType(typeId);
    }

    public int searchByBreed(AnimalService animalService) throws SQLException {
        int breedId = displayService.promptForBreedToSearch();
        return animalService.searchByBreed(breedId);
    }
}