package uz.pdp.online.project.base;

import uz.pdp.online.project.Storage;
import uz.pdp.online.project.impl.AdminOperations;
import uz.pdp.online.project.model.Cinema;
import uz.pdp.online.project.model.CinemaManager;
import uz.pdp.online.project.model.Role;
import uz.pdp.online.project.model.User;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class AdminConsole implements AdminOperations {

    static Scanner scannerText = new Scanner(System.in);
    static Scanner scannerInt = new Scanner(System.in);

    @Override
    public void startAdmin(User user) {
        System.out.println(" Assalomu alaykum. Hurmatli " + user.getName() + " janoblari! ");
        label:
        while (true) {
            System.out.println(" ================================================================================= ");
            System.out.println("    1-> Add Cinema; 2-> Add Manager; 3-> Add Manager to Cinema; 0-> Go To Back; ");
            String com = scannerText.nextLine();
            switch (com) {
                case "1":
                    addCinema();
                    break;
                case "2":
                    addManager();
                    break;
                case "3":
                    addManagerToCinema();
                    break;
                case "0":
                    break label;
            }
        }
    }

    private void addManagerToCinema() {
        while (true) {
            System.out.println(" ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ");
            System.out.println(" Let's start add Manager to Cinema. Be carefully! ");
            System.out.println(" 1-> Continue; 0-> Go To Back; ");
            if (scannerText.nextLine().equals("0")) {
                break;
            }

            System.out.println(" Firstly, You must choose Cinema --> ");
            if (Storage.getStorage().cinemaList.size() == 0) {
                System.out.println(" Cinema List is empty ");
            } else {
                Cinema cinema = chosenCinemaForAdmin();
                if (cinema == null) {
                    System.out.println(" ID is not found,  Try again! ");
                } else {
                    System.out.println(" Then, You must choose Manager --> ");
                    List<User> managersList = Storage.getStorage().usersList.stream()
                            .filter(user -> user.getRole().equals(Role.MANAGER)).toList();
                    if (managersList.size() == 0){
                        System.out.println(" Manager List is empty ");
                    } else {
                        User manager = chosenManagerForAdmin(managersList);
                        if (manager == null){
                            System.out.println(" This ID is not found ");
                        } else {
                            if (findManagerAndCinema(cinema, manager)){
                                System.out.println(" This Manager was previously attached to this Cinema");
                            } else {
                                CinemaManager cinemaManager = new CinemaManager(CinemaManager.getCount(), cinema, manager);
                                Storage.getStorage().cinemaManagerList.add(cinemaManager);
                                System.out.println(" Successfully added this Cinema and this Manager, Thank you! ");
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean findManagerAndCinema(Cinema cinema, User manager){
        CinemaManager cinemaManager1 = Storage.getStorage().cinemaManagerList.stream()
                .filter(cinemaManager -> cinemaManager.getCinema().equals(cinema) && cinemaManager.getUser().equals(manager))
                .findFirst().orElse(null);
        return cinemaManager1 != null;
    }

    public User chosenManagerForAdmin(List<User> managersList){
        managersList.forEach(user -> {
            System.out.println(user.toString());
            System.out.println(" ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ");
        });
        System.out.println(" Enter your chosen Manager's ID: ");
        int chosen = scannerInt.nextInt();
        return managersList.stream()
                .filter(user -> user.getId() == chosen)
                .findFirst().orElse(null);
    }

    public Cinema chosenCinemaForAdmin() {
        Storage.getStorage().cinemaList.forEach(cinema -> {
            System.out.println(cinema.toString());
            System.out.println(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
        });
        System.out.println(" Enter your chosen Cinema's ID: ");
        int chosen = scannerInt.nextInt();
        return Storage.getStorage().cinemaList.stream()
                .filter(cinema -> cinema.getId() == chosen)
                .findFirst().orElse(null);
    }

    private void addManager() {
        while (true) {
            System.out.println(" ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ");
            System.out.println(" Let's start add Manager. Be carefully! ");
            System.out.println(" 1-> Continue; 0-> Go To Back; ");
            if (scannerText.nextLine().equals("0")) {
                break;
            }

            String name;
            while (true) {
                System.out.print(" Enter Manager name: ");
                name = scannerText.nextLine();
                if (Objects.isNull(name) || name.isBlank()) {
                    System.out.println(" Name is null or empty, Try again! ");
                } else {
                    break;
                }
            }
            String login;
            while (true) {
                System.out.print(" Enter Manager login: ");
                login = scannerText.nextLine();
                if (Objects.isNull(login) || login.isBlank()) {
                    System.out.println(" Login is null or empty, Try again! ");
                } else {
                    break;
                }
            }
            String password;
            while (true) {
                System.out.print(" Enter Manager password: ");
                password = scannerText.nextLine();
                if (Objects.isNull(password) || password.isBlank()) {
                    System.out.println(" Password is null or empty, Try again! ");
                } else {
                    break;
                }
            }
            String finalName = name;
            String finalLogin = login;
            String finalPassword = password;

            if (Application.getApplication().isHere(finalLogin) == null) {
                User user = new User(User.getCount(), finalName, finalLogin, finalPassword, Role.MANAGER, 0);
                Storage.getStorage().usersList.add(user);
                System.out.println(" Successfully added this Manager, Thank you. ");
            } else {
                System.out.println(" This Login is already exists ");
            }
        }
    }

    private void addCinema() {
        while (true) {
            System.out.println(" ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ");
            System.out.println(" Let's start add Cinema. Be carefully! ");
            System.out.println(" 1-> Continue; 0-> Go To Back; ");
            if (scannerText.nextLine().equals("0")) {
                break;
            }

            String name;
            while (true) {
                System.out.print(" Enter Cinema's name: ");
                name = scannerText.nextLine();
                if (Objects.isNull(name) || name.isBlank()) {
                    System.out.println(" Name is null or empty, Try again! ");
                } else {
                    break;
                }
            }
            String address;
            while (true) {
                System.out.print(" Enter Cinema's address: ");
                address = scannerText.nextLine();
                if (Objects.isNull(address) || address.isBlank()) {
                    System.out.println(" Address is null or empty, Try again! ");
                } else {
                    break;
                }
            }
            String finalName = name;
            String finalAddress = address;
            System.out.print(" Enter The Number of Seat Of Cinema: ");
            int theNumberOfSeat = scannerInt.nextInt();
            if (isName(finalName) == null) {
                Cinema cinema = new Cinema(Cinema.getCount(), finalName, finalAddress, theNumberOfSeat);
                Storage.getStorage().cinemaList.add(cinema);
                System.out.println(" Successfully added New Cinema. Thank you! ");
            } else {
                System.out.println(" This name is already exists ");
            }

        }
    }

    public Cinema isName(String name) {
        return Storage.getStorage().cinemaList.stream()
                .filter(cinema -> cinema.getName().equals(name)).findFirst().orElse(null);
    }

    static AdminConsole adminConsole;

    public static AdminConsole getAdminConsole() {
        if (adminConsole == null) {
            adminConsole = new AdminConsole();
        }
        return adminConsole;
    }
}
