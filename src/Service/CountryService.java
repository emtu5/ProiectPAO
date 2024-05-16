package Service;

import Model.Country;
import Model.User;
import Repository.CountryRepository;

import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

public class CountryService {
    private final CountryRepository countryRepository;
    public CountryService() {
        this.countryRepository = new CountryRepository();
    }

    public void addCountry(Country country) {
        countryRepository.addCountry(country);
    }

    public void removeCountry(Country country) {
        countryRepository.removeCountry(country);
    }
    public Country findCountryByName(String name) {
        return countryRepository.getCountryByName(name);
    }

    public SortedSet<Country> getCountries() {
        return countryRepository.getCountries();
    }
    public void showCountries() {
        for (Country country : this.getCountries()) {
            System.out.println(country);
        }
    }
}
