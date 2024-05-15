package Model;

import java.util.ArrayList;
import java.util.SortedMap;
import java.util.TreeMap;

public abstract class LiveShow {
    protected String showName;
    // to decide running order
    protected ArrayList<Entry> entries;
    protected SortedMap<Entry, Integer> score = new TreeMap<>();
    protected ArrayList<Vote> votes;
    protected VotingSystem votingSystem;

    protected LiveShow(String showName, VotingSystem votingSystem) {
        this.showName = showName;
        this.votingSystem = votingSystem;
    }

    public void addEntry(Entry entry) {
        this.score.put(entry, -1);
    }
    public void addVote(Vote vote) {
        this.votes.add(vote);
    }
    public void results() {
        // check who hasn't voted (they are disqualified and their score is -1)
        for (Vote v : votes) {
            score.put(v.getUser(), 0);
        }
        // add the points from each vote
        ArrayList<Integer> points = votingSystem.getPoints();
        int i = 0;
        for (Vote v : votes) {
            ArrayList<Country> countries = v.getCountries();
            for (Entry e : score.keySet()) {
                if (e.getCountry().equals(countries.get(i))) {
                    score.put(e, score.get(e) + points.get(i));
                    i++;
                    break;
                }
            }
        }
    }
    public abstract void running_order_shuffle();
}
