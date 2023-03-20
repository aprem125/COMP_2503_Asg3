import java.util.Scanner;

/**
 * COMP 2503 Winter 2020 Assignment 2
 * 
 * This program must read a input stream and keeps track of the frequency at
 * which an avenger is mentioned either by name or alias.
 *
 * @author Maryam Elahi
 * @date Fall 2020
 */

public class A3 {

	public String[][] avengerRoster = { { "captainamerica", "rogers" }, { "ironman", "stark" },
			{ "blackwidow", "romanoff" }, { "hulk", "banner" }, { "blackpanther", "tchalla" }, { "thor", "odinson" },
			{ "hawkeye", "barton" }, { "warmachine", "rhodes" }, { "spiderman", "parker" },
			{ "wintersoldier", "barnes" } };

	private int topN = 4;
	private int totalwordcount = 0;
	private Scanner input = new Scanner(System.in);

	private SLL<Avenger> mentionList = new SLL<Avenger>();
	private SLL<Avenger> alphabticalList = new SLL<Avenger>(new AvengerAlphaComparator());
	private SLL<Avenger> mostPopularList = new SLL<Avenger>(new AvengerComparatorFreqDesc());
	private SLL<Avenger> leastPopularList = new SLL<Avenger>(new AvengerComparatorFreqAsc());

	/**
	 * main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		A3 a3 = new A3();
		a3.run();
	}

	public void run() {

		readInput();
		createdOrderedLists();
		printResults();
	}

	/**
	 * this method takes the avengers in the mention list and transfer them in into
	 * a ordered lists
	 * 
	 */
	private void createdOrderedLists() {
		Node<Avenger> current = mentionList.getHead();
		while (current != null) {
			mostPopularList.addInOrder(current.getData());
			alphabticalList.addInOrder(current.getData());
			leastPopularList.addInOrder(current.getData());
			current = current.getNext();
		}

	}

	/**
	 * read the input stream and keep track how many times avengers are mentioned by
	 * alias or last name.
	 * 
	 * @throws FileNotFoundException
	 */
	private void readInput() { // remove exception after testing
		/*
		 * In a loop, while the scanner object has not reached end of stream, - read a
		 * word. - clean up the word - if the word is not empty, add the word count. -
		 * Check if the word is either an avenger alias or last name then - Create a new
		 * avenger object with the corresponding alias and last name. - if this avenger
		 * has already been mentioned, increase the frequency count for the object
		 * already in the list. - if this avenger has not been mentioned before, add the
		 * newly created avenger to the end of the list, remember to set the frequency.
		 */

		while (input.hasNext()) {

			String word = cleanWord(input.next());

			if (!word.isEmpty()) {
				totalwordcount++;

				if (isAvenger(word)) {
					Avenger avenger = createAvengerObject(word);
					if (mentionList.contains(avenger)) {
						mentionList.get(mentionList.indexOf(avenger)).addFrequency();
					} else {
						mentionList.addLast(avenger);
					}
				}
			}
		}
	}

	private Avenger createAvengerObject(String word) {
		int inx = -1;
		for (int i = 0; i < avengerRoster.length; i++) {
			if (avengerRoster[i][0].equals(word) || avengerRoster[i][1].equals(word)) {
				inx = i;
				break;
			}
		}
		if (inx != -1) {
			return new Avenger(avengerRoster[inx][0], avengerRoster[inx][1], inx);
		} else
			return null;
	}

	private boolean isAvenger(String word) {
		for (int i = 0; i < avengerRoster.length; i++) {
			if ((avengerRoster[i][0].equals(word)) || (avengerRoster[i][1].equals(word))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param next
	 * @return
	 */
	private String cleanWord(String next) {
		// First, if there is an apostrophe, the substring
		// before the apostrophe is used and the rest is ignored.
		// Words are converted to all lowercase.
		// All other punctuation and numbers are skipped.

		String ret;
		int inx = next.indexOf('\'');
		if (inx != -1)
			ret = next.substring(0, inx).toLowerCase().trim().replaceAll("[^a-z]", "");
		else
			ret = next.toLowerCase().trim().replaceAll("[^a-z]", "");
		return ret;

	}

	/**
	 * print the results
	 *
	 */
	private void printResults() { // need to add number of avangers mentioned
		System.out.println();
		// SLL sll = new SLL();
		// int S =sll.size();
		System.out.println("Total number of words: " + totalwordcount);
		System.out.println("Number of Avengers Mentioned: " + mentionList.size());
		System.out.println();

		System.out.println("All avengers in the order they appeared in the input stream:");
		// Todo: Print the list of avengers in the order they appeared in the input
		// Make sure you follow the formatting example in the sample output

		System.out.println();
		mentionList.printList();

		System.out.println("Top " + topN + " most popular avengers:");
		// Todo: Print the most popular avengers, see the instructions for tie breaking
		// Make sure you follow the formatting example in the sample output
		mostPopularList.printFour();

		System.out.println("Top " + topN + " least popular avengers:");
		// Todo: Print the least popular avengers, see the instructions for tie breaking
		// Make sure you follow the formatting example in the sample output

		System.out.println();
		leastPopularList.printFour();

		System.out.println("All mentioned avengers in alphabetical order:");
		// Todo: Print the list of avengers in alphabetical order

		System.out.println();
		alphabticalList.printList();
	}
}
