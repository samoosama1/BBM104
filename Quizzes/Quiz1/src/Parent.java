public class Parent {
    int upperLimitNumber;
    int getDigitNum(int num) {
        int counter = 0;
        while(num > 0) {
            counter += 1;
            num /= 10;
        }
        return counter;
    }

    void setUpperLimitNumber(int num) {
        this.upperLimitNumber = num;
    }
}