package WarmUp;

//Terry Schmidt, October 2014

public class StringToLongFun {
    public static void main(String[] args) {
    	System.out.println("These should return the digit form of the char it is passed.");
    	System.out.println(charToDigit('1'));
    	System.out.println(charToDigit('2'));
    	System.out.println(charToDigit('7'));
    	System.out.println("");
    	
    	System.out.println("These aren't digits that can be composed into a long so they should return -1 signifying an error.");
    	System.out.println(charToDigit('.'));
    	System.out.println(charToDigit('-'));
    	System.out.println(charToDigit('a'));
    	System.out.println(charToDigit('x'));
    	System.out.println("");
    	
    	System.out.println("Testing method that returns 10 raised to a power passed to the method.");
    	System.out.println(exponentOfTen(0));
    	System.out.println(exponentOfTen(1));
    	System.out.println(exponentOfTen(5));
    	System.out.println(exponentOfTen(10));
    	System.out.println("");
    	
    	System.out.println("These should just return the long that the string represents.");
    	System.out.println(stringToLong("4"));
    	System.out.println(stringToLong("27"));
    	System.out.println(stringToLong("192"));
    	System.out.println(stringToLong("9964"));
    	System.out.println(stringToLong("45902"));
    	System.out.println(stringToLong("178392"));
    	System.out.println(stringToLong("3269840"));
    	System.out.println(stringToLong("12345678"));
    	System.out.println(stringToLong("1234567891234567891"));
    }
    
    
    
    
    public static long stringToLong(String str) throws IllegalArgumentException {
    	char currentChar;
        int digit = 0;
        long longToBeReturned = 0;
        long longToBeReturnedPrev = 0;
        
        if (str.length() > 19) {
        	System.out.println("That string is tooooooo long!");
            return -1;
           }

        if (str.length() == 0){
        	System.out.println("Why did you pass a string of length 0?!");
            return -1;
           }
        
        for (int exponent = 0; exponent < str.length(); exponent++)  {
            currentChar = str.charAt((str.length() -1) - exponent);
            	if (-1 == charToDigit(currentChar)) {
            		return -1;
            	}

            digit = charToDigit(currentChar);
            longToBeReturned = longToBeReturned + (digit * exponentOfTen(exponent));

            //if it got smaller, then there was an overflow.
            	if (longToBeReturned < longToBeReturnedPrev) {
            		IllegalArgumentException overflow = new IllegalArgumentException("The number became too big for Java to hold and overflowed.");
            		throw overflow;
                 }
           }
        return longToBeReturned;
     }
    
    
    

    public static int charToDigit(char character) {
        if (!Character.isDigit(character)) {
            return -1;
           }
        int digitToBeReturned = Character.digit(character, 10);
        return digitToBeReturned;
      }
    
    
    

    public static long exponentOfTen(int power) {
        if (power > 18) {
        	System.out.println("I can't raise 10 to the " + power + " power.  There would be overflow.  :(");
            return -1;
           }
        long tenToPower = 1;
        for (int i = 0; i < power; i++)  {
            tenToPower = 10 * tenToPower;
            }
     return tenToPower;
      }
}
