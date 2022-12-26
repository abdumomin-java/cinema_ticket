package uz.pdp.online.project.model;

import java.util.Objects;

public class CinemaManager {

    static int count = 1;

    {
        count++;
    }

    private int id;
    private Cinema cinema;
    private User user;

    public CinemaManager() {
    }

    public CinemaManager(int id, Cinema cinema, User user) {
        this.id = id;
        this.cinema = cinema;
        this.user = user;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        CinemaManager.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CinemaManager that = (CinemaManager) o;
        return id == that.id && Objects.equals(cinema, that.cinema) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cinema, user);
    }

    @Override
    public String toString() {
        return "CinemaManager{" +
                "id=" + id +
                ", cinema=" + cinema +
                ", user=" + user +
                '}';
    }
}
