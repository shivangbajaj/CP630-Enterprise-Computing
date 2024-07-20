package ec.stats.sb;

import javax.ejb.Local;

@Local
public interface StatsStatefulLocal {
    void insertData(double a);
    void createModel();
    String getStats();
}
