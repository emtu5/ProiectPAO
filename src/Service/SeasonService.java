package Service;

import Model.*;
import Repository.ISeasonRepository;
import Repository.SeasonRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class SeasonService {
    private final SeasonRepository seasonRepository;
    private int currentSeasonId = 0;

    public SeasonService(){
        this.seasonRepository = new SeasonRepository();
    }

    public void addSeason (Season season) {
        seasonRepository.addSeason(season);
    }

    public void addEntry(Entry entry) {
        seasonRepository.addEntry(entry, currentSeasonId);
    }

    public ArrayList<Season> getSeasons() {
        return seasonRepository.getSeasons();
    }

    public void selectCurrentSeason(int seasonId) {
        currentSeasonId = seasonId;
    }

    public void addSong(User user, String s) {
        seasonRepository.addSong(user, s, currentSeasonId);
    }

    public void advanceSeason() {
        Season currentSeason = seasonRepository.getSeasonById(currentSeasonId);
        int n = currentSeason.getAutoQualifiers();
        int semifinals = currentSeason.getShows().size() - 1;
        if (currentSeason.getStatus() == SeasonStatus.SIGNUPS) {
            ArrayList<Entry> entries = currentSeason.getEntries();
            // code that randomises the entries, select the first n as auto-qualifiers
            Collections.shuffle(entries);
            if (semifinals == 0) {
                n = entries.size();
            }
            int semi_index = 0;
            for (int i = 0; i < entries.size(); i++) {
                if (i < n) {
                    currentSeason.addEntryToShow(entries.get(i), semifinals);
                }
                else {
                    // distribute the rest evenly to semifinals
                    currentSeason.addEntryToShow(entries.get(i), semi_index % semifinals);
                    semi_index++;
                }
            }

        }
        else if (currentSeason.getStatus() == SeasonStatus.IN_PROGRESS) {
            int currentShow = currentSeason.getCurrentShow();
            LiveShow show = currentSeason.getShows().get(currentShow);
            show.results();
            if (show instanceof Semifinal) {
                ArrayList<Entry> qualifiers = ((Semifinal) show).getQualifiers();
                for (Entry e : qualifiers) {
                    currentSeason.addEntryToShow(e, semifinals);
                }
            }
            currentSeason.advanceSeason();
        }
        else {
            System.out.println("Current season has already finished!");
        }
        seasonRepository.updateSeason(currentSeason, currentSeasonId);
    }

    public void displayEntriesInSeason() {
        Season currentSeason = seasonRepository.getSeasonById(currentSeasonId);
        for (Entry e : currentSeason.getEntries()) {
            System.out.println(e);
        }
    }

    public void displayEntriesInShow() {
        Season currentSeason = seasonRepository.getSeasonById(currentSeasonId);
        int currentShow = currentSeason.getCurrentShow();
        LiveShow show = currentSeason.getShows().get(currentShow);
        show.displayshow();
    }

    public void addVote (Vote vote) {
        Season currentSeason = seasonRepository.getSeasonById(currentSeasonId);
        currentSeason.addVote(vote);
        seasonRepository.updateSeason(currentSeason, currentSeasonId);
    }
}
