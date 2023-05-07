import java.util.*;
import java.util.function.Consumer;

public class ArrayListVerse implements Prompter{
    private final List<Verse> verseList = new ArrayList<>();

    public ArrayListVerse(List<String[]> inputList) {
        inputList.forEach((arr) -> verseList.add(new Verse(Integer.parseInt(arr[0]), arr[1])));
    }

    @Override
    public void prompt() {
        StringBuilder unorderedStr = new StringBuilder();
        StringBuilder orderedStr = new StringBuilder();
        verseList.forEach((v) -> unorderedStr.append(v.toString()).append("\n"));
        verseList.sort(new IDComparator());
        verseList.forEach((v) -> orderedStr.append(v.toString()).append("\n"));
        FileOutput.writeToFile("poemArrayList.txt", String.valueOf(unorderedStr), false, true);
        FileOutput.writeToFile("poemArrayListOrderByID.txt", String.valueOf(orderedStr), false, true);
    }
}
