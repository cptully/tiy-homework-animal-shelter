import java.util.Scanner;

/**
 * Created by chris on 8/12/16.
 */
public class MenuService {
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

    // used in multiple places to interact with AnimalService
    public int promptForAnimalToView(String prompt) {
        return waitForInt("\n\nPlease enter the index of the animal you want to " + prompt + ". " +
                          "\n(enter 0 to see a list of all animals)");
    }

    public String promptForString(String message) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);

        System.out.println(message);

        String input = scanner.nextLine();
        return input.trim();
    }

    private int waitForInt(String message) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(message);

        String input = scanner.nextLine();

        int value;
        try {
            value = Integer.parseInt(input);

        } catch(Exception e){
            System.out.println("\nPlease provide a number.\n");

            value = waitForInt(message);
        }

        return value;
    }

    private String waitForYesNo(String message) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(message);

        String input = scanner.nextLine();
        String answer = input.substring(0, 1);
        if ((answer.equals("y")) || (answer.equals("n"))) {
            return answer;
        } else {
            answer = waitForYesNo("Please enter either \"y\" for yes or n for no.");
        }

        return answer;
    }


}
