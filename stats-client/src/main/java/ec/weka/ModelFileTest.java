package ec.weka;

import java.io.File;
import java.io.FileWriter;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class ModelFileTest {

    public static void main(String[] args) throws Exception {
        String modelFilePath = args[1];
        String dataFilePath = args[3];
        String testFilePath = args[5];
        String outputFilePath = args[7];

        Classifier model = (Classifier) weka.core.SerializationHelper.read(modelFilePath);

        DataSource source = new DataSource(dataFilePath);
        Instances data = source.getDataSet();
        data.setClassIndex(data.numAttributes() - 1);

        DataSource testSource = new DataSource(testFilePath);
        Instances testData = testSource.getDataSet();
        testData.setClassIndex(testData.numAttributes() - 1);

        System.out.println("Testing linear regression model...");
        Evaluation eval = new Evaluation(data);
        eval.evaluateModel(model, testData);
        System.out.println(eval.toSummaryString());

        try (FileWriter writer = new FileWriter(new File(outputFilePath))) {
            writer.write(eval.toSummaryString());
        }
        System.out.println("Testing result saved to " + outputFilePath);
    }
}
