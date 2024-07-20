package ec.jpa;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import ec.lab.SBStatelessLocal;

@Stateful
public class UserSB implements UserSBRemote {
        
    @EJB
    private SBStatelessLocal sbsl;
    
	@EJB
	private SecurityLocal guard;
        
    public String Predict(int a, String user) {		
    	if (guard.validate(user) == true) {			
    	  return sbsl.getPrediction(a);
    	} 
    	else 
    	  return "invalid user";
    }

}