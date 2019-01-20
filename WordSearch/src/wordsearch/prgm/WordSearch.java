package wordsearch.prgm;

import java.util.LinkedList;


/**
 * This class will take care of the behavior of the wordsearch this class is
 * immutable, once it is created you cannot change it's data fields. Just like a
 * real word search!
 * 
 * @author Jacob Abkes
 *
 */
public class WordSearch {
	private Character[][] puzzle;
	private String[] wordbank;

	private static int[] x = { 1, -1, -1, 0, 0, 1, 1, -1 }; // these bad boys will be holding the directional modifiers
	private static int[] y = { 1, 0, 1, -1, 1, -1, 0, -1 }; 

	/**
	 * This really is only used for searching one word at a time.
	 * Precondition == puzzle is a non jagged 2d array 
	 * @param puzzle
	 * 		Will throw an illegal argument exception if the 2darray is null or empty
	 */
	public WordSearch(Character[][] puzzle) {
		if (puzzle == null || puzzle.length < 1 || puzzle[0].length < 1) {
			throw new IllegalArgumentException("The puzzle does not match the" + " expected behavior");
		}
		// copy each item into the puzzle
		this.puzzle = new Character[puzzle.length][puzzle[0].length];
		for (int i = 0; i < puzzle.length; i++) {
			for (int j = 0; j < puzzle[i].length; j++) {
				this.puzzle[i][j] = puzzle[i][j];
			}
		}
	}

	/**
	 * Precondition == puzzle is a non jagged 2d array 
	 * Precondition == all wordbank words will be at least three characters 
	 * puzzle and wordbank cannot be null otherwise throws IA exception
	 * 
	 * @param puzzle
	 * @param wordbank
	 */
	public WordSearch(Character[][] puzzle, String[] wordbank) {
		if (puzzle == null || puzzle.length < 1 || puzzle[0].length < 1) {
			throw new IllegalArgumentException("The puzzle does not match the" + " expected behavior");
		}
		if (wordbank == null || wordbank.length < 1) {
			throw new IllegalArgumentException("The wordbank does not match" + " the expected behavior");
		}
		this.wordbank = new String[wordbank.length];
		for (int i = 0; i < wordbank.length; i++) {
			this.wordbank[i] = wordbank[i];
		}
		this.puzzle = new Character[puzzle.length][puzzle[0].length];
		for (int i = 0; i < puzzle.length; i++) {
			for (int j = 0; j < puzzle[i].length; j++) {
				this.puzzle[i][j] = puzzle[i][j];
			}
		}
	}
	
	/**
	 * This method, when given a string of characters, it will attempt to find a word
	 * if this word is located inside the puzzle. If not it will return null.
	 * @param word
	 * @return
	 * The returned array will either be null if the word is not found or
	 * If the word is found it will return the start point at pair[0] and end point at pair[1]
	 * 
	 * The x value of the pair corresponds to the column number inside of the 2darray puzzle
	 * The y value will correspond to the row number
	 */
	public Pair[] searchWord(String word) 
	{
		if(word == null || word.length() < 1) 
		{
			throw new IllegalArgumentException("The argument was empty or null");
		}
		if(puzzle == null || puzzle.length < 1) {
			throw new IllegalStateException("The puzzle is empty or null");
		}
		for(int row = 0; row < puzzle.length; row++) 
		{
			for(int col = 0; col < puzzle[row].length; col++) 
			{
				if(puzzle[row][col] == word.charAt(0)) 
				{
					for(int dir = 0; dir < x.length; dir++)
					{
						int rowSearch = row;
						int colSearch = col;
						for(int i = 1; i < word.length(); i++) 
						{
							if(!checkBounds(puzzle, colSearch, rowSearch, dir)) 
							{
								break;
							}
							rowSearch += y[dir];
							colSearch += x[dir];
							if(word.charAt(i)!=puzzle[rowSearch][colSearch]) 
							{
								break;
							}
							if(i == word.length()-1) 
							{
								Pair[] returned = new Pair[2];
								returned[0] = new Pair(col,row);
								returned[1] = new Pair(colSearch, rowSearch);
								return returned;
							}
						}
					}
				}
			}
		}
		return null;
	}

//	 * This method specifically uses an iterrative approach starting at 0,0, the
//	 * method will search for the first letter of any word, if found it will check
//	 * all adjacent for the second and keep going... when it finds the last letter
//	 * of the word it will start back at the word's starting position, re-check the
//	 * position for other words, then keep iterating
	
//	 * beenfound lets us keep track of which words have been found and don't need to
//	 * be checked for, beenfound[i] corresponds to the state of wordbank[i]
	
