package wordsearch.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import wordsearch.prgm.Pair;
import wordsearch.prgm.WordSearch;

import org.junit.Test;
/**
 * Some of the testing done for the wordsearch item
 * @author Jacob Abkes
 *
 */
public class WordSearchTest 
{

	@Test
	/**
	 * This method tests that we correctly copy the items given
	 * the correct params
	 */
	public void WordSearchCreater1() 
	{
		Character[][] puzzle = new Character[][] {{'a','b','c'}, {'c','b','a'},{'c','b','a'}};
		String[] wordbank = new String[]{"cab"};
		WordSearch cw = new WordSearch(puzzle, wordbank);
		assertEquals(9, cw.getNumItems().intValue()); //soft check all items are present
		assertEquals(1, cw.getNumWords().intValue()); //soft check all words are present
		assertEquals(3, cw.getSize().intValue()); //soft check all is good
		//hard check the puzzle and the wordbank
		for(int i = 0; i < puzzle.length; i++) {
			
			for(int j = 0; j < puzzle.length; j++) //hard check all items in puzzle
			{
				assertTrue(puzzle[i][j].compareTo(cw.getItem(i, j)) == 0);
			}
		}
		//hard check the words
		assertEquals(0, wordbank[0].compareTo(cw.getWord(0)));
	}
	
