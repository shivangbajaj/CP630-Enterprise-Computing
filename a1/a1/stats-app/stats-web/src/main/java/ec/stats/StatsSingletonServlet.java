package ec.stats;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import ec.stats.sb.*;

@WebServlet("/add-data")
public class StatsSingletonServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private StatsSingletonRemote statsSingleton;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println((new Date()).toString()+"<br>");
            out.println("Web component to add data: <br>");
            double value = Double.parseDouble(request.getParameter("value"));
            statsSingleton.addData(value);
            out.println("Value: " + value + "<br>");
            out.println("Count: " + statsSingleton.getCount() + "<br>");
        } catch (Exception e) {
            throw new ServletException(e);
        } finally {
            out.close();
            statsSingleton.saveModel();
        }
    }
}
