package ec.weka;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import ec.stats.db.DBUtil;
import weka.classifiers.Classifier;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.SerializationHelper;

public class ModelDBPredict {
    public static void main(String[] args) {
        if (args.length != 6 || !args[0].equals("-table") || !args[2].equals("-name") || !args[4].equals("-query")) {
            System.out.println("Usage: java -cp target/stats-client.jar ec.weka.ModelDBPredict -table <table_name> -name <model_name> -query <query_instance>");
            return;
        }

        String tableName = args[1];
        String modelName = args[3];
        String queryInstance = args[5];

        try (Connection connection = DriverManager.getConnection(DBUtil.URL, DBUtil.USER, DBUtil.PASS)) {
            String query = "SELECT object FROM " + tableName + " WHERE name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, modelName);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        byte[] modelBytes = resultSet.getBytes("object");
                        Classifier model = deserializeModel(modelBytes);

                        Instance query1 = new DenseInstance(6);
                        String[] queryValues = queryInstance.split(",");
                        for (int i = 0; i < queryValues.length; i++) {
                            query1.setValue(i, Double.parseDouble(queryValues[i]));
                        }

                        double prediction = model.classifyInstance(query1);
                        System.out.println("Prediction: " + prediction);
                    } else {
                        System.out.println("Model with name '" + modelName + "' not found in table '" + tableName + "'.");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Classifier deserializeModel(byte[] modelBytes) throws IOException, ClassNotFoundException {
        try (InputStream is = new ByteArrayInputStream(modelBytes)) {
            return (Classifier) SerializationHelper.read(is);
        } catch (Exception e) {
    		return null;
		}
    }
}
