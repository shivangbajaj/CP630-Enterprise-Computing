package com.ec.lab;

import java.io.Serializable;
import javax.inject.Inject;
import org.jboss.as.quickstarts.greeter.domain.User;
import org.jboss.as.quickstarts.greeter.domain.UserDao;

//@ManagedBean(name = "userLogin", eager = true)
//@SessionScoped
public class UserLogin implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String password;

	@Inject
	private UserDao userDao;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		System.out.println("name============:" + name);
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		System.out.println("password===========:" + password);
		this.password = password;
	}

	public String login() {
		User user = userDao.getForUsername(name);

		if (user != null) {
			System.out.println("Hello, " + user.getFirstName() + " " + user.getLastName() + "!");
		} else {
			System.out.println("user not found");
			return "home";
		}
		
		if (user.getUsername().equals(name) && user.getPassword().equals(password)) {

			String role = user.getRole();

			if (role.equals("1")) {
				return "page1";
			} else if (role.equals("2")) {

				return "page2";
			} else if (role.equals("3")) {
				return "page3";
			} else
				return "home";
		} else {
			System.out.println("fail authentication");
			return "relogin";
		}

	}
}