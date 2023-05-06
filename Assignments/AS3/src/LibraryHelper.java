import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LibraryHelper {
    List<String[]> inputArrList = new ArrayList<>();
    Library library = new Library();

    public LibraryHelper(String[] inputArr) {
        for (String s : inputArr) {
            inputArrList.add(s.split("\t"));
        }
    }

    public void startLibrary() {
        for (String[] command : inputArrList) {
            switch (command[0]) {
                case "addBook":
                    library.addBook(command[1]);
                    break;
                case "addMember":
                    library.addMember(command[1]);
                    break;
                case "borrowBook":
                    library.borrowBook(
                            Integer.parseInt(command[1]), Integer.parseInt(command[2]), LocalDate.parse(command[3]));
                    break;
                case "returnBook":
                    break;
                case "getTheHistory":
                    break;
                case "readInLibrary":
                    break;
            }
        }
    }

}
