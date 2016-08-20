import com.theIronYard.Animal.AnimalService;
import com.theIronYard.Animal.Animal;

/**
 * Created by chris on 8/12/16.
 */


public class MenuService {
    private DisplayService display = new DisplayService();
    private AnimalService animals = new AnimalService();



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