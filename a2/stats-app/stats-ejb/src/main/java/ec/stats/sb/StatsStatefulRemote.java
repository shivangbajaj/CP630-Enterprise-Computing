//CP630 A2 by Vaibhav Thakur (235811400)
package ec.stats.sb;

import javax.ejb.Remote;

@Remote
public interface StatsStatefulRemote
{
    public void insertData(Double x);
    public void createModel();
    public String getStats();
} 