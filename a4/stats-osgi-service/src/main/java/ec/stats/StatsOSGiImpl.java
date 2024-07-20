package ec.stats;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

public class StatsOSGiImpl implements StatsOSGi {
	
    private StatsSummary ss;

    public StatsOSGiImpl() {
        // Load StatsSummary from file
        try {
            FileInputStream fis = new FileInputStream("C:/enterprise/tmp/model/stats.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            ss = (StatsSummary) ois.readObject();
            ois.close();
            fis.close();
        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getCount() {
        return ss.getCount();
    }

    @Override
    public double getMin() {
        return ss.getMin();
    }

    @Override
    public double getMax() {
        return ss.getMax();
    }

    @Override
    public double getMean() {
        return ss.getMean();
    }

    @Override
    public double getSTD() {
        return ss.getSTD();
    }
}
