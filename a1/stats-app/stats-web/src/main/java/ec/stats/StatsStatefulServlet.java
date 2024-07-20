package ec.stats;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ec.stats.sb.*;

@WebServlet("/insert-data")
public class StatsStatefulServlet extends HttpServlet {
    private static final long serialVersionUID = 2L;
    
    @EJB
    private StatsStatefulRemote statsStateful;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println((new Date()).toString()+"<br>");
            out.println(" <br>");
            double value = Double.parseDouble(request.getParameter("value"));
            statsStateful.insertData(value);
            statsStateful.createModel();
            String sm_st =  statsStateful.getStats();
            out.println(sm_st);
        } catch (Exception e) {
            throw new ServletException(e);
        } finally {
            out.close();
        }
    }
}
