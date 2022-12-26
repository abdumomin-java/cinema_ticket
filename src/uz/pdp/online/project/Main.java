package uz.pdp.online.project;

import uz.pdp.online.project.base.Application;
import uz.pdp.online.project.model.Cinema;
import uz.pdp.online.project.model.Role;
import uz.pdp.online.project.model.User;

public class Main {

    public static void main(String[] args) {

        User admin = new User(User.getCount(), "admin", "admin", "admin", Role.ADMIN, 0);
        Storage.getStorage().usersList.add(admin);

        Cinema cinema1 = new Cinema(Cinema.getCount(), "Satira", "Chirchiq", 15);
        Storage.getStorage().cinemaList.add(cinema1);

        User manager = new User(User.getCount(), "manager", "manager", "manager", Role.MANAGER, 0);
        Storage.getStorage().usersList.add(manager);

        Application.getApplication().startApplications();

    }

}
