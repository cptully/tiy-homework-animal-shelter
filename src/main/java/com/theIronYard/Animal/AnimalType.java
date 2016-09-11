package com.theIronYard.Animal;

/**
 * Created by chris on 9/7/16.
 */
public class AnimalType {
    private int id;
    private String name;

    public AnimalType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public AnimalType(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnimalType that = (AnimalType) o;

        if (getId() != that.getId()) return false;
        return getName().equals(that.getName());

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getName().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "AnimalType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
