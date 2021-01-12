package nibbles;
import nibbles.Game;
public class Main {
    public static void main(String[] args) {
        Cell initPos = new Cell(0, 0);
        Snake snake = new Snake(initPos);
        Board board = new Board(10, 10);
        Game newGame = new Game(snake, board);

    }
}
