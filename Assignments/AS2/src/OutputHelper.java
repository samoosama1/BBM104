/**
 * Helper class for outputting to output file. Provides abstraction by excluding the output file location.
 */
public class OutputHelper {
    private static String outputFile;

    /**
     * Writes the given string to the output file
     * @param output string that is wanted to be written to the file.
     */
    public static void write(String output) {
        FileOutput.writeToFile(OutputHelper.outputFile, output, true, true);
    }

    /**
     * Sets the output file in order to write to it.
     * @param outputFile path of the output file.
     */
    public static void setOutputFile(String outputFile) {
        OutputHelper.outputFile = outputFile;
        // Clears the file and creates it if it wasn't created.
        FileOutput.writeToFile(outputFile, "", false, false);
    }
}
