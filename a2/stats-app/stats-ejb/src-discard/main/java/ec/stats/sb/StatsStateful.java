package ec.stats;

import javax.ejb.EJB;

import ec.stats.interfaces.StatsSingletonLocal;
import ec.stats.interfaces.StatsStatefulLocal;
import ec.stats.interfaces.StatsStatefulRemote;
import ec.stats.interfaces.StatsStatelessLocal;

public class StatsStateful implements StatsStatefulLocal, StatsStatefulRemote {
	
	@EJB
	private StatsSingletonLocal singleton;
	
	@EJB
	private StatsStatelessLocal statelessLocal;

	@Override
	public void insertData(Double x) {
		// TODO Auto-generated method stub
		singleton.addData(x);
	}

	@Override
	public void createModel() {
		// TODO Auto-generated method stub
		singleton.saveModel();
	}

	@Override
	public String getStats() {
		// TODO Auto-generated method stub
		statelessLocal.getCount();
		return singleton.toString();
	}
	
	public int getCount() {
		return singleton.getCount();
	}
}
