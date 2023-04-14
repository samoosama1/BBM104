import java.util.ArrayList;

public class Grid {
    final private String[][] board;

    public String[][] getBoard() {
        return board;
    }

    public Grid(String[] input) {
        int row = input.length;
        int col = input[0].split(" ").length;
        String[] rowArray;
        this.board = new String[row][col];
        for (int x = 0; x < row; x++) {
            rowArray = input[x].split(" ");

        }
    }

    public StringBuilder promptEndScreen(String[] initialBoard, ArrayList<String> madeMoves, boolean gameOver) {
        StringBuilder out = new StringBuilder();
        out.append("Game board:\n");
        for (String line : initialBoard) {
            out.append(line).append("\n");
        }
        out.append("\nYour movement is:\n").append(String.join(" ", madeMoves)).append("\n");
        out.append("\nYour output is:\n");
        for (String[] row : board) {
            out.append(String.join(" ", row)).append("\n");
        }
        out.append(gameOver ? "\nGame Over!" : "");
        out.append(String.format("\nScore: %d%n", Ball.getPoints()));
        return out;
    }
}
