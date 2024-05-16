package Repository;

import Model.User;

import java.util.ArrayList;
import java.util.SortedSet;

public interface IUserRepository {
    void addUser(User user);
    User getUserByUsername(String username);
    void removeUser(User user);
    SortedSet<User> getUsers();
}
