package ec.stats.web;

import ec.stats.sb.StatsStateless;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/get")
public class StatsStatelessServlet extends HttpServlet {

	@EJB
	private StatsStateless statsStateless;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// Get the query message from the HTTP query parameter "value"
		String query = request.getParameter("value");

		// Call the corresponding method of StatsStatelessLocal based on the query
		String result = "";
		if ("count".equalsIgnoreCase(query)) {
			result = String.valueOf(statsStateless.getCount());
		} else if ("min".equalsIgnoreCase(query)) {
			result = String.valueOf(statsStateless.getMin());
		} else if ("max".equalsIgnoreCase(query)) {
			result = String.valueOf(statsStateless.getMax());
		} else if ("mean".equalsIgnoreCase(query)) {
			result = String.valueOf(statsStateless.getMean());
		} else if ("std".equalsIgnoreCase(query)) {
			result = String.valueOf(statsStateless.getSTD());
		} else if ("summary".equalsIgnoreCase(query)) {
			 result += ("Count = " + statsStateless.getCount() + "<br>");
			 result += ("Min = " + statsStateless.getMin() + "<br>");
			 result += ("Max = " + statsStateless.getMax() + "<br>");
			 result += ("Mean = " + statsStateless.getMean() + "<br>");
			 result += ("Standard Deviation = " + statsStateless.getSTD() + "<br>");
		}

		// Return the calling result in HTML
		response.getWriter().println("<html><body>Result: " + result + "</body></html>");
	}
}
