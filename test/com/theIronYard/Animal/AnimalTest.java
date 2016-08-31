package com.theIronYard.Animal;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by chris on 8/28/16.
 */
@RunWith(JUnitParamsRunner.class)
public class AnimalTest {
    private Animal newAnimal;

    @Before
    public void setupTestEnvironment(){
        newAnimal = new Animal();
    }

    @Test
    @Parameters
    public void animalSetNameTest(String name, String expectedResult) {
        // arrange

        // act
        newAnimal.setName(name);

        // assert
        assertThat(newAnimal.getName(), is(expectedResult));

    }

    private Object[] parametersForAnimalSetNameTest() {
        return new Object[] {
                new Object[] {"George", "George"},
                new Object[] {"myst", "myst"},
                new Object[] {"99", "99"}
        };
    }

    @Test
    public void animalSetSpeciesTest() {
        // arrange

        // act
        newAnimal.setSpecies("canine");

        // assert
        assertThat(newAnimal.getSpecies(), is("canine"));
    }

    @Test
    public void animalSetBreedTest() {
        // arrange

        // act
        newAnimal.setBreed("border collie");

        // assert
        assertThat(newAnimal.getBreed(), is("border collie"));
    }

    @Test
    public void animalSetColorTest() {
        // arrange

        // act
        newAnimal.setColor("black");

        // assert
        assertThat(newAnimal.getColor(), is("black"));
    }

    @Test
    @Parameters
    public void animalSetDescriptionTest(String description, String expectedResult) {
        // arrange

        // act
        newAnimal.setDescription(description);

        // assert
        assertThat(newAnimal.getDescription(), is(expectedResult));
    }

    private Object[] parametersForAnimalSetDescriptionTest() {
        return new Object[] {
                new Object[] {"short", "short"},
                new Object[] {"This is a long sentence description of an animal.", "This is a long sentence description of an animal."},
                new Object[] {"", ""}
        };
    }


    @Test
    public void animalToStringTest() {
        // arrange
        newAnimal = new Animal("myst", "feline", "calico", "grey", "description");

        // act
        String result = newAnimal.toString();

        // assert
        assertThat(result, is("myst, feline, calico, grey"));
    }

    @Test
    public void animalToStringVerboseTest() {
        // arrange
        newAnimal = new Animal("myst", "feline", "calico", "grey", "description");

        // act
        String result = newAnimal.toString("v");

        // assert
        assertThat(result, is("Name:\t\t\tmyst\nSpecies:\t\tfeline\nBreed:\t\t\tcalico\nColor:\t\t\tgrey\nDescription:\tdescription"));
    }

}
