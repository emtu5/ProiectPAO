package Model;

import java.util.ArrayList;

public class Vote {
    private User user;
    private ArrayList<Country> countries;
    public Vote(User user) {
        this.user = user;
    }
    public void addCountry(Country country) {
        countries.add(country);
    }
}
