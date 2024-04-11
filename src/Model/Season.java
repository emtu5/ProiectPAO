package Model;

import java.util.ArrayList;

public class Season {
    private final int seasonId;
    private static int latestSeasonId = 1;
    private String seasonName;
    private ArrayList<Entry> entries = new ArrayList<>();
    //a list of semifinals and a final
    private ArrayList<LiveShow> shows = new ArrayList<>();
    private ArrayList<User> autoQualifiers = new ArrayList<>();
    private SeasonStatus status = SeasonStatus.SIGNUPS;
    // the show it's currently at in that list
    private int currentShow = 0;

    public Season(String seasonName) {
        this.seasonId = latestSeasonId;
        latestSeasonId++;
        this.seasonName = seasonName;
    }

    public int getSeasonId() {
        return seasonId;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public ArrayList<Entry> getEntries() {
        return entries;
    }

    public ArrayList<User> getAutoQualifiers() {
        return autoQualifiers;
    }

    public SeasonStatus getStatus() {
        return status;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }

    public void addEntry (Entry e) {
        this.entries.add(e);
    }

//    public void
    public void addAutoQualifier (User u) {
        this.autoQualifiers.add(u);
    }

    public void endSignups() {
        this.status = SeasonStatus.IN_PROGRESS;
    }

    public void nextSeason() {
        currentShow++;
        if (currentShow == shows.size()) {
            this.status = SeasonStatus.FINISHED;
        }
    }

    public void addVote(Vote vote) {
        if (this.status == SeasonStatus.IN_PROGRESS) {
            this.shows.get(currentShow).addVote(vote);
        }
    }
}
