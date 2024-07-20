package ec.jpa;

import javax.ejb.Remote;

@Remote
public interface UserSBRemote {
	 public String Predict(int a, String user);
}