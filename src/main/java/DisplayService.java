import com.theIronYard.Animal.Animal;
import com.theIronYard.Animal.AnimalBreed;
import com.theIronYard.Animal.AnimalService;
import com.theIronYard.Animal.AnimalType;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by chris on 8/19/16.
 */
public class DisplayService {
    private InputStream in = System.in;
    private OutputStream out = System.out;

    public InputStream getIn() {return in;}
    public void setIn(InputStream in) {this.in = in;}

    public OutputStream getOut() {return out;}
    public void setOut(OutputStream out) {this.out = out;}


    public int promptForMainMenuSelection() {

        // build the menu
        System.out.println("\n\n-- Main Menu --\n" +
                "\n" +
                "1) List animals\n" +
                "2) Create an animal\n" +
                "3) View animal details\n" +
                "4) Edit an animal\n" +
                "5) Delete an animal\n" +
                "6) Quit\n");

        int choice = waitForInt("Please choose an option:");

        //check for valid input
        if ((choice < 1) || (choice > 6)) {
            System.out.println("\n\n\nPlease choose a number between 1 and 6");
            choice = promptForMainMenuSelection();
        }

        return choice;
    }

    // confirm choice to exit application
    public String promptForExit() {
        return waitForYesNo("Are you sure you want to exit? (y/n)");
    }

    // confirm choice to remove animal
    public String promptForRemove(String animalName) {
        return waitForYesNo("Are you sure you want to remove " + animalName + "from the shelter?\" (y/n)");
    }

    private void displayAnimalList (AnimalService animals) throws SQLException {
        ArrayList<Animal> animalList = animals.listAnimals();
        for (Animal animal: animalList) {
            System.out.println(animal.getId() + "\t" + animal.getName());
        }
    }

    void displayAnimals(AnimalService animals) throws SQLException {
        displayAnimalList(animals);
        promptForString("Press <enter> to continue...", false);
    }

    void displayAnimal(AnimalService animals, int index) throws SQLException {
        if (index >= 0) {
            System.out.println(animals.getAnimal(index).toString("v"));
            promptForString("Press <Enter> to continue", false);
        } else {
            System.out.println("No animal to display!");
        }
    }

    // used in multiple places to interact with AnimalService
    int promptForAnimalToView(AnimalService animals, String prompt) throws SQLException {
        displayAnimalList(animals);
        int choice = waitForInt("\n\nPlease enter the index of the animal you want to " + prompt + ". ");
        if (!animals.contains(choice)) {
            System.out.println("That is not a valid animal ID!");
            return -1;
        }
        return choice;
    }

    Animal promptForNewAnimal(ArrayList<AnimalType> types, ArrayList<AnimalBreed> breeds) {
        // name
        String name = promptForString("Name: ", true);

        // type
        String prompt = "(";
        for (AnimalType type: types) {
            prompt = prompt + type.getName() + ", ";
        }
        prompt = prompt + ")";
        String type = "";
        while (!containsType(types, type)) {
            type = promptForString("Type" + prompt + ": ", true);
        }

        // breed
        prompt = "(";
        for (AnimalBreed breed: breeds) {
            prompt = prompt + breed.getName() + ", ";
        }
        prompt = prompt + ")";
        String breed = "";
        while (!containsBreed(breeds, breed)) {
            breed =  promptForString("Breed: ([" + prompt + "]): ", false);
        }

        // color
        String color = promptForString("Color: ", true);

        // description
        String description = promptForString("Description: ", true);

        AnimalType animalType = new AnimalType(type);
        for(AnimalType t : types) {
            if(t.getName().equals(type)) {
                animalType.setId(t.getId());
                break;
            }
        }

        AnimalBreed animalBreed = new AnimalBreed(breed);
        for (AnimalBreed b : breeds) {
            if (b.getName().equals(breed)) {
                animalBreed.setId(b.getId());
                break;
            }
        }
        return new Animal(name, animalType, animalBreed, color, description);
    }

