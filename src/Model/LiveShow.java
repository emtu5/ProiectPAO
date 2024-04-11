package Model;

import java.util.ArrayList;
import java.util.SortedMap;
import java.util.TreeMap;

public abstract class LiveShow {
    protected String showName;
    protected SortedMap<Entry, Integer> score = new TreeMap<>();
    protected ArrayList<Vote> votes;
    protected VotingSystem votingSystem = VotingSystem.top10_12_1;

    protected LiveShow(String showName) {
        this.showName = showName;
    }

    public void addVote(Vote vote) {
        this.votes.add(vote);
    }
    public abstract void results();
}
