package ec.stats.sb;

import java.io.ByteArrayInputStream;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import weka.classifiers.Classifier;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.SerializationHelper;

import ec.stats.jpa.Model;

@Stateless
public class LRStateless implements LRStatelessLocal {

    @PersistenceContext
    private EntityManager entityManager;
    public static final Logger LOGGER = Logger.getLogger(StatsStateless.class.getName());
    @Override
    public double prediction(String modelName, double[] argument) {
        try {
            TypedQuery<Model> qry = entityManager.createQuery("SELECT m FROM Model m WHERE m.name = :modelName", Model.class);
            qry.setParameter("modelName", modelName);
            Model mdlEty = qry.getSingleResult();
            byte[] mdlByt = mdlEty.getObject();
            Classifier mdl = deserializeModel(mdlByt);
            Instance instance = new DenseInstance(argument.length);
            for (int i = 0; i < argument.length; i++) {
                instance.setValue(i, argument[i]);
            }
           double prediction = mdl.classifyInstance(instance);
            return prediction;
        } catch (Exception e) {
            e.printStackTrace();
            return Double.NaN;
        }
    }

    private Classifier deserializeModel(byte[] modelBytes) throws Exception {
        return (Classifier) SerializationHelper.read(new ByteArrayInputStream(modelBytes));
    }
}
