import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Library {
    private Map<Integer, Member> memberMap = new HashMap<>();
    private Map<Integer, Book> bookMap = new HashMap<>();
    private int bookIDCounter = 1;
    private int memberIDCounter = 1;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public void addBook(String bookType) {
        if (bookType.equals("P"))
            bookMap.put(bookIDCounter, new PrintedBook(bookIDCounter));
        else
            bookMap.put(bookIDCounter, new HandwrittenBook(bookIDCounter));
        FileOutput.writeToFile("output.txt", String.format("Created new book: %s [id: %d]",
                bookType.equals("P") ? "Printed" : "Handwritten", bookIDCounter), true, true);
        bookIDCounter++;
    }

    public void addMember(String memberType) {
        if (memberType.equals("S")) {
            memberMap.put(memberIDCounter, new Student(memberIDCounter));
        }
        else {
            memberMap.put(memberIDCounter, new Academic(memberIDCounter));
        }
        FileOutput.writeToFile("output.txt", String.format("Created new member: %s [id: %d]",
                memberType.equals("S") ? "Student" : "Academic", memberIDCounter), true, true);
        memberIDCounter++;
    }

    public void borrowBook(int bookID, int memberID, LocalDate borrowDate) {
        Member member = memberMap.get(memberID);
        if (bookMap.get(bookID) != null && member.canBorrow(bookMap.get(bookID))) {
            member.borrowBook(bookMap.get(bookID), borrowDate);
            bookMap.put(bookID, null);
            FileOutput.writeToFile("output.txt", String.format("The book [%d] was borrowed by member [%d] at %s",
                bookID, memberID, formatter.format(borrowDate)), true, true);
        } else {
            FileOutput.writeToFile("output.txt", "You cannot borrow this book!", true, true);
        }
    }

    public void returnBook(int bookID, int memberID, LocalDate returnDate) {
        bookMap.put(bookID, memberMap.get(memberID).returnBook(bookID, returnDate));
        FileOutput.writeToFile("output.txt", String.format("The book [%d] was returned by member [%d] at %s",
                bookID, memberID, formatter.format(returnDate)), true, true);
    }
}
