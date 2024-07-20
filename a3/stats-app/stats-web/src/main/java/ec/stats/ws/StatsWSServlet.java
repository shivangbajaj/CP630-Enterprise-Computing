package ec.stats.ws;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

@WebServlet("/statsws")
public class StatsWSServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            URL url = new URL("http://localhost:8080/stats-ws/StatsWSImpl?wsdl");
            QName qname = new QName("http://ws.stats.ec/", "StatsWSImplService");
            Service service = Service.create(url, qname);
            StatsWS statsWS = service.getPort(StatsWS.class);

            out.println("<html><body>");
            out.println("<h1>Statistics Summary Information</h1>");
            out.println("<p>Count: " + statsWS.getCount() + "</p>");
            out.println("<p>Min: " + statsWS.getMin() + "</p>");
            out.println("<p>Max: " + statsWS.getMax() + "</p>");
            out.println("<p>Mean: " + statsWS.getMean() + "</p>");
            out.println("<p>Standard Deviation: " + statsWS.getSTD() + "</p>");
            out.println("</body></html>");
        } catch (Exception e) {
            out.println("<html><body>");
            out.println("<h1>Error</h1>");
            out.println("<p>" + e.getMessage() + "</p>");
            out.println("</body></html>");
        }
    }
}
