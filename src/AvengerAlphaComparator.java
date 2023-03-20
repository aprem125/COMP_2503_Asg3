
import java.util.Comparator;

public class AvengerAlphaComparator implements Comparator<Avenger> {
	@Override

	/**
	 * Alphabetical order: ascending order of letter hierarchy in case of tie check
	 * the aliases
	 */
	public int compare(Avenger a1, Avenger a2) {
		return a1.getAlias().compareTo(a2.getAlias());
	}
}
