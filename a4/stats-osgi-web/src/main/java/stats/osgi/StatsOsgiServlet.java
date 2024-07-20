package stats.osgi;

import javax.servlet.annotation.WebServlet;
import ec.stats.StatsOSGi;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/stats")
public class StatsOsgiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StatsOSGi statsService;

	public StatsOsgiServlet() {
		statsService = null;
	}

	public StatsOsgiServlet(StatsOSGi service) {
		statsService = service;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	    String query = req.getParameter("query");
	    if (statsService != null) {
	        JSONObject jsonResponse = new JSONObject();
	        if (query != null) {
	            switch (query) {
	                case "count":
	                    jsonResponse.put("count", statsService.getCount());
	                    break;
	                case "min":
	                    jsonResponse.put("min", statsService.getMin());
	                    break;
	                case "max":
	                    jsonResponse.put("max", statsService.getMax());
	                    break;
	                case "mean":
	                    jsonResponse.put("mean", statsService.getMean());
	                    break;
	                case "std":
	                    jsonResponse.put("std", statsService.getSTD());
	                    break;
	                case "all":
	                    jsonResponse.put("count", statsService.getCount());
	                    jsonResponse.put("min", statsService.getMin());
	                    jsonResponse.put("max", statsService.getMax());
	                    jsonResponse.put("mean", statsService.getMean());
	                    jsonResponse.put("std", statsService.getSTD());
	                    break;
	                default:
	                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	                    resp.getWriter().println("Invalid query");
	                    return;
	            }
	        } else {
	            // No query provided, return all stats by default
	            jsonResponse.put("count", statsService.getCount());
	            jsonResponse.put("min", statsService.getMin());
	            jsonResponse.put("max", statsService.getMax());
	            jsonResponse.put("mean", statsService.getMean());
	            jsonResponse.put("std", statsService.getSTD());
	        }

	        resp.setContentType("application/json");
	        resp.getWriter().write(jsonResponse.toString());
	    } else {
	        resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        resp.getWriter().println("Failed to obtain StatsOSGi service.");
	    }
	}

}
