package ec.stats;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyStatistics {

    private static final Logger logger = Logger.getLogger(MyStatistics.class.getName());

    public static void main(String[] args) {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        logger.addHandler(consoleHandler);

        ECStatistics ecStatistics = new ECStatistics();

        for (int i = 1; i <= 10000; i++) {
            ecStatistics.addData(i);
        }

        logger.log(Level.INFO, "Count: " + ecStatistics.getCount());
        logger.log(Level.INFO, "Min: " + ecStatistics.getMin());
        logger.log(Level.INFO, "Max: " + ecStatistics.getMax());
        logger.log(Level.INFO, "Mean: " + ecStatistics.getMean());
        logger.log(Level.INFO, "STD: " + ecStatistics.getSTD());

        ecStatistics.stats();

        logger.log(Level.INFO, "Count after stats(): " + ecStatistics.getCount());
        logger.log(Level.INFO, "Min after stats(): " + ecStatistics.getMin());
        logger.log(Level.INFO, "Max after stats(): " + ecStatistics.getMax());
        logger.log(Level.INFO, "Mean after stats(): " + ecStatistics.getMean());
        logger.log(Level.INFO, "STD after stats(): " + ecStatistics.getSTD());
    }
}
