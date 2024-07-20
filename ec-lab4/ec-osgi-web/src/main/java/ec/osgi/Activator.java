package ec.osgi;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpService;
import org.osgi.util.tracker.ServiceTracker;
import ec.osgi.serviceapi.CalculatorService;

public class Activator implements BundleActivator {
    private ServiceTracker httpServiceTracker;
    private ServiceTracker tracker;
    private CalculatorService service;

    @Override
    public void start(BundleContext context) throws Exception {
		tracker = new ServiceTracker(context, CalculatorService.class.getName(), null);
		tracker.open();
		service = (CalculatorService) tracker.getService();
    	
        httpServiceTracker = new ServiceTracker(context, HttpService.class.getName(), null) {
            @Override
            public Object addingService(ServiceReference ref) {
                HttpService httpService = (HttpService) context.getService(ref);
                try {
                   httpService.registerServlet("/ec-osgi-web", new OSGiServlet(service), null, null);
                   httpService.registerServlet("/ec-osgi-web1", new OSGiServlet1(service), null, null);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return httpService;
            }
            public void removedService(ServiceReference ref, Object service) {
                try {
                    ((HttpService) service).unregister("/ec-osgi-web");
                    ((HttpService) service).unregister("/ec-osgi-web1");
               
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
        httpServiceTracker.open();
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        httpServiceTracker.close();
        tracker.close();
        tracker = null;
    }

}
