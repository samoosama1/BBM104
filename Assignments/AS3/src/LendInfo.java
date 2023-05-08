public class LendInfo {
    private int bookID;
    private String lendStr;

    public int getBookID() {
        return bookID;
    }

    public String getLendStr() {
        return lendStr;
    }

    public LendInfo(int bookID, String borrowStr) {
        this.bookID = bookID;
        this.lendStr = borrowStr;
    }
}
