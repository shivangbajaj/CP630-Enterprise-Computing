package ec.stats.jpa;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import ec.stats.sb.StatsStateless;

@Stateful
public class UserDaoImpl implements UserDao {
	public static final Logger LOGGER = Logger.getLogger(StatsStateless.class.getName());

	@PersistenceContext(unitName = "stats-ejb")
	private EntityManager em;

	@Override
	public void addUser(User user) {
		try {
			em.persist(user);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
		}
	}

	@Override
	public User getUser(String name, String password) {
		try {
			LOGGER.info("in getuser");
			TypedQuery<User> query = em
					.createQuery("SELECT u FROM User u WHERE u.name = :name AND u.password = :password", User.class);

			query.setParameter("name", name);
			query.setParameter("password", password);

			return query.getSingleResult();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
			return null;
		}

	}

	@Override
	public List<User> getAllUser() {
		try {
			return em.createQuery("SELECT u FROM User u", User.class).getResultList();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
			return null;
		}
	}
}