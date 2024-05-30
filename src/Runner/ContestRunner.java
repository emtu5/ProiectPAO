package Runner;

import Model.*;

import Service.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.SortedSet;

public class ContestRunner {

    private final UserService userService;
    private final CountryService countryService;
    private final EntryService entryService;
    private final SeasonService seasonService;
    private final AuditLogService auditLogService;
    private final VotingSystemService votingSystemService;
    private final Scanner scanner;
    public ContestRunner() {
        this.userService = new UserService();
        this.countryService = new CountryService();
        this.entryService = new EntryService();
        this.seasonService = new SeasonService();
        this.auditLogService = new AuditLogService();
        this.votingSystemService = new VotingSystemService();
        this.scanner = new Scanner(System.in);
    }

    public void run() throws FileNotFoundException {
        File countries = new File("countries.txt");
        Scanner fileScanner = new Scanner(countries);
        while (fileScanner.hasNextLine()) {
            String country = fileScanner.nextLine();
            Country c = new Country(country);
            countryService.addCountry(c);
        }
        fileScanner.close();
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
                5. Select current season
                6. Add user to current season (username, country, song - optional)
                7. Add song to participant (users who haven't submitted a song)
                8. Advance current season
                9. Display all current entries from season
                10. Display all current entries from show
                11. Add vote to current show
                12. Display all seasons
                13. Add a voting system
                14. Remove voting system
                15. Update voting system
                """);
        int option = Integer.parseInt(scanner.nextLine());
        switch (option) {
            case 0 -> System.exit(0);
            case 1-> addUser();
            case 2-> removeUser();
            case 3-> displayUsers();
            case 4-> startSeason();
            case 5-> selectCurrentSeason();
            case 6-> addUserToCurrentSeason();
            case 7-> addEntryToCurrentSeason();
            case 8-> advanceCurrentSeason();
            case 9-> displayCurrentEntriesFromSeason();
            case 10-> displayCurrentEntriesFromShow();
            case 11-> addVoteToCurrentShow();
            case 12-> displayAllSeasons();
            case 13-> addVotingSystem();
            case 14-> removeVotingSystem();
            case 15-> updateVotingSystem();
            default -> System.out.println("Invalid option");
        }
    }

    private void addUser() {
        auditLogService.log("addUser");
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter email:");
        String email = scanner.nextLine();
        User user = new User(username, email);
        userService.addUser(user);
    }

    private void removeUser() {
        auditLogService.log("removeUser");
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        User user = userService.findUserByUsername(username);
        if (user == null) {
            System.out.println("User not found");
            return;
        }
        userService.removeUser(user);
    }

    private void displayUsers() {
        auditLogService.log("displayUsers");
        SortedSet<User> users = userService.getUsers();
        for (User user: users) {
            System.out.println(user);
        }
    }

    private void startSeason() {
        auditLogService.log("startSeason");
        System.out.println("Enter season name:");
        String seasonName = scanner.nextLine();
        System.out.println("Enter number of shows (minimum 1, a final):");
        int shows = Integer.parseInt(scanner.nextLine());
        if (shows < 1) {
            System.out.println("Invalid number of shows");
            return;
        }
        int autoqualifiers = 0;
        int qualifiersSemi = 0;
        if (shows > 1) {
            System.out.println("Enter number of auto-qualifiers:");
            autoqualifiers = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter number of qualifiers per semifinal:");
            qualifiersSemi = Integer.parseInt(scanner.nextLine());
        }
        System.out.println("Enter voting system name for semifinal(s)");
        String semifinal = scanner.nextLine();
        VotingSystem votingSystemSemifinal = votingSystemService.getVotingSystemByName(semifinal);
        if (votingSystemSemifinal == null) {
            System.out.println("Voting system not found");
            return;
        }
        System.out.println("Enter voting system name for final");
        String final_ = scanner.nextLine();
        VotingSystem votingSystemFinal = votingSystemService.getVotingSystemByName(final_);
        if (votingSystemFinal == null) {
            System.out.println("Voting system not found");
            return;
        }

        Season season = new Season(seasonName, shows, votingSystemSemifinal, votingSystemFinal,
                autoqualifiers, qualifiersSemi);
        seasonService.selectCurrentSeason(season.getSeasonId());
        seasonService.addSeason(season);
    }

    private void selectCurrentSeason() {
        auditLogService.log("selectCurrentSeason");
        System.out.println("Enter season id:");
        int seasonId = Integer.parseInt(scanner.nextLine());
        seasonService.selectCurrentSeason(seasonId);
    }

    private void addUserToCurrentSeason() {
        auditLogService.log("addUserToCurrentSeason");
        System.out.println("Enter username, country and song (optional) separated by comma");
        String[] usercountry = scanner.nextLine().split(",");
        if (usercountry.length != 2 && usercountry.length != 3) {
            System.out.println("Invalid input");
            return;
        }
        User user = userService.findUserByUsername(usercountry[0]);
        if (user == null) {
            System.out.println("User not found");
            return;
        }
        Country country = countryService.findCountryByName(usercountry[1]);
        if (country == null) {
            System.out.println("Country not found");
            return;
        }
        String song = usercountry.length == 3 ? usercountry[2] : "";
        if (entryService.getEntryBySong(song) != null) {
            System.out.println("Song has already been submitted before. User must submit different song");
            song = "";
        }
        Entry entry = new Entry(user, country, song);
        seasonService.addEntry(entry);
    }

    private void addEntryToCurrentSeason() {
        auditLogService.log("addEntryToCurrentSeason");
        System.out.println("Enter user and song separated by comma");
        String[] usersong = scanner.nextLine().split(",");
        if (usersong.length != 2) {
            System.out.println("Invalid input");
            return;
        }
        User user = userService.findUserByUsername(usersong[0]);
        if (user == null) {
            System.out.println("User not found");
            return;
        }
        seasonService.addSong(user, usersong[1]);
    }

    private void advanceCurrentSeason() {
        auditLogService.log("advanceCurrentSeason");
        seasonService.advanceSeason();
    }

    private void displayCurrentEntriesFromSeason() {
        auditLogService.log("displayCurrentEntriesFromSeason");
        seasonService.displayEntriesInSeason();
    }

    private void displayCurrentEntriesFromShow() {
        auditLogService.log("displayCurrentEntriesFromShow");
        seasonService.displayEntriesInShow();
    }

    private void addVoteToCurrentShow() {
        auditLogService.log("addVoteToCurrentShow");
        System.out.println("Enter vote details (user and countries separated by comma)");
        String[] votes = scanner.nextLine().split(",");
        User user = userService.findUserByUsername(votes[0]);
        if (user == null) {
            System.out.println("User not found");
            return;
        }
        ArrayList<Country> countries = new ArrayList<>();
        for (int i = 1 ; i < votes.length ; i++) {
            Country country = countryService.findCountryByName(votes[i]);
            if (country == null) {
                System.out.println("Country not found");
                return;
            }
            countries.add(country);
        }
        Vote vote = new Vote(user, countries);
        seasonService.addVote(vote);
    }

    private void displayAllSeasons() {
        auditLogService.log("displayAllSeasons");
        ArrayList<Season> seasons = seasonService.getSeasons();
        for (Season season: seasons) {
            System.out.println(season);
        }
    }

    private void addVotingSystem() {
        auditLogService.log("addVotingSystem");
        System.out.println("Enter name for voting system");
        String votingSystemName = scanner.nextLine();
        System.out.println("Enter numbers for system (numbers representing point totals separated by comma");
        String[] pointTotals = scanner.nextLine().split(",");
        ArrayList<Integer> votingSystemPoints = new ArrayList<>();
        for (String s : pointTotals) {
            votingSystemPoints.add(Integer.parseInt(s));
        }
        VotingSystem votingSystem = new VotingSystem(votingSystemName, votingSystemPoints);
        votingSystemService.addVotingSystem(votingSystem);
    }

    private void removeVotingSystem() {
        auditLogService.log("removeVotingSystem");
        System.out.println("Enter voting system name:");
        String name = scanner.nextLine();
        VotingSystem votingSystem = votingSystemService.getVotingSystemByName(name);
        if (votingSystem == null) {
            System.out.println("Voting system not found");
            return;
        }
        votingSystemService.removeVotingSystem(votingSystem);
    }

    private void updateVotingSystem() {
        auditLogService.log("updateVotingSystem");
        System.out.println("Enter voting system name:");
        String name = scanner.nextLine();
        VotingSystem votingSystem = votingSystemService.getVotingSystemByName(name);
        if (votingSystem == null) {
            System.out.println("Voting system not found");
            return;
        }
        System.out.println("Enter new numbers for system (numbers representing point totals separated by comma");
        String[] pointTotals = scanner.nextLine().split(",");
        ArrayList<Integer> votingSystemPoints = new ArrayList<>();
        for (String s : pointTotals) {
            votingSystemPoints.add(Integer.parseInt(s));
        }
        votingSystemService.updateVotingSystem(votingSystem, votingSystemPoints);
    }
}
