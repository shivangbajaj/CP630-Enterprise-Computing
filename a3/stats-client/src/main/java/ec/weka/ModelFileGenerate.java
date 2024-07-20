package ec.weka;

import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.functions.LinearRegression;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;

public class ModelFileGenerate {
    public static void main(String[] args) throws Exception {
        List<String> argList = Arrays.asList(args);
        try {
        	 String dataFilePath = argList.get(argList.indexOf("-datafile") + 1);
             String modelFilePath = argList.get(argList.indexOf("-modelfile") + 1);

             DataSource source = new DataSource(dataFilePath);
             Instances data = source.getDataSet();
             data.setClassIndex(data.numAttributes() - 1);

             LinearRegression model = new LinearRegression();
             model.buildClassifier(data);

             // Save model to file
             try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(modelFilePath)))) {
                 oos.writeObject(model);
             }

             System.out.println("Linear regression model successfully generated and saved to " + modelFilePath);
		} catch (Exception e) {
			// TODO: handle exception
		}
       
    }
}
