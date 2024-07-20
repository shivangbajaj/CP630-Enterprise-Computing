import org.junit.Test;

import static org.junit.Assert.*;

import ec.stats.ECStatistics;

public class StatisticsTest {

    @Test
    public void testStatisticsOperations() {
        ECStatistics ecStatistics = new ECStatistics();

        assertEquals(0, ecStatistics.getCount());
        assertEquals(Double.POSITIVE_INFINITY, ecStatistics.getMin(), 0.0);
        assertEquals(Double.NEGATIVE_INFINITY, ecStatistics.getMax(), 0.0);
        assertEquals(0.0, ecStatistics.getMean(), 0.0);
        assertEquals(0.0, ecStatistics.getSTD(), 0.0);

        ecStatistics.addData(10.0);
        ecStatistics.addData(20.0);
        ecStatistics.addData(30.0);

        assertEquals(3, ecStatistics.getCount());
        assertEquals(10.0, ecStatistics.getMin(), 0.0);
        assertEquals(30.0, ecStatistics.getMax(), 0.0);
        assertEquals(20.0, ecStatistics.getMean(), 0.0001);
        assertEquals(8.16496580927726, ecStatistics.getSTD(), 0.0001);

        ecStatistics.stats()	;

        assertEquals(3, ecStatistics.getCount());
        assertEquals(10, ecStatistics.getMin(), 0.0);
        assertEquals(30, ecStatistics.getMax(), 0.0);
        assertEquals(20, ecStatistics.getMean(), 0.0);
        assertEquals(8.16496580927726, ecStatistics.getSTD(), 0.0);
        
    }
}
