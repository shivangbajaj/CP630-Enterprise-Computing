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