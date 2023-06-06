public class CheckExpressionValidity {
    public boolean isValid(String expression) {
        CircularDoublyLinkedList<Character> stack = new CircularDoublyLinkedList<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.addToTail(c);
            } else if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.removeFromTail();
                if ((c == ')' && top != '(') || (c == '}' && top != '{') || (c == ']' && top != '[')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public void checkValidity(String expression, String outputPath) {
        String out = String.format("\"%s\" is%sa valid expression.", expression, isValid(expression) ? " " : " not ");
        FileOutput.writeToFile(outputPath, out, true, true);
    }

}
