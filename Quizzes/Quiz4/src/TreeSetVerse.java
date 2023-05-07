import java.util.*;

public class TreeSetVerse implements Prompter{
    private final TreeSet<String> stringTreeSet = new TreeSet<>();
    private final TreeSet<Verse> verseTreeSet = new TreeSet<>(new IDComparator());

    public TreeSetVerse(List<String[]> inputList) {
        for (String[] arr : inputList) {
            stringTreeSet.add(String.join("\t", arr));
            verseTreeSet.add(new Verse(Integer.parseInt(arr[0]), arr[1]));
        }
    }

    @Override
    public void prompt() {
        StringBuilder unorderedStr = new StringBuilder();
        StringBuilder orderedStr = new StringBuilder();
        stringTreeSet.forEach(s -> unorderedStr.append(s).append("\n"));
        verseTreeSet.forEach((v) -> orderedStr.append(v.toString()).append("\n"));
        FileOutput.writeToFile("poemTreeSet.txt", String.valueOf(unorderedStr), false, true);
        FileOutput.writeToFile("poemTreeSetOrderByID.txt", String.valueOf(orderedStr), false, true);
    }
}
