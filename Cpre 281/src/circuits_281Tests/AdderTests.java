package circuits_281Tests;

import circuits_281.Adder;

public class AdderTests 
{
	public static void main(String[] args) {
		
	Adder ad = new Adder(5);
	boolean[] num1 = {false, false ,false , true};
	boolean[] num2 = {false, false ,true , false};
	boolean[] num3 = {false, false ,true , true};
	boolean[] num4 = {false, true ,false , false};
	boolean[] num5 = {false, true ,false , true};
	boolean[] num6 = {false, true ,true , false};
	boolean[] num12 = {true, true ,false , false};
	boolean[] test = new boolean[5];
	System.out.println(test[0]);
	}
}
