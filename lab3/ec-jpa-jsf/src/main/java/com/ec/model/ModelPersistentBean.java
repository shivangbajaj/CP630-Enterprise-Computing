package com.ec.model;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.ec.lab.StatsSummary;

@Stateful
@Alternative
public class ModelPersistentBean implements ModelEJBRemote {

	@Inject
	private EntityManager entityManager;

	public ModelPersistentBean() {
	}

	public void addModel(Model model) {
		System.out.println("================add model ====================");
		
		StatsSummary ssobj = new StatsSummary();
		ssobj.setCount(2);
		ssobj.setMin(2);
		ssobj.setMax(2);
		ssobj.setMean(2);
		ssobj.setSTD(2);
		
		entityManager.persist(model);
	}

	public Model getModel() {
		Query query = entityManager.createQuery("select u from Model u where u.name = :name");
		query.setParameter("name", "stats");
		System.out.println("==========="+query.getSingleResult().getClass().getName());
		return (Model) query.getSingleResult();
	}

}