import com.theIronYard.Animal.Animal;
import com.theIronYard.Animal.AnimalsService;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by chris on 8/19/16.
 */
public class DisplayService {

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
        if ((choice < 1) && (choice > 6)) {
            System.out.println("\n\n\n\n\n\n\nPlease choose a number between 1 and 6");
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

    private void displayAnimalList (AnimalsService animals) {
        ArrayList<String> animalList = animals.listAnimals();
        int i = 1;
        for (String animal: animalList) {
            System.out.println(i++ + "\t" + animal);
        }
    }

    public void displayAnimals(AnimalsService animals){
        displayAnimalList(animals);
        promptForString("Press <enter> to continue...");
    }

    // used in multiple places to interact with AnimalService
    public int promptForAnimalToView(AnimalsService animals,  String prompt) {
        displayAnimalList(animals);
        int choice = waitForInt("\n\nPlease enter the index of the animal you want to " + prompt + ". ");
        return --choice;
    }

    public Animal promptForNewAnimal() {
        String name = promptForString("Name: ");
        String species = promptForString("Species: ");
        String breed = promptForString("Breed: ");
        String color = promptForString("Color: ");
        String description = promptForString("Description: ");

        return new Animal(name, species, breed, color, description);
    }

    public Animal promptForNewAnimalData(Animal animal) {
        String name = promptForString("Name (" + animal.getName() + "): ");
        String species = promptForString("Species (" + animal.getSpecies() + "): ");
        String breed = promptForString("Breed: (" + animal.getBreed() + "): ");
        String color = promptForString("Color: (" + animal.getColor() + "): ");
        String description = promptForString("Description: (" + animal.getDescription() + "): ");

        return new Animal(name, species, breed, color, description);
    }

    public String promptForString(String message) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
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

    public void displayAnimal(AnimalsService animals, int index) {
        System.out.println(animals.getAnimal(index).toString("v"));
        promptForString("Press <Enter> to continue");
    }
}
