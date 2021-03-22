package pl.lukakan.mvcfirstproject;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private final List<User> users;

    public UserRepository() {
        users = new ArrayList<>();
        User testUser1 = new User("Jan", "Kowalski", 28);
        User testUser2 = new User("Tadeusz", "Nowak", 54);
        User testUser3 = new User("Barbara", "Zawadzka", 54);
        users.add(testUser1);
        users.add(testUser2);
        users.add(testUser3);
    }

    public List<User> getAll() {
        return users;
    }

    public void add(User user) {
        users.add(user);
    }

}
