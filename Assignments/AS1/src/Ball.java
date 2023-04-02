public class Ball {
    private int[] currentPosition;
    private int[] targetPosition;

    public static int getPoints() {
        return points;
    }

    private static int points = 0;
    private String direction;
    private String occupant;

    public boolean isGameOver() {
        return gameOver;
    }

    private boolean gameOver = false;
    final String asterisk = "*";

    public Ball(String[][] board) {
        int row = board.length;
        int col = board[0].length;
        boolean notFound = true;
        for (int x = 0; (x < row) && notFound; x++) {
            for (int y = 0; y < col; y++) {
                if (board[x][y].equals(asterisk)) {
                    currentPosition = new int[]{x,y};
                    notFound = false;
                    break;
                }
            }
        }
    }

    public void makeMove(String direction, String[][] board) {
        this.direction = direction;
        setTargetPosition(board);
        chooseAction(board);
    }

    public void direcSwitcher() {
        switch (direction) {
            case "L":
                direction = "R";
                break;
            case "R":
                direction = "L";
                break;
            case "U":
                direction = "D";
                break;
            case "D":
                direction = "U";
                break;
        }
    }
    public void chooseAction(String[][] board) {
        switch (occupant) {
            case "W":
                direcSwitcher();
                makeMove(direction, board);
                break;
            case "H":
                int currentRow = currentPosition[0], currentCol = currentPosition[1];
                board[currentRow][currentCol] = " ";
                gameOver = true;
                break;
            case "R":
                swap(board, true);
                points += 10;
                break;
            case "Y":
                swap(board, true);
                points += 5;
                break;
            case "B":
                swap(board, true);
                points -= 5;
                break;
            default:
                swap(board, false);

        }
    }

    public void swap(String[][] board, boolean isSpecial) {
        int targetRow = targetPosition[0], targetCol = targetPosition[1];
        int currentRow = currentPosition[0], currentCol = currentPosition[1];
        board[targetRow][targetCol] = asterisk;
        board[currentRow][currentCol] = isSpecial ? "X" : occupant ;
        currentPosition = targetPosition;
        targetPosition = null;
        occupant = null;
    }

    public void setTargetPosition(String[][] board) {
        int currentRow = currentPosition[0], currentCol = currentPosition[1];
        int rowSize = board.length, colSize = board[0].length;
        int targetRow = 0;
        int targetCol = 0;
        switch(direction) {
            case "L":
                targetRow = currentRow;
                targetCol = (currentCol - 1 < 0) ? (currentCol - 1 + colSize) : (currentCol - 1);
                break;
            case "R":
                targetRow = currentRow;
                targetCol = (currentCol + 1 >= colSize) ? (currentCol + 1 - colSize) : (currentCol + 1);
                break;
            case "U":
                targetRow = (currentRow - 1 < 0) ? (currentRow - 1 + rowSize) : (currentRow - 1);
                targetCol = currentCol;
                break;
            case "D":
                targetRow = (currentRow + 1 >= rowSize) ? (currentRow + 1 - rowSize) : (currentRow + 1);
                targetCol = currentCol;
                break;
        }
        targetPosition = new int[]{targetRow, targetCol};
        occupant = board[targetRow][targetCol];
    }
}