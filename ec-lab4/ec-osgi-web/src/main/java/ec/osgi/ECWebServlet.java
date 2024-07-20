package ec.osgi;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ECWebServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		try {
			out.println((new Date()).toString() + "<br>");
			out.println("ECWebServletOSGi"+ "<br>");
			String value = request.getParameter("value");
			out.println("Query value: ");
			out.println(value);

		} catch (Exception ex) {
			throw new ServletException(ex);
		} finally {
			out.close();
		}
	}
}