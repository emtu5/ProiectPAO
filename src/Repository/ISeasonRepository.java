package Repository;

import Model.Entry;
import Model.Season;
import Model.User;

import java.util.ArrayList;

public interface ISeasonRepository {
    void addSeason(Season season);
    void updateSeason(Season season, int id);
    Season getSeasonById(int id);
    void removeSeason(Season season);
    ArrayList<Season> getSeasons();
    void addEntry(Entry entry, int id);
    void addSong(User user, String song, int id);

    void advanceSeason(int currentSeasonId);
}
