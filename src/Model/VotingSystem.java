package Model;

import java.util.ArrayList;

public class VotingSystem {
    private final ArrayList<Integer> points;
    public VotingSystem(ArrayList<Integer> points) {
        this.points = points;
    }

    public ArrayList<Integer> getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return points.toString();
    }
}
