package Repository;

import Model.Country;

import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

public class CountryRepository implements ICountryRepository {
    private final SortedSet<Country> countries;

    public CountryRepository() {
        this.countries = new TreeSet<>();
    }
    @Override
    public void addCountry(Country country) {
        countries.add(country);
    }

    @Override
    public void removeCountry(Country country) {
        countries.remove(country);
    }

    @Override
    public Country getCountryByName(String name) {
        return countries.stream().filter(country -> country.getName().equals(name)).findFirst().orElse(null);
    }


    @Override
    public SortedSet<Country> getCountries() {
        return null;
    }
}
