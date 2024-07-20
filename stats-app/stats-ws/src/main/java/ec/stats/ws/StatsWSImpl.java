package ec.stats.ws;

import javax.ejb.EJB;
import javax.jws.WebService;

import ec.stats.sb.StatsStateless;

@WebService(endpointInterface = "ec.stats.ws.StatsWS")
public class StatsWSImpl implements StatsWS {
	
    @EJB
    private StatsStateless statsStateless;

    @Override
    public int getCount() {
        return statsStateless.getCount();
    }

    @Override
    public double getMin() {
        return statsStateless.getMin();
    }

    @Override
    public double getMax() {
        return statsStateless.getMax();
    }

    @Override
    public double getMean() {
        return statsStateless.getMean();
    }

    @Override
    public double getSTD() {
        return statsStateless.getSTD();
    }
}
