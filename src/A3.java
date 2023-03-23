import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * A class to analyze text from a file for mentions of Avengers characters. The
 * program maintains several binary search trees (BSTs) to track the Avengers in
 * different orders, such as the order they appear in the text, their popularity
 * (frequency of mentions), and alphabetical order.
 */
public class A3 {

	public String[][] avengerRoster = { { "captainamerica", "rogers" }, { "ironman", "stark" },
			{ "blackwidow", "romanoff" }, { "hulk", "banner" }, { "blackpanther", "tchalla" }, { "thor", "odinson" },
			{ "hawkeye", "barton" }, { "warmachine", "rhodes" }, { "spiderman", "parker" },
			{ "wintersoldier", "barnes" } };

	private int topN = 4;
	private int totalwordcount = 0;
	private Scanner input = new Scanner(System.in);
	private BST<Avenger> alphabeticalBST = new BST<>();
	private BST<Avenger> mentionBST = new BST<Avenger>(new AvengerComparatorMentionOrder());
	private BST<Avenger> mostPopularBST = new BST<Avenger>(new AvengerComparatorFreqDesc());
	private BST<Avenger> leastPopularBST = new BST<Avenger>(new AvengerComparatorFreqAsc());
	private int mentionOrderCounter = 0;

	public static void main(String[] args) {
		A3 a3 = new A3();
		a3.run();
	}

	/**
	 * A class to analyze text from a file for mentions of Avengers characters. The
	 * program maintains several binary search trees (BSTs) to track the Avengers in
	 * different orders, such as the order they appear in the text, their popularity
	 * (frequency of mentions), and alphabetical order.
	 */

	
	 public void run() { 
		 readInput(); 
		 createAlternativeOrderBSTs();
		 printResults(); 
	 }
	 


	/**
	 * Creates alternative order BSTs (mentionBST, mostPopularBST, and
	 * leastPopularBST) based on the alphabeticalBST.
	 */
	private void createAlternativeOrderBSTs() {
		alphabeticalBST.remove(new Avenger("hawkeye", "barton"));

		for (Avenger avenger : alphabeticalBST) {
			mentionBST.add(avenger);
			mostPopularBST.add(avenger);
			leastPopularBST.add(avenger);
		}

	}

	/**
	 * Creates alternative order BSTs (mentionBST, mostPopularBST, and
	 * leastPopularBST) based on the alphabeticalBST.
	 */
	private void readInput() {
		while (input.hasNext()) {
			String word = clean(input.next());
			if (!word.isEmpty()) {
				totalwordcount++;

				for (String[] avengerInfo : avengerRoster) {
					if (word.equalsIgnoreCase(avengerInfo[0]) || word.equalsIgnoreCase(avengerInfo[1])) {
						Avenger newAvenger = new Avenger(avengerInfo[0], avengerInfo[1]);
						Avenger existingAvenger = alphabeticalBST.find(newAvenger);

						if (existingAvenger != null) {
							existingAvenger.addFrequency();
						} else {
							newAvenger.setMentionOrder(++mentionOrderCounter);
							newAvenger.addFrequency();
							alphabeticalBST.add(newAvenger);
						}
					}
				}
			}
		}
	}

	/**
	 * Cleans a given word by converting it to lowercase and removing any
	 * non-alphabetic characters.
	 *
	 * @param word The word to clean
	 * @return The cleaned word
	 */
	private String clean(String next) {
		String ret;
		int inx = next.indexOf('\'');
		if (inx != -1)
			ret = next.substring(0, inx).toLowerCase().trim().replaceAll("[^a-z]", "");
		else
			ret = next.toLowerCase().trim().replaceAll("[^a-z]", "");
		return ret;
	}

	/**
	 * Prints all Avengers from the BST bst The BST contains all of the Avengers
	 * that need to be printed Prints the top N Avengers in the given BST. bst The
	 * BST containing the Avengers to print topN The number of Avengers to print
	 * from the top Prints the heights of the different BSTs along with their
	 * optimal heights.
	 */
	private void printResults() {
		System.out.println("Total number of words: " + totalwordcount);
		System.out.println("Number of Avengers Mentioned: " + alphabeticalBST.size());
		System.out.println();

		System.out.println("All avengers in the order they appeared in the input stream:");
		printAvengers(mentionBST);
		System.out.println();

		System.out.println("Top " + topN + " most popular avengers:");
		printAvengers(mostPopularBST, topN);
		System.out.println();

		System.out.println("Top " + topN + " least popular avengers:");
		printAvengers(leastPopularBST, topN);
		System.out.println();

		System.out.println("All mentioned avengers in alphabetical order:");
		printAvengers(alphabeticalBST);
		System.out.println();

		printTreeHeights();
	}

	private void printAvengers(BST<Avenger> bst) {
		for (Avenger avenger : bst) {
			System.out.print(avenger);
		}
	}

	private void printAvengers(BST<Avenger> bst, int topN) {
		int count = 0;
		for (Avenger avenger : bst) {
			if (count++ < topN) {
				System.out.print(avenger);
			} else {
				break;
			}
		}
	}

	private void printTreeHeights() {
		System.out.println("Height of the mention order tree is : " + mentionBST.height()
				+ " (Optimal height for this tree is : " + mentionBST.optimalHeight() + ")");
		System.out.println("Height of the alphabetical tree is : " + alphabeticalBST.height()
				+ " (Optimal height for this tree is : " + alphabeticalBST.optimalHeight() + ")");
		System.out.println("Height of the most frequent tree is : " + mostPopularBST.height()
				+ " (Optimal height for this tree is : " + mostPopularBST.optimalHeight() + ")");
		System.out.println("Height of the least frequent tree is : " + leastPopularBST.height()
				+ " (Optimal height for this tree is : " + leastPopularBST.optimalHeight() + ")");
	}
}
