
import stdlib.*;

// Terry Schmidt, ID 1433009

public class EqualTemperedFrequency implements Comparable<EqualTemperedFrequency> {
	double frequency;
	
	public EqualTemperedFrequency(double initial) {
		this.frequency = initial;
	} 
	
	public EqualTemperedFrequency next() {
		if (frequency < 4186.01) {
			return new EqualTemperedFrequency(frequency * Math.pow(2, 0.08333));
		}
		else {
			return new EqualTemperedFrequency(frequency);
		}
	}
	
	public void play(double duration) {
		final int sliceCount = (int) (StdAudio.SAMPLE_RATE * duration);
		final double[] slices = new double[sliceCount+1];
		for (int i=0; i<= sliceCount; i++){
			slices[i] = Math.sin(2 * Math.PI * i * this.frequency / StdAudio.SAMPLE_RATE);
		}
		StdAudio.play(slices);
	}
	
	boolean isLessThanOrEqual(EqualTemperedFrequency b) {
		return compareTo(b) < 0;
	}
	
	public String toString() {
		return "The current frequency is: " + frequency;
	}
	
	public int compareTo(EqualTemperedFrequency that) {
        if (this.frequency < that.frequency) return -1;
        if (this.frequency > that.frequency) return +1;
        else return  0;
	}
	
}
