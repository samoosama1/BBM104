public class PrintedBook extends Book {
    public PrintedBook(int ID) {
        super(ID, true, true);
    }

    public String toString() {
        return String.format("Printed [id: %d]", getID());
    }
}
