package Runner;

import Model.*;

import Service.SeasonService;
import Service.UserService;

import java.util.Scanner;

public class ContestRunner {

    private final SeasonService seasonService;

    private final UserService userService;

    private final Scanner scanner = new Scanner(System.in);
    public ContestRunner() {
        this.seasonService = new SeasonService();
        this.userService = new UserService();
    }

    public void run() {
        while (true) {
            displayMenu();
        }
    }

    private void displayMenu() {
        System.out.println("""
                Welcome to Eurovision Simulator! Select an option from below:
                                
                0. Exit
                1. Add users to the contest
                2. Remove users from the contest
                3. Display all users
                4. Start season
                5. Check current season
                6. Add user to current season
                7. Add entry to current season
                8. Advance current season
                9. Check current show
                10. Display all current entries from season
                11. Display all current entries from show
                12. Add vote to current show
                13. Display all seasons
                """);
        int option = scanner.nextInt();
        switch (option) {
            case 0 -> System.exit(0);
            case 1-> addUser();
            case 2-> removeUser();
            case 3-> displayUsers();
            case 4-> startSeason();
            case 5-> checkCurrentSeason();
            case 6-> addUserToCurrentSeason();
            case 7-> addEntryToCurrentSeason();
            case 8-> advanceCurrentSeason();
            case 9-> checkCurrentShow();
            case 10-> displayCurrentEntriesFromSeason();
            case 11-> displayCurrentEntriesFromShow();
            case 12-> addVoteToCurrentShow();
            case 13-> displayAllSeasons();
            default -> System.out.println("Invalid option");
        }
    }

    private void addUser() {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter email:");
        String email = scanner.nextLine();
        User user = new User(username, email);
        contestService.addUser(user);
    }

    private void removeUser() {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        User user = userService.findUserByUsername(username);
        if (user == null) {
            System.out.println("User not found");
            return;
        }
        contestService.removeUser(user);
    }

    private void displayUsers() {
        System.out.println(contestService.getUsers());
    }

    private void startSeason() {
        System.out.println("Enter season name:");
        String seasonName = scanner.nextLine();
        System.out.println("Enter number of shows:");
        int shows = scanner.nextInt();
        Season season = new Season(seasonName);
        seasonService.addSeason(season);
    }

    private void checkCurrentSeason() {
        Season season = seasonService.getCurrentSeason();
        System.out.println(season);
    }

    private void addUserToCurrentSeason() {
        System.out.println("Enter username and country seprataed by comma");
        String[] usernames = scanner.nextLine().split(",");
        Season season = seasonService.getCurrentSeason();
        seasonService.addAutoQualifiers(season, usernames);
    }

    private void addEntryToCurrentSeason() {
        System.out.println("Enter entry details:");
        String entryDetails = scanner.nextLine();
        Season season = seasonService.getCurrentSeason();
        seasonService.addEntry(season, entryDetails);
    }

    private void advanceCurrentSeason() {
        seasonService.advanceSeason();
    }

    private void checkCurrentShow() {
        Season season = seasonService.getCurrentSeason();
        LiveShow show = season.getCurrentShow();
        System.out.println(show);
    }

    private void displayCurrentEntriesFromSeason() {
        Season season = seasonService.getCurrentSeason();
        SeasonService.showEntriesInSeason(season);
    }

    private void displayCurrentEntriesFromShow() {
        Season season = seasonService.getCurrentSeason();
        LiveShow show = season.getCurrentShow();
        show.showEntries();
    }

    private void addVoteToCurrentShow() {
        System.out.println("Enter vote details:");
        String voteDetails = scanner.nextLine();
        Season season = seasonService.getCurrentSeason();
        LiveShow show = season.getCurrentShow();
        show.addVote(voteDetails);
    }

    private void displayAllSeasons() {
        seasonService.getSeasons();
    }
}
