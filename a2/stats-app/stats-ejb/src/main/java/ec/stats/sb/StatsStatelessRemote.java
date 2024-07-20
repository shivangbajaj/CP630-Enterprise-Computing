package ec.stats.sb;

import javax.ejb.Remote;

import ec.stats.StatsSummary;

@Remote
public interface StatsStatelessRemote
{
    public int getCount();
    public Double getMin();
    public Double getMax();
    public Double getMean();
    public Double getSTD();
    public String toString();
    public StatsSummary LoadModel();
}