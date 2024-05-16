package Model;

import java.util.ArrayList;

public class Vote {
    private User user;
    private ArrayList<Country> countries;
    public Vote(User user, ArrayList<Country> countries) {
        this.user = user;
        this.countries = countries;
    }

    public User getUser() {
        return user;
    }

    public ArrayList<Country> getCountries() {
        return countries;
    }

    public void addCountry(Country country) {
        countries.add(country);
    }
}
