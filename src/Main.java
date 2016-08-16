/**
 * Created by chris on 8/12/16.
 */
public class Main {
    public static void main(String [] args) {
        MenuService menu = new MenuService();
        AnimalsService animals = new AnimalsService();

        int choice = 0;
        int subChoice = 0;

        do {
            choice = menu.promptForMainMenuSelection();
            switch (choice) {
                case 1:             //1) List animals
                    animals.listAnimals();
                    break;
                case 2:             //2) Create an animal
                    animals.addAnimal();
                    break;
                case 3:             //3) View animal details
                    subChoice = menu.promptForAnimalToView("view");
                    if (subChoice == 0) {
                        animals.listAnimals();
                    } else {
                        System.out.println(animals.getAnimal(subChoice).toString("v"));
                    }
                    break;
                case 4:             //4) Edit an animal
                    subChoice = menu.promptForAnimalToView("edit");
                    if (subChoice == 0) {
                        animals.listAnimals();
                    } else {
                        animals.editAnimal(subChoice);
                    }
                    break;
                case 5:             //5) Delete an animal
                    subChoice = menu.promptForAnimalToView("delete");
                    if (subChoice == 0) {
                        animals.listAnimals();
                    } else {
                        animals.removeAnimal(subChoice);
                    }
                    break;
                case 6:             //6) Quit
                    menu.promptForExit();
                    System.out.println("Goodbye...");
                    break;
                default:
                    break;
            }
        } while (choice != 6);
    }
}
