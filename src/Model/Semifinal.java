package Model;


import java.util.ArrayList;
import java.util.NavigableMap;
import java.util.TreeMap;

public class Semifinal extends LiveShow{
    private int qualifiers;
    public Semifinal(String showName, VotingSystem votingSystem, int qualifiers) {
        super(showName, votingSystem);
        this.qualifiers = qualifiers;
    }

    public ArrayList<Entry> getQualifiers() {
        ArrayList<Entry> result = new ArrayList<>();
        TreeMap<Integer, Entry> sorted = new TreeMap<>();
        for (Entry e : score.keySet()) {
            sorted.put(score.get(e), e);
        }
        NavigableMap<Integer, Entry> reversed = sorted.descendingMap();
        for (int i = 0; i < qualifiers; i++) {
            Entry e = reversed.get(reversed.firstKey());
            result.add(e);
            reversed.remove(reversed.firstKey());
        }
        return result;
    }

    @Override
    public void displayshow() {
        System.out.println(STR."\{showName} - Number of qualifiers: \{qualifiers} out of \{score.size()}");
        for (Entry e : score.keySet()) {
            System.out.println(STR."\{e} - \{score.get(e) != -1 ? score.get(e) : "N/A"}");
        }
    }
}
