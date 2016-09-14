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
            AnimalService animalService = new AnimalService(animalRepository,
                    animalBreedRepository,
                    animalTypeRepository,
                    noteRepository);

            MenuService menuService = new MenuService(display, animalService);

            int choice;
            int subChoice;
            boolean running = true;

            while (running) {

                choice = menuService.showMenu();
                switch (choice) {
                    // TODO: 9/13/16 implement search
                    case 1:             //1) List animalService
                        menuService.listAnimals();
                        break;
                    case 8:
                        subChoice = menuService.searchDB();
                        while (subChoice != 5) {
                            switch (subChoice) {
                                case 1:
                                    String name = menuService.searchByName(animalService);
                                    break;
                                case 2:
                                    int typeId = menuService.searchByType(animalService);
                                    break;
                                case 3:
                                    int breedId = menuService.searchByBreed(animalService);
                                    break;
                                case 4:
                                    menuService.listAnimals();
                                    break;
                            }
                        }
                        break;
                    case 2:             //2) Create an animal
                        // TODO: 9/13/16 allow breed to be blank
                        menuService.addAnimal();
                        break;
                    case 3:             //3) View animal details
                        menuService.viewAnimalDetails();
                        break;
                    case 4:             //4) Edit an animal
                        // TODO: 9/13/16 add notes to edit animal
                        menuService.editAnimal();
                        break;
                    case 5:             //5) Delete an animal
                        menuService.deleteAnimal();
                        break;
                    case 6:             //6) Manage DB
                        subChoice = menuService.manageDB();
                        while (subChoice != 7) {
                            switch (subChoice) {
                                case 1:     //1) add type
                                    menuService.addType();
                                    break;
                                case 2:     //2) edit type
                                    menuService.editType();
                                    break;
                                case 3:     //3) delete type
                                    menuService.deleteType();
                                    break;
                                case 4:     //3) add breed
                                    menuService.addBreed();
                                    break;
                                case 5:     //5) edit breed
                                    menuService.editBreed();
                                    break;
                                case 6:     //6) delete breed
                                    menuService.deleteBreed();
                                    break;
                                default:
                                    break;
                            }
                            subChoice = menuService.manageDB();
                        }
                        break;
                    case 7:             //7) quit
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
