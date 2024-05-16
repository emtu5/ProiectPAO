package Model;

public class Final extends LiveShow {

    public Final(String showName, VotingSystem votingSystem) {
        super(showName, votingSystem);
    }

    @Override
    public void displayshow() {
        System.out.println(showName + " - Number of entries: " + score.size());
        for (Entry e : score.keySet()) {
            System.out.println(e + " - " + (score.get(e) != -1 ? score.get(e) : "N/A"));
        }
    }
}
