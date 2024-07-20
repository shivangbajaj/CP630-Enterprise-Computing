package ec.weka;

import weka.classifiers.Classifier;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import java.io.File;
import java.io.FileWriter;

public class ModelFilePredict {

    public static void main(String[] args) throws Exception {
        String modelFilePath = args[1];
        String predictFilePath = args[3];
        String outputFilePath = args[5];

        Classifier model = (Classifier) weka.core.SerializationHelper.read(modelFilePath);

        DataSource source = new DataSource(predictFilePath);
        Instances predictData = source.getDataSet();
        predictData.setClassIndex(predictData.numAttributes() - 1);

        double[] predictions = new double[predictData.numInstances()];
        for (int i = 0; i < predictData.numInstances(); i++) {
            double prediction = model.classifyInstance(predictData.instance(i));
            predictions[i] = prediction;
        }

        try (FileWriter writer = new FileWriter(new File(outputFilePath))) {
            for (double prediction : predictions) {
                writer.write(String.valueOf(prediction) + System.lineSeparator());
            }
        }
        System.out.println("Predictions saved to " + outputFilePath);
    }
}
