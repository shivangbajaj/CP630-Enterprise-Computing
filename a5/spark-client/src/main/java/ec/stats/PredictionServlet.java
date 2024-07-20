package ec.stats;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.regression.LinearRegressionModel;
import org.apache.spark.mllib.linalg.Vectors;

@WebServlet("/predict")
public class PredictionServlet extends HttpServlet {
    private LinearRegressionModel model;

    @Override
    public void init() {
        SparkConf conf = new SparkConf().setAppName("PredictionServlet").setMaster("local[4]");
        JavaSparkContext sc = new JavaSparkContext(conf);
        
        model = LinearRegressionModel.load(sc.sc(), "C:/enterprise/workspace/a5/sparklab/data/model");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, 
    IOException {
        response.setContentType("text/plain");
        
        String featuresParam = request.getParameter("features");
        
        String[] featuresStringArray = featuresParam.split(",");
        
        double[] features = new double[featuresStringArray.length];
        for (int i = 0; i < featuresStringArray.length; i++) {
            features[i] = Double.parseDouble(featuresStringArray[i]);
        }
        
        double prediction = model.predict(Vectors.dense(features));
        
        response.getWriter().println("Prediction: " + prediction);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
