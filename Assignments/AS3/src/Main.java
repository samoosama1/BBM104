public class Main {
    public static void main(String[] args) {
        LibraryHelper libraryHelper = new LibraryHelper(FileInput.readFile(args[0], true, true), args[1]);
        libraryHelper.startLibrary();
    }
}
