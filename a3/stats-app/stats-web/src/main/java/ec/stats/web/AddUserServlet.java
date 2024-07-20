package ec.stats.web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.stats.jpa.User;
import ec.stats.jpa.UserDao;

@WebServlet("/addUser")
public class AddUserServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private UserDao userDao;
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	// Retrieve form parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        // Create User object
        User user = new User(username, password, Integer.parseInt(role));

        // Add user to the database
        userDao.addUser(user);

        // Redirect back to the login page
        response.sendRedirect("login.html");
    }
}