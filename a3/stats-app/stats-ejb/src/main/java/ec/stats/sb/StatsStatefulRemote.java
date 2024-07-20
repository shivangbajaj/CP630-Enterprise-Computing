package ec.stats.sb;

import javax.ejb.Remote;

@Remote
public interface StatsStatefulRemote {
    void insertData(double a);
    void createModel();
    String getStats();
}
