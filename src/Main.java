/**
 * Created by chris on 8/12/16.
 */
public class Main {

    public static void main(String [] args) {
        MenuService menu = new MenuService();
        int choice;
        boolean running = true;

        while (running) {

            choice = menu.showMenu();
            switch (choice) {
                case 1:             //1) List animals
                    menu.listAnimals();
                    break;
                case 2:             //2) Create an animal
                    menu.addAnimal();
                    break;
                case 3:             //3) View animal details
                    menu.viewAnimalDetails();
                    break;
                case 4:             //4) Edit an animal
                    menu.editAnimal();
                    break;
                case 5:             //5) Delete an animal
                    menu.deleteAnimal();
                    break;
                case 6:             //6) Quit
                    running = false;
                    break;
                default:
                    break;
            }
        }
    }
}
