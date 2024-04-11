package Service;

import Model.Country;
import Model.User;

import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

public class CountryService {
    private static SortedSet<Country> countries = new TreeSet<>();
    private CountryService() {}

    public static Country addCountry(String name) {
//        for (Country country: countries) {
//            if (country.getName().equals(name)) {
//                return null;
//            }
//        }
        Country country = new Country(name);
        countries.add(country);
        return country;
    }

    public static Country findCountryByName(String name) {
        for (Country country: countries) {
            if (country.getName().equals(name)) {
                return country;
            }
        }
        return null;
    }

    public static void showCountries() {
        for (Country country : countries) {
            System.out.println(country);
        }
    }
}
