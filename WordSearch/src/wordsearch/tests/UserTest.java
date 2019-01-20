package wordsearch.tests;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.*;

import wordsearch.prgm.User;
import wordsearch.prgm.WordSearch;



/**
 * 
 * @author Jacob Abkes
 * This class will be testing the file read in different scenarios depending on the
 * files given to the test. I will have some files that test edge cases of the algorithm.
 *
 */
public class UserTest 
{

	@Test
	/**
	 * This tests to see if we are reading things correctly on a simple file
	 * Also tests to make sure we are converting everything to lowercase based on file inputs
	 */
	public void readFilesTest1() 
	{
		File puz = new File("simplePuzzle.txt");
		File words = new File("simpleWordBank.txt");
		WordSearch cw = User.createWordSearch(puz, words);
		
		Character[][] puzzle = new Character[][] {{'s','r','n'},{'c','a','t'},{'r','t','t'}};
		String[] wordbank = new String[] {"rat","cat","sat","ran"};
		//Files are reading good so begin testing the systems 
		
		// Hardcheck the puzzle is being copied from the file correctly
		for(int i = 0; i < puzzle.length; i++) 
		{
			for(int j = 0; j< puzzle[i].length; j++) 
			{
				assertTrue(puzzle[i][j].compareTo(cw.getItem(i, j)) == 0);
			}
			
		}
		//Hardcheck the wordbank
		for(int i = 0; i < wordbank.length; i++) 
		{
			assertTrue(wordbank[i].compareTo(cw.getWord(i))==0);
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void readFileTest2() 
	{
		File puz = new File("blankPuzzle.txt");
		File words = new File("simpleWordBank.txt");
		@SuppressWarnings("unused")
		WordSearch cw = User.createWordSearch(puz, words);
	}
	@Test(expected = IllegalArgumentException.class)
	public void readFileTest3() 
	{
		File puz = new File("simplePuzzle.txt");
		File words = new File("blankWordBank.txt");
		@SuppressWarnings("unused")
		WordSearch cw = User.createWordSearch(puz, words);
	}
	
	@Test
	//More complex testing
	public void readFileTest4() 
	{
		File puz = new File("complexPuzzle.txt");
		File words = new File("complexWordBank.txt");
		WordSearch cw = User.createWordSearch(puz, words);
		
		Character[][] puzzle = new Character[][] //this took a while to type, probably could just generated
											// some random stuff for it
											//this will be used later to test the searching tho
			{
				{'c','b','c','l','i','n','x','w'},
				{'o','e','s','e','o','h','l','i'},
				{'m','i','x','t','a','p','e','l'},
				{'p','g','s','t','a','e','y','d'},
				{'u','a','s','e','g','u','j','c'},
				{'t','s','d','r','f','d','d','a'},
				{'e','l','f','c','a','o','g','r'},
				{'r','w','i','l','d','k','o','d'}
			};
		String[] wordbank = new String[] {"linx","computer","wildcard","mixtape","letter","elf"};
		for(int i = 0; i < puzzle.length; i++) 
		{
			for(int j = 0; j < puzzle[i].length; j++) 
			{
				assertTrue(puzzle[i][j] == cw.getItem(i, j));
			}
		}
		
		for(int i = 0; i < wordbank.length; i++) 
		{
			assertTrue(wordbank[i].compareTo(cw.getWord(i)) ==0);
		}
	}
}
