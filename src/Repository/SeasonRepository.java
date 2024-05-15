package Repository;

import Model.Season;

import java.util.ArrayList;

public class SeasonRepository {
    private static final ArrayList<Season> seasons = new ArrayList<>();
    public void addSeason(Season season) {
        seasons.add(season);
    }
    public ArrayList<Season> getSeasons() {
        return seasons;
    };
}
