package uz.pdp.online.project.model;

import java.util.Objects;

public class Movie {

    static int count = 1;

    {
        count++;
    }

    private int id;
    private String name;
    private String date;
    private String description;
    private Cinema cinema;
    private int priceForPerSeat;

    public Movie() {
    }

    public Movie(int id, String name, String date, String description, Cinema cinema, int priceForPerSeat) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.description = description;
        this.cinema = cinema;
        this.priceForPerSeat = priceForPerSeat;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Movie.count = count;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public int getPriceForPerSeat() {
        return priceForPerSeat;
    }

    public void setPriceForPerSeat(int priceForPerSeat) {
        this.priceForPerSeat = priceForPerSeat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id == movie.id && priceForPerSeat == movie.priceForPerSeat && Objects.equals(name, movie.name) && Objects.equals(date, movie.date) && Objects.equals(description, movie.description) && Objects.equals(cinema, movie.cinema);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, date, description, cinema, priceForPerSeat);
    }

    @Override
    public String toString() {
        return " Movie-> ID: " + id + "| Name: " + name + "| Date: " + date + "| Description: " + description +"| PriceForPerSeat: " + priceForPerSeat +
                "\n        " + cinema ;


    }
}
