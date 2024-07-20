package ec.jpa;

import ec.jpa.model.User;
import ec.jpa.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.List;

public class JPAUserTest {

    public static void main(String[] args) throws IOException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ec-jpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        UserRepository userrepository = new UserRepository(entityManager);

        User user = new User("admin");
        User savedUser = userrepository.save(user);
        
        String name = savedUser.getName();
        Integer id = savedUser.getId();       
        
        System.out.println("Saved user name: " + name);
        System.out.println("Saved user id: " + id);         
        User findUser = userrepository.findById(id);
        
        System.out.println("Find user by id:"+id);
        if ( findUser != null) System.out.println(findUser.toString());
       
        findUser = userrepository.findByName(name);
        System.out.println("Find user by name:"+name);
        if ( findUser != null) System.out.println(findUser.toString());
       
        user = new User("guest");
        savedUser = userrepository.save(user);
        
        List<User> users = userrepository.findAll();
        System.out.println("All users:");
        users.forEach(System.out::println);  
          
        userrepository.removeUserByID(user.getId());
        users = userrepository.findAll();
        System.out.println("All users after removal:");
        users.forEach(System.out::println);  
        
        entityManager.close();
        entityManagerFactory.close();
    }
}
