public class intToBinary {
	public static void main(String[] args) {
		System.out.println(intToBinaries(14));
	}
	
	public static String intToBinaries(int n) {
		String result = "";
		int nModdedBy2;
		
		if(n == 0) {
			return "0";
		} 
		
		while(n != 0) {
			nModdedBy2 = n % 2;
			result = nModdedBy2 + result;
			n = n / 2;
		}
		return result;
	}
}
