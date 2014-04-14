import stdlib.StdAudio;
import stdlib.StdIn;

public class PlaySong {

	public static void main(String[] args) {
		StdIn.fromFile("data/doubles.txt");
		double[] numbers = StdIn.readAllDoubles();
		for (int index = 0; index < numbers.length; index++) {
			playTone(numbers[index], 0.25);
		}
	}

	public static void playTone(double frequency, double duration) {
		final int sliceCount = (int) (StdAudio.SAMPLE_RATE * duration);
		final double[] slices = new double[sliceCount + 1];
		for (int i = 0; i <= sliceCount; i++) {
			slices[i] = Math.sin(2 * Math.PI * i * frequency
					/ StdAudio.SAMPLE_RATE);
		}
		StdAudio.play(slices);

	}

}
