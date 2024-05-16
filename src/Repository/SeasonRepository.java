package Repository;

import Model.Entry;
import Model.Season;
import Model.User;

import java.util.ArrayList;

public class SeasonRepository implements ISeasonRepository{
    private final ArrayList<Season> seasons = new ArrayList<>();

    @Override
    public void addSeason(Season season) {
        seasons.add(season);
    }

    @Override
    public void updateSeason(Season season, int id) {
        seasons.set(id, season);
    }

    @Override
    public Season getSeasonById(int id) {
        return seasons.stream().filter(season -> season.getSeasonId() == id).findFirst().orElse(null);
    }

    @Override
    public void removeSeason(Season season) {
        seasons.remove(season);
    }

    @Override
    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    @Override
    public void addEntry(Entry entry, int id) {
        seasons.get(id).addEntry(entry);
    }

    @Override
    public void addSong(User user, String song, int id) {
        seasons.get(id).addSong(user, song);
    }

    @Override
    public void advanceSeason(int id) {
        seasons.get(id).advanceSeason();
    }
}
