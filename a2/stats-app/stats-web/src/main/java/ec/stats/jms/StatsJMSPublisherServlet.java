//CP630 A2 by Vaibhav Thakur (235811400)
package ec.stats.jms;

import java.io.IOException;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Topic;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/publisher")
public class StatsJMSPublisherServlet extends HttpServlet {

    /**
    
*/
  private static final long serialVersionUID = 1L;

    @Resource(lookup = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "java:/topic/test")
    private Topic topic;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String message = request.getParameter("message");
        if (message != null && isDouble(message)) {
            try (JMSContext context = connectionFactory.createContext()) {
                context.createProducer().send(topic, message);
                response.getWriter().println("Message published to topic: " + message);
            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().println("Error publishing message: " + e.getMessage());
            }
        } else {
            response.getWriter().println("Invalid message value.");
        }
    }

    private boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}