//CP630 A2 by Vaibhav Thakur (235811400)
package ec.stats.jpa;

import javax.ejb.Stateful;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateful
@LocalBean
public class UserDaoImpl implements UserDao {

    @PersistenceContext(unitName = "statsPU")
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getUser(String name, String password) {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.name = :name AND u.password = :password", User.class);
        query.setParameter("name", name);
        query.setParameter("password", password);
        List<User> users = query.getResultList();
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public List<User> getAllUser() {
        Query query = entityManager.createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();
    }
}
