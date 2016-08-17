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

        String name = addMenu.promptForString("Enter the new animal's name:");
        String species = addMenu.promptForString("The new animal's species is: ");
        String breed = addMenu.promptForString("The new animal's breed is:");
        String description = addMenu.promptForString("Please add any descriptive information about the new animal:");
        Animal newAnimal = new Animal(name, species, breed, description);
        animalList.add(newAnimal);
        //newAnimal.medicalRecord.setWeight(addMenu.promptForWeight(name));
        //newAnimal.color = addMenu.promptForString("please enter " + name + "'s color:");
    }

    public void addAnimal(String name, String species, String breed, String description){
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
        //double weight;
        //String color;

        System.out.println("Please answer the following questions. Press enter to keep the current values.\n" +
                "\n");
        name = addMenu.promptForString("Animal name [" + currentAnimal.name + "]:");
        species = addMenu.promptForString("Species [" + currentAnimal.species + "]:");
        breed = addMenu.promptForString("Breed [" + currentAnimal.breed + "]:");
        description = addMenu.promptForString("Description [" + currentAnimal.description + "]:");
        //weight = addMenu.promptForWeight(currentAnimal.name);

        // display edited animal
        System.out.printf("label (Current value) New value\n");
        System.out.printf("Name (%s) %s\n", currentAnimal.name, name);
        System.out.printf("Name (%s) %s\n", currentAnimal.species, species);
        System.out.printf("Name (%s) %s\n", currentAnimal.breed, breed);
        System.out.printf("Name (%s) -->\n\t%s\n", currentAnimal.description, description);
        String answer = addMenu.promptForString("Are these edits correct? (y/n)");
        if (answer.equals("y")) {
            if (!name.equals("")) currentAnimal.name = name;
            if (!species.equals("")) currentAnimal.species = species;
            if (!breed.equals("")) currentAnimal.breed = breed;
            if (!description.equals("")) currentAnimal.description = description;
            System.out.printf("\n\nRecord %d updated to reflect new data.\n", index - 1);
        }
    }

    public void removeAnimal(int index) {
        MenuService menu = new MenuService();
        String name = animalList.get(index).name;

        System.out.println(animalList.get(index).toString());
        String answer = menu.promptForRemove(name);
        if (answer.equals("y")) {
            animalList.remove(index);
            System.out.println("\n" + name + " has been removed from the shelter!");
        }
    }

    public void displayAnimal(int index) {
        System.out.println(animalList.get(index).toString("v"));
        MenuService menu = new MenuService();
        menu.promptForString("\nPress any key to continue...");
    }
}
