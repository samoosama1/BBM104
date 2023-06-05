import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String inputPath = args[0];
        String outputPath = args[1];
        String[] input = FileInput.readFile(inputPath, true, true);
        List<String[]> inputList = new ArrayList<>();
        FileOutput.writeToFile(outputPath, "", false, false);

        Base10ToBase2Converter converter = new Base10ToBase2Converter();
        CheckExpressionValidity validity = new CheckExpressionValidity();
        PalindromeChecker palindrome = new PalindromeChecker();
        BinaryCounter binaryCounter = new BinaryCounter();

        for (String line : input) {
            inputList.add(line.split("\t"));
        }

        for (String[] arr : inputList) {
            String command = arr[0];
            String data = arr[1];
            switch (command) {
                case "Convert from Base 10 to Base 2:":
                    converter.convert(Integer.parseInt(data), outputPath);
                    break;
                case "Count from 1 up to n in binary:":
                    binaryCounter.countInBinary(Integer.parseInt(data), outputPath);
                    break;
                case "Check if following is palindrome or not:":
                    palindrome.checkPalindrome(data, outputPath);
                    break;
                case "Check if following expression is valid or not:":
                    validity.checkValidity(data, outputPath);
                    break;
            }
        }
    }
}