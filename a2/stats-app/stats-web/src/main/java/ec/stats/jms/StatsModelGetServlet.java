package ec.stats.jms;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.stats.jpa.Model;
import ec.stats.jpa.ModelDao;

@WebServlet("/modelget")
public class StatsModelGetServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private ModelDao modelDao;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        String modelName = request.getParameter("modelname");
        String query = request.getParameter("query");

        PrintWriter out = response.getWriter();

        try {
           
            Model model = modelDao.getModel(modelName);

            if (model != null) {
                
                String propertyValue = getPropertyFromModel(model, query);
                out.println("<html><body>");
                out.println("<h2>Model Property Value</h2>");
                out.println("<p>Model Name: " + modelName + "</p>");
                out.println("<p>Query: " + query + "</p>");
                out.println("<p>Property Value: " + propertyValue + "</p>");
                out.println("</body></html>");
            } else {
                out.println("<html><body>");
                out.println("<p>Model Name: " + modelName + "</p>");
                out.println("<p>Queried Element: " + query + "</p>");
                out.println("</body></html>");
            }
        } catch (Exception e) {
            out.println("<html><body>");
            out.println("<h2>Error Retrieving Model</h2>");
            out.println("<p>Error: " + e.getMessage() + "</p>");
            out.println("</body></html>");
        }
    }

    private String getPropertyFromModel(Model model, String query) {
        
        return ""; 
    }
}
