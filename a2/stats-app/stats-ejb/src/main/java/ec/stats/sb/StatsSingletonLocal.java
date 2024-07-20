//CP630 A2 by Vaibhav Thakur (235811400)

package ec.stats.sb;

import javax.ejb.Local;

import ec.stats.DescriptiveStatistics;

@Local
public interface StatsSingletonLocal
{
    public void addData(Double x);
    public int getCount();
    public DescriptiveStatistics stats();
    public void saveModel();
}