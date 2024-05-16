package Repository;

import Model.Country;

import java.util.ArrayList;
import java.util.SortedSet;

public interface ICountryRepository {
    void addCountry(Country country);
    void removeCountry(Country country);
    Country getCountryByName(String name);
    SortedSet<Country> getCountries();
}
