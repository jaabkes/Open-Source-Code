package slay.test;
import static org.junit.Assert.assertEquals;

import org.junit.*;

import slay.source.Hex;
/**
 * The purpose of this class is to test the behavior of the Hex object
 * If all tests pass, the class should function correctly there isn't any real
 * logic in this class so we just need to test it's creation
 * @author Jacob Abkes
 *
 */
public class HexTest 
{

	@Test
	public void testConstruction() 
	{
		Hex test = new Hex();
		assertEquals(0, test.getOwner());
		assertEquals(null,test.getPawn());
		
		for(int i = 0; i < test.getAllNeighbors().length; i++) {
			assertEquals(null, test.getNeighbor(i));
			assert(i < 6);
		}
	}
	
}
