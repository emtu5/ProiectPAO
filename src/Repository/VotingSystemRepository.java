package Repository;

import Model.User;
import Model.VotingSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.*;

public class VotingSystemRepository implements IVotingSystemRepository {
    public VotingSystemRepository () {
        addVotingSystemsFromFile();
    }

    private void addVotingSystemsFromFile() {
        Connection conn = null;
        PreparedStatement votingSystemStmt = null;
        try {
            conn = DbConstants.getConnection();
            String insertVotingSystem = "INSERT INTO VotingSystem(name, points) VALUES(?, ?);";
            votingSystemStmt = conn.prepareStatement(insertVotingSystem);

            File countries = new File("votingsystems.txt");
            Scanner fileScanner = new Scanner(countries);
            while (fileScanner.hasNextLine()) {
                String name = fileScanner.nextLine();
                String[] pointTotals = fileScanner.nextLine().split(",");
                ArrayList<Integer> votingSystemPoints = new ArrayList<>();
                for (String s : pointTotals) {
                    votingSystemPoints.add(Integer.parseInt(s));
                }
                Array points = conn.createArrayOf("INTEGER", votingSystemPoints.toArray());
                votingSystemStmt.setString(1, name);
                votingSystemStmt.setArray(2, points);
                votingSystemStmt.executeUpdate();
            }
            fileScanner.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                assert votingSystemStmt != null;
                votingSystemStmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void addVotingSystem(VotingSystem votingSystem) {
        Connection conn = null;
        PreparedStatement votingSystemStmt = null;
        try {
            conn = DbConstants.getConnection();
            String insertVotingSystem = "INSERT INTO VotingSystem(name, points) VALUES(?, ?);";
            votingSystemStmt = conn.prepareStatement(insertVotingSystem);
            votingSystemStmt.setString(1, votingSystem.getName());
            Array points = conn.createArrayOf("INTEGER", votingSystem.getPoints().toArray());
            votingSystemStmt.setArray(2, points);
            votingSystemStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert votingSystemStmt != null;
                votingSystemStmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public VotingSystem getVotingSystemByName(String name) {
        Connection conn = null;
        PreparedStatement votingSystemStmt = null;
        ResultSet rs = null;
        try {
            conn = DbConstants.getConnection();
            String getVotingSystem = "SELECT * FROM VotingSystem WHERE name = ?;";
            votingSystemStmt = conn.prepareStatement(getVotingSystem);
            votingSystemStmt.setString(1, name);
            rs = votingSystemStmt.executeQuery();
            if (rs.next()) {
                Integer[] points = (Integer[]) rs.getArray("points").getArray();
                return new VotingSystem(rs.getString("name"), new ArrayList<>(Arrays.asList(points)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert rs != null;
                rs.close();
                votingSystemStmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public ArrayList<VotingSystem> getVotingSystems() {
        Connection conn = null;
        PreparedStatement votingSystemStmt = null;
        ResultSet rs = null;
        try {
            conn = DbConstants.getConnection();
            String getVotingSystems = "SELECT * FROM VotingSystem;";
            votingSystemStmt = conn.prepareStatement(getVotingSystems);
            rs = votingSystemStmt.executeQuery();
            ArrayList<VotingSystem> votingSystems = new ArrayList<>();
            while (rs.next()) {
                Integer[] points = (Integer[]) rs.getArray("points").getArray();
                VotingSystem votingSystem = new VotingSystem(rs.getString("name"), new ArrayList<>(Arrays.asList(points)));
                votingSystems.add(votingSystem);
            }
            return votingSystems;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert rs != null;
                rs.close();
                votingSystemStmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void removeVotingSystem(VotingSystem votingSystem) {
        Connection conn = null;
        PreparedStatement votingSystemStmt = null;
        try {
            conn = DbConstants.getConnection();
            String removeVotingSystem = "DELETE FROM VotingSystem WHERE name = ?;";
            votingSystemStmt = conn.prepareStatement(removeVotingSystem);
            votingSystemStmt.setString(1, votingSystem.getName());
            votingSystemStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert votingSystemStmt != null;
                votingSystemStmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateVotingSystem(VotingSystem votingSystem, ArrayList<Integer> votingSystemPoints) {
        Connection conn = null;
        PreparedStatement votingSystemStmt = null;
        try {
            conn = DbConstants.getConnection();
            String updateVotingSystem = "UPDATE VotingSystem SET points = ? WHERE name = ?;";
            votingSystemStmt = conn.prepareStatement(updateVotingSystem);
            Array points = conn.createArrayOf("INTEGER", votingSystemPoints.toArray());
            votingSystemStmt.setArray(1, points);
            votingSystemStmt.setString(2, votingSystem.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert votingSystemStmt != null;
                votingSystemStmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
