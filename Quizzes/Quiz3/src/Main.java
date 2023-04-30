public class Main {
    public static void main(String[] args) {
        String outFilePath = "output.txt";

        InputHandler inHandler = new InputHandler(args, outFilePath);
        inHandler.checkInput();
    }
}