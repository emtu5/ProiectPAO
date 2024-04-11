package Service;

import Model.Country;
import Model.Entry;
import Model.User;
import Model.Vote;

import java.util.ArrayList;

public class VoteService {
    private static ArrayList<Vote> votes = new ArrayList<>();
    private VoteService() {}

    public static Vote addVote(String args) {
        String[] params = args.split("\\s+");
        User u = UserService.findUserByUsername(params[0]);
        if (u == null) {
            return null;
        }
        Vote v = new Vote(u);
        for (int i = 1; i < params.length; i++) {
            Country c = CountryService.findCountryByName(params[i]);
            if (c == null) {
                continue;
            }
            v.addCountry(c);
        }
        return v;
    }
}
