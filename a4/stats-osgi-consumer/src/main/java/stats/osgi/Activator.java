package stats.osgi;

import ec.stats.StatsOSGi;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

public class Activator implements BundleActivator {
    private ServiceTracker<StatsOSGi, StatsOSGi> tracker;

    @Override
    public void start(BundleContext context) throws Exception {
        tracker = new ServiceTracker<StatsOSGi, StatsOSGi>(context, StatsOSGi.class, null);
        tracker.open();
        StatsOSGi statsService = (StatsOSGi) tracker.getService();
        if (statsService != null) {
            // Invoke methods on statsService and print results
            int count = statsService.getCount();
            double min = statsService.getMin();
            double max = statsService.getMax();
            double mean = statsService.getMean();
            double std = statsService.getSTD();
            System.out.println("Count: " + count);
            System.out.println("Min: " + min);
            System.out.println("Max: " + max);
            System.out.println("Mean: " + mean);
            System.out.println("Standard Deviation: " + std);
        } else {
            System.out.println("Failed to obtain StatsOSGi service.");
        }
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        if (tracker != null) {
            tracker.close();
            tracker = null;
        }
    }
}
