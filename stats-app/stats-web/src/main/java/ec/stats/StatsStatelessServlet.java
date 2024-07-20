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

@WebServlet("/get")
public class StatsStatelessServlet extends HttpServlet {
    private static final long serialVersionUID = 3L;

    @EJB
    private StatsStatelessLocal statsStateless;
    

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println((new Date()).toString()+"<br>");
            out.println("Statistics: <br>");
            String query = request.getParameter("value");
            if(query.equals("count")){
                out.println("Count: " + statsStateless.getCount() + "<br>");
            } else if(query.equals("min")) {
                out.println("Min: " + statsStateless.getMin() + "<br>");
            } else if(query.equals("max")) {
                out.println("Max: " + statsStateless.getMax() + "<br>");
            } else if(query.equals("mean")) {
                out.println("Mean: " + statsStateless.getMean() + "<br>");
            } else if(query.equals("std")) {
                out.println("Standard deviation: " + statsStateless.getSTD() + "<br>");
            } else if(query.equals("summary")) {
                out.println("Count = " + statsStateless.getCount() + "<br>");
                out.println("Min = " + statsStateless.getMin() + "<br>");
                out.println("Max = " + statsStateless.getMax() + "<br>");
                out.println("Mean = " + statsStateless.getMean() + "<br>");
                out.println("Standard Deviation = " + statsStateless.getSTD() + "<br>");
            }

        } catch (Exception e) {
            throw new ServletException(e);
        } finally {
            out.close();
        }
    }
}
