package ec.jpa.repository;

import javax.persistence.EntityManager;

import ec.jpa.model.Model;

import java.util.List;
import java.util.Optional;

public class ModelRepository {
    private EntityManager entityManager;

    public ModelRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<Model> save(Model Model) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(Model);
            entityManager.getTransaction().commit();
            return Optional.of(Model);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


    public void createModel(Model Model) {
        entityManager.persist(Model);
    }
    
    public Optional<Model> findById(Integer id) {
        Model Model = entityManager.find(Model.class, id);
        return Model != null ? Optional.of(Model) : Optional.empty();
    }

    public List<Model> findAll() {
        return entityManager.createQuery("from Model").getResultList();
    }

    public Optional<Model> findByID(int id) {
        Model Model = entityManager.createQuery("select u from Model u where u.id = :modelid",  Model.class).setParameter("modelid", id).getSingleResult();
        return Model != null ? Optional.of(Model) : Optional.empty();
    }
    
    public Optional<Model> findByName(String name) {
        Model Model = entityManager.createNamedQuery("Model.findByName", Model.class)
                .setParameter("name", name)
                .getSingleResult();
        return Model != null ? Optional.of(Model) : Optional.empty();
    }
    public void removeAll() {
        try {
            entityManager.getTransaction().begin();
            entityManager.createQuery("delete from Model u").executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
