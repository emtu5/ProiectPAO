package Repository;

import Model.Entry;

import java.util.ArrayList;

public interface IEntryRepository {
    void addEntry(Entry entry);
    void removeEntry(Entry entry);
    ArrayList<Entry> getEntries();
}
