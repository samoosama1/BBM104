import java.util.ArrayList;

public class Emirp extends Parent{
    int numReverser(int num) {
        int numDigits = getDigitNum(num);
        int digit;
        int result = 0;
        while(numDigits > 0) {
            digit = num % 10;
            result += digit * (int) Math.pow(10, --numDigits);
            num /= 10;
        }
        return result;
    }

    boolean isEmirp(int num, boolean isReversed) {
        for(int i = 2; i < Math.sqrt(num); i++) {
            if (num % i == 0)
                return false;
        }
        if (isReversed) {
            return num != numReverser(num);
        }
        else
            return isEmirp(numReverser(num), true);
    }

    void generateEmirp() {
        ArrayList<String> emirpList = new ArrayList<>();
        String number;
        for(int curNum = 3; curNum <= upperLimitNumber; curNum += 2) {
            if (isEmirp(curNum, false)) {
                number = String.valueOf(curNum);
                emirpList.add(number);
            }
        }
        String output = String.join(" ", emirpList);
        FileOutput.writeToFile("output.txt", output + "\n", true, true);
    }
}