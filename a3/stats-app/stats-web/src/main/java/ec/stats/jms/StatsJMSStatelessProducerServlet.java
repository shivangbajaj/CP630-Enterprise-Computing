package ec.stats.jms;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ec.stats.mdb.StatsJMSStatelessLocal;
import java.io.IOException;

@WebServlet("/sbproducer")
public class StatsJMSStatelessProducerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private StatsJMSStatelessLocal statsJMSStateless;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String message = request.getParameter("message");
		if (message != null && message.equals("save")) {
			statsJMSStateless.produce(message);
			response.getWriter().println("Message sent: " + message);
		} else {
			response.getWriter().println("Invalid message value.");
		}
	}
}
