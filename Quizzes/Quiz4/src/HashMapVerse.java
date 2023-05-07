import java.util.*;
public class HashMapVerse implements Prompter {
    private final Map<Integer, String> verseMap = new HashMap<>();
    public HashMapVerse(List<String[]> inputList) {
        inputList.forEach((arr) -> verseMap.put(Integer.parseInt(arr[0]), arr[1]));
    }

    @Override
    public void prompt() {
        StringBuilder out = new StringBuilder();
        Map<Integer, String> treeMap = new TreeMap<>(verseMap);
        for(Map.Entry<Integer, String> entry : treeMap.entrySet()) {
            out.append(entry.getKey()).append("\t").append(entry.getValue()).append("\n");
        }
        FileOutput.writeToFile("poemHashMap.txt", String.valueOf(out), false, true);
    }
}
