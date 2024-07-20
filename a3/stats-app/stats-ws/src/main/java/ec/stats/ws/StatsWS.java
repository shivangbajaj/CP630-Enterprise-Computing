package ec.stats.ws;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
@WebService
@SOAPBinding(style = Style.RPC)
public interface StatsWS {
    @WebMethod
    int getCounts();
    @WebMethod
    double getMini();
    @WebMethod
    double getMaxi();
    @WebMethod
    double getMeans();
    @WebMethod
    double getSTD();
}
