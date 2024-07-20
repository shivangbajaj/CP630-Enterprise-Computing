package ec.lab;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import ec.lab.SBSingletonRemote;
import ec.lab.SBStatefulRemote;
import ec.lab.SBStatelessRemote;

public class EJBClient {
	private static final Logger logger = LogManager.getLogger(EJBClient.class.getName());  
    public static void main(String[] args) throws NamingException {
        SBStatelessRemote sbstateless = (SBStatelessRemote) InitialContext
                .doLookup("ec-ejb/SBStateless!ec.lab.SBStatelessRemote");
        System.out.println(sbstateless.getSBType());
        System.out.println(sbstateless.getPrediction(70));
		logger.info("RMI of SBStateless"); 
		
        SBStatefulRemote sbstateful = (SBStatefulRemote) InitialContext
                .doLookup("ec-ejb/SBStateful!ec.lab.SBStatefulRemote");
        System.out.println(sbstateful.getSBType());
        System.out.println(sbstateful.Predict(60));
        System.out.println(sbstateful.getCounter());
		logger.info("RMI of SBStateful"); 

        SBSingletonRemote sbsingleton = (SBSingletonRemote) InitialContext
                .doLookup("ec-ejb/SBSingleton!ec.lab.SBSingletonRemote");
        System.out.println(sbsingleton.getSBType());
        System.out.println(sbsingleton.getCounter());
		logger.info("RMI of SBSingleton"); 

		logger.info("RMI of ec-ejb is done!"); 
    }
}