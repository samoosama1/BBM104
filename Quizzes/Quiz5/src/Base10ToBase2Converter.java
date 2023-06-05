public class Base10ToBase2Converter {
    public void convert(int number, String outPath) {
        FileOutput.writeToFile(outPath, String.format("Equivalent of %d (base 10) in base 2 is: ", number), true, false);
        if (number < 0) {
            FileOutput.writeToFile(outPath, "-", true, false);
            number *= -1;
        }
        CircularDoublyLinkedList<Integer> stack = new CircularDoublyLinkedList<>();
        if (number == 0) {
            stack.addToTail(number);
        } else {
            int digit;
            while (number > 0) {
                digit = number % 2;
                stack.addToTail(digit);
                number /= 2;
            }
        }
        while (!stack.isEmpty()) {
            FileOutput.writeToFile(outPath, stack.removeFromTail().toString(), true, false);
        }
        FileOutput.writeToFile(outPath, "\n", true, false);
    }
}
