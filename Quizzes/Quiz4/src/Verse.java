import java.util.Comparator;

public class Verse {
    private int verseID;
    private String verseStr;

    public int getVerseID() {
        return verseID;
    }

    public String getVerseStr() {
        return verseStr;
    }

    public Verse(int verseID, String verseStr) {
        this.verseID = verseID;
        this.verseStr = verseStr;
    }

    public String toString() {
        return String.format("%d\t%s", this.verseID, this.verseStr);
    }
}
