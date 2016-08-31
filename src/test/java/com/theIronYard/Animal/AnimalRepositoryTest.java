package com.theIronYard.Animal;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by chris on 8/28/16.
 */
@RunWith(JUnitParamsRunner.class)
public class AnimalRepositoryTest {
    private AnimalRepository animalList;

    @Before
    public void setupTestEnvironment() {
        animalList = new AnimalRepository();
        for (int i = animalList.size() - 1; i >= 0; i--) {
            animalList.remove(i);
        }
    }

    @Test
    @Parameters
    public void setPathTest(Path path, String expectedResult) {
        // arrange

        // act
        animalList.setPath(path);

        // assert
        assertThat(animalList.getPath().toString(), is(expectedResult));
    }

    private Object[] parametersForSetPathTest() {
        return new Object[]{
                new Object[]{Paths.get(""), "./animalDatabase.json"},
                new Object[]{Paths.get("/Users/chris/Projects/tiy-homework-animal-shelter-clojure"),
                        "/Users/chris/Projects/tiy-homework-animal-shelter-clojure"},
                new Object[]{null, "./animalDatabase.json"}
        };
    }

    @Test
    /**
     * Given an Animal with valid data
     * When that Animal is retrieved
     * Then the instance returned is valid
     */
    public void getAnimalNotNullTest() {
        // arrange
        Animal newAnimal = new Animal("myst", "feline", "tabby", "grey", "skittish");
        animalList.add(newAnimal);

        // act
        Animal result = animalList.get(animalList.size() - 1);

        // assert
        assertThat(result, equalTo(newAnimal));
    }

    /**
     * Given an valid AnimalRepository with no animals
     * When getAnimal is called
     * Then an IndexOutOfBoundsException is thrown
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void getAnimalFailsOnEmptyDBTest() {
        // arrange

        // act
        Animal result = animalList.get(animalList.size() - 1);

        // assert
        assertThat(result, is(not(null)));
    }

    @Test
    /**
     * Given an Animal with valid data
     * When that Animal is added to animalList
     * Then the result is true
     */
    public void addAnimalNotFalseTest() {
        // arrange
        Animal newAnimal = new Animal("myst", "feline", "tabby", "grey", "skittish");

        // act
        boolean result = animalList.add(newAnimal);
//        boolean result = animalList.get(0);

        // assert
        assertThat(result, is(true));
    }


    @Test
    /**
     * Given an AnimalRepository with known list of animals
     * When a specific animal is removed
     * Then that animal is removed
     */
    public void removeAnimalRemovesExpectedAnimalTest() {
        // arrange
        animalList.add(new Animal("Shadow", "dog", "border collie", "black and white", "energetic and friendly; liked to chase balls"));
        animalList.add(new Animal("Mia", "cat", "domestic short hair", "calico", "skittish"));
        animalList.add(new Animal("Rags", "cat", "domestic short hair", "black and white", "likes to hunt"));
        Animal newAnimal = new Animal("Myst", "feline", "calico", "grey", "skittish");
        animalList.add(newAnimal);

        // act
        Animal result = animalList.remove(animalList.size() - 1);

        // assert
        assertThat(result, is(newAnimal));
    }

    @Test
    /**
     * Given an AnimalRepository with known list of animals
     * When given an index out of bounds
     * Then nothing happens
     */
    public void removeAnimalHandlesIndexOutOfBoundsTest() {
        // arrange
        animalList.add(new Animal("Shadow", "dog", "border collie", "black and white", "energetic and friendly; liked to chase balls"));
        animalList.add(new Animal("Mia", "cat", "domestic short hair", "calico", "skittish"));
        animalList.add(new Animal("Rags", "cat", "domestic short hair", "black and white", "likes to hunt"));
        Animal expectedResult = new Animal();

        // act
        Animal result = animalList.remove(animalList.size());

        // assert
        assertThat(result, is(expectedResult));
    }

    @Test
    /**
     * Given an empty AnimalRepository
     * When contains is called with an Animal
     * Then false is returned
     */
    public void containsAnimalReturnsFalseWhenDbEmptyTest() {
        // arrange
        Animal newAnimal = new Animal();

        // act
        boolean result = animalList.contains(newAnimal);

        // assert
        assertThat(result, is(false));
    }
    @Test
    /**
     * Given an empty AnimalRepository
     * When contains is called with an index
     * Then false is returned
     */
    public void containsIntReturnsFalseWhenDbEmptyTest() {
        // arrange

        // act
        boolean result = animalList.contains(0);

        // assert
        assertThat(result, is(false));
    }


    @Test
    /**
     * Given an AnimalRepository with known list of animals
     * When a list is requested
     * Then that List is the right length
     */
    public void listAnimalReturnsTest() {
        // arrange
        animalList.add(new Animal("Shadow", "dog", "border collie", "black and white", "energetic and friendly; liked to chase balls"));
        animalList.add(new Animal("Mia", "cat", "domestic short hair", "calico", "skittish"));
        animalList.add(new Animal("Rags", "cat", "domestic short hair", "black and white", "likes to hunt"));
        Animal newAnimal = new Animal("Myst", "feline", "tabby", "grey", "skittish");
        animalList.add(newAnimal);

        // act
        List<String> result = animalList.list();

        // assert
        assertThat(result.size(), is(4));
    }

    @Test
    /**
     * Given an AnimalRepository with known list of animals
     * When an Animal is edited
     * Then name is unchanged but any other edited values are correct
     */
    public void editAnimalObjectSucceedsTest() {
        // arrange
        animalList.add(new Animal("Shadow", "dog", "border collie", "black and white", "energetic and friendly; liked to chase balls"));
        animalList.add(new Animal("Mia", "cat", "domestic short hair", "calico", "skittish"));
        animalList.add(new Animal("Rags", "cat", "domestic short hair", "black and white", "likes to hunt"));
        Animal newAnimal = new Animal("Myst", "feline", "tabby", "grey", "skittish");
        Animal expectedResult = new Animal("Rags", "feline", "tabby", "grey", "skittish");

        // act
        animalList.editAnimal(newAnimal, 2);
        Animal result = animalList.get(2);

        // assert
        assertThat(result, equalTo(expectedResult));
    }

    @Test
    /**
     * Given an AnimalRepository with known list of animals
     * When an Animal is edited
     * Then name is unchanged but any other edited values are correct
     */
    public void editAnimalValuesSucceedsTest() {
        // arrange
        animalList.add(new Animal("Shadow", "dog", "border collie", "black and white", "energetic and friendly; liked to chase balls"));
        animalList.add(new Animal("Mia", "cat", "domestic short hair", "calico", "skittish"));
        animalList.add(new Animal("Rags", "cat", "domestic short hair", "black and white", "likes to hunt"));
        Animal expectedResult = new Animal("Rags", "feline", "tabby", "grey", "skittish");

        // act
        animalList.editAnimal(2, "Myst", "feline", "tabby", "grey", "skittish");
        Animal result = animalList.get(2);

        // assert
        assertThat(result, equalTo(expectedResult));
    }

}
