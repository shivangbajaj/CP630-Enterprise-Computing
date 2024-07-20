package ec.jpa;

import javax.ejb.Local;

@Local
public interface SecurityLocal {
	public Boolean validate(String user);
}