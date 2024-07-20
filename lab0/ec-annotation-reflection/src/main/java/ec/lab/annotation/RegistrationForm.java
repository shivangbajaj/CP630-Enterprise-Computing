package ec.lab.annotation;


/*
 * Application example of annotation
 */
public class RegistrationForm {
	@MyFieldAnnotation("email")
    private String email;

	@MyFieldAnnotation("password")
    private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}