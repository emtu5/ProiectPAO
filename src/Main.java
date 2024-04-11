import Model.*;
import Service.*;

public class Main {
    public static void main(String[] args) {
        User test1 = UserService.addUser("emtu", "test123@gmail.com");
        User test2 = UserService.addUser("m2", "test123@gmail.com");
        User test3 = UserService.addUser("anomalous", "expectopatronum@gmail.com");
        CountryService.addCountry("Romania");
        CountryService.addCountry("USA");
        CountryService.addCountry("France");
        CountryService.addCountry("USA");

        CountryService.showCountries();
        UserService.showUsers();

        User test4 = UserService.findUserByUsername("anomalous");
//        System.out.println(test4);
        Season no1 = SeasonService.addSeason("Season 1 - BUCHAREST", 1);
        SeasonService.addEntry(no1, "emtu Romania Fly Project - Mandala");
    }
}
