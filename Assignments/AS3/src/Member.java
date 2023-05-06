import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public abstract class Member {
    private int ID;
    private final PrintedBook[] borrowedBooks;
    private final HashMap<Integer, LocalDate> borrowDates = new HashMap<>();

    public Member(int ID, int numOfBooksToBorrow) {
        this.ID = ID;
        this.borrowedBooks = new PrintedBook[numOfBooksToBorrow];
    }

    public int getID() {
        return ID;
    }

    public boolean canBorrow(Book bookToBorrow) {
        if (bookToBorrow instanceof HandwrittenBook)
            return false;
        for (PrintedBook book : borrowedBooks) {
            if (book == null)
                return true;
        }
        return false;
    }

    public void borrowBook(Book bookToBorrow, LocalDate borrowDate) {
        for (int i = 0; i < borrowedBooks.length; i++) {
            if (borrowedBooks[i] == null) {
                borrowedBooks[i] = (PrintedBook) bookToBorrow;
                borrowDates.put(bookToBorrow.getID() , borrowDate);
                break;
            }
        }
    }

    public Book returnBook(int bookID, LocalDate returnDate) {
        for (int i = 0; i < borrowedBooks.length; i++) {
            if (borrowedBooks[i].getID() == bookID) {
                Book temp = borrowedBooks[i];
                borrowedBooks[i] = null;
                return temp;
            }
        }
        return null;
    }

    public long calculateDays(LocalDate borrowDate, LocalDate returnDate) {
        return ChronoUnit.DAYS.between(borrowDate, returnDate);
    }
}
