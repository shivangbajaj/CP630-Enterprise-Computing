package ec.stats.sb;

import java.io.ByteArrayInputStream;

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

    @Override
    public double prediction(String modelName, double[] argument) {
        try {
            // Query the database for the model with the specified name
            TypedQuery<Model> query = entityManager.createQuery("SELECT m FROM Model m WHERE m.name = :modelName", Model.class);
            query.setParameter("modelName", modelName);
            Model modelEntity = query.getSingleResult();

            // Deserialize the model object from the entity
            byte[] modelBytes = modelEntity.getObject();
            Classifier model = deserializeModel(modelBytes);

            // Create an instance for prediction
            Instance instance = new DenseInstance(argument.length);
            for (int i = 0; i < argument.length; i++) {
                instance.setValue(i, argument[i]);
            }

            // Perform prediction
            double prediction = model.classifyInstance(instance);
            return prediction;
        } catch (Exception e) {
            e.printStackTrace();
            return Double.NaN; // Handle the exception accordingly
        }
    }

    private Classifier deserializeModel(byte[] modelBytes) throws Exception {
        return (Classifier) SerializationHelper.read(new ByteArrayInputStream(modelBytes));
    }
}
