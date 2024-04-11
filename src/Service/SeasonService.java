package Service;

import Model.Entry;
import Model.Season;
import Model.SeasonStatus;

import java.util.ArrayList;

public class SeasonService {
    private static ArrayList<Season> seasons = new ArrayList<>();

    private SeasonService(){}

    public static Season addSeason (String seasonName, int shows) {
        if (shows < 1) {
            throw new IllegalArgumentException("Season must have at least one live show! (the final)");
        }
        Season season = new Season(seasonName);
        seasons.add(season);
        return season;
    }

    public static Season findSeasonById(int id) {
        for (Season season : seasons) {
            if (season.getSeasonId() == id) {
                return season;
            }
        }
        return null;
    }

    public static void addEntry(Season season, String args) {
        if (season.getStatus() != SeasonStatus.SIGNUPS) {
            return;
        }
        Entry e = EntryService.addEntry(args);
        if (e == null) {
            return;
        }
        for (Entry entry: season.getEntries()) {
            if (entry.getUser().equals(e.getUser())) {
                return;
            }
            if (entry.getCountry().equals(e.getCountry())) {
                return;
            }
        }
        season.addEntry(e);
    }

    public static ArrayList<Season> getSeasons() {
        return seasons;
    }
}
