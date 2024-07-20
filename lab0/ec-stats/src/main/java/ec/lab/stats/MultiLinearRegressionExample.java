package ec.lab.stats;

import org.apache.commons.math3.stat.regression.GLSMultipleLinearRegression;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;

/*
 * Y=X*b+u
 * OLS regression
 * GLS regression
 */
public class MultiLinearRegressionExample {
	
	public static void main(String[] args) {
				
		OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();
		
		double[] y = new double[]{11.0, 12.0, 13.0, 14.0, 15.0, 16.0};
		double[][] x = new double[6][];
		x[0] = new double[]{0, 0, 0, 0, 0};
		x[1] = new double[]{2.0, 0, 0, 0, 0};
		x[2] = new double[]{0, 3.0, 0, 0, 0};
		x[3] = new double[]{0, 0, 4.0, 0, 0};
		x[4] = new double[]{0, 0, 0, 5.0, 0};
		x[5] = new double[]{0, 0, 0, 0, 6.0};          
		regression.newSampleData(y, x);
		
		
		double[] beta = regression.estimateRegressionParameters();       

		double[] residuals = regression.estimateResiduals();

		double[][] parametersVariance = regression.estimateRegressionParametersVariance();		
		
		System.out.println(beta.length);
		
		for (double d : beta) {
	        System.out.println("B: " + d);
	    }
		
		for (double d : residuals) {
	        System.out.println("u: " + d);
	    }
		

		for (int i=0; i<parametersVariance.length; i++) {
			for (int j=0; j<parametersVariance[i].length; j++) {
				System.out.print(parametersVariance[i][j] + " ");
			}
			System.out.println("\n");
		}
			
		
		System.out.println("estimateRegressandVariance()="+ regression.estimateRegressandVariance());
		System.out.println("calculateRSquared()="+ regression.calculateRSquared());
		System.out.println("estimateRegressionStandardError()="+ regression.estimateRegressionStandardError());
		
		
		double result = beta[0];
		for (int i=1; i<6; i++) {
			result += beta[i]*x[0][i-1];
	       
	    }
		
		System.out.println(result);
		
		System.out.println(y[0]-result);
		

		double[] xt = {1, 0, 0, 0, 0};
		
		double result1 = beta[0];
		for (int i=1; i<6; i++) {
			result1 += beta[i]*xt[i-1];
	    }
		System.out.println(result1);
		
		

		GLSMultipleLinearRegression regression1 = new GLSMultipleLinearRegression();
		double[] y1 = new double[]{11.0, 12.0, 13.0, 14.0, 15.0, 16.0};
		double[][] x1 = new double[6][];
		x1[0] = new double[]{0, 0, 0, 0, 0};
		x1[1] = new double[]{2.0, 0, 0, 0, 0};
		x1[2] = new double[]{0, 3.0, 0, 0, 0};
		x1[3] = new double[]{0, 0, 4.0, 0, 0};
		x1[4] = new double[]{0, 0, 0, 5.0, 0};
		x1[5] = new double[]{0, 0, 0, 0, 6.0};          
		double[][] omega = new double[6][];
		omega[0] = new double[]{1.1, 0, 0, 0, 0, 0};
		omega[1] = new double[]{0, 2.2, 0, 0, 0, 0};
		omega[2] = new double[]{0, 0, 3.3, 0, 0, 0};
		omega[3] = new double[]{0, 0, 0, 4.4, 0, 0};
		omega[4] = new double[]{0, 0, 0, 0, 5.5, 0};
		omega[5] = new double[]{0, 0, 0, 0, 0, 6.6};
		regression1.newSampleData(y1, x1, omega); 
		
		beta = regression.estimateRegressionParameters();       

		residuals = regression.estimateResiduals();

		parametersVariance = regression.estimateRegressionParametersVariance();	
		
		
		System.out.println(beta.length);
		
		for (double d : beta) {
	        System.out.println("B: " + d);
	    }
		
		for (double d : residuals) {
	        System.out.println("u: " + d);
	    }
		

		for (int i=0; i<parametersVariance.length; i++) {
			for (int j=0; j<parametersVariance[i].length; j++) {
				System.out.print(parametersVariance[i][j] + " ");
			}
			System.out.println("\n");
		}
			
		
		System.out.println("estimateRegressandVariance()="+ regression.estimateRegressandVariance());
		System.out.println("calculateRSquared()="+ regression.calculateRSquared());
		System.out.println("estimateRegressionStandardError()="+ regression.estimateRegressionStandardError());
		
		
		result = beta[0];
		for (int i=1; i<6; i++) {
			result += beta[i]*x[0][i-1];
	       
	    }
		
		System.out.println(result);
		
		System.out.println(y[0]-result);
		

		int xt1[] = {1, 0, 0, 0, 0};
		
		result1 = beta[0];
		for (int i=1; i<6; i++) {
			result1 += beta[i]*xt1[i-1];
	    }
		System.out.println(result1);

		
			         
	}
		

}
