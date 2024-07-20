//CP630 A2 by Vaibhav Thakur (235811400)
package ec.stats.jms;

import ec.stats.mdb.StatsJMSStatelessLocal;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sbproducer")
public class StatsJMSStatelessProducerServlet extends HttpServlet {

    @Inject
    private StatsJMSStatelessLocal statsJMSStateless;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String message = request.getParameter("message");

        if (message != null && !message.isEmpty()) {
            statsJMSStateless.produce(message);
            response.getWriter().println("Message sent");
        } else {
            response.getWriter().println("Invalid or missingmessage");
        }
    }
}
