package Repository;

import Model.User;

import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

public class UserRepository implements IUserRepository {
    private final SortedSet<User> users;

    public UserRepository() {
        this.users = new TreeSet<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return users.stream().filter(user -> user.getUsername().equals(username)).findFirst().orElse(null);
    }

    @Override
    public void removeUser(User user) {
        users.remove(user);
    }

    @Override
    public SortedSet<User> getUsers() {
        return users;
    }
}
