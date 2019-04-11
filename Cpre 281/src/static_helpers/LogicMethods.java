package static_helpers;

public class LogicMethods {
	
	private static final int BASE = 2;
	
	/**
	 * This method will take a binary number as an argument
	 * and will read it's bits and return the corresponding
	 * decimal value of that binary number
	 * Throws an exception if the array is null or of length 0
	 * @param num
	 * @return
	 */
	public static int parseBinary(boolean[] num) 
	{
		if(num == null || num.length == 0)
			throw new IllegalArgumentException();
		int val = 0;
		int counter = 0;
		for(int i = num.length-1; i >= 0; i--, counter++) 
		{
			val += Math.pow(BASE, counter) * parseBool(num[i]);
		}
		return val;
	}
	
	/**
	 * Converts boolean into integer
	 * @param bool
	 * @return
	 */
	private static int parseBool(boolean bool) {
		if(bool)
			return 1;
		return 0;
	}
	
	/**
	 * This method will parse a non-negative decimal value 
	 * and return the binary representation of it.
	 * @param num
	 * @return
	 */
	public static boolean[] parseDecimal(int num) 
	{
		if(num < 0)
			throw new IllegalArgumentException(); //TODO 
		return null;
	}
	
	/**
	 * This method provides a copy of the argument expanded to the given length
	 * since this is a binary expansion, all elements will be shifted as far right as possible
	 * @param arr
	 * @param length
	 * @return
	 */
		public static boolean[] copyExpand(boolean[] arr, int length){
			return null; //TODO
		}

}
