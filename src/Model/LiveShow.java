package Model;

import java.util.*;

public abstract class LiveShow {
    protected String showName;
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
        Set<User> users_voted = new HashSet<>();
        for (Vote v : votes) {
            users_voted.add(v.getUser());
        }
        Map<Country, Integer> temp = new HashMap<>();
        for (Entry e : score.keySet()) {
            if (users_voted.contains(e.getUser())) {
                temp.put(e.getCountry(), 0);
            }
        }
        // add the points from each vote
        ArrayList<Integer> points = votingSystem.getPoints();

        int i = 0;
        for (Vote v : votes) {
            ArrayList<Country> countries = v.getCountries();
            i = 0;
            for (Country c : temp.keySet()) {
                temp.put(c, temp.get(c) + points.get(i));
                i++;
            }
        }

        score.replaceAll((e, v) -> temp.getOrDefault(e.getCountry(), -1));

    }
}
