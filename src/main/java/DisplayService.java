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
        if (!animals.contains(--choice)) {
            System.out.println("That is not a valid animal ID!");
            return -1;
        }
        return --choice;
    }

    Animal promptForNewAnimal(ArrayList<String> types, ArrayList<String> breeds) {
        String name = promptForString("Name: ", true);
        String prompt = "(";
        for (String type: types) {
            prompt = prompt + type + ", ";
        }
        prompt = prompt + ")";
        String type = promptForString("Type" + prompt + ": ", true);
        prompt = "(";
        for (String breed: breeds) {
            prompt = prompt + breed + ", ";
        }
        prompt = prompt + ")";
        String breed = promptForString("Breed" + prompt + ": ", true);
        String color = promptForString("Color: ", true);
        String description = promptForString("Description: ", true);

        AnimalType animalType = null;
        if (!types.contains(type)) {
            types.add(type);
        }
        animalType = new AnimalType(type);
        AnimalBreed animalBreed = null;
        if (!breeds.contains(breed)) {
            breeds.add(breed);
        }
        animalBreed = new AnimalBreed(breed);
        return new Animal(name, animalType, animalBreed, color, description);
    }

    Animal promptForNewAnimalData(Animal animal, ArrayList<String> types, ArrayList<String> breeds) {
        String name = promptForString("Name (" + animal.getName() + "): ", false);
        String prompt = "(";
        for (String type: types) {
            prompt = prompt + type + ", ";
        }
        prompt = prompt + ")";
        String type = promptForString("Type (" + animal.getType() + "[" + prompt + "]): ", false);
        prompt = "(";
        for (String breed: breeds) {
            prompt = prompt + breed + ", ";
        }
        prompt = prompt + ")";
        String breed = promptForString("Breed: (" + animal.getBreed() + "[" + prompt + "]): ", false);
        String color = promptForString("Color: (" + animal.getColor() + "): ", false);
        String description = promptForString("Description: (" + animal.getDescription() + "): ", false);

        AnimalType animalType = null;
        if (types.contains(type)) {animalType = new AnimalType(-1, type);}
        AnimalBreed animalBreed = null;
        if (breeds.contains(breed)) {animalBreed = new AnimalBreed(-1, breed);}
        return new Animal(name, animalType, animalBreed, color, description);
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
