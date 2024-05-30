package Repository;

import Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

public class UserRepository implements IUserRepository {

    public void addUser(User user) {
        Connection conn = null;
        PreparedStatement userStmt = null;
        try {
            conn = DbConstants.getConnection();
            String insertUser = "INSERT INTO User_(name, email) VALUES(?, ?);";
            userStmt = conn.prepareStatement(insertUser);
            userStmt.setString(1, user.getUsername());
            userStmt.setString(2, user.getEmail());
            userStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert userStmt != null;
                userStmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public User getUserByUsername(String username) {
        Connection conn = null;
        PreparedStatement userStmt = null;
        ResultSet rs = null;
        try {
            conn = DbConstants.getConnection();
            String findUser = "SELECT * FROM User_ WHERE name = ?;";
            userStmt = conn.prepareStatement(findUser);
            userStmt.setString(1, username);
            rs = userStmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getString("name"), rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert rs != null;
                rs.close();
                userStmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void removeUser(User user) {
        Connection conn = null;
        PreparedStatement userStmt = null;
        try {
            conn = DbConstants.getConnection();
            String removeUser = "DELETE FROM User_ WHERE name = ?;";
            userStmt = conn.prepareStatement(removeUser);
            userStmt.setString(1, user.getUsername());
            userStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert userStmt != null;
                userStmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public SortedSet<User> getUsers() {
        Connection conn = null;
        PreparedStatement userStmt = null;
        ResultSet rs = null;
        try {
            conn = DbConstants.getConnection();
            String findUsers = "SELECT * FROM User_;";
            userStmt = conn.prepareStatement(findUsers);
            rs = userStmt.executeQuery();
            SortedSet<User> users = new TreeSet<User>();
            while (rs.next()) {
                User user = new User(rs.getString("name"), rs.getString("email"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert rs != null;
                rs.close();
                userStmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
