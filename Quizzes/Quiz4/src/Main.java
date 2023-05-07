import java.util.*;

public class Main {
    public static void main(String[] args) {
        String[] inputArr = FileInput.readFile(args[0], true, true);
        List<String[]> inputList = new ArrayList<>();
        Arrays.asList(inputArr).forEach((s) -> inputList.add(s.split("\t")));

        HashSetVerse hashSetVerse = new HashSetVerse(inputList);
        hashSetVerse.prompt();

        ArrayListVerse arrayListVerse = new ArrayListVerse(inputList);
        arrayListVerse.prompt();

        TreeSetVerse treeSetVerse = new TreeSetVerse(inputList);
        treeSetVerse.prompt();

        HashMapVerse hashMapVerse = new HashMapVerse(inputList);
        hashMapVerse.prompt();
    }
}