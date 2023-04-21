public class OutputHelper {
    private static String outputFile;

    public static void write(String output) {
        FileOutput.writeToFile(OutputHelper.outputFile, output, true, true);
    }

    public static void setOutputFile(String outputFile) {
        OutputHelper.outputFile = outputFile;
        FileOutput.writeToFile(outputFile, "", false, false);
    }
}
