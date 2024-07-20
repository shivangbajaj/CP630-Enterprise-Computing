package ec.stats.ws;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

public class StatsWSClient {
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://localhost:8080/stats-ws/StatsWSImpl?wsdl");
        QName qname = new QName("http://ws.stats.ec/", "StatsWSImplService");
        Service service = Service.create(url, qname);
        StatsWS statsWS = service.getPort(StatsWS.class);
        System.out.println("Count: " + statsWS.getCount());
        System.out.println("Min: " + statsWS.getMin());
        System.out.println("Max: " + statsWS.getMax());
        System.out.println("Mean: " + statsWS.getMean());
        System.out.println("Standard Deviation: " + statsWS.getSTD());
    }
}
