package com.theIronYard.Animal;

import java.sql.Date;
import java.time.LocalDateTime;

/**
 * Created by chris on 9/1/16.
 */
public class Note {
    private int id;
    private int animalId;
    private String content;
    private LocalDateTime date;

    public Note(String content) {
        this.content = content;
        this.date = LocalDateTime.now();
    }

    Note(int id, String content, LocalDateTime date) {
        this.id = id;
        this.content = content;
        this.date = date;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public int getAnimalId() {return animalId;}

    public void setAnimalId(int animalId) {this.animalId = animalId;}

    public String getContent() {return content;}

    public void setContent(String content) {this.content = content;}

    public LocalDateTime getDate() {return date;}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Note notes = (Note) o;

        if (getId() != notes.getId()) return false;
        if (!getContent().equals(notes.getContent())) return false;
        return getDate().equals(notes.getDate());

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getContent().hashCode();
        result = 31 * result + getDate().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", date=" + date +
                '}';
    }
}
