import java.util.ArrayList;

public class Abundant extends Parent{
    boolean isAbundant(int dividend) {
        int quotient;
        int sum = 1;
        for (int divisor = 2; divisor <= Math.sqrt(dividend); divisor++) {
            if (dividend % divisor == 0) {
                quotient = dividend / divisor;
                if (quotient == divisor)
                    sum += divisor;
                else
                    sum += divisor + quotient;
            }
        }
        return sum > dividend;
    }

    void generateAbundant() {
        ArrayList<String> abundantList = new ArrayList<>();
        String number;
        for(int curNum = 1; curNum <= upperLimitNumber; curNum++) {
            if (isAbundant(curNum)) {
                number = String.valueOf(curNum);
                abundantList.add(number);
            }
        }
        String output = String.join(" ", abundantList);
        FileOutput.writeToFile("output.txt", output + "\n", true, true);
    }
}