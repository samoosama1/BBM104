import java.util.ArrayList;

public class Armstrong extends Parent{
    boolean isArmstrong(int num, int digitNum) {
        int result = 0;
        int digit;
        int curNum = num;
        while(num > 0) {
            digit = num % 10;
            result += (int) Math.pow(digit , digitNum);
            num /= 10;
        }
        return curNum == result;
    }

    void generateArmstrong() {
        ArrayList<String> armstrongList = new ArrayList<>();
        String number;
        for(int curNum = 1; curNum <= upperLimitNumber; curNum++) {
            int curDigitNum = getDigitNum(curNum);
            if (isArmstrong(curNum, curDigitNum)) {
                number = String.valueOf(curNum);
                armstrongList.add(number);
            }
        }
        String output = String.join(" ", armstrongList);
        FileOutput.writeToFile("output.txt", output + "\n", true, true);
    }
}