import java.util.*;
import java.util.function.Consumer;

public class HashSetVerse implements Prompter{
    private final Set<Verse> verseSet = new HashSet<>();

    public HashSetVerse(List<String[]> inputList) {
        inputList.forEach((arr) -> verseSet.add(new Verse(Integer.parseInt(arr[0]), arr[1])));
    }

    public void prompt() {
        StringBuilder str = new StringBuilder();
        verseSet.forEach((v -> str.append(v.toString()).append("\n")));
        FileOutput.writeToFile("poemHashSet.txt", String.valueOf(str), false, true);
    }
}
