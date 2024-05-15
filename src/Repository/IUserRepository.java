package Repository;

import Model.User;

import java.util.ArrayList;

public interface IUserRepository {
    void addUser(User user);
    User getUserByUsername(String username);
    void removeUser(User user);
    ArrayList<User> getUsers();
}
