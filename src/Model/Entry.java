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
}
