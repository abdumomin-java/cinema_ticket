package uz.pdp.online.project.base;

import uz.pdp.online.project.Storage;
import uz.pdp.online.project.impl.UserOperation;
import uz.pdp.online.project.model.*;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class UserConsole implements UserOperation {

    static Scanner scannerText = new Scanner(System.in);
    static Scanner scannerInt = new Scanner(System.in);

    @Override
    public void startUser(User user) {
        System.out.println(" Assalomu alaykum. Hurmatli " + user.getName());
        label:
        while (true) {
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println("     1-> Buying ticket; 2-> Show My history; 3-> Show My balance; 0-> Go To Back; ");
            String com = scannerText.nextLine();
            switch (com) {
                case "1":
                    buyingTicket(user);
                    break;
                case "2":
                    showMyOrders(user);
                    break;
                case "3":
                    showMyBalance(user);
                    break;
                case "0":
                    break label;
                default:
                    System.out.println(" Wrong command! ");
                    break;
            }
        }
    }

    private void showMyBalance(User user) {
        while (true) {
            System.out.println(" + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + ");
            System.out.println(" Your balance: " + user.getBalance());
            System.out.println("    1-> Filling My Balance; 0-> Go To Back; ");
            String com = scannerText.nextLine();
            if (com.equals("1")){
                System.out.println(" Enter Balance: ");
                int balance = scannerInt.nextInt();
                user.setBalance(user.getBalance()+balance);
                System.out.println(" Successfully Filing Your Balance. Thank you! ");

            } else {
                break;
            }
        }
    }

    private void showMyOrders(User user) {
        List<Order> orders = Storage.getStorage().orderList.stream()
                .filter(order -> order.getUser().equals(user)).toList();
        if (orders.size() == 0){
            System.out.println(" Your Order List is empty ");
        } else {
            orders.forEach(order -> {
                System.out.println(order.toString());
                System.out.println(" ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
            });
        }
    }

    private void buyingTicket(User user) {
        while (true) {
            System.out.println(" ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ");
            System.out.println(" Let's start buying Ticket to Cinema. Be carefully! ");
            System.out.println(" 1-> Continue; 0-> Go To Back; ");
            if (scannerText.nextLine().equals("0")) {
                break;
            }

            System.out.println(" Firstly, You must choose Cinema --> ");
            if (Storage.getStorage().cinemaList.size() == 0) {
                System.out.println(" Cinema List is empty ");
            } else {
                Cinema cinema = chosenCinemaForUser();
                if (Objects.isNull(cinema)) {
                    System.out.println(" This Cinema's ID is not found ");
                } else {
                    System.out.println(" Secondly, You must choose Movie from Cinema --> ");
                    List<Movie> availableMovies = Storage.getStorage().movieList.stream()
                            .filter(movie -> movie.getCinema().equals(cinema)).toList();

                    if (availableMovies.size() == 0) {
                        System.out.println(" Movie List is empty ");
                    } else {
                        Movie movie = chosenMovieForUser(availableMovies);
                        if (Objects.isNull(movie)) {
                            System.out.println(" This Movie's ID is not found ");
                        } else {
                            chosenTicketForUser(user, movie);

                        }
                    }
                }
            }
        }
    }

    private void chosenTicketForUser(User user, Movie movie) {
        List<Ticket> tickets = Storage.getStorage().ticketList.stream()
                .filter(ticket -> ticket.getMovie().equals(movie)).toList();

        tickets.forEach(ticket -> {
            if (ticket.getStatus().equals(Status.AVAILABLE)) {
                System.out.println(" Ticket { id=" + ticket.getId() + ", movie=" + ticket.getMovie() + "\n Ticket => Price=" + ticket.getPrice() + ", seatNumber=" + ticket.getSeatNumber() + ", status=" + ticket.getStatus() + '}');
                System.out.println(" = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
            }
        });
        System.out.println(" Enter your chosen Ticket's ID: ");
        int chosen = scannerInt.nextInt();
        Ticket ticket1 = tickets.stream().filter(ticket -> ticket.getId() == chosen)
                .findFirst().orElse(null);
        if (Objects.isNull(ticket1)) {
            System.out.println(" This Ticket's ID is not found ");
        } else {
            if (user.getBalance() >= ticket1.getPrice()) {
                Order order = new Order(Order.getCount(), user, ticket1, ticket1.getPrice());
                Storage.getStorage().orderList.add(order);
                user.setBalance(user.getBalance() - ticket1.getPrice());

                Ticket new1 = Storage.getStorage().ticketList.get(ticket1.getId()-1);
                new1.setStatus(Status.SOLD);
                Storage.getStorage().ticketList.set(ticket1.getId()-1, new1);
                System.out.println(" Successfully buy, Thank you for buying! ");
            } else {
                System.out.println(" Your balance is enough, Don't forget to fill your balance! ");
            }
        }
    }

    private Movie chosenMovieForUser(List<Movie> availableMovies) {
        availableMovies.forEach(movie -> {
            System.out.println(movie.toString());
            System.out.println(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
        });
        System.out.println(" Enter your chosen Movie's ID: ");
        int i = scannerInt.nextInt();
        return availableMovies.stream()
                .filter(movie -> movie.getId() == i).findFirst().orElse(null);
    }

    private Cinema chosenCinemaForUser() {
        Storage.getStorage().cinemaList.forEach(cinema -> {
            System.out.println(cinema.toString());
            System.out.println(" ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");
        });
        System.out.println(" Enter your chosen Cinema's ID: ");
        int chosen = scannerInt.nextInt();
        return Storage.getStorage().cinemaList.stream()
                .filter(cinema -> cinema.getId() == chosen)
                .findFirst().orElse(null);
    }


    static UserConsole userConsole;

    public static UserConsole getUserConsole() {
        if (userConsole == null) {
            userConsole = new UserConsole();
        }
        return userConsole;
    }
}
