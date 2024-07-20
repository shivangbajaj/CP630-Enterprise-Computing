package ec.jpa;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.logging.Logger;

import ec.jpa.model.User;
import ec.jpa.repository.UserRepository;


@Singleton
@LocalBean
public class SecurityGuard implements SecurityLocal {
    private static final Logger LOGGER = Logger.getLogger(SecurityGuard.class);
    
    @PersistenceContext(unitName="primary")
    private EntityManager entityManager;

    private UserRepository userrep;
    
    /**
     * Default constructor. 
     */
    public SecurityGuard() {
    }

    @Override
    public Boolean validate(String username) {
        userrep = new UserRepository(entityManager);
        LOGGER.info("check user: " + username );
        User user = userrep.findByName(username);
        if (user!=null) {
          LOGGER.info(username + ": valid" );
        }
        else
          LOGGER.info(username + ": invalid" );
        return user != null ? true : false;
    }
}