import com.theIronYard.Animal.*;
//import com.theIronYard.Animal.Note;
//import com.theIronYard.Animal.NoteRepository;
//import com.theIronYard.Animal.Notes;

import java.sql.SQLException;

/**
 * Created by chris on 8/12/16.
 */
public class Main {

    public static void main(String [] args) {
        DisplayService display = new DisplayService();
        try {
            String jdbcUrl = "jdbc:postgresql://localhost/animalrepository_test";
            AnimalRepository animalRepository = new AnimalRepository(jdbcUrl);
            AnimalBreedRepository animalBreedRepository = new AnimalBreedRepository(jdbcUrl);
            AnimalTypeRepository animalTypeRepository = new AnimalTypeRepository(jdbcUrl);
            NoteRepository noteRepository = new NoteRepository(jdbcUrl);
            AnimalService animals = new AnimalService(animalRepository,
                    animalBreedRepository,
                    animalTypeRepository,
                    noteRepository);

            MenuService menu = new MenuService(display, animals);

            int choice;
            boolean running = true;

            while (running) {

                choice = menu.showMenu();
                switch (choice) {
                    case 1:             //1) List animals
                        menu.listAnimals();
                        // manage
                        // subchoice = submenu()
                        // switch (subchoice)
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
        } catch (SQLException e){
            System.out.println("Error connecting to database!");
            e.printStackTrace();
        }

    }
}
