import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Library {
    private final Map<Integer, Member> memberMap = new HashMap<>();
    private final Map<Integer, Book> bookMap = new HashMap<>();
    private final List<Student> studentList = new ArrayList<>();
    private final List<Academic> academicList = new ArrayList<>();
    private final List<PrintedBook> printedBooks = new ArrayList<>();
    private final List<HandwrittenBook> handwrittenBooks = new ArrayList<>();
    private final List<LendInfo> borrowInfos = new ArrayList<>();
    private final List<LendInfo> readInfos = new ArrayList<>();
    private int bookIDCounter = 1;
    private int memberIDCounter = 1;
    private final String outPath;

    public Library(String outPath) {
        this.outPath = outPath;
    }

    public void addBook(String bookType) {
        if (bookType.equals("P")) {
            PrintedBook printedBook = new PrintedBook(bookIDCounter);
            bookMap.put(bookIDCounter, printedBook);
            printedBooks.add(printedBook);
        }
        else {
            HandwrittenBook handwrittenBook = new HandwrittenBook(bookIDCounter);
            bookMap.put(bookIDCounter, handwrittenBook);
            handwrittenBooks.add(handwrittenBook);
        }
        FileOutput.writeToFile(outPath, String.format("Created new book: %s [id: %d]",
                bookType.equals("P") ? "Printed" : "Handwritten", bookIDCounter), true, true);
        bookIDCounter++;
    }

    public void addMember(String memberType) {
        if (memberType.equals("S")) {
            Student student = new Student(memberIDCounter);
            memberMap.put(memberIDCounter, student);
            studentList.add(student);
        }
        else {
            Academic academic = new Academic(memberIDCounter);
            memberMap.put(memberIDCounter, academic);
            academicList.add(academic);
        }
        FileOutput.writeToFile(outPath, String.format("Created new member: %s [id: %d]",
                memberType.equals("S") ? "Student" : "Academic", memberIDCounter), true, true);
        memberIDCounter++;
    }

    public void borrowBook(int bookID, int memberID, LocalDate borrowDate) {
        Member member = memberMap.get(memberID);
        if (bookMap.get(bookID) != null && bookMap.get(bookID).isBorrowable()) {
            if (member.canBorrow()) {
                member.borrowBook(bookMap.get(bookID), borrowDate);
                String out = String.format("The book [%d] was borrowed by member [%d] at %s",
                        bookID, memberID, DateTimeFormatter.ofPattern("yyyy-MM-dd").format(borrowDate));
                borrowInfos.add(new LendInfo(bookID, out));
                FileOutput.writeToFile(outPath, out, true, true);
            }
            else
                FileOutput.writeToFile(outPath, "You exceeded the borrowing limit!", true, true);
        } else
            FileOutput.writeToFile(outPath, "You cannot borrow this book!", true, true);
    }

    public void returnBook(int bookID, int memberID, LocalDate returnDate) {
        Member member = memberMap.get(memberID);
        if (member.returnBook(bookID, returnDate) != null) {
            Book book = member.returnBook(bookID, returnDate);
            bookMap.put(bookID, book);
            borrowInfos.removeIf((b -> b.getBookID() == bookID));
            readInfos.removeIf((b -> b.getBookID() == bookID));
            FileOutput.writeToFile(outPath, String.format("The book [%d] was returned by member [%d] at %s Fee: %d",
                            bookID, memberID, DateTimeFormatter.ofPattern("yyyy-MM-dd").format(returnDate), member.getFee()),
                    true, true);
            if (member.getFee() > 0)
                FileOutput.writeToFile(outPath, "You must pay a penalty!", true, true);
        }
        else
            FileOutput.writeToFile(outPath, "Member doesn't have the book!", true, true);
    }

    public void extendBook(int bookID, int memberID, LocalDate currentDate) {
        Member member = memberMap.get(memberID);
        if (member.canExtend(bookID) != null) {
            if (member.canExtend(bookID)) {
                member.extendBook(bookID);
                FileOutput.writeToFile(outPath, String.format("The deadline of the book [%d] was extended by " +
                                "member [%d] at %s", bookID, memberID,
                                DateTimeFormatter.ofPattern("yyyy-MM-dd").format(currentDate)) , true, true);
                return;
            }
        }
        FileOutput.writeToFile(outPath, "You cannot extend the deadline!", true, true);
    }

    public void readInLibrary(int bookID, int memberID, LocalDate currentDate) {
        Book book = bookMap.get(bookID);
        Member member = memberMap.get(memberID);
        if (book == null) {
            FileOutput.writeToFile(outPath, "You can not read this book!", true, true);
            return;
        }
        if (!book.isReadableByStudents() && member instanceof Student) {
            FileOutput.writeToFile(outPath, "Students can not read handwritten books!", true, true);
            return;
        }
        String out = String.format("The book [%d] was read in library by member [%d] at %s",
                bookID, memberID, DateTimeFormatter.ofPattern("yyyy-MM-dd").format(currentDate));
        FileOutput.writeToFile(outPath, out, true, true);
        member.readInLibrary(bookMap.replace(bookID, null));
    }

    public void getTheHistory() {
        StringBuilder out = new StringBuilder();
        out.append("History of library:\n\n");
        out.append(String.format("Number of students: %d\n", studentList.size()));
        studentList.forEach((s) -> out.append(s.toString()).append("\n"));
        out.append(String.format("\nNumber of academics: %d\n", academicList.size()));
        academicList.forEach((a) -> out.append(a.toString()).append("\n"));
        out.append(String.format("\nNumber of printed books: %d\n", printedBooks.size()));
        printedBooks.forEach((p) -> out.append(p.toString()).append("\n"));
        out.append(String.format("\nNumber of handwritten books: %d\n", handwrittenBooks.size()));
        handwrittenBooks.forEach((h) -> out.append(h.toString()).append("\n"));
        out.append(String.format("\nNumber of borrowed books: %d\n", borrowInfos.size()));
        borrowInfos.forEach(b -> out.append(b.getLendStr()).append("\n"));
        out.append(String.format("\nNumber of books read in library: %d\n", readInfos.size()));
        readInfos.forEach(r -> out.append(r.getLendStr()).append("\n"));
        FileOutput.writeToFile(outPath, String.valueOf(out), true, true);
    }
}
