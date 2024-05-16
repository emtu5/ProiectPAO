package Service;

import Model.User;
import Repository.UserRepository;

import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

public class UserService {
    private final UserRepository userRepository;

    public UserService(){
        this.userRepository = new UserRepository();
    }

    public void addUser(User user) {
        userRepository.addUser(user);
    }

    public void removeUser(User user) {
        userRepository.removeUser(user);
    }
    public User findUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    public SortedSet<User> getUsers() {
        return userRepository.getUsers();
    }

    public void showUsers() {
        SortedSet<User> users = this.getUsers();
        for (User user: users) {
            System.out.println(user);
        }
    }
}
