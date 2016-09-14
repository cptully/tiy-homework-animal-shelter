import com.theIronYard.Animal.*;

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


    int promptForMainMenuSelection() {

        // build the menu
        System.out.println("\n\n-- Main Menu --\n" +
                "\n" +
                "1) List animals\n" +
                "2) Create an animal\n" +
                "3) View animal details\n" +
                "4) Edit an animal\n" +
                "5) Delete an animal\n" +
                "6) Manage the database\n" +
                "7) Quit\n");

        int choice = waitForInt("Please choose an option:");

        //check for valid input
        if ((choice < 1) || (choice > 7)) {
            System.out.println("\n\n\nPlease choose a number between 1 and 7");
            choice = promptForMainMenuSelection();
        }

        return choice;
    }

    int promptForDbManagementSelection() {
        System.out.println("\n\n------ Database Management menu ------\n" +
                "1) Add an animal type\n" +
                "2) Edit an animal type\n" +
                "3) Delete an animal type\n" +
                "4) Add an animal breed\n" +
                "5) Edit an animal breed\n" +
                "6) Delete an animal breed\n" +
                "7) Return to Main Menu\n");

        int choice = waitForInt("Please choose an option:");

        //check for valid input
        if ((choice < 1) || (choice > 7)) {
            System.out.println("\n\n\nPlease choose a number between 1 and 7");
            choice = promptForDbManagementSelection();
        }

        return choice;
    }

    int promptForSearchMethod() {
        System.out.println("\n\n------ Search menu ------\n" +
                "1) Search by animal name\n" +
                "2) Search by animal type\n" +
                "3) Search by animal breed\n" +
                "4) List all animals\n" +
                "5) Return to Main Menu\n");

        int choice = waitForInt("Please choose an option:");

        //check for valid input
        if ((choice < 1) || (choice > 5)) {
            System.out.println("\n\n\nPlease choose a number between 1 and 5");
            choice = promptForSearchMethod();
        }

        return choice;
    }

    int promptForNoteEdit(){
        int choice = waitForInt("\n1) Create new note   2) Delete note    3) Return to main menu");

        //check for valid input
        if ((choice < 1) || (choice > 3)) {
            System.out.println("\n\n\nPlease choose a number between 1 and 3");
            choice = promptForMainMenuSelection();
        }

        return choice;
    }

    AnimalBreed promptForNewBreed(AnimalService animalService) throws SQLException {
        String breed = promptForString("Enter a name for the new breed:", true);
        displayTypeList(animalService);
        int typeId = waitForInt("\nPlease enter the animal type ID for " + breed + ":");
        return new AnimalBreed(breed, typeId);
    }

    int promptForBreedToEdit(AnimalService animalService) throws SQLException {
        displayBreedList(animalService);
        return waitForInt("\nPlease enter the ID of the breed to be edited:");
    }

    AnimalBreed promptForNewBreedData(AnimalBreed animalBreed, AnimalService animalService) throws SQLException {
        String newBreed = promptForString("Enter a new name for " + animalBreed.getName() + ":", false);
        if (!newBreed.equals("")) animalBreed.setName(newBreed);
        displayTypeList(animalService);
        int newTypeId = waitForInt("\nPlease enter a new animal type ID for "
                + animalBreed.getName() +
                " [" + animalBreed.getTypeId() + "]  (-1 for no change):");
        if (newTypeId >= 0) animalBreed.setTypeId(newTypeId);
        return animalBreed;
    }

    int promptForBreedToDelete(AnimalService animalService) throws SQLException {
        displayBreedList(animalService);

        return waitForInt("Please enter the ID of the breed to be deleted:");
    }

    String promptForNewType() {
        return promptForString("Please enter a name for the new animal type:", true);
    }

    AnimalType promptForAnimalTypeToEdit(AnimalService animalService) throws SQLException {
        displayTypeList(animalService);
        int typeId = waitForInt("Please enter the ID of the animal type to be edited:");
        return animalService.getType(typeId);
    }

    AnimalType promptForNewTypeName(AnimalType animalType) {
        String newType = promptForString("Please enter the new name of the " + animalType.getTypeName() + ":", false);
        if (!newType.equals("")) animalType.setTypeName(newType);
        return animalType;
    }

    int promptForTypeToDelete(AnimalService animalService) throws SQLException {
        displayTypeList(animalService);

        return waitForInt("Please enter the ID of the type to be deleted:");
    }

    void deleteError(String message) {
        System.out.println(message);
        promptForString("Press <enter> to continue...", false);
    }


    private void displayBreedList(AnimalService animalService) throws SQLException {
        ArrayList<AnimalBreed> animalBreeds = animalService.listBreeds();
        for (AnimalBreed animalBreed : animalBreeds) {
            System.out.println(animalBreed.getBreedId() + "\t" + animalBreed.getName());
        }
    }

    private void displayTypeList(AnimalService animalService) throws SQLException {
        ArrayList<AnimalType> animalTypes = animalService.listTypes();
        for (AnimalType animalType : animalTypes) {
            System.out.println(animalType.getTypeId() + "\t" + animalType.getTypeName());
        }
    }

    // confirm choice to exit application
    String promptForExit() {
        return waitForYesNo("Are you sure you want to exit? (y/n)");
    }

    // confirm choice to remove animal
    String promptForRemove(String animalName) {
        return waitForYesNo("Are you sure you want to remove " + animalName + " from the shelter?\" (y/n)");
    }

    private void displayAnimalList (AnimalService animalService) throws SQLException {
        ArrayList<Animal> animalList = animalService.listAnimals();
        for (Animal animal: animalList) {
            System.out.println(animal.getId() + "\t" + animal.getName());
        }
    }

    void displayAnimals(AnimalService animals) throws SQLException {
        displayAnimalList(animals);
        promptForString("Press <enter> to continue...", false);
    }

    void displayAnimal(AnimalService animals, int id) throws SQLException {
        if (animals.contains(id)) {
            Animal animal = animals.getAnimal(id);
            System.out.println(animal.toString("v"));
            if (animal.getNotes().size() > 0) {
                System.out.println("\n--Notes");
                System.out.println("Id\tDate\t\t\t\t\t\tNote");
                for (Note note : animal.getNotes()) {
                    System.out.println(note.toString());
                }
            }
        } else {
            System.out.println("No animal to display!");
        }
        //return -1;
    }

    int promptForNoteToDelete(Animal animal) {
        int choice = -1;

        while (!containsNote(animal.getNotes(), choice)) {
            choice = waitForInt("Please enter the ID of the note to delete. (type -1 to cancel)");
            if (choice == -1) break;
        }

        return choice;
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
            prompt = prompt + type.getTypeName() + ", ";
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
            if(t.getTypeName().equals(type)) {
                animalType.setTypeId(t.getTypeId());
                break;
            }
        }

        AnimalBreed animalBreed = new AnimalBreed(breed);
        for (AnimalBreed b : breeds) {
            if (b.getName().equals(breed)) {
                animalBreed.setBreedId(b.getBreedId());
                break;
            }
        }
        return new Animal(name, animalType, animalBreed, color, description);
    }

    private boolean containsType(ArrayList<AnimalType> types, String newType) {
        for(AnimalType type : types) {
            if(type != null && type.getTypeName().equals(newType)) {
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

    private boolean containsNote(ArrayList<Note> notes, int id) {
        for (Note note : notes) {
            if (note != null && note.getId() == id) {
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
            prompt = prompt + type.getTypeName() + ", ";
        }
        prompt = prompt + ")";
        String type = "";

        while (!containsType(types, type)) {
            type = promptForString("Type (" + animal.getType().getTypeName() + "[" + prompt + "]): ", false);
            if (type.equals("")) {
                type = animal.getType().getTypeName();
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
            if(t.getTypeName().equals(type)) {
                animalType.setTypeId(t.getTypeId());
                break;
            }
        }

        AnimalBreed animalBreed = new AnimalBreed(breed);
        for (AnimalBreed b : breeds) {
            if (b.getName().equals(breed)) {
                animalBreed.setBreedId(b.getBreedId());
            }
        }
        return new Animal(animal.getId(), name, animalType, animalBreed, color, description);
    }

    Animal promptForNewNote(AnimalService animalService, Animal animal) throws SQLException {
        String note = promptForString("Please enter a note for " + animal.getName() + ":", false);
        if (!note.isEmpty()) {
            animalService.addNote(animal.getId(), note);
        }
        return animalService.getAnimal(animal.getId());
    }

    String promptForNameToSearch() {
        return promptForString("Enter the name of the animal:", true);
    }

    int promptForTypeToSearch(AnimalService animalService) throws SQLException {
        displayTypeList(animalService);
        return waitForInt("\nPlease enter the ID of the type to be searched:");
    }

    int promptForBreedToSearch(AnimalService animalService) throws SQLException {
        displayBreedList(animalService);
        return waitForInt("\nplease entre the ID of the breed to be searched");
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
