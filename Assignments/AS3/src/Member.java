import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

public abstract class Member {
    private final int ID;
    private final int dayLimit;
    private long fee;
    private final PrintedBook[] borrowedBooks;
    private final HashMap<Integer, Book> readBooks = new HashMap<>();
    private final HashMap<Integer, LocalDate> deadlineDates = new HashMap<>();
    private final HashMap<Integer, Boolean> extendMap = new HashMap<>();

    public Member(int ID, int numOfBooksToBorrow, int dayLimit) {
        this.ID = ID;
        this.dayLimit = dayLimit;
        this.borrowedBooks = new PrintedBook[numOfBooksToBorrow];
    }

    public int getID() {
        return ID;
    }

    public boolean canBorrow() {
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
                deadlineDates.put(bookToBorrow.getID(), borrowDate.plusDays(dayLimit));
                extendMap.put(bookToBorrow.getID(), true);
                return;
            }
        }
    }

    public void readInLibrary(Book bookToRead) {
        readBooks.put(bookToRead.getID(), bookToRead);
    }

    public long getFee() {
        return fee;
    }

    public Book returnBook(int bookID, LocalDate returnDate) {
        for (int i = 0; i < borrowedBooks.length; i++) {
            if (borrowedBooks[i] != null && borrowedBooks[i].getID() == bookID) {
                fee = 0;
                if (returnDate.isAfter(deadlineDates.get(bookID))) {
                    fee = calculateFee(deadlineDates.get(bookID), returnDate);
                }
                extendMap.remove(bookID);
                deadlineDates.remove(bookID);
                Book temp = borrowedBooks[i];
                borrowedBooks[i] = null;
                return temp;
            }
        }
        fee = 0;
        return readBooks.remove(bookID);
    }

    public LocalDate getBookDeadline(int bookID) {
        return deadlineDates.get(bookID);
    }

    public Boolean canExtend(int bookID) {
        return extendMap.get(bookID);
    }

    public void extendBook(int bookID) {
        deadlineDates.put(bookID, deadlineDates.get(bookID).plusDays(dayLimit));
        extendMap.put(bookID, false);
    }

    public long calculateFee(LocalDate deadlineDate, LocalDate returnDate) {
        return ChronoUnit.DAYS.between(deadlineDate, returnDate);
    }
}
