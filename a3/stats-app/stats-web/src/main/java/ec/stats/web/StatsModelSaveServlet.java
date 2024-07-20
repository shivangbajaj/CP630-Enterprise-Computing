package ec.stats.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.stats.StatsSummary;
import ec.stats.jpa.*;
import ec.stats.sb.StatsStateless;
import ec.stats.sb.StatsStatelessLocal;

@WebServlet("/modelsave")
public class StatsModelSaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final Logger LOGGER = Logger.getLogger(StatsStateless.class.getName());

	@EJB
	private StatsStatelessLocal statsStateless;

	@EJB
	private ModelDao modelDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {

			// Read the model name from the request body
//			String modelName = readRequestBody(request);
            String modelName = request.getParameter("modelname");


			// Check if the model name is empty or null
			if (modelName == null || modelName.isEmpty()) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Model name is missing");
				return;
			}

			StatsSummary statsSummary = statsStateless.loadModel();
		
			int count = statsSummary.getCount();
			double min = statsSummary.getMin();
			double max = statsSummary.getMax();
			double mean = statsSummary.getMean();
			double std = statsSummary.getSTD();

			// Serialize StatsSummary object into a byte array
			byte[] statsSummaryBytes = serializeObject(statsSummary);

			// Create Model object
			Model model = new Model();
			model.setName(modelName);
			model.setObject(statsSummaryBytes);
			model.setClassName(StatsSummary.class.getName());
			// Convert current date to Timestamp
			Timestamp timestamp = new Timestamp(new Date().getTime());
			model.setDate(timestamp);
			modelDao.saveModel(model);

			response.getWriter().println("Stats model saved successfully with name: " + modelName);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"Failed to save stats model: " + e.getMessage());
		}
	}

	private String readRequestBody(HttpServletRequest request) throws IOException {
		StringBuilder requestBody = new StringBuilder();
		try (InputStream inputStream = request.getInputStream()) {
			int character;
			while ((character = inputStream.read()) != -1) {
				requestBody.append((char) character);
			}
		}
		return requestBody.toString().trim();
	}

	private byte[] serializeObject(Object object) throws IOException {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(object);
			return bos.toByteArray();
		} catch (Exception e) {
			return null;
		}

	}
}
