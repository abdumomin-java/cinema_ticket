package uz.pdp.online.project.base;

import uz.pdp.online.project.Storage;
import uz.pdp.online.project.impl.ApplicationOperations;
import uz.pdp.online.project.model.Role;
import uz.pdp.online.project.model.User;

import java.util.Objects;
import java.util.Scanner;

public class Application implements ApplicationOperations {

    static Scanner scannerText = new Scanner(System.in);

    static Scanner scannerInt = new Scanner(System.in);

    @Override
    public void startApplications() {
        System.out.println(" # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # ");
        System.out.println(" # # # # #           WELCOME TO ONLINE BUYING TICKET           # # # # # ");
        System.out.println(" # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # ");
        label:
        while (true) {
            System.out.println(" ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ");
            System.out.println("            1-> Sign IN;  2-> Sign UP;  0-> Stop program;            ");
            String com = scannerText.nextLine();
            switch (com) {
                case "1":
                    User user = singIN();
                    if (user == null) {
                        System.out.println(" This user is not found ");
                    } else {
                        if (user.getRole().equals(Role.ADMIN)) {
                            AdminConsole.getAdminConsole().startAdmin(user);
                        } else if (user.getRole().equals(Role.MANAGER)) {
                            ManagerConsole.getManagerConsole().startManager(user);
                        } else {
                            UserConsole.getUserConsole().startUser(user);
                        }
                    }
                    break;
                case "2":
                    signUP();
                    break;
                case "0":
                    break label;
                default:
                    System.out.println(" Wrong answer!!! ");
                    break;
            }
        }
    }

    private void signUP() {
        String name;
        while (true) {
            System.out.println(" Enter your name: ");
            name = scannerText.nextLine();
            if (Objects.isNull(name) || name.isBlank()){
                System.out.println(" Name is null or empty, Try again! ");
            } else {
                break;
            }
        }
        String login;
        while (true) {
            System.out.println(" Enter your login: ");
            login = scannerText.nextLine();
            if (Objects.isNull(login) || login.isBlank()){
                System.out.println(" Login is null or empty, Try again! ");
            } else {
                break;
            }
        }
        String password;
        while (true) {
            System.out.println(" Enter your password: ");
            password = scannerText.nextLine();
            if (Objects.isNull(password) || password.isBlank()){
                System.out.println(" Password is null or empty, Try again! ");
            } else {
                break;
            }
        }
        System.out.println(" Enter your balance: ");
        int balance = scannerInt.nextInt();

        String finalName = name;
        String finalLogin = login;
        String finalPassword = password;

        if (isHere(finalLogin) == null){
            User user = new User(User.getCount(), finalName, finalLogin, finalPassword, Role.USER, balance);
            Storage.getStorage().usersList.add(user);
            System.out.println(" Successfully added this User, Thank you. ");
            UserConsole.getUserConsole().startUser(user);
        } else {
            System.out.println(" This Login is already exists ");
        }

    }

    public User isHere(String login){
        return Storage.getStorage().usersList.stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst().orElse(null);
    }

    private User singIN() {
        String login;
        while (true) {
            System.out.print(" Enter your login: ");
            login = scannerText.nextLine();
            if (Objects.isNull(login) || login.isBlank()) {
                System.out.println(" Login is null or empty, Try again! ");
            } else {
                break;
            }
        }
        String password;
        while (true) {
            System.out.print(" Enter your password: ");
            password = scannerText.nextLine();
            if (Objects.isNull(password) || password.isBlank()) {
                System.out.println(" Password is null or empty, Try again! ");
            } else {
                break;
            }
        }
        String finalLogin = login;
        String finalPassword = password;
        return Storage.getStorage().usersList.stream()
                .filter(user -> user.getLogin().equals(finalLogin) && user.getPassword().equals(finalPassword))
                .findFirst().orElse(null);
    }

    static Application application;

    public static Application getApplication() {
        if (application == null) {
            application = new Application();
        }
        return application;
    }

}

