package ec.stats;

public interface Statistics {
	
	void addData(double dataValue);
    /**
     * Compute and reurn the descriptive stats
     *
     */
    void stats();

    /**
     * Return thenumber of data elements in the data structure.
     *
     */
    int getCount();

    /**
     * Return the minimum value in the data structure
     *
     * @return The minimum value.
     */
    double getMin();

    /**
     * Return the max value in the data structure
     *
     * @return The maxi value.
     */
    double getMax();

    /**
     * Return teh average (mean)value of the data structure
     *
     * @return The average value.
     */
    double getMean();

    /**
     * Return the standard deviation of the data vals
     *
     * @return The standard deviation.
     */
    double getSTD();
}
