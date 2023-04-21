public class Main {
    public static void main(String[] args) {
        String inputFile = args[0];
        String outputFile = args[1];
        OutputHelper.setOutputFile(outputFile);

        SmartSystem smartSystem = new SmartSystem(FileInput.readFile(inputFile, true, true));
        smartSystem.startSystem();
    }
}