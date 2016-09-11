package com.theIronYard.Animal;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by chris on 9/1/16.
 */
public class NoteRepository {
    // properties
    private ArrayList<Note> animalNotes = new ArrayList<>();
    private Connection connection;
    private String jdbcUrl = "jdbc:postgresql://localhost/animalrepository_test";
    private Statement statement;

    // constructor
    public NoteRepository(String jdbcUrl) throws SQLException {
        if (!jdbcUrl.equals("")) {this.jdbcUrl = jdbcUrl;}
        this.connection = DriverManager.getConnection(this.jdbcUrl);
    }

    // getters and setters
    public ArrayList<Note> getAnimalNotes() {
        return animalNotes;
    }


    // methods
    public boolean addNote(int animalID, String note) throws SQLException {
        // create note in memory
        Note newNote = new Note(note);

        // write note to database
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO note " +
                "(note, date)" +
                "VALUES " +
                "(?, ?) RETURNING noteid");
        preparedStatement.setInt(1, newNote.getId());
        Timestamp timestamp = Timestamp.valueOf(newNote.getDate());
        preparedStatement.setTimestamp(2, timestamp);
        ResultSet resultSet = preparedStatement.executeQuery();

        // store the ID of the new note in it's object
        resultSet.next();
        int noteID = resultSet.getInt(1);
        newNote.setId(noteID);

        // return the result of adding the note to the ArrayList in memory
        return animalNotes.add(newNote);
    }

    public ResultSet getAnimalNotes(int id) throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement("SELECT n.noteid, n.note, n.date " +
                        "FROM animal AS a " +
                        "JOIN note AS n " +
                        "ON a.id = n.animalid " +
                        "WHERE a.id = ?");

        preparedStatement.setInt(1, id);
        return preparedStatement.executeQuery();
    }

    public boolean delete(int animalID, int noteID) {
        return false;
    }

    public void writeDB() throws SQLException {
//        Statement stmt = connection.createStatement();
//        stmt.executeQuery("INSERT INTO note ()");
    }
}
