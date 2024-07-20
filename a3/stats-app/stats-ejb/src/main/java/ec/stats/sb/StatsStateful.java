package ec.stats.sb;

import javax.ejb.Stateful;

import ec.stats.StatsSummary;

import javax.ejb.LocalBean;
import javax.ejb.EJB;

@Stateful
@LocalBean
public class StatsStateful implements StatsStatefulRemote, StatsStatefulLocal {
	@EJB
	private StatsSingleton statsSingleton;

	@EJB
	private StatsStateless statsStateless;

	@Override
	public void insertData(double a) {
		statsSingleton.addData(a);
	}

	@Override
	public void createModel() {
		statsSingleton.saveModel();
	}

	@Override
	public String getStats() {
		StatsSummary stats = statsStateless.loadModel();
		return stats.toString();
	}

    public int getCount() {
        return statsSingleton.getCount();
    }
}
