import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;

public class FileInputGetter {
    public static String getFileContent(Path inputFilePath) throws IOException {
        return new String(Files.readAllBytes(inputFilePath));
    }
}
