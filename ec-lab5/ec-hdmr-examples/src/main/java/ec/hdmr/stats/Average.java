package ec.hdmr.stats;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableUtils;

public class Average implements Writable {
	double number;
	long count;

	public Average() {
		super();
	}

	public Average(double number, long count) {
		this.number = number;
		this.count = count;
	}

	public double getNumber() {
		return number;
	}

	public void setNumber(double number) {
		this.number = number;
	}

	public long getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void readFields(DataInput dataInput) throws IOException {
		number = Double.parseDouble(WritableUtils.readString(dataInput));
		count = WritableUtils.readVLong(dataInput);
	}

	public void write(DataOutput dataOutput) throws IOException {
		WritableUtils.writeString(dataOutput, String.valueOf(number));
		WritableUtils.writeVLong(dataOutput, count);
	}
}