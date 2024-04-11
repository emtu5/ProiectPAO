package Service;

import Model.User;

import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

public class UserService {
    private static final SortedSet<User> users = new TreeSet<>();

    private UserService(){}

    public static User addUser(String username, String email) {
//        for (User user: users) {
//            if (user.getUsername().equals(username)) {
//                return null;
//            }
//        }
        User user = new User(username, email);
        users.add(user);
        return user;
    }

    public static User findUserByUsername(String username) {
        for (User user: users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static SortedSet<User> getUsers() {
        return users;
    }

    public static void showUsers() {
        for (User user: users) {
            System.out.println(user);
        }
    }
}
