package Service;

import Model.VotingSystem;
import Repository.VotingSystemRepository;

import java.util.ArrayList;

public class VotingSystemService {
    private final VotingSystemRepository votingSystemRepository;
    public VotingSystemService() {
        this.votingSystemRepository = new VotingSystemRepository();
    }
    public void addVotingSystem(VotingSystem votingSystem) {
        votingSystemRepository.addVotingSystem(votingSystem);
    }

    public VotingSystem getVotingSystemByName(String name) {
        return votingSystemRepository.getVotingSystemByName(name);
    }

    public void removeVotingSystem(VotingSystem votingSystem) {
        votingSystemRepository.removeVotingSystem(votingSystem);
    }

    public void updateVotingSystem(VotingSystem votingSystem, ArrayList<Integer> votingSystemPoints) {
        votingSystemRepository.updateVotingSystem(votingSystem, votingSystemPoints);
    }
}
