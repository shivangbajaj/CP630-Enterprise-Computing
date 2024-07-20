package ec.stats;

import java.io.Serializable;

public class StatsSummary implements Serializable
{
	//
    private static final long serialVersionUID = 1L;
    private int counts = 0;
    private Double min = 0d;
    private Double max = 0d;
    private Double mean = 0d;
    private Double std = 0d;
    public int getCount(){return counts;}
    public void setCount(int a){counts = a;}
    public double getMin(){return min;}
    public void setMin(Double a){min = a;}
    public double getMax(){return max;}
    public void setMax(Double a){max = a;}
    public double getMean(){return mean;}
    public void setMean(Double a){mean = a;}
    public double getSTD(){return std;}
    public void setSTD(Double a){std = a;}
    public String toString(){
        StringBuffer buffer = new StringBuffer();
        buffer.append("count=" + counts + "\n");
        buffer.append("min=" + min+"\n");
        buffer.append("max=" + max+"\n");
        buffer.append("mean=" +   mean + "\n");
        buffer.append("std=" + std    + "\n");
        return buffer.toString();
        //
    }
}