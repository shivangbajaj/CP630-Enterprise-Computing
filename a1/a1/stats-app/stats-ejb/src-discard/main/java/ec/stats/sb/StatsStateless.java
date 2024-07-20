package ec.stats;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import ec.stats.interfaces.StatsStatelessLocal;
import ec.stats.interfaces.StatsStatelessRemote;

public class StatsStateless implements StatsStatelessLocal, StatsStatelessRemote {
	
	StatsSummary sm = new StatsSummary();
	
	@Override
	public StatsSummary loadModel() throws FileNotFoundException, IOException, ClassNotFoundException{
		String filePath = "C:/enterprise/tmp/model/stats.bin";
		Path path = Paths.get(filePath);
		
		if(Files.exists(path)) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
			sm = (StatsSummary) ois.readObject();
			return sm;
		}else {
			System.err.println("File not found: " + filePath);
		}
		
		return null;
	}
	
	@Override
	public int getCount() {
	    if (sm == null) { 
	    	
	    	try {
	    		sm = loadModel();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    if (sm != null)
	        return sm.getCount();
	    else 
	        return 0;
	}

	@Override
	public double getMin() {
		// TODO Auto-generated method stub
		return sm.getMin();
	}

	@Override
	public double getMax() {
		// TODO Auto-generated method stub
		return sm.getMax();
	}

	@Override
	public double getMean() {
		// TODO Auto-generated method stub
		return sm.getMean();
	}

	@Override
	public double getSTD() {
		// TODO Auto-generated method stub
		return sm.getSTD();
	}
}
