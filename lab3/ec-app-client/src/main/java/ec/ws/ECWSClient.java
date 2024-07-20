package ec.ws;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class ECWSClient {
    public static void main(String[] args) throws MalformedURLException {
    	URL url = new URL("http://localhost:8080/ec-ws/ecws?WSDL");
        QName qname = new QName("http://ws.ec/", "ECWSImplService");
        Service service = Service.create(url, qname);
        ECWS ms  = service.getPort(ECWS.class);
        System.out.println(ms.predict(90));       
    }
}