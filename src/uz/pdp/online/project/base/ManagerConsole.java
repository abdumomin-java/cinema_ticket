package uz.pdp.online.project.base;

import uz.pdp.online.project.Storage;
import uz.pdp.online.project.impl.ManagerOperations;
import uz.pdp.online.project.model.*;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ManagerConsole implements ManagerOperations {

    static Scanner scannerText = new Scanner(System.in);
    static Scanner scannerInt = new Scanner(System.in);

    @Override
    public void startManager(User user) {
        System.out.println(" Assalomu alaykum. Hurmatli " + user.getName() + " Manager! ");
        label:
        while (true) {
            System.out.println(" +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("""
                       1-> Add Movie to Cinema
                       2-> Show more options ;\s
                       0-> Go TO Back; \
                    """);
            String com = scannerText.nextLine();
            switch (com) {
                case "1":
                    addMovieToCinema(user);
                    break;
                case "2":
                    showMoreOptions(user);
                    break;
                case "0":
                    break label;
                default:
                    System.out.println(" Wrong answer! ");
                    break;
            }
        }
    }

    private void showMoreOptions(User user) {
        System.out.println(" Firstly, You must choose Cinema --> ");
        List<CinemaManager> cinemaManagers = Storage.getStorage().cinemaManagerList.stream()
                .filter(cinemaManager -> cinemaManager.getUser().equals(user)).toList();
        if (cinemaManagers.size() == 0) {
            System.out.println(" Cinema List is empty ");
        } else {
            CinemaManager cinemaManager = chosenCinemaForManager(cinemaManagers);
            if (cinemaManager == null) {
                System.out.println(" This ID is not found ");
            } else {
                Cinema cinema = cinemaManager.getCinema();
                System.out.println(" Secondly, You must choose Movie --> ");
                List<Movie> moviesForShow = Storage.getStorage().movieList.stream()
                        .filter(movie -> movie.getCinema().equals(cinema)).toList();
                if (moviesForShow.size() == 0){
                    System.out.println(" Movie List is empty ");
                } else {
                    Movie movie = chosenMovieForManager(moviesForShow);
                    if (movie == null) {
                        System.out.println(" This Movie's ID is not found ");
                    } else {
                        showTicketsForMovie(movie);

                    }
                }
            }
        }
    }

    private void showTicketsForMovie(Movie movie) {
        Storage.getStorage().ticketList.forEach(ticket -> {
            if (ticket.getMovie().equals(movie)) {
                System.out.println(" Ticket { id=" + ticket.getId() + ", " + ticket.getMovie() + "\n         => Price=" + ticket.getPrice() + ", seatNumber=" + ticket.getSeatNumber() + ", status=" + ticket.getStatus() + '}');
                System.out.println(" = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
            }
        });

    }

    private Movie chosenMovieForManager(List<Movie> moviesForShow) {
        moviesForShow.forEach(movie -> {
            System.out.println(movie.toString());
            System.out.println(" + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + ");
        });
        System.out.println(" Enter your chosen Movie's ID: ");
        int chosen = scannerInt.nextInt();
        return moviesForShow.stream()
                .filter(movie -> movie.getId() == chosen)
                .findFirst().orElse(null);
    }

    private void addMovieToCinema(User user) {
        while (true) {
            System.out.println(" ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ");
            System.out.println(" Let's start add Movie to Cinema. Be carefully! ");
            System.out.println(" 1-> Continue; 0-> Go To Back; ");
            if (scannerText.nextLine().equals("0")) {
                break;
            }

            List<CinemaManager> cinemaManagers = Storage.getStorage().cinemaManagerList.stream()
                    .filter(cinemaManager -> cinemaManager.getUser().equals(user)).toList();
            if (cinemaManagers.size() == 0) {
                System.out.println(" Cinema List is empty ");
            } else {
                CinemaManager cinemaManager = chosenCinemaForManager(cinemaManagers);
                if (cinemaManager == null) {
                    System.out.println(" This ID is not found ");
                } else {
                    Cinema cinema = cinemaManager.getCinema();
                    String name;
                    while (true) {
                        System.out.print(" Enter Movie name: ");
                        name = scannerText.nextLine();
                        if (Objects.isNull(name) || name.isBlank()) {
                            System.out.println(" Name is null or empty, Try again! ");
                        } else {
                            break;
                        }
                    }
                    String date;
                    while (true) {
                        System.out.print(" Enter Movie date: ");
                        date = scannerText.nextLine();
                        if (Objects.isNull(date) || date.isBlank()) {
                            System.out.println(" Date is null or empty, Try again! ");
                        } else {
                            break;
                        }
                    }
                    String description;
                    while (true) {
                        System.out.print(" Enter Movie description: ");
                        description = scannerText.nextLine();
                        if (Objects.isNull(description) || description.isBlank()) {
                            System.out.println(" Description is null or empty, Try again! ");
                        } else {
                            break;
                        }
                    }
                    String finalName = name;
                    String finalDate = date;
                    String finalDesc = description;
                    System.out.print(" Enter Price For Per Seat: ");
                    int price = scannerInt.nextInt();

                    if (isMovieName(finalName)) {
                        Movie movie = new Movie(Movie.getCount(), finalName, finalDate, finalDesc, cinema, price);
                        Storage.getStorage().movieList.add(movie);
                        System.out.println(" Successfully added Movie. Thank you! ");

                        for (int i = 0; i < cinema.getTheNumberOfSeat(); i++) {
                            Ticket ticket = new Ticket(Ticket.getCount(), movie, price, (i + 1), Status.AVAILABLE);
                            Storage.getStorage().ticketList.add(ticket);
                        }
                        System.out.println(" Successfully added " + cinema.getTheNumberOfSeat() + " Ticket, Thank you! ");
                    } else {
                        System.out.println(" This Movie's Name is already exists ");
                    }
                }
            }
        }
    }

    public boolean isMovieName(String name) {
        Movie movie1 = Storage.getStorage().movieList.stream()
                .filter(movie -> movie.getName().equals(name))
                .findFirst().orElse(null);
        return movie1 == null;
    }

    public CinemaManager chosenCinemaForManager(List<CinemaManager> cinemaManagers) {
        cinemaManagers.forEach(cinemaManager -> {
            System.out.println(cinemaManager.getCinema().toString());
            System.out.println(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
        });
        System.out.println(" Enter your chosen Cinema's ID: ");
        int chosen = scannerInt.nextInt();
        return cinemaManagers.stream()
                .filter(cinemaManager -> cinemaManager.getCinema().getId() == chosen)
                .findFirst().orElse(null);
    }


    static ManagerConsole managerConsole;

    public static ManagerConsole getManagerConsole() {
        if (managerConsole == null) {
            managerConsole = new ManagerConsole();
        }
        return managerConsole;
    }
}
