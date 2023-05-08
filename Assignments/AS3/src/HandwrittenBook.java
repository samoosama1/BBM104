public class HandwrittenBook extends Book {
    public HandwrittenBook(int ID) {
        super(ID, false, false);
    }

    public String toString() {
        return String.format("Handwritten [id: %d]", getID());
    }
}
