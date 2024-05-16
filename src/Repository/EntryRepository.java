package Repository;

import Model.Entry;

import java.util.ArrayList;


public class EntryRepository implements IEntryRepository {
    private final ArrayList<Entry> entries;

    public EntryRepository() {
        this.entries = new ArrayList<>();
    }
    @Override
    public void addEntry(Entry entry) {
        entries.add(entry);
    }

    @Override
    public Entry getEntryBySong(String song) {
        return entries.stream().filter(entry -> entry.getSong().equals(song)).findFirst().orElse(null);
    }

    @Override
    public void removeEntry(Entry entry) {
        entries.remove(entry);
    }

    @Override
    public ArrayList<Entry> getEntries() {
        return entries;
    }
}
