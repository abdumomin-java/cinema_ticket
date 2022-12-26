package uz.pdp.online.project;

import uz.pdp.online.project.model.*;

import java.util.ArrayList;

public class Storage {

    public static Storage storage;

    public static Storage getStorage() {
        if (storage == null){
            storage = new Storage();
        }
        return storage;
    }

    public ArrayList<User> usersList = new ArrayList<>();
    public ArrayList<Cinema> cinemaList = new ArrayList<>();
    public ArrayList<CinemaManager> cinemaManagerList = new ArrayList<>();
    public ArrayList<Movie> movieList = new ArrayList<>();
    public ArrayList<Order> orderList = new ArrayList<>();
    public ArrayList<Ticket> ticketList = new ArrayList<>();

}