	/**
	 * This will search for the start and end positions of all the words in the
	 * wordbank.
	 * 	@return
	 * Pair[0][0] will be the start point and Pair[0][1] will be the stop point of
	 * the word in wordbank[0] and then wordbank[1] would be Pair[1][0] and
	 * Pair[1][1] ie the row value of the integer array will correspond to the word's index
	 * number in the wordbank
	 * 
	 * 
	 * 
	 * The x value of the pair corresponds to the column number inside of the 2darray puzzle
	 * The y value will correspond to the row number
	 * 
	 */
	public Pair[][] searchItrForward() {
		// Assume the wordsearch has been initialized
		Pair[][] locations = new Pair[wordbank.length][2];
		boolean[] beenfound = new boolean[wordbank.length];
		LinkedList<Pair> cur;
		for (int i = 0; i < beenfound.length; i++)
			beenfound[i] = false; // nothing has been found yet
		for (int i = 0; i < puzzle.length; i++) {
			for (int j = 0; j < puzzle[i].length; j++) {
				boolean[] foundthissearch = new boolean[wordbank.length];
				for (int q = 0; q < foundthissearch.length; q++)
					foundthissearch[q] = false;
				cur = searchChar(new Pair(j, i), beenfound, foundthissearch);
				if (cur.size() > 1) { //if we found some words here
					for (int k = 0; k < foundthissearch.length; k++) {
						if (foundthissearch[k]) 
							locations[k][0] = cur.getFirst(); //we know if they were found that
						// the first item in the list should be their
						//start points
					}
					cur.removeFirst(); //we take out the start point
					assert(cur.size() != 0);
					for (int k = 0; k < foundthissearch.length; k++) {
						if (foundthissearch[k]) {
							//we got their start point setup, 
							//and we know if they were found
							locations[k][1] = cur.getFirst();
							cur.removeFirst();
						}
					}

				}
			}
		}
		// print the results
		System.out.println();
		for (int i = 0; i < locations.length; i++) 
		{
			if (beenfound[i]) 
			{
				System.out.println("The word " + wordbank[i] + " was found at " + "Start: " + locations[i][0].toString()
						+ " End: " + locations[i][1].toString());
			} else
				System.out.println( wordbank[i] + " not located in puzzle");
		}

		return locations;
	}


	/**
	 * This method is the original version of searchChar, I decided to leave it in here
	 * to reflect on how I can better change my thinking when starting a problem
	 * 
	 * This method has way more lines than it needed, all it took was some restructuring
	 * @param letterIndex
	 * @param beenfound
	 * @param foundthissearch
	 * @return
	 */
	private LinkedList<Pair> searchCharv1(Pair letterIndex, boolean[] beenfound, boolean[] foundthissearch) {
		LinkedList<Pair> returned = new LinkedList<Pair>();
		returned.add(letterIndex); //it is the starting point
		for (int w = 0; w < wordbank.length; w++) { //for each word
			if (puzzle[letterIndex.getY()][letterIndex.getX()] == wordbank[w].charAt(0) && !beenfound[w]) //if the first char matches
			{
				int dir = 0;
				while (dir < x.length) { //for each available direction
					int xStart = letterIndex.getX(); //save the start character position so we can modify it
					int yStart = letterIndex.getY();
					if (checkBounds(puzzle, xStart, yStart, dir)
							&& wordbank[w].charAt(1) == puzzle[yStart + y[dir]][xStart + x[dir]]) // check some
																									// direction
					{
						boolean broke = false;
						yStart += y[dir];
						xStart += x[dir];
						for (int wordchar = 2; wordchar < wordbank[w].length(); wordchar++) {
							if (!checkBounds(puzzle, xStart, yStart, dir)) {
								broke = true;
								break;
							}
							yStart += y[dir];
							xStart += x[dir];
							if (wordbank[w].charAt(wordchar) != puzzle[yStart][xStart]) {
								broke = true;
								break; // if something doesn't match break
							}
						}
						if (!broke) {
							returned.add(new Pair(xStart, yStart)); // if we make it here all letters matched and we
																	// have found the end point
							beenfound[w] = true;
							foundthissearch[w] = true;
						}
					}
					if (beenfound[w])
						break; // if we found the word no need to keep searching other dirs for it
					dir++;
				}
			}
		}

		return returned;
	}
	
	
	// this will return some length list, if size == 1 then no word has been found
	// the first item in the returned list will always represent the starting point,
	// all other items will represent end points corresponding to different words
	// ie if pair.size > 2 then more than one word lies on this character
	
