package Repository;

import Model.Country;

import java.util.ArrayList;

public interface ICountryRepository {
    void addCountry(Country country);
    void removeCountry(Country country);
    ArrayList<Country> getCountries();
}
