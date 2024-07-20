package com.ec.model;

import javax.ejb.Stateful;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

@Stateful
@Alternative
public class EJBModelDao implements ModelDao {

	@Inject
	private EntityManager entityManager;

	@Override
	public Model getForModelname(String modelname) {
		Query query = entityManager.createQuery("select u from Model u where u.name = :name");
		query.setParameter("name", modelname);
		
		Model model = null;
		
		try {
		    model = (Model) query.getSingleResult();
		} catch ( NoResultException e) {
			
			e.printStackTrace();
			model = null;
		}
		
		return model;
	}

	@Override
	public void createModel(Model model) {	
		entityManager.persist(model);
	}

}