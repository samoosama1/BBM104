import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

public class InputHandler {
    String outFilePath;
    String[] commandLineArr;

    public InputHandler(String[] args, String outFilePath) {
        this.commandLineArr = args;
        this.outFilePath = outFilePath;
    }

    public void checkInput() {
        try {
            if (commandLineArr.length != 1) {
                throw new MultipleParametersException("There should be only 1 paremeter");
            }
            String pathStr = commandLineArr[0];
            String fileContent = FileInputGetter.getFileContent(Paths.get(pathStr));
            if (fileContent.isEmpty())
                throw new FileEmptyException("The input file should not be empty");
            if (containsInvalidChar(fileContent))
                throw new InvalidCharException("The input file should not contains unexpected characters");
            FileOutput.writeToFile(outFilePath, fileContent, true, true);

        } catch (IOException e) {
            FileOutput.writeToFile(outFilePath,
                    "There should be an input file in the specified path", true, true);
        } catch (FileEmptyException e) {
            FileOutput.writeToFile(outFilePath, e.getMessage(), true, true);
        } catch (InvalidCharException e) {
            FileOutput.writeToFile(outFilePath, e.getMessage(), true, true);
        } catch (MultipleParametersException e) {
            FileOutput.writeToFile(outFilePath, e.getMessage(), true, true);
        }
    }

    public boolean containsInvalidChar(String fileContent) {
        for (int i = 0; i < fileContent.length(); i++) {
            char currentChar = fileContent.charAt(i);
            if (!(Character.isLetter(currentChar)) && !(Character.isWhitespace(currentChar))) {
                return true;
            }
        }
        return false;
    }
}
