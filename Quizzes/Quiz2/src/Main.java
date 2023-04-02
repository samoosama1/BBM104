public class Main {
    static String inputFile;
    static String outputFile;
    public static void main(String[] args) {
        inputFile = args[0];
        outputFile = args[1];
        FileOutput.writeToFile(outputFile, "", false, false);
        TripController tc = new TripController();
    }
}
