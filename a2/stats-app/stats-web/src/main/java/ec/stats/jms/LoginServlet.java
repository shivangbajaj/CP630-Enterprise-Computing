//CP630 A2 by Vaibhav Thakur (235811400)
package ec.stats.jms;

import javax.ejb.*;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.stats.jpa.User;
import ec.stats.jpa.UserDao;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @EJB
    private UserDao userDao;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userDao.getUser(username, password);

        if (user != null) {
            int role = user.getRole();
            if (role == 1) {
                response.sendRedirect("adminPage.html");
            } else if (role == 2) {
                response.sendRedirect("developerPage.html");
            } else if (role == 3) {
                response.sendRedirect("generalUserPage.html");
            }
        } else {
            response.sendRedirect("login.html?error=1");
        }
    }
}
