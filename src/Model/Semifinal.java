package Model;


import java.util.ArrayList;

public class Semifinal extends LiveShow{
    private int qualifiers;
    public Semifinal(String showName, VotingSystem votingSystem, int qualifiers) {
        super(showName, votingSystem);
        this.qualifiers = qualifiers;
    }

    public ArrayList<Entry> getQualifiers() {
        return null;
    }
    @Override
    public void results() {
        //W.I.P
        System.out.println("This is a semifinal!");
    }
}
