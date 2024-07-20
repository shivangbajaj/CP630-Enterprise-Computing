//CP630 A2 by Vaibhav Thakur (235811400)
package ec.stats;

public class DescriptiveStatistics {
	public int counter;
	public Double mini,
			max,
			mean,
			varianc,
			std;

	public DescriptiveStatistics(int count, Double min, Double max, Double mean, Double variance, Double std) {
		this.counter = count;
		this.mini = min;
		this.max = max;
		this.mean = mean;
		this.varianc = variance;
		this.std = std;
	}

	public DescriptiveStatistics() {
		this.counter = 0;
		this.mini = 0d;
		this.max = 0d;
		this.mean = 0d;
		this.varianc = 0d;
		this.std = 0d;
	}
}
