package Repository;

import Model.VotingSystem;

import java.util.ArrayList;

public interface IVotingSystemRepository {
    void addVotingSystem(VotingSystem votingSystem);
    VotingSystem getVotingSystemByName(String name);
    ArrayList<VotingSystem> getVotingSystems();
    void removeVotingSystem(VotingSystem votingSystem);
    void updateVotingSystem(VotingSystem votingSystem, ArrayList<Integer> votingSystemPoints);
}
