/**
 * A comparator for Avenger objects that compares them based on their mention order.
 */

import java.util.Comparator;

public class AvengerComparatorMentionOrder implements Comparator<Avenger> {

     /**
     * Compares two Avenger objects based on their mention order.
     *
     * @param a1 The first Avenger object to compare.
     * @param a2 The second Avenger object to compare.
     * @return A negative integer, zero, or a positive integer if the mention order of the first Avenger is less than,
     *         equal to, or greater than the mention order of the second Avenger.
     */
   
    @Override
    public int compare(Avenger a1, Avenger a2) {
        int mentionOrder1 = a1.getMentionOrder();
        int mentionOrder2 = a2.getMentionOrder();

        return Integer.compare(mentionOrder1, mentionOrder2);
    }
}
