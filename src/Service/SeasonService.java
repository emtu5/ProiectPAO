package Service;

import Model.Season;

import java.util.ArrayList;

public class SeasonService {
    private static ArrayList<Season> seasons = new ArrayList<>();

    private SeasonService(){}

    public static Season addSeason (String seasonName, int shows) {
        if (shows < 1) {
            throw new IllegalArgumentException("Season must have at least one live show! (the final)");
        }
        Season season = new Season(seasonName);
        // logic for adding shows
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

    public static Season addEntry(Season season, String args) {
        // logic for making user entry
        return null;
    }

    public static ArrayList<Season> getSeasons() {
        return seasons;
    }
}
