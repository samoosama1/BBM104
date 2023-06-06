public class BinaryCounter {
    public void countInBinary(int n, String outputPath) {
        String out = String.format("Counting from 1 up to %d in binary:", n);
        FileOutput.writeToFile(outputPath, out, true, false);

        CircularDoublyLinkedList<String> queue = new CircularDoublyLinkedList<>();
        queue.addToTail("1");

        for (int i = 1; i <= n; i++) {
            String binaryNumber = queue.removeFromHead();
            FileOutput.writeToFile(outputPath, " " + binaryNumber, true, false);

            queue.addToTail(binaryNumber + "0");
            queue.addToTail(binaryNumber + "1");
        }
        FileOutput.writeToFile(outputPath, "", true, true);
    }
}