	@Test 
	/**
	 * This method tests that we correctly copy the items given the correct
	 * params
	 */
	public void WordSearchCreater2() 
	{
		Character[][] puzzle = new Character[][] {{'a','b','c'}, {'c','b','a'},{'c','b','a'}};
		WordSearch cw = new WordSearch(puzzle);
		assertEquals(9, cw.getNumItems().intValue()); //soft check all items are present
		for(int i = 0; i < puzzle.length; i++) {
			
			for(int j = 0; j < puzzle.length; j++) //hard check all items in puzzle
			{
				assertTrue(puzzle[i][j].compareTo(cw.getItem(i, j)) == 0);
			}
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	/**
	 * This method will test some of the exceptions this method might throw
	 */
	public void WordSearchCreater3() 
	{
		Character[][] puzzle = null;
		@SuppressWarnings("unused")
		WordSearch cw = new WordSearch(puzzle);
	}
	
	@Test(expected = IllegalArgumentException.class)
	/**
	 * This method will test giving a blank puzzle
	 */
	public void WordSearchCreater4() 
	{
		Character[][] puzzle = new Character[0][0];
		@SuppressWarnings("unused")
		WordSearch cw = new WordSearch(puzzle);
	}
	
	@Test(expected = IllegalArgumentException.class)
	/**
	 * This method will test giving a blank null wordbank
	 */
	public void WordSearchCreater5() 
	{
		Character[][] puzzle = new Character[][] {{'a','b','c'}, {'c','b','a'},{'c','b','a'}};
		String[] wordbank = null;
		
		@SuppressWarnings("unused")
		WordSearch cw = new WordSearch(puzzle, wordbank);
	}
	
	@Test(expected = IllegalArgumentException.class)
	/**
	 * This method will test giving a blank wordbank
	 */
	public void WordSearchCreater6() 
	{
		Character[][] puzzle = new Character[][] {{'a','b','c'}, {'c','b','a'},{'c','b','a'}};
		String[] wordbank = new String[0];
		@SuppressWarnings("unused")
		WordSearch cw = new WordSearch(puzzle, wordbank);
	}
	
	@Test //Should have no problem with an uneven 2darr
	public void WordSearchCreater7() 
	{
		Character[][] puzzle = new Character[][] {{'a','b','c','y'}, {'c','b','a', 'd'},{'c','b','a','b'}};
		String[] wordbank = new String[] {"word"};
		@SuppressWarnings("unused")
		WordSearch cw = new WordSearch(puzzle, wordbank);

		//hard check the puzzle and the wordbank
		for(int i = 0; i < puzzle.length; i++) {
			
			for(int j = 0; j < puzzle.length; j++) //hard check all items in puzzle
			{
				assertTrue(puzzle[i][j].compareTo(cw.getItem(i, j)) == 0);
			}
		}
		//hard check the words
		assertEquals(0, wordbank[0].compareTo(cw.getWord(0)));
		
	}
	
	
	/**
	 * All of these marked out tests can still be ran, and should be correct.
	 * You just need to switch searchChar in WordSearch to public
	 */
	
//	/**
//	 * Starting at the R in the first row, it will attempt to find the rest of the word "rat" and any 
//	 * other words that may start there. In this case it should have only a start and end point inside
//	 * the linkedlist
//	 */
//	@Test 
//	public void searchCharTest1()
//	{
//		Character[][] puzzle = new Character[][] {{'s','r','n'},
//												  {'c','a','t'},
//												  {'r','t','t'}};
//		String[] wordbank = new String[] {"rat","cat","sat","ran"};
//		WordSearch cw = new WordSearch(puzzle, wordbank);
//		boolean[] beenfound = new boolean[wordbank.length];
//		boolean[] foundthissearch = new boolean[wordbank.length];
//
//		for(int i = 0; i < wordbank.length; i++) {
//			beenfound[i] = false;
//			foundthissearch[i] = false;
//		}
//		Pair p = cw.Pair(1,0); //this method will soley be for testing
//		LinkedList<Pair> test  = cw.searchChar(p, beenfound, foundthissearch);
//		assertTrue(beenfound[0]);
//		assertEquals(p.getX(), test.getFirst().getX());
//		assertEquals(p.getY(), test.getFirst().getY());
//		//we checked to make sure the start point was set correctly
//		test.remove();
//		//the next element should be the end point of the word "rat"
//		assertEquals(1, test.getFirst().getX());
//		assertEquals(2, test.getFirst().getY());
//		test.removeFirst();
//		assertTrue(test.isEmpty());
//
//	}
//	
//	/**
//	 * This method is just testing a similar scenario to the previous test
//	 */
//	@Test
//	public void searchCharTest2() 
//	{
//		Character[][] puzzle = new Character[][] 
//			 {{'s','r','n'},
//			  {'c','a','t'},
//			  {'r','t','t'}};
//		String[] wordbank = new String[] {"rat","cat","sat","ran"};
//		WordSearch cw = new WordSearch(puzzle, wordbank);
//		boolean[] beenfound = new boolean[wordbank.length];
//		boolean[] foundthissearch = new boolean[wordbank.length];
//
//		for(int i = 0; i < wordbank.length; i++) {
//			beenfound[i] = false;
//			foundthissearch[i] = false;
//		}
//		
//		Pair p = cw.Pair(0, 2); //bottom left
//		LinkedList<Pair> test = cw.searchChar(p, beenfound, foundthissearch);
//		assertTrue(beenfound[3]);
//		assertEquals(p.getX(), test.getFirst().getX());
//		assertEquals(p.getY(), test.getFirst().getY());
//		//we checked to make sure the start point was set correctly
//		test.remove();
//		assertEquals(2, test.getFirst().getX());
//		assertEquals(0, test.getFirst().getY());
//		test.removeFirst();
//		assertTrue(test.isEmpty());
//
//	}
//	
//	/**
//	 * This will be testing to see if we are correctly copying the end point of two different words
//	 */
//	@Test
//	public void searchCharTest3() 
//	{
//		Character[][] puzzle = new Character[][] 
//				 {{'s','r','n'},
//				  {'c','a','t'},
//				  {'r','a','t'}};
//		String[] wordbank = new String[] {"rat","cat","sat","ran"};
//		WordSearch cw = new WordSearch(puzzle, wordbank);
//		boolean[] beenfound = new boolean[wordbank.length];
//		boolean[] foundthissearch = new boolean[wordbank.length];
//
//		for(int i = 0; i < wordbank.length; i++) {
//			beenfound[i] = false;
//			foundthissearch[i] = false;
//		}
//		
//		Pair p = cw.Pair(0, 2); //bottom left
//		LinkedList<Pair> test = cw.searchChar(p, beenfound, foundthissearch);
//		//rat should be found first because of the ordering of our words in the wordbank
//		assertTrue(beenfound[0]);
//		assertEquals(p.getX(), test.getFirst().getX());
//		assertEquals(p.getY(), test.getFirst().getY());
//		//we checked to make sure the start point was set correctly
//		test.removeFirst();
//		assertEquals(2, test.getFirst().getX());
//		assertEquals(2, test.getFirst().getY());
//		test.removeFirst();
//		// now we see if ran was found
//		assertFalse(test.isEmpty());
//		assertTrue(beenfound[3]);
//		assertEquals(2, test.getFirst().getX());
//		assertEquals(0, test.getFirst().getY());
//		test.removeFirst();
//		assertTrue(test.isEmpty());
//		
//	}
//	/**
//	 * This tests the search where there will be three words attached to the point
//	 */
//	@Test
//	public void searchCharTest4() 
//	{
//		Character[][] puzzle = new Character[][] 
//				 {{'w','r','n'},
//				  {'o','a','t'},
//				  {'r','a','t'}};
//		String[] wordbank = new String[] {"rat","row","ran"};
//		WordSearch cw = new WordSearch(puzzle, wordbank);
//		boolean[] beenfound = new boolean[wordbank.length];
//		boolean[] foundthissearch = new boolean[wordbank.length];
//		for(int i = 0; i < wordbank.length; i++) {
//			beenfound[i] = false;
//			foundthissearch[i] = false;
//		}
//		Pair p = cw.Pair(0, 2); //bottom left
//		LinkedList<Pair> test = cw.searchChar(p, beenfound,foundthissearch);
//		//rat should be found first because of the ordering of our words in the wordbank
//		assertTrue(beenfound[0]);
//		assertEquals(p.getX(), test.getFirst().getX());
//		assertEquals(p.getY(), test.getFirst().getY());
//
//		test.removeFirst();
//		assertEquals(2, test.getFirst().getX());
//		assertEquals(2, test.getFirst().getY());
//		test.removeFirst();
//		// check for row
//		assertTrue(beenfound[1]);
//		assertEquals(0, test.getFirst().getX()); assertEquals(0, test.getFirst().getY());
//		test.removeFirst();
//		
//		// now we see if ran was found
//		assertFalse(test.isEmpty());
//		assertTrue(beenfound[2]);
//		assertEquals(2, test.getFirst().getX());
//		assertEquals(0, test.getFirst().getY());
//		test.removeFirst();
//		assertTrue(test.isEmpty());
//		
//	}
//	
	
	@Test
	/**
	 * If this test comes out correct, there is very little that could actually go wrong
	 */
	public void searchTest1() 
	{

		Character[][] puzzle = new Character[][] 
			 {{'s','r','n'},
			  {'c','a','t'},
			  {'r','t','t'}};
		String[] wordbank = new String[] {"rat","cat","sat","ran"};
		WordSearch cw = new WordSearch(puzzle, wordbank);
		
		Pair[][] test = cw.searchItrForward();
		//Now we need only worry about the points 
		assertEquals(1,test[0][0].getX()); assertEquals(0, test[0][0].getY()); //rat is the first word in the wordbank
		assertEquals(1, test[0][1].getX()); assertEquals(2,test[0][1].getY());
		//cat is the second word in the wordbank
		assertEquals(0, test[1][0].getX()); assertEquals(1, test[1][0].getY());
		assertEquals(2, test[1][1].getX()); assertEquals(1, test[1][1].getY());
		//sat is the third in the wordbank
		assertEquals(0, test[2][0].getX()); assertEquals(0, test[2][0].getY());
		assertEquals(2, test[2][1].getX()); assertEquals(2, test[2][1].getY());
		//ran is the last item in the wordbank
		assertEquals(0, test[3][0].getX()); assertEquals(2, test[3][0].getY());
		assertEquals(2, test[3][1].getX()); assertEquals(0, test[3][1].getY());
		
	}
	
	@Test
	public void searchWordTest1() 
	{
		Character[][] puzzle = new Character[][] 
			 {{'s','r','n'},
			  {'c','a','t'},
			  {'r','t','t'}};
		String[] wordbank = new String[] {"rat","cat","sat","ran"};
		WordSearch cw = new WordSearch(puzzle, wordbank);
		Pair[] test = cw.searchWord("rat");
		assertTrue(test != null);
		assertEquals(1, test[0].getX()); assertEquals(0, test[0].getY());
		assertEquals(1, test[1].getX()); assertEquals(2, test[1].getY());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}