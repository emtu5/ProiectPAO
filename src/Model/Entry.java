package Model;

public class Entry implements Comparable<Entry>{
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

    @Override
    public String toString() {
        return STR."\{this.user.getUsername()} | \{this.country} | \{this.song}";
    }

    @Override
    public int compareTo(Entry o) {
        return this.country.compareTo(o.country);
    }
}
