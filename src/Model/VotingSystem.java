package Model;

import java.util.ArrayList;

public class VotingSystem {
    private String name;
    private final ArrayList<Integer> points;

    public VotingSystem(String name, ArrayList<Integer> points) {
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Integer> getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return points.toString();
    }
}
