package ec.stats.jms;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ec.stats.mdb.StatsJMSStatelessLocal;

@WebServlet("/sbpublisher")
public class StatsJMSStatelessPublisherServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@EJB
    private StatsJMSStatelessLocal statsJMSStateless;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String message = request.getParameter("message");
        if (message != null && isDouble(message)) {
        	statsJMSStateless.publish(message);
            response.getWriter().println("Data published: " + message);
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