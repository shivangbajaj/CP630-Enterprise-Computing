package ec.stats.web;

import ec.stats.sb.StatsStateful;
import ec.stats.sb.StatsStateless;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/insert-data")
public class StatsStatefulServlet extends HttpServlet {

	public static final Logger LOGGER = Logger.getLogger(StatsStateless.class.getName());

    @EJB
    private StatsStateful statsStateful;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Get the insert value from the HTTP query parameter "value"
        double insertValue = Double.parseDouble(request.getParameter("value"));

        // Call insertData, createModel, and getStats methods
        statsStateful.insertData(insertValue);
        statsStateful.createModel();
        String result = statsStateful.getStats();
		LOGGER.info(result);

        // Return the result in HTML
        response.getWriter().println("<html><body>Result: " + result + "</body></html>");
    }
}
