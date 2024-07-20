/*
 * HBF
 */
package ec.jms;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.mdb.JMSStatelessLocal;

@WebServlet("/sbjms")
public class SBJMSWebServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	JMSStatelessLocal sb;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mstr = "Servlet to EJB JMS client";
		sb.produce(mstr);
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();		
		out.println("message sent:"+mstr);
	}
}

