import com.theIronYard.Animal.Animal;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by chris on 8/30/16.
 */

@RunWith(JUnitParamsRunner.class)
public class DisplayServiceTest {
    DisplayService display = new DisplayService();
    InputStream stdin;

    @Before
    public void SetupSystemInMock() {
        stdin = System.in;
    }

    @After
    public void cleanUp(){
        System.setIn(stdin);
    }

    @Test
    @Parameters
    public void promptForMainMenuSelectionTest(String data, int expectedResult) {
        // arrange
        int result = 0;
        //String data = "1\n";
//        InputStream stdin = System.in;

        // act
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            result = display.promptForMainMenuSelection();
        } finally {
            System.setIn(stdin);
        }

        // assert
        assertThat(result, equalTo(expectedResult));
    }

    private Object[] parametersForPromptForMainMenuSelectionTest() {
        return new Object[]{
                new Object[]{"1\n", 1},
                new Object[]{"2\n", 2},
                new Object[]{"3\n", 3},
                new Object[]{"4\n", 4},
                new Object[]{"5\n", 5},
                new Object[]{"6\n", 6}
        };
    }

    @Test(expected = NoSuchElementException.class)
    public void promptForMainMenuSelectionOutOfBoundsTest() {
        // arrange
        int result = 0;
        String data = "7\n";

        // act
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            result = display.promptForMainMenuSelection();
        } finally {
            System.setIn(stdin);
        }

        // assert
        assertThat(result, equalTo(6));
    }

    @Test
    @Parameters
    public void promptForExitTest(String data, String expectedResult){
        // arrange
        String result = "";

        // act
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            result = display.promptForExit();
        } finally {
            System.setIn(stdin);
        }

        // assert
        assertThat(result, equalTo(expectedResult));
    }
    private Object[] parametersForPromptForExitTest() {
        return new Object[]{
                new Object[]{"y\n", "y"},
                new Object[]{"n\n", "n"}
        };
    }

    @Test
    @Parameters
    public void promptForRemoveTest(String data, String expectedResult) {
        // arrange
        String result = "";

        // act
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            result = display.promptForExit();
        } finally {
            System.setIn(stdin);
        }

        // assert
        assertThat(result, equalTo(expectedResult));

    }
    private Object[] parametersForPromptForRemoveTest() {
        return new Object[]{
                new Object[]{"y\n", "y"},
                new Object[]{"n\n", "n"}
        };
    }

    @Test
    public void promptForNewAnimalDataTest() {
        // arrange
        String name = "myst";
        String species = "feline";
        String breed = "tabby";
        String description = "grey";
        String color = "skittish";
        Animal expectedResult = new Animal(name, species, breed, color, description);
        Animal result;
        String data = name + System.getProperty("line.separator") + species +"\r\n" + breed + "\r\n" + color +"\r\n" + description + "\r\n";


        // act
        try {
            display.setIn(new ByteArrayInputStream(data.getBytes()));
//            System.setIn(new ByteArrayInputStream(data.getBytes()));
            result = display.promptForNewAnimal();
        } finally {
//            System.setIn(stdin);
            display.setIn(System.in);
        }

        // assert
        assertThat(result, equalTo(expectedResult));

    }


}
