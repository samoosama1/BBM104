public class PalindromeChecker {
    public boolean isPalindrome(String inputString) {
        CircularDoublyLinkedList<Character> stack = new CircularDoublyLinkedList<>();

        for (int i = 0; i < inputString.length(); i++) {
            char c = Character.toLowerCase(inputString.charAt(i));
            if (Character.isLetter(c)) {
                stack.addToHead(c);
            }
        }

        for (int i = 0; i < inputString.length(); i++) {
            char c = Character.toLowerCase(inputString.charAt(i));
            if (Character.isLetter(c)) {
                char top = stack.removeFromHead();
                if (c != top) {
                    return false;
                }
            }
        }
        return true;
    }

    public void checkPalindrome(String inputString, String outputPath) {
        String out = String.format("\"%s\" is%sa palindrome.", inputString, isPalindrome(inputString) ? " " : " not ");
        FileOutput.writeToFile(outputPath, out, true, true);
    }
}
