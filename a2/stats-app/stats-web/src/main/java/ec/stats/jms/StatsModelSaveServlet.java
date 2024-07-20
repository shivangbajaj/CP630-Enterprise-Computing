// package ec.stats.jms;

// import ec.stats.StatsSummary;
// import ec.stats.jpa.Model;
// import ec.stats.jpa.ModelDao;
// import ec.stats.sb.StatsStatelessLocal;
// import javax.ejb.*;

// import javax.servlet.annotation.WebServlet;
// import javax.servlet.http.HttpServlet;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import java.io.ByteArrayOutputStream;
// import java.io.IOException;
// import java.io.ObjectOutputStream;

// @WebServlet("/modelsave")
// public class StatsModelSaveServlet extends HttpServlet {

//     @EJB
//     private StatsStatelessLocal statsStatelessLocal;

//     @EJB
//     private ModelDao modelDao;

//     @Override
//     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//         // Get model name from HTTP query parameter
//         String modelName = request.getParameter("modelname");

//         // Get stats values using StatsStatelessLocal
//         int count = statsStatelessLocal.getCount();
//         double min = statsStatelessLocal.getMin();
//         double max = statsStatelessLocal.getMax();
//         double mean = statsStatelessLocal.getMean();
//         double std = statsStatelessLocal.getSTD();

//         // Create StatsSummary object
//         StatsSummary statsSummary = new StatsSummary();
//         statsSummary.setCount(count);
//         statsSummary.setMin(min);
//         statsSummary.setMax(max);
//         statsSummary.setMean(mean);
//         statsSummary.setSTD(std);

//         try {
//             // Convert StatsSummary object to byte array
//             ByteArrayOutputStream bos = new ByteArrayOutputStream();
//             ObjectOutputStream out = new ObjectOutputStream(bos);
//             out.writeObject(statsSummary);
//             out.flush();
//             byte[] statsSummaryBytes = bos.toByteArray();
//             out.close();

//             // Create Model object
//             Model model = new Model();
//             model.setName(modelName);
//             model.setObject(statsSummaryBytes); // Set the byte array
//             model.setClassName(StatsSummary.class.getName()); // Store class name of stats summary object

//             // Save model to database using ModelDao
//             modelDao.saveModel(model);

//             // Redirect to a success page or provide some response to the user
//             response.getWriter().println("Model saved successfully!");
//         } catch (Exception e) {
//             // Handle exceptions
//             e.printStackTrace();
//             response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error saving model");
//         }
//     }
// }
//package ec.stats.web;
package ec.stats.jms;
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

			String modelName = request.getParameter("modelname");


			if (modelName == null || modelName.isEmpty()) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Model name is missing");
				return;
			}

			StatsSummary statsSummary = statsStateless.LoadModel();
            try{
            if(statsSummary!=null){
                int count = statsSummary.getCount();
                double min = statsSummary.getMin();
                double max = statsSummary.getMax();
                double mean = statsSummary.getMean();
                double std = statsSummary.getSTD();

                byte[] statsSummaryBytes = serializeObject(statsSummary);

                Model model = new Model();
                model.setName(modelName);
                model.setObject(statsSummaryBytes);
                model.setClassName(StatsSummary.class.getName());
                Timestamp timestamp = new Timestamp(new Date().getTime());
                model.setDate(timestamp);
                modelDao.saveModel(model);
            }
        }catch(Exception e){}

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