package Model;

import java.util.ArrayList;
import java.util.SortedMap;
import java.util.TreeMap;

public abstract class LiveShow {
    protected String showName;
    protected SortedMap<Entry, Integer> score = new TreeMap<>();
    protected ArrayList<Vote> votes;

    protected LiveShow(String showName) {
        this.showName = showName;
    }

    public abstract void results();

//    public
}
