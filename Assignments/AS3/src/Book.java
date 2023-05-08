public abstract class Book {
    private final int ID;
    private final boolean isBorrowable;
    private final boolean isReadableByStudents;

    public boolean isBorrowable() {
        return isBorrowable;
    }

    public boolean isReadableByStudents() {
        return isReadableByStudents;
    }

    public int getID() {
        return ID;
    }

    public Book(int ID, boolean isBorrowable, boolean isReadableByStudents) {
        this.ID = ID;
        this.isBorrowable = isBorrowable;
        this.isReadableByStudents = isReadableByStudents;
    }
}
