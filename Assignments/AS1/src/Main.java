import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String outputFile = "output.txt";
        String boardInputFile = args[0];
        String moveInputFile = args[1];
        ArrayList<String> madeMoves = new ArrayList<>();

        String[] boardInput = ReadFromFile.readFile(boardInputFile);
        String[] moveInput = ReadFromFile.readFile(moveInputFile)[0].split(" ");
        Grid gameBoard = new Grid(boardInput);
        Ball whiteBall = new Ball(gameBoard.getBoard());
        for (String move : moveInput) {
            whiteBall.makeMove(move, gameBoard.getBoard());
            madeMoves.add(move);
            if (whiteBall.isGameOver())
                break;
        }
        String out = gameBoard.promptEndScreen(boardInput , madeMoves, whiteBall.isGameOver()).toString();
        FileOutput.writeToFile(outputFile, out, false, false);
    }
}