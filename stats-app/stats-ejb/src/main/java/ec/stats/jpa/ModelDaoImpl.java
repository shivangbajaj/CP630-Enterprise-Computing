package ec.stats.jpa;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateful
public class ModelDaoImpl implements ModelDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveModel(Model model) {
        entityManager.persist(model);
    }

    @Override
    public Model getModel(String modelName) {
    	try {
    		return entityManager.createQuery("SELECT m FROM Model m WHERE m.name = :modelName", Model.class)
                    .setParameter("modelName", modelName)
                    .getSingleResult();
		} catch (Exception e) {
			return null;
		}
    }
}