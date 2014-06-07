package hw5solutions;
import java.util.*;

//name: Terry Schmidt, ID#: 1433009, CSC402

public class Djia implements Comparable<Djia> {
	private double closing;
	private String date;
	
	public Djia(String date, double closing) {
		this.date = date;
		this.closing = closing;
	}
	
	public String date() {
		return date;
	}

	
	public double closing() {
		return closing;
	}
	
	public int compareTo(Djia djiaItem) {
		if (this.closing < djiaItem.closing)  {
			return -1;
		}
		
		if (this.closing > djiaItem.closing) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
	public String toString() {
		return "The date is " + date + ", and the closing is " + closing;
	}
	
	
}
