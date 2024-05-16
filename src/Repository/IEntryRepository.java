package Repository;

import Model.Entry;

import java.util.ArrayList;

public interface IEntryRepository {
    void addEntry(Entry entry);
    Entry getEntryBySong(String song);
    void removeEntry(Entry entry);
    ArrayList<Entry> getEntries();
}
