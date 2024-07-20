//CP630 A2 by Vaibhav Thakur (235811400)
package ec.stats.sb;

import javax.ejb.Remote;

import ec.stats.DescriptiveStatistics;

@Remote
public interface StatsSingletonRemote
{
    public void addData(Double x);
    public int getCount();
    public DescriptiveStatistics stats();
    public void saveModel();
}