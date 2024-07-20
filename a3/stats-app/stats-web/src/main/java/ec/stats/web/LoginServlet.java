package ec.stats.web;

import ec.stats.jpa.User;
import ec.stats.jpa.UserDao;
import ec.stats.sb.StatsStateless;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final Logger LOGGER = Logger.getLogger(StatsStateless.class.getName());

	@EJB
	private UserDao userDao;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().println("in dopost");

		LOGGER.info("in dopost");

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		LOGGER.info("username = " + username);
		LOGGER.info("password = " + password);

		try {
			User user = userDao.getUser(username, password);
			if (user != null) {
				LOGGER.info("\"in user != null\"");

				int role = user.getRole();
				switch (role) {
				case 1:
					response.sendRedirect("adminPage.html");
					break;
				case 2:
					response.sendRedirect("developerPage.html");
					break;
				case 3:
					response.sendRedirect("generalUserPage.html");
					break;
				default:
					response.sendRedirect("error.html");
					break;
				}
			} else {
				response.sendRedirect("login.html");
			}
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}

	}
}
