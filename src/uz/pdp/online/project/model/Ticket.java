package uz.pdp.online.project.model;

import java.util.Objects;

public class Ticket {

    static int count = 1;

    {
        count++;
    }

    private int id;
    private Movie movie;
    private int price;
    private int seatNumber;
    private Status status;

    public Ticket() {
    }

    public Ticket(int id, Movie movie, int price, int seatNumber, Status status) {
        this.id = id;
        this.movie = movie;
        this.price = price;
        this.seatNumber = seatNumber;
        this.status = status;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Ticket.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id && price == ticket.price && seatNumber == ticket.seatNumber && Objects.equals(movie, ticket.movie) && status == ticket.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movie, price, seatNumber, status);
    }

    @Override
    public String toString() {
        return "Ticket { id=" + id + ", movie=" + movie + ", price=" + price + ", seatNumber=" + seatNumber + ", status=" + status + '}';
    }
}
