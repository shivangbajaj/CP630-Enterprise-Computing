package ec.jsf;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class User {
	private String name = "null";
	private String password = "null";

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String login() {
		if (getName().equals("admin") && getPassword().equals("cp630")) {
			return "loginSuccessJSF.xhtml";
		}
		else {
			return "relogin-jsf.xhtml";
		}
	}
}
