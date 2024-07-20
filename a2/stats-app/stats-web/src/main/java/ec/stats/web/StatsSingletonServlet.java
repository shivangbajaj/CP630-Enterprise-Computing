package ec.stats.web;

import ec.stats.sb.StatsSingleton;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add-data")
public class StatsSingletonServlet extends HttpServlet {

    @EJB
    private StatsSingleton statsSingleton;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Get data from the HTTP query parameter "value"
        double data = Double.parseDouble(request.getParameter("value"));

        // Call addData and saveModel methods
        statsSingleton.addData(data);
        statsSingleton.saveModel();

        // Return an HTML page with the added data value
        response.getWriter().println("<html><body>" + data + " added, model saved.</body></html>");
    }
}
