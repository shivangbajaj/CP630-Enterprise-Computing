//CP630 A2 by Vaibhav Thakur (235811400)
package ec.stats.jpa;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateful
public class ModelDaoImpl implements ModelDao {

    @PersistenceContext(unitName = "statsPU")
    private EntityManager entityManager;

    @Override
    public void saveModel(Model model) {
        entityManager.persist(model);
    }

    @Override
    public Model getModel(String modelName) {
        Query query = entityManager.createQuery("SELECT m FROM Model m WHERE m.name = :modelName", Model.class);
        query.setParameter("modelName", modelName);
        try {
            return (Model) query.getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }
}
