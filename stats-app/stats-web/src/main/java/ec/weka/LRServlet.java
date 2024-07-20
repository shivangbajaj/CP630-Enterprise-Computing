package ec.weka;

import java.io.IOException;
import java.util.Arrays;
import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ec.stats.sb.LRStatelessLocal;

@WebServlet("/predict")
public class LRServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@EJB
    private LRStatelessLocal lrStateless;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String modelName = request.getParameter("name");
        String query = request.getParameter("query");
        if (modelName != null && query != null) {
            double[] argument = Arrays.stream(query.split(",")).mapToDouble(Double::parseDouble).toArray();
            double prediction = lrStateless.prediction(modelName, argument);
            response.getWriter().println("Prediction: " + prediction);
        } else {
            response.getWriter().println("Invalid parameters. Please provide 'name' and 'query'.");
        }
    }
}
