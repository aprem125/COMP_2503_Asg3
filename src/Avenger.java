
public class Avenger implements Comparable<Avenger> {
	private String heroAlias;
	private String heroName;
	private int frequency;
	private int mentionOrder;


	public Avenger(String alias, String name) {
		heroName = name;
		heroAlias = alias;
		frequency = 0;
        this.mentionOrder = -1;
	}

	// Getters and Setters
	public String getAlias() {
		return heroAlias;
	}

	public String getName() {
		return heroName;
	}

	public int getFrequency() {
		return frequency;
	}

	public void addFrequency() {
		this.frequency++;
	}
    public int getMentionOrder() {
        return mentionOrder;
    }

    public void setMentionOrder(int mentionOrder) {
        this.mentionOrder = mentionOrder;
    }
	/**
	 * this method compare Avenger Alias 
	 */
	@Override
	public int compareTo(Avenger other) {
		if (other == null)
			return -1;
		return this.getAlias().compareTo(other.getAlias());
	}

	/**
	 * this method compare Avengers 
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
 * this method match Avenger with Alises 
 */
	@Override
	public int hashCode() {
		String aliasAndName = heroAlias + heroName;
		return aliasAndName.hashCode();
	}
	
	/**
	 * to string method used for printing out the result
	 */
	public String toString() {
		return heroAlias + " aka " + heroName + " mentioned " + frequency + " time(s)" + "\n";
	}
}
