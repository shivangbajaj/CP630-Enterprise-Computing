package ec.stats.jpa;

import java.util.List;

public interface UserDao {
    void addUser(User user);
    User getUser(String name, String password);
    List<User> getAllUser();
}
