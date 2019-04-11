package circuits_281;
import java.util.Arrays;

import static_helpers.LogicMethods;
/**
 * Upon instantiation of this class you will provide it with 
 * the number of bits you want this adder can add. This cannot be changed,
 * i.e. if you create an (n-1)-bit adder. It will at most add n-1-bit numbers
 * @author Jacob Abkes
 *
 *Boolean arrays will be read as (n-1) bit numbers,
 *where the element at the zeroth index is the MSB
 *and the element at the n-1 index is the LSB
 *
 *this adder will not do anything unless told
 *which means you must load in the values you want to add
 *and then call the add method. The add method is the equivalent
 *of having an enable switch turned on. It will hold it's past state
 *until add is called again
 */
public class Adder 
{
	/**
	 * This contains the size of this adder with an integer,
	 * this is for sanities sake. 
	 */
	private int size;
	/**
	 * The currently loaded x binary value
	 */
	private boolean[] xLoad;
	/**
	 * The currently loaded y binary value
	 */
	private boolean[] yLoad;
	/**
	 * is there overflow with the last addition called?
	 */
	private boolean overFlow;
	/**
	 * does this adder have a carryout to the n+1 place on the last addition called?
	 */
	private boolean cOut;
	/**
	 * This constructor is used to take an integer as an argument
	 * for the size of this adder. 
	 * Will throw an IllegalArgumentException if the number is less than
	 * or equal to zero
	 * @param n
	 */
	public Adder(int n)
	{
		if(n <= 0)
			throw new IllegalArgumentException();
		size = n;
		cOut = false;
		overFlow = false;
		xLoad = new boolean[n]; //each value is already initialized to false
		yLoad = new boolean[n]; //which means it is in a ground state
	}
	
	/**
	 * This constructor is uded to take a boolean array
	 * as an argument. All boolean arrays will be treated
	 * as a binary number, where element [0] = MSB and the
	 * element [length-1] is the LSB
	 * Will throw an illegal argument exception if num is a null reference 
	 * @param num
	 */
	public Adder(boolean[] num)
	{
		if(num == null)
			throw new IllegalArgumentException();
		size = LogicMethods.parseBinary(num)+1;
	}
	
	/**
	 * This method will load the values into the adder
	 * Throws IAE if the arguments are null, empty or are to
	 * large for this adder.
	 * @param x
	 * @param y
	 */
	public void load(boolean[] x, boolean[]y) 
	{
		//TODO
		if(x == null || y == null || x.length == 0 || y.length == 0)
			throw new IllegalArgumentException("null or empty arguments");
		if(x.length > size || y.length > size)
			throw new IllegalArgumentException("number of bits is too large");
		checkInitilization();
		if(x.length < size) {
			//copy x into a proper sized arr
		}
		if(y.length < size) {
			//copy y into a proper sized arr
		}
		if(y.length == size && x.length == size){ //The easy case
			this.xLoad = Arrays.copyOf(x, x.length);
			this.yLoad = Arrays.copyOf(y, y.length);
		}
		//done
	}
	
	public boolean[] add() {
		checkInitilization();
		return null;
	}
	
	private void detectOverFlow() {
		checkInitilization();
	}
	
	/**
	 * This method just ensures us that someone isn't attempting to change the
	 * state of this adder when it has not been properly initialized
	 */
	private void checkInitilization() {
		if(xLoad == null || yLoad == null) {
			throw new IllegalStateException("Has not been instantiated");
		}
	}
}