	//This method is the reason why we need more than 2 characters in a word inside the wordbank
	//this could be edited to allow for shorter expressions easily, although in a wordsearch
	//you would never have a two letter word included
	
	/**
	 * This method is the edited version of the method above 
	 * @param letterIndex
	 * 		Location of the char we are currently looking at in the puzzle
	 * @param beenfound
	 * 		This is the running tab on which words have been found
	 * @param foundthissearch
	 * 		This param needs to be an initialized boolean array where all items are false
	 * 		After this function finishes this array should contain true inside of any indice
	 * 		where we have found a word on this call to search char
	 * @return
	 * 		This will return some length list where the first item in the list will be the same pair passed to
	 * 		This method as letterIndex. If there are more items than one, they will represent the endpoint to 
	 * 		a word that we found. I.e. if we find a word than it's endpoint will be some item following 
	 * 		the start point
	 */
	private LinkedList<Pair> searchChar(Pair letterIndex, boolean[] beenfound, boolean[] foundthissearch) {
		LinkedList<Pair> returned = new LinkedList<Pair>();
		returned.add(letterIndex); //it is the starting point
		for (int w = 0; w < wordbank.length; w++) 
		{ //for each word
			if (puzzle[letterIndex.getY()][letterIndex.getX()] == wordbank[w].charAt(0) && !beenfound[w]) //if the first char matches
			{
				int dir = 0;
				while (dir < x.length) 
				{ //for each available direction
					int xStart = letterIndex.getX(); //save the start character position so we can modify it
					int yStart = letterIndex.getY();
					for(int i = 1; i < wordbank[w].length(); i++)
					{
							if (!checkBounds(puzzle, xStart, yStart, dir)) {
								break;
							}
							yStart += y[dir];
							xStart += x[dir];
							if (wordbank[w].charAt(i) != puzzle[yStart][xStart]) {
								break; // if something doesn't match break
							}
							if(i == wordbank[w].length()-1) {
								returned.add(new Pair(xStart, yStart)); // if we make it here all letters matched and we
								// have found the end point
								beenfound[w] = true;
								foundthissearch[w] = true;
							}
					}
					if (beenfound[w])
						break; // if we found the word no need to keep searching other dirs for it
					dir++;
					}
				}
			}

		return returned;
	}

	/**
	 * This will see if the next modification of indices for movement will step out
	 * of bounds
	 * 
	 * @param puzzle
	 * @param x
	 * @param y
	 * @param dir
	 * @return returns true if it is okay to take the next step returns false if it
	 *         will cause an out of bounds exception
	 */
	private boolean checkBounds(Character[][] puzzle, int column, int row, int dir) {
		int xcheck = column + x[dir];
		int ycheck = row + y[dir];
		if (xcheck >= puzzle[row].length || ycheck >= puzzle.length ||
				ycheck < 0 || xcheck < 0) {
			return false;
		}
		return true;
	}

	/**
	 * This method will tell us how many elements are in each column and row
	 * 
	 * @return
	 */
	public Integer getSize() {
		return puzzle.length;
	}

	/**
	 * This returns the total number of elements in the wordsearch
	 * 
	 * @return
	 */
	public Integer getNumItems() {
		return puzzle.length * puzzle.length;
	}

	/**
	 * This returns the number of words in the wordbank
	 * 
	 * @return
	 */
	public Integer getNumWords() {
		return wordbank.length;
	}

	/**
	 * Gives access to a specific item
	 * 
	 * @param y
	 * @param x
	 * @return
	 */
	public Character getItem(int y, int x) {
		return puzzle[y][x];
	}

	/**
	 * Gives access to a specific word
	 * 
	 * @param num
	 * @return
	 */
	public String getWord(int num) {
		return wordbank[num];
	}

}