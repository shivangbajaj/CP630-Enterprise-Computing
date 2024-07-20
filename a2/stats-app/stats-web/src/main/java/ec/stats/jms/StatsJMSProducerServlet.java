//CP630 A2 by Vaibhav Thakur (235811400)
package ec.stats.jms;

import java.io.IOException;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.Topic;
import javax.naming.Context;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/producer")
public class StatsJMSProducerServlet extends HttpServlet {

    /**
     
*/
  private static final long serialVersionUID = 1L;

    @Resource(lookup = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "java:/queue/test")
    private Queue queue;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
       
        String message = request.getParameter("message");
        response.getWriter().println("msg : " + message);

        if (message != null && message.equals("save")) {
            try (JMSContext context = connectionFactory.createContext()) {
                
                final Destination destination = queue;
                context.createProducer().send(destination, message);
                response.getWriter().println("Message sent to the queue: " + message);
            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().println("Error sending message: " + e.getMessage());
            }
        } else {
            response.getWriter().println("Invalid message value.");
        }
    }
}