package ec.stats.jms;

import ec.stats.mdb.StatsJMSStatelessLocal;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sbpublisher")
public class StatsJMSStatelessPublisherServlet extends HttpServlet {

    @EJB
    private StatsJMSStatelessLocal statsJMSStateless;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String data = request.getParameter("message");

        if (data != null && !data.isEmpty()) {
            statsJMSStateless.publish(data);
            response.getWriter().println("Data: "+data+" published ");
        } else {
            response.getWriter().println("Invalid or missing 'data'");
        }
    }
}
