package ec.stats;

import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;
import ec.stats.StatsOSGi;
import ec.stats.StatsOSGiImpl;

public class Activator implements BundleActivator {
    private StatsOSGiImpl service;
	private ServiceTracker tracker;
    @Override
    public void start(BundleContext context) throws Exception {
        service = new StatsOSGiImpl();
        context.registerService(StatsOSGi.class.getName(), service, null);
        System.out.println("stats-osgi-service bundle started.");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        tracker.close();
		tracker = null;
		service = null;
        System.out.println("stats-osgi-service bundle stopped.");
    }
}
