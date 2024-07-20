package ec.lab;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import ec.jpa.UserSBRemote;

public class EJBClient2 {
	public static void main(String[] args) throws NamingException {
		UserSBRemote sbstateful = (UserSBRemote) InitialContext
		.doLookup("ec-ejb/UserSB!ec.jpa.UserSBRemote");
		System.out.println(sbstateful.Predict(70, "admin"));
		System.out.println(sbstateful.Predict(60, "guest"));
		System.out.println("RMI done!");
	}
}
