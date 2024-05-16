package Model;

import java.util.ArrayList;

public class Season {
    private final int seasonId;
    private static int latestSeasonId = 0;
    private String seasonName;
    private ArrayList<Entry> entries = new ArrayList<>();
    //a list of semifinals and a final
    private ArrayList<LiveShow> shows = new ArrayList<>();
    private VotingSystem votingSystemSemifinals;
    private VotingSystem votingSystemFinal;
    private int autoQualifiers;
    private int qualifiers;
    private SeasonStatus status = SeasonStatus.SIGNUPS;
    private int currentShow = 0;

    public Season(String seasonName, int shows, VotingSystem semi, VotingSystem _final,
                  int autoqualifiers, int qualifiers) {
        this.seasonId = latestSeasonId;
        latestSeasonId++;
        this.seasonName = seasonName;
        this.votingSystemSemifinals = semi;
        this.votingSystemFinal = _final;
        for (int i = 0; i < shows - 1; i++) {
            this.shows.add(new Semifinal(STR."Semifinal \{i + 1}", semi, qualifiers));
        }
        this.shows.add(new Final("Final", _final));
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

    public int getAutoQualifiers() {
        return autoQualifiers;
    }

    public SeasonStatus getStatus() {
        return status;
    }

    public void addEntry (Entry e) {
        this.entries.add(e);
    }

    public void advanceSeason() {
        currentShow++;
        if (currentShow == shows.size()) {
            currentShow--;
            this.status = SeasonStatus.FINISHED;
        }
    }

    public void addVote(Vote vote) {
        if (this.status == SeasonStatus.IN_PROGRESS) {
            this.shows.get(currentShow).addVote(vote);
        }
    }

    @Override
    public String toString() {
        return STR."Season \{this.seasonId} | \{this.seasonName} | No. of participants: \{this.entries.size()}";
    }

    public void addSong(User user, String song) {
        for (Entry entry: entries) {
            if (entry.getUser().equals(user)) {
                entry.setSong(song);
                return;
            }
        }
    }

    public int getQualifiers() {
        return qualifiers;
    }

    public ArrayList<LiveShow> getShows() {
        return shows;
    }

    public void addEntryToShow(Entry entry, int show) {
        if (show < shows.size() && show >= 0) {
            shows.get(show).addEntry(entry);
        }
    }
    public int getCurrentShow() {
        return currentShow;
    }
    public void addVoteToShow(Vote vote, int show) {
        if (show < shows.size() && show >= 0) {
            shows.get(show).addVote(vote);
        }
    }

    public void setStatus(SeasonStatus seasonStatus) {
        this.status = seasonStatus;
    }
}
