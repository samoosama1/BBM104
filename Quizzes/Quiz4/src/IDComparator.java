import java.util.Comparator;

public class IDComparator implements Comparator<Verse> {
    @Override
    public int compare(Verse o1, Verse o2) {
        return o1.getVerseID() - o2.getVerseID();
    }
}
