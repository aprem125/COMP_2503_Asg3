import java.util.Comparator;

public class AvengerComparatorMentionOrder implements Comparator<Avenger> {

    @Override
    public int compare(Avenger a1, Avenger a2) {
        int mentionOrder1 = a1.getMentionOrder();
        int mentionOrder2 = a2.getMentionOrder();

        return Integer.compare(mentionOrder1, mentionOrder2);
    }
}
