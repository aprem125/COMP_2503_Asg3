
/**
 * Represents an Avenger with properties like alias, name, frequency, and
 * mention order. This class implements Comparable for sorting Avengers based on
 * their aliases.
 */
public class Avenger implements Comparable<Avenger> {
	private String heroAlias;
	private String heroName;
	private int frequency;
	private int mentionOrder;

	/**
	 * Constructs an Avenger object with the specified alias and name.
	 *
	 * @param alias The alias of the Avenger.
	 * @param name  The name of the Avenger.
	 */

	public Avenger(String alias, String name) {
		heroName = name;
		heroAlias = alias;
		frequency = 0;
		this.mentionOrder = -1;
	}

	// Getters and Setters
	/**
	 * Returns the alias of the Avenger.
	 *
	 * @return The alias of the Avenger.
	 */
	public String getAlias() {
		return heroAlias;
	}

	/**
	 * Returns the name of the Avenger.
	 *
	 * @return The name of the Avenger.
	 */

	public String getName() {
		return heroName;
	}

	/**
	 * Returns the frequency of the Avenger.
	 *
	 * @return The frequency of the Avenger.
	 */

	public int getFrequency() {
		return frequency;
	}

	/**
	 * Increments the frequency of the Avenger by 1.
	 */

	public void addFrequency() {
		this.frequency++;
	}

	/**
	 * Returns the mention order of the Avenger.
	 *
	 * @return The mention order of the Avenger.
	 */

	public int getMentionOrder() {
		return mentionOrder;
	}

	/**
	 * Sets the mention order of the Avenger.
	 *
	 * @param mentionOrder The mention order to set.
	 */
	public void setMentionOrder(int mentionOrder) {
		this.mentionOrder = mentionOrder;
	}

	/**
	 * this method compare Avenger Alias
	 */
	@Override
	/**
	 * Compares the current Avenger object to another Avenger object based on their
	 * aliases.
	 *
	 * @param other The other Avenger object to compare.
	 * @return A negative integer, zero, or a positive integer if this Avenger's
	 *         alias is less than, equal to, or greater than the other Avenger's
	 *         alias.
	 */
	public int compareTo(Avenger other) {
		if (other == null)
			return -1;
		return this.getAlias().compareTo(other.getAlias());
	}

	/**
	 * Compares the current Avenger object to another object for equality.
	 *
	 * @param other The other object to compare for equality.
	 * @return True if the other object is an Avenger and has the same alias and
	 *         name, false otherwise.
	 */

	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;
		Avenger a = (Avenger) other;
		if (this.getAlias().equals(a.getAlias()) && this.getName().equals(a.getName()))
			return true;
		else
			return false;
	}

	/**
	 * Generates a hash code for the Avenger object based on its alias and name.
	 *
	 * @return A hash code for the Avenger object.
	 */
	@Override
	public int hashCode() {
		String aliasAndName = heroAlias + heroName;
		return aliasAndName.hashCode();
	}

	/**
	 * Returns a string representation of the Avenger object, including alias, name,
	 * and frequency.
	 *
	 * @return A string representation of the Avenger object.
	 */

	public String toString() {
		return heroAlias + " aka " + heroName + " mentioned " + frequency + " time(s)" + "\n";
	}
}
