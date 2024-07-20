package ec.osgi;

import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;
import ec.osgi.serviceapi.CalculatorService;
import ec.osgi.serviceimpl.CalculatorServiceImpl;

public class Activator implements BundleActivator {
	private CalculatorService service;
	private BundleContext fContext;
	private ServiceTracker tracker;
	
	public void start(BundleContext context) throws Exception {
		fContext = context;
		service = new CalculatorServiceImpl();
		Hashtable props = new Hashtable();
		//can add properties
		context.registerService(CalculatorService.class.getName(), service, props);
		// create a tracker and track the service
		tracker = new ServiceTracker(context, CalculatorService.class.getName(), null);
		tracker.open();	
		service = (CalculatorService) tracker.getService();
		System.out.println("3+4="+service.sum(3, 4));
	}

	public void stop(BundleContext context) throws Exception {
		tracker.close();
		tracker = null;
		service = null;
		fContext = null;
		System.out.println("servie stop");
	}
}
