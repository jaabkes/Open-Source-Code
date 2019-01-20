package wordsearch.prgm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
/**
 * This class is the "user" class, in which the main function sits
 * @author Jacob Abkes
 *
 */
public class User {
	public static void main(String[] args) {
		// Extract puzzle file from args[0]
		// extract wordbank file from args[1]
		System.out.println("would you like to type the exact file path?");
		System.out.println("if not your file path will be pulled from args[0](puzzle) and " + "(wordbank)args[1]");
		System.out.println();
		System.out.println("please type yes or no");
		Scanner sc = new Scanner(System.in);
		if (sc.nextLine().toLowerCase().compareTo("no") == 0) // automatic file referencing
		{
			sc.close();
			System.out.println("Attempting to create game");
			try {
				WordSearch game = createWordSearch(new File(args[0]), new File(args[1]));
				final long startTime = System.currentTimeMillis();
				game.searchItrForward();
				final long endTime = System.currentTimeMillis();
				System.out.println("Total execution time was: " + (endTime - startTime) + " ms");
			} catch (IllegalArgumentException e) {
				System.out.println("incorrect file settings try again, closing program");
				System.exit(-1);
			}
		} else {
			System.out.println("Please type in the file path for the puzzle");
			File puzFile = new File(sc.nextLine());
			System.out.println("Please type in the file path for the wordbank");
			File wordFile = new File(sc.nextLine());
			sc.close();
			System.out.println("Attempting to create game");
			try {
				WordSearch game = createWordSearch(puzFile, wordFile);
				final long startTime = System.currentTimeMillis();
				game.searchItrForward();
				final long endTime = System.currentTimeMillis();
				System.out.println("Total execution time was: " + (endTime - startTime) + " ms");

			} catch (IllegalArgumentException e) {
				System.out.println("incorrect file settings try again, closing program");
				System.out.println("Error was: " + e.getMessage());
				System.exit(-1);
			}
		}

	}

	/**
	 * This method will read these files and give the correct params to the new
	 * WordSearch object based on the given files
	 * 
	 * errors will be thrown upwards except for file not found errors ie they will
	 * be caught inside main
	 * 
	 * @param puzzlef
	 * @param wordf
	 * @return
	 */
	public static WordSearch createWordSearch(File puzzlef, File wordf) {
		// Rules for input files are;
		// each line for the puzzle is the same length as each row
		// the puzzle's 0,0 will be the top left item in the file
		// the puzzle has items in it
		// wordbank has one word per line
		// each item in the wordbank is included inside the puzzle
		// wordbank has items
		// each word is included only once in the wordbank and in the puzzle

		/**
		 * If we changed this part to handle uneven puzzles the rest of the code should
		 * function fine
		 */
		Character[][] puzzle = new Character[0][0];
		/**
		 * Im using a linkedlist since I won't know the size of the array initially I
		 * could do a similar system with the puzzle if I wanted to allow non-square
		 * puzzles
		 */
		LinkedList<String> wordbank = new LinkedList<String>();

		try { // this will be the process for reading the puzzle file
			Scanner sc = new Scanner(puzzlef);
			if (sc.hasNextLine()) // make sure there is at least one item
			{
				String ln = sc.nextLine(); // grab the first line
				ln = ln.replaceAll("\\s", "");
				puzzle = new Character[ln.length()][ln.length()]; // we now know how big it is
				// start adding elements into the puzzle
				int lineNum = 0;
				while (sc.hasNextLine() || puzzle[0][0] == null) // second statement is to make sure
				// we don't skip the first line
				{
					if (puzzle[0][0] != null) // if it's not the first line
					{
						ln = sc.nextLine();
						ln = ln.replaceAll("\\s", ""); // fix to the whitespace problems
					}
					for (int j = 0; j < ln.length(); j++) {
						puzzle[lineNum][j] = ln.charAt(j);
						puzzle[lineNum][j] = Character.toLowerCase(puzzle[lineNum][j]);
					}
					lineNum++;
				}
			} else {
				sc.close();
				throw new IllegalArgumentException("Puzzle is an empty file");
				// if not let main know
			}
			sc.close();
		} catch (FileNotFoundException e) {
			// Lets us know which file wasn't found
			e.printStackTrace();
			System.out.println("File " + puzzlef.getName() + " not found closing program");
			System.exit(-1);
		}
		// try to add items into the wordbank
		try {
			Scanner sc = new Scanner(wordf);
			if (sc.hasNextLine()) {
				String ln = sc.nextLine();
				if (ln.length() < 3) {
					sc.close();
					throw new IllegalArgumentException("wordbank item is less than 3 characters long closing program");
				}
				wordbank.add(ln);
				while (sc.hasNextLine()) {
					if (ln.length() < 3) {
						sc.close();
						throw new IllegalArgumentException(
								"wordbank item is less than 3 characters long closing program");
					}
					ln = sc.nextLine();
					wordbank.add(ln);
				}

			} else {
				sc.close();
				throw new IllegalArgumentException("Wordfile is Empty");
			}
			sc.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File " + wordf.getName() + " not found closing program");
			System.exit(-1);

		}
		// This should be okay, all errors would have been thrown or handled to
		// prevent reaching these statements
		String[] wordbankFinal = new String[wordbank.size()];
		for (int i = 0; i < wordbankFinal.length; i++) {
			wordbankFinal[i] = wordbank.getFirst().toLowerCase();
			wordbank.removeFirst(); // Decided to delete them to avoid using
									// an Iterator
		}
		WordSearch cw = new WordSearch(puzzle, wordbankFinal);
		return cw;
	}

}