package ec.lab;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.LocalBean;

/**
 * Session Bean implementation class SBStateful
 */
@Stateful
@LocalBean
public class SBStateful implements SBStatefulRemote, SBStatefulLocal {
        
    @EJB
    private SBStatelessLocal sbsl;
        
    public SBStateful() {
    }

    @Override
    public String getSBType() {
        return "stateful " + sbsl.getCounter();
    }

    @Override
    public String Predict(int a) {        
        return sbsl.getPrediction(a);
    }

    @Override
    public int getCounter() {
        return sbsl.getCounter();
    }

}