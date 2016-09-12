package com.theIronYard.Animal;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by chris on 9/10/16.
 */
public class AnimalTypeRepository {
    // properties
    private ArrayList<AnimalType> animalTypes = new ArrayList<>();
    private Connection connection;
    private String jdbcUrl = "jdbc:postgresql://localhost/animalrepository_test";

    // constructor
    public AnimalTypeRepository(String jdbcUrl) throws SQLException {
        if (!jdbcUrl.equals("")) {this.jdbcUrl = jdbcUrl;}
        this.connection = DriverManager.getConnection(this.jdbcUrl);
    }

    // getters and setters
    public ResultSet getAnimalTypes() throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery("SELECT * FROM type ORDER BY typeid");
    }

    // methods
    public boolean addType(String type) throws SQLException {
        AnimalType animalType= new AnimalType(type);

        // write note to database
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO type " +
                "(type)" +
                "VALUES " +
                "(?) RETURNING typeid");
        preparedStatement.setInt(1, animalType.getId());
        ResultSet resultSet = preparedStatement.executeQuery();

        // store the ID of the new note in it's object
        resultSet.next();
        int breedId = resultSet.getInt(1);
        animalType.setId(breedId);

        // return the result of adding the note to the ArrayList in memory
        return animalTypes.add(animalType);
    }

    public boolean delete(int animalID, int typeId) {
        return false;
    }

    public void writeDB() throws SQLException {
//        Statement stmt = connection.createStatement();
//        stmt.executeQuery("INSERT INTO note ()");
    }


}
