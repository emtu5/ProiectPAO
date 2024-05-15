package Model;

import java.util.ArrayList;

public class VotingSystem {
    private ArrayList<Integer> points;
    public VotingSystem() {
        points = new ArrayList<>();
    }

    public ArrayList<Integer> getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return points.toString();
    }
}
