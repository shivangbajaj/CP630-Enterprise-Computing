//CP630 A2 by Vaibhav Thakur (235811400)
package ec.stats.sb;

import javax.ejb.Local;

@Local
public interface StatsStatefulLocal
{
    public void insertData(Double x);
    public void createModel();
    public String getStats();
}