package com.theIronYard.Animal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by chris on 8/12/16.
 */
public class AnimalService {
    // properties
    private AnimalRepository animalRepository;
    private AnimalBreedRepository animalBreedRepository;
    private AnimalTypeRepository animalTypeRepository;
    private NoteRepository noteRepository;

    /**
     * The AnimalService class constructor requires an AnimalRepository object
     *
     * @param animalRepository an AnimalRepository object
     */
    public AnimalService(AnimalRepository animalRepository,
                         AnimalBreedRepository animalBreedRepository,
                         AnimalTypeRepository animalTypeRepository,
                         NoteRepository noteRepository) {
        this.animalRepository = animalRepository;
        this.animalBreedRepository = animalBreedRepository;
        this.animalTypeRepository = animalTypeRepository;
        this.noteRepository = noteRepository;
    }


    public ArrayList<String> getValidAnimalTypes() throws SQLException {
        ResultSet resultSet = animalTypeRepository.getAnimalTypes();

        ArrayList<String> types = new ArrayList<>();
        while (resultSet.next()) {
            types.add(resultSet.getString("type"));
        }

        return types;
    }

    public ArrayList<String> getValidAnimalBreeds() throws SQLException {
        ResultSet resultSet = animalBreedRepository.getAnimalBreeds();

        ArrayList<String> breeds = new ArrayList<>();
        while (resultSet.next()) {
            breeds.add(resultSet.getString("breed"));
        }
        return breeds;
    }

    /**
     * listAnimals
     *
     * @return ArrayList&lt;String&gt; of string representations of animals
     */
    public ArrayList<Animal> listAnimals() throws SQLException {
        ResultSet resultSet = animalRepository.list();

        ArrayList<Animal> animals = new ArrayList<>();
        while (resultSet.next()){
            AnimalType type = new AnimalType(resultSet.getInt("typeid"), resultSet.getString("type"));
            AnimalBreed breed = new AnimalBreed(resultSet.getInt("breedid"), resultSet.getString("breed"));
            Animal animal = new Animal(
                    resultSet.getString("name"),
                    type,
                    breed,
                    resultSet.getString("color"),
                    resultSet.getString("description"));
            animal.setId(resultSet.getInt("id"));
            ArrayList<Note> notes = listNotes(resultSet.getInt("id"));
            animal.setNotes(notes);
            animals.add(animal);
        }

        return animals;
    }

    public ArrayList<Note> listNotes(int id) throws SQLException {
        ArrayList<Note> notes = new ArrayList<>();
        ResultSet animalNotes = noteRepository.getAnimalNotes(id);
        while (animalNotes.next()){
            Note note = new Note(animalNotes.getInt("noteid"),
                    animalNotes.getString("note"),
                    animalNotes.getTimestamp("date").toLocalDateTime());
            notes.add(note);
        }
        return notes;
    }

    public void addNote(int id, String note) throws SQLException {
        noteRepository.addNote(id, note);
    }

    /**
     * getAnimal gets the animal object for the submitted integer index
     * @param id - the object to be returned
     * @return an Animal object
     */
    public Animal getAnimal(int id) throws SQLException {
        Animal newAnimal = new Animal();

        ResultSet resultSet = animalRepository.get(id);

        while (resultSet.next()) {
            newAnimal.setId(resultSet.getInt("id"));
            newAnimal.setName(resultSet.getString("name"));
            AnimalType newType = new AnimalType(resultSet.getInt("typeid"),
                                                resultSet.getString("type"));
            newAnimal.setType(newType);
            AnimalBreed newBreed = new AnimalBreed(resultSet.getInt("breedid"),
                                                   resultSet.getString("breed"));
            newAnimal.setBreed(newBreed);
            newAnimal.setColor(resultSet.getString("color"));
            newAnimal.setDescription(resultSet.getString("description"));
        }

        ArrayList<Note> notes = listNotes(id);
        newAnimal.setNotes(notes);
        return newAnimal;
    }

    /**
     * addAnimal adds the supplied Animal to the AnimalRepository
     *
     * @param newAnimal Animal to be added
     */
    public void addAnimal(Animal newAnimal) throws SQLException {
        animalRepository.add(newAnimal);
    }

    /**
     * editAnimal passes the edited animal to AnimalRepository
     *
     * @param animal the edited animal
     */
    public void editAnimal(Animal animal) throws SQLException {
        animalRepository.editAnimal(animal);
    }

    /**
     * removeAnimal removes the selected animal from the repository
     *
     * @param id the zero based index of the animal to remove
     */
    public void removeAnimal(int id) throws SQLException {
        animalRepository.remove(id);}

    public boolean contains(Animal animal) throws SQLException { return animalRepository.contains(animal.getId()); }

    public boolean contains(int index) throws SQLException {
        return animalRepository.contains(index);
    }

    public int size() throws SQLException {return animalRepository.size();}
}
