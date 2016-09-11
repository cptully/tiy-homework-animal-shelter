package com.theIronYard.Animal;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by chris on 9/10/16.
 */
public class AnimalBreedRepository {
    // properties
    private ArrayList<AnimalBreed> animalBreeds = new ArrayList<>();
    private Connection connection;
    private String jdbcUrl = "jdbc:postgresql://localhost/animalrepository_test";
    private Statement statement;

    // constructor
    public AnimalBreedRepository(String jdbcUrl) throws SQLException {
        if (!jdbcUrl.equals("")) {this.jdbcUrl = jdbcUrl;}
        this.connection = DriverManager.getConnection(this.jdbcUrl);
    }

    // getters and setters
    public ResultSet getAnimalBreeds() throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery("SELECT * FROM breed");
    }

    // methods
    public boolean addBreed(String breed) throws SQLException {
        // create note in memory
        AnimalBreed animalBreed = new AnimalBreed(breed);

        // write note to database
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO breed " +
                "(breed)" +
                "VALUES " +
                "(?) RETURNING breedid");
        preparedStatement.setInt(1, animalBreed.getId());
        ResultSet resultSet = preparedStatement.executeQuery();

        // store the ID of the new note in it's object
        resultSet.next();
        int breedId = resultSet.getInt(1);
        animalBreed.setId(breedId);

        // return the result of adding the note to the ArrayList in memory
        return animalBreeds.add(animalBreed);
    }

    public boolean delete(int animalID, int breedId) {
        return false;
    }

    public void writeDB() throws SQLException {
//        Statement stmt = connection.createStatement();
//        stmt.executeQuery("INSERT INTO note ()");
    }

}
