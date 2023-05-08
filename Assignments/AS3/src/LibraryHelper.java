import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LibraryHelper {
    List<String[]> inputArrList = new ArrayList<>();
    Library library;

    public LibraryHelper(String[] inputArr, String outPath) {
        for (String s : inputArr) {
            inputArrList.add(s.split("\t"));
        }
        FileOutput.writeToFile(outPath, "", false, false);
        library = new Library(outPath);
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
                    library.returnBook(Integer.parseInt(command[1]), Integer.parseInt(command[2]), LocalDate.parse(command[3]));
                    break;
                case "readInLibrary":
                    library.readInLibrary(Integer.parseInt(command[1]), Integer.parseInt(command[2]), LocalDate.parse(command[3]));
                    break;
                case "getTheHistory":
                    library.getTheHistory();
                    break;
                case "extendBook":
                    library.extendBook(Integer.parseInt(command[1]), Integer.parseInt(command[2]), LocalDate.parse(command[3]));
            }
        }
    }
}
