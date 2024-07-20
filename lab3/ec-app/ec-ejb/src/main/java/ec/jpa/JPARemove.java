package ec.jpa;

import ec.jpa.repository.ModelRepository;
import ec.jpa.repository.UserRepository;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;

public class JPARemove {

    public static void main(String[] args) throws IOException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ec-jpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        UserRepository userrepository = new UserRepository(entityManager);
        userrepository.removeAll();
        
        ModelRepository modelRepository = new ModelRepository(entityManager);
        modelRepository.removeAll();
        
        entityManager.close();
        entityManagerFactory.close();
    }
}
