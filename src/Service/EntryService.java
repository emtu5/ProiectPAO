package Service;

import Model.Country;
import Model.Entry;
import Model.User;
import Repository.EntryRepository;

import java.util.ArrayList;
import java.util.Objects;

public class EntryService {
    private final EntryRepository entryRepository;
    public EntryService() {
        this.entryRepository = new EntryRepository();
    }

    public void addEntry(Entry entry) {
        entryRepository.addEntry(entry);
    }

    public ArrayList<Entry> getEntries() {
        return entryRepository.getEntries();
    }

    public Entry getEntryBySong(String song) {
        return entryRepository.getEntryBySong(song);
    }
}
