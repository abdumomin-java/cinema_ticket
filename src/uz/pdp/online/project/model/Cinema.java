package uz.pdp.online.project.model;

import java.util.Objects;

public class Cinema {

    static int count = 1;

    {
        count++;
    }

    private int id;
    private String name;
    private String address;
    private int theNumberOfSeat;

    public Cinema() {
    }

    public Cinema(int id, String name, String address, int theNumberOfSeat) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.theNumberOfSeat = theNumberOfSeat;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Cinema.count = count;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTheNumberOfSeat() {
        return theNumberOfSeat;
    }

    public void setTheNumberOfSeat(int theNumberOfSeat) {
        this.theNumberOfSeat = theNumberOfSeat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cinema cinema = (Cinema) o;
        return id == cinema.id && theNumberOfSeat == cinema.theNumberOfSeat && Objects.equals(name, cinema.name) && Objects.equals(address, cinema.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, theNumberOfSeat);
    }

    @Override
    public String toString() {
        return " Cinema->  ID: " + id + "| Name: " + name + "| Address: " + address + "| TheNumberOfSeat: " + theNumberOfSeat ;
    }
}
