package com.theIronYard.Animal;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by chris on 8/20/16.
 */
public class AnimalRepository {
    // properties
    private Connection connection;
    private String jdbcUrl = "jdbc:postgresql://localhost/animalrepository_test";


    // constructor
    public AnimalRepository(String jdbcUrl) throws SQLException {
        if (!jdbcUrl.equals("")) {
            this.jdbcUrl = jdbcUrl;
        }
        this.connection = DriverManager.getConnection(this.jdbcUrl);
    }

    // private methods
    
    // methods used by other Animal Service
    ResultSet get(int id) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT a.id, a.name, t.typeid, t.type, b.breedid, b.breed, a.color, a.description " +
                        "FROM animal AS a " +
                        "JOIN type AS t " +
                        "ON a.typeid = t.typeid " +
                        "JOIN breed AS b " +
                        "ON a.breedid = b.breedid " +
                        "WHERE a.id = ?");

        preparedStatement.setInt(1, id);

        return preparedStatement.executeQuery();
    }

    Animal add(Animal animal) throws SQLException {
        PreparedStatement preparedStatement = connection.
                prepareStatement("INSERT INTO animal (name, typeid, breedid, color, description) " +
                        "VALUES (?, ?, ?, ?, ?) RETURNING id");

        preparedStatement.setString(1, animal.getName());
        preparedStatement.setInt(2, animal.getType().getId());
        preparedStatement.setInt(3, animal.getBreed().getId());
        preparedStatement.setString(4, animal.getColor());
        preparedStatement.setString(5, animal.getDescription());

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            animal.setId(resultSet.getInt("id"));
        }

        return animal;
    }

    void remove(int id) throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement("DELETE FROM animal WHERE id=?");

        preparedStatement.setInt(1, id);
        preparedStatement.execute();

        //return new Animal();
    }

    boolean contains(int id) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT count(1) FROM animal WHERE id = ?");
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            return resultSet.getInt(1) == 1;
        }
        return false;
    }

    boolean contains(Animal animal) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT count(1) FROM animal WHERE id = ?");
        preparedStatement.setInt(1, animal.getId());

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            return resultSet.getInt(1) == 1;
        }
        return false;
    }

    ResultSet list() throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery("SELECT a.id, a.name, a.typeid, t.type, a.breedid, b.breed, a.color, a.description " +
                "FROM animal AS a " +
                "JOIN type AS t " +
                "ON t.typeid = a.typeid " +
                "JOIN breed AS b " +
                "ON b.breedid = a.breedid " +
                "ORDER BY a.id");
    }

    void editAnimal(Animal animal) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("UPDATE animal " +
                        "SET name = ?, " +
                        "color = ?, " +
                        "description = ?, " +
                        "typeid = ?, " +
                        "breedid = ? " +
                        "WHERE id = ?;");

        preparedStatement.setString(1, animal.getName());
        preparedStatement.setString(2, animal.getColor());
        preparedStatement.setString(3, animal.getDescription());
        preparedStatement.setInt(4, animal.getType().getId());
        preparedStatement.setInt(5, animal.getBreed().getId());
        preparedStatement.setInt(6, animal.getId());

        preparedStatement.execute();
    }

    public int size() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT count(id) FROM animal");

        resultSet.next();
        return resultSet.getInt("count");
    }

}