    private boolean containsType(ArrayList<AnimalType> types, String newType) {
        for(AnimalType type : types) {
            if(type != null && type.getName().equals(newType)) {
                return true;
            }
        }
        return false;
    }

    private boolean containsBreed(ArrayList<AnimalBreed> breeds, String newBreed) {
        for(AnimalBreed breed : breeds) {
            if(breed != null && breed.getName().equals(newBreed)) {
                return true;
            }
        }
        return false;
    }

    Animal promptForNewAnimalData(Animal animal, ArrayList<AnimalType> types, ArrayList<AnimalBreed> breeds) {
        // name
        String name = promptForString("Name (" + animal.getName() + "): ", false);
        if (name.equals("")) name = animal.getName();

        // type
        String prompt = "(";
        for (AnimalType type: types) {
            prompt = prompt + type.getName() + ", ";
        }
        prompt = prompt + ")";
        String type = "";

        while (!containsType(types, type)) {
            type = promptForString("Type (" + animal.getType().getName() + "[" + prompt + "]): ", false);
            if (type.equals("")) {
                type = animal.getType().getName();
                break;
            }
        }

        // breed
        prompt = "(";
        for (AnimalBreed breed: breeds) {
            prompt = prompt + breed + ", ";
        }
        prompt = prompt + ")";
        String breed = "";
        while (!containsBreed(breeds, breed)) {
            breed =  promptForString("Breed: (" + animal.getBreed().getName() + "[" + prompt + "]): ", false);
            if (breed.equals("")) {
                breed = animal.getBreed().getName();
                break;
            }
        }

        // color
        String color = promptForString("Color: (" + animal.getColor() + "): ", false);
        if (color.equals("")) color = animal.getColor();

        // description
        String description = promptForString("Description: (" + animal.getDescription() + "): ", false);
        if (description.equals("")) description = animal.getDescription();

        AnimalType animalType = new AnimalType(type);
        for(AnimalType t : types) {
            if(t.getName().equals(type)) {
                animalType.setId(t.getId());
                break;
            }
        }

        AnimalBreed animalBreed = new AnimalBreed(breed);
        for (AnimalBreed b : breeds) {
            if (b.getName().equals(breed)) {
                animalBreed.setId(b.getId());
            }
        }
        return new Animal(animal.getId(), name, animalType, animalBreed, color, description);
    }

    Animal promptForNewNote(Animal animal) {
        String note = promptForString("Please enter a note for " + animal.getName() + ":", false);
        if (!note.isEmpty()) {
            animal.addNote(note);
        }
        return animal;
    }

    private String promptForString(String message, boolean required) {
        System.out.println(message);
        Scanner scanner = new Scanner(in);
        String input = scanner.nextLine();
        input = input.trim();
        if (input.isEmpty() && required) {
            System.out.println("You must enter a value for: " + message);
            promptForString(message, required);
        }
        return input.trim();
    }


    public double promptForWeight(String name) {
        //System.out.printf("%s's weight: ", name);
        return waitForDouble(name + "'s weight: ");
    }

    private int waitForInt(String message) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(message);

        String input = scanner.nextLine();

        int value;
        try {
            value = Integer.parseInt(input);

        } catch (Exception e) {
            System.out.println("\nPlease provide a number.\n");

            value = waitForInt(message);
        }

        return value;
    }

    private double waitForDouble(String message) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(message);

        String input = scanner.nextLine();

        double value;
        try {
            value = Double.parseDouble(input);

        } catch (Exception e) {
            System.out.println("\nPlease provide a number.\n");

            value = waitForInt(message);
        }

        return value;
    }

    private String waitForYesNo(String message) {
        Scanner scanner = new Scanner(System.in);
        String answer;

        System.out.println(message);

        String input = scanner.nextLine();
        if (input.equals("")) {
            answer = "";
        } else {
            answer = input.substring(0, 1);
        }
        if ((answer.equals("y")) || (answer.equals("n"))) {
            return answer;
        } else {
            answer = waitForYesNo("Please enter either \"y\" for yes or \"n\" for no.");
        }

        return answer;
    }

}
