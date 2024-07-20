//CP630 A2 by Vaibhav Thakur (235811400)
package ec.stats.jpa;


import java.util.List;
import javax.ejb.Remote;

@Remote
public interface UserDao {
    void addUser(User user);
    User getUser(String name, String password);
    List<User> getAllUser();
}
