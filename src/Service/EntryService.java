package Service;

import Model.Country;
import Model.Entry;
import Model.User;

import java.util.ArrayList;
import java.util.Objects;

public class EntryService {
    private static ArrayList<Entry> entries = new ArrayList<>();
    private EntryService() {}

    public static Entry addEntry(String args) {
        String[] params = args.split("\\s+", 3);
        User u = UserService.findUserByUsername(params[0]);
        Country c = CountryService.findCountryByName(params[1]);
        if (u == null || c == null || params[2].isEmpty()) {
            return null;
        }
        // no song may be submitted twice
        for (Entry entry: entries) {
            if (entry.getSong().equals(params[2])) {
                return null;
            }
        }
        Entry e = new Entry(u, c, params[2]);
        entries.add(e);
        return e;
    }

    public static ArrayList<Entry> getEntries() {
        return entries;
    }
}
