import java.util.ArrayList;

/**
 * Created by chris on 8/12/16.
 */
public class AnimalsService {
    private ArrayList<Animal> animalList = new ArrayList<>(10);

    public void listAnimals() {
        MenuService listMenu = new MenuService();

        for (int i = 0; i < animalList.size(); i++) {
            System.out.println((i+1) + ", " + animalList.get(i).toString());
        }
        listMenu.promptForString("Press <enter> to return to the main menu.");
    }

    public Animal getAnimal(int index) {
        return animalList.get(index);
    }

    public void addAnimal(){
        MenuService addMenu = new MenuService();
        String name;
        String species;
        String breed;
        String description;

        name = addMenu.promptForString("Enter the new animal's name:");
        species = addMenu.promptForString("The new animal's species is: ");
        breed = addMenu.promptForString("The new animal's breed is:");
        description = addMenu.promptForString("Please add any descriptive information about the new animal:");
        Animal newAnimal = new Animal(name, species, breed, description);
        animalList.add(newAnimal);
    }

    public void editAnimal(int index){
        MenuService addMenu = new MenuService();
        Animal currentAnimal = animalList.get(index);

        String name;
        String species;
        String breed;
        String description;

        System.out.println("Please answer the following questions. Press enter to keep the current values.\n" +
                "\n");
        name = addMenu.promptForString("Animal name [" + currentAnimal.name + "]:");
        if (!name.equals("")) currentAnimal.name = name;
        species = addMenu.promptForString("Species [" + currentAnimal.species + "]:");
        if (!species.equals("")) currentAnimal.species = species;
        breed = addMenu.promptForString("Breed [" + currentAnimal.breed + "]:");
        if (!breed.equals("")) currentAnimal.breed = breed;
        description = addMenu.promptForString("Description [" + currentAnimal.description + "]:");
        if (!description.equals("")) currentAnimal.description = description;
    }

    public void removeAnimal(int index) {
        MenuService menu = new MenuService();
        String name = animalList.get(index).name;

        System.out.println(animalList.get(index).toString());
        String answer = menu.promptForRemove(name);
        if (answer.equals("y")) {
            animalList.remove(index);
            System.out.println(name + " has been removed from the shelter!");
        }
    }
}
