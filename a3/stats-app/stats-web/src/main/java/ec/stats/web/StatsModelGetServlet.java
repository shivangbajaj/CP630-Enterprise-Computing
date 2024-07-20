package ec.stats.web;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.stats.StatsSummary;
import ec.stats.jpa.Model;
import ec.stats.jpa.ModelDao;
import ec.stats.sb.StatsStateless;

@WebServlet("/modelget")
public class StatsModelGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final Logger LOGGER = Logger.getLogger(StatsStateless.class.getName());

	@EJB
	private ModelDao modelDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// Get model name and query from the request parameters
		String modelName = request.getParameter("modelname");
		String query = request.getParameter("query");

		LOGGER.info("modelname = " + modelName);
		LOGGER.info("query = " + query);

		
		// Check if model name or query is missing
		if (modelName == null || modelName.isEmpty() || query == null || query.isEmpty()) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Model name or query is missing");
			return;
		}

		try {
			// Get the model object from the database
			Model model = modelDao.getModel(modelName);

			// Check if the model exists
			if (model == null) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "Model with name " + modelName + " not found");
				return;
			}

			// Deserialize the object from byte array
			Object modelObject = deserializeObject(model.getObject());

			// Handle different queries
			switch (query) {
			case "count":
				if (modelObject instanceof StatsSummary) {
					int count = ((StatsSummary) modelObject).getCount();
					response.getWriter().println("Count: " + count);
				} else {
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Invalid object type for query");
				}
				break;
			case "min":
				if (modelObject instanceof StatsSummary) {
					double min = ((StatsSummary) modelObject).getMin();
					response.getWriter().println("Min: " + min);
				} else {
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Invalid object type for query");
				}
				break;
			case "max":
				if (modelObject instanceof StatsSummary) {
					double max = ((StatsSummary) modelObject).getMax();
					response.getWriter().println("Max: " + max);
				} else {
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Invalid object type for query");
				}
				break;
			case "mean":
				if (modelObject instanceof StatsSummary) {
					double mean = ((StatsSummary) modelObject).getMean();
					response.getWriter().println("Mean: " + mean);
				} else {
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Invalid object type for query");
				}
				break;
			case "std":
				if (modelObject instanceof StatsSummary) {
					double std = ((StatsSummary) modelObject).getSTD();
					response.getWriter().println("STD: " + std);
				} else {
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Invalid object type for query");
				}
				break;
			// Add more cases for other queries if needed
			default:
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid query");
			}
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"Failed to process request: " + e.getMessage());
//			StringWriter sw = new StringWriter();
//			e.printStackTrace(new PrintWriter(sw));
//			String exceptionAsString = sw.toString();
//			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
//					"Failed to process request: " + exceptionAsString);
		}
	}

	private Object deserializeObject(byte[] bytes) {
		try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
				ObjectInputStream ois = new ObjectInputStream(bis)) {
			return ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
