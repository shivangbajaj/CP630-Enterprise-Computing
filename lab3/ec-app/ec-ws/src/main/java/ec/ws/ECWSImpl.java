package ec.ws;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;
import ec.lab.SBStatelessLocal;

@WebService(endpointInterface = "ec.ws.ECWS")
public class ECWSImpl implements ECWS {
    @EJB
    SBStatelessLocal sbsl;
            
    @WebMethod()
    public String predict(int value) {
        return sbsl.getPrediction(value);
    }
}