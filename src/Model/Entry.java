package Model;

public class Entry {
    private User user;
    private Country country;
    private String song;
    public Entry(User user, Country country, String song) {
        this.user = user;
        this.country = country;
        this.song = song;
    }

    public User getUser() {
        return user;
    }

    public Country getCountry() {
        return country;
    }

    public String getSong() {
        return song;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void setSong(String song) {
        this.song = song;
    }
}
