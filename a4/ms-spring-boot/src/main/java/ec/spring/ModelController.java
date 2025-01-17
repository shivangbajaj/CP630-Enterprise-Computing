package ec.spring;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ec.weka.EcModel;
import ec.weka.ModelService;
import weka.classifiers.Classifier;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.SerializationHelper;

@RestController
public class ModelController {

	@Autowired
	private ModelService modelService;

	@GetMapping("/model/predict/{queryInstance}")
	public String predict(@PathVariable String queryInstance) {
		try {
			System.out.println("in predict");
			System.out.println("query - " + queryInstance);
			double prediction = modelService.predict(queryInstance);
			return Double.toString(prediction);
		} catch (IllegalArgumentException e) {
			return "error occured " + e.getMessage();
		} catch (Exception e) {
			return "error occured " + e.getMessage();
		}
	}
}
