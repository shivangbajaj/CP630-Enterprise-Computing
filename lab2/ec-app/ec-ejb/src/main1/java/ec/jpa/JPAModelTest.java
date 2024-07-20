package ec.jpa;

import ec.jpa.model.Model;
import ec.jpa.repository.ModelRepository;
import ec.stats.StatsSummary;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class JPAModelTest {

    public static void main(String[] args) throws IOException {
        // Create our entity manager
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ec-jpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
		ModelRepository modelrepository = new ModelRepository(entityManager);

		// create and save model object
        Model model = new Model();
        model.setName("stats");
        model.setClassname(StatsSummary.class.toString());
              
        StatsSummary ssobj = new StatsSummary();
		ssobj.setCount(1);
		ssobj.setMin(1);
		ssobj.setMax(1);
		ssobj.setMean(1);
		ssobj.setSTD(0);
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream out = null;
		try {
		  out = new ObjectOutputStream(bos);   
		  out.writeObject(ssobj);
		  out.flush();
		  byte[] yourBytes = bos.toByteArray();
		} finally {
		  try {
		    bos.close();
		  } catch (IOException ex) {
		    // ignore close exception
		  }
		}
		
		model.setObject(bos.toByteArray());
		modelrepository.save(model);
		
		
		// find model from all
        List<Model> ms = modelrepository.findAll();
        System.out.println("Model:");
        ms.forEach(System.out::println);
        
        if (ms.size()> 0 && ms.get(0).getName().equals("stats")) {
        	byte[] buf = ms.get(0).getObject(); 
            if (buf != null) {
            	ObjectInputStream objectIn = new ObjectInputStream(new ByteArrayInputStream(buf));
            	StatsSummary statsmodel = null;
				try {
					statsmodel = (StatsSummary)  objectIn.readObject();
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	System.out.println(statsmodel.toString());
            }                    	                    	
        }
		                
        // Close the entity manager and associated factory
        entityManager.close();
        entityManagerFactory.close();
    }
}
