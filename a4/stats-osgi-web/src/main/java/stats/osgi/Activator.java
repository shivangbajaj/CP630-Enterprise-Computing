package stats.osgi;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpService;
import org.osgi.util.tracker.ServiceTracker;
import ec.stats.StatsOSGi;

public class Activator implements BundleActivator {
    private ServiceTracker<HttpService, HttpService> httpServiceTracker;
    private ServiceTracker<StatsOSGi, StatsOSGi> tracker;
    private StatsOSGi service;

    @Override
    public void start(BundleContext context) throws Exception {
        tracker = new ServiceTracker<StatsOSGi, StatsOSGi>(context, StatsOSGi.class, null);
		tracker.open();
		service = (StatsOSGi) tracker.getService();
    	
        httpServiceTracker = new ServiceTracker<HttpService, HttpService>(context, HttpService.class.getName(), null) {
            @Override
            public HttpService addingService(ServiceReference<HttpService> ref) {
                HttpService httpService = (HttpService) context.getService(ref);
                try {
                   httpService.registerServlet("/stats-osgi-web", new StatsOsgiServlet(service), null, null);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return httpService;
            }
            public void removedService(ServiceReference<HttpService> ref, HttpService service) {
                try {
                    ((HttpService) service).unregister("/stats-osgi-web");
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