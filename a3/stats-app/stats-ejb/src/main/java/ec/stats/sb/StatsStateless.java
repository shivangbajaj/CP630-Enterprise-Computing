package ec.stats.sb;

import javax.ejb.Stateless;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import ec.stats.StatsSummary;

@Stateless
@LocalBean
public class StatsStateless implements StatsStatelessLocal, StatsStatelessRemote {
	public static final String SAVE_PATH = "C:\\enterprise\\tmp\\model\\stats.bin";
	public static final Logger LOGGER = Logger.getLogger(StatsStateless.class.getName());
	private StatsSummary sm;

	// Load method to load StatsSummary from file
	public StatsSummary loadModel() {
        try 
        {
            FileInputStream fileIn = new FileInputStream(SAVE_PATH);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            sm = (StatsSummary)in.readObject();
            in.close();
            fileIn.close();
        }
        catch (IOException i)
        {
            i.printStackTrace();
            return null;
        }
        catch (ClassNotFoundException c)
        {
            System.out.println("Stats summary not found!!");
            c.printStackTrace();
            return null;
        }
        LOGGER.info("Model read: \n" + sm.toString());
        return sm;
	}

	// Implementation of getCount method
	@Override
	public int getCount() {
		if (sm == null) {
			sm = loadModel();
		}
		return (sm != null) ? sm.getCount() : 0;
	}

	// Implementation of getMin method
	@Override
	public double getMin() {
		if (sm == null) {
			sm = loadModel();
		}
		return (sm != null) ? sm.getMin() : 0.0;
	}

	// Implementation of getMax method
	@Override
	public double getMax() {
		if (sm == null) {
			sm = loadModel();
		}
		return (sm != null) ? sm.getMax() : 0.0;
	}

	// Implementation of getMean method
	@Override
	public double getMean() {
		if (sm == null) {
			sm = loadModel();
		}
		return (sm != null) ? sm.getMean() : 0.0;
	}

	// Implementation of getSTD method
	@Override
	public double getSTD() {
		if (sm == null) {
			sm = loadModel();
		}
		return (sm != null) ? sm.getSTD() : 0.0;
	}

	// Implementation of toString method
	@Override
	public String toString() {
		if (sm == null) {
			sm = loadModel();
		}
		return (sm != null) ? sm.toString() : "StatsSummary not available";
	}
}
