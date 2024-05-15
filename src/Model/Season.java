package Model;

import java.util.ArrayList;

public class Season {
    private final int seasonId;
    private static int latestSeasonId = 1;
    private String seasonName;
    private ArrayList<Entry> entries = new ArrayList<>();
    //a list of semifinals and a final
    private ArrayList<LiveShow> shows = new ArrayList<>();
    private SeasonStatus status = SeasonStatus.SIGNUPS;
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

    public void addEntry (Entry e) {
        this.entries.add(e);
    }

//    public void
    public void addAutoQualifier (User u) {
        this.autoQualifiers.add(u);
    }

    public void advanceSeason() {
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
