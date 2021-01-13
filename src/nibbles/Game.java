package nibbles;


import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/** Represents a game of the snake game.
 * @author Justin Pau
 */
public class Game implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    /** We only care about instances where 'W', 'A', 'S', and 'D' get pressed.
     *  When they get pressed the direction of the snake changes. */
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_W && this.direction != direction.DOWN) {
            this.direction = direction.UP;
        } else if (keyCode == KeyEvent.VK_A && this.direction != direction.RIGHT) {
            this.direction = direction.LEFT;
        } else if (keyCode == KeyEvent.VK_S && this.direction != direction.UP) {
            this.direction = direction.DOWN;
        } else if (keyCode == KeyEvent.VK_D && this.direction != direction.LEFT) {
            this.direction = direction.RIGHT;
        }


        JPanel drawPanel = (JPanel) e.getSource();
        drawPanel.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    /** The frame for this game. */
    private JFrame frame;

    /** The snake for this game. */
    private Snake snake;

    /** The board for this game. */
    private Board board;

    /** The direction of the snake in the game. */
    private Direction direction;

    /** Is this game over or not. */
    private boolean gameOver;

    /** The width of the board that this game is played on. */
    public static int width = 30;

    /** The height of the board that this game is played on. */
    public static int height = 30;

    /** The dimensions of a cell on the board. */
    public static final int dimension = 20;

    /** Constructor for a game given snake and board. */
    public Game(Snake snake, Board board) {
        this.snake = snake;
        this.board = board;
        direction = Direction.NONE;
        gameOver = false;
    }

    /** Get the snake of this game. */
    public Snake getSnake() {
        return snake;
    }

    /** Get the width of the board. */
    public int getWidth() {
        return width;
    }

    /** Get the height of the board. */
    public int getHeight() {
        return height;
    }

    /** Get the board that this game is played on. */
    public Board getBoard() {
        return board;
    }

    /** Return whether or not this game is over. */
    public boolean isGameOver() {
        return gameOver;
    }

    /** Set the status of the game. */
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    /** Get the dimension of a cell on the board. */
    public int getDimension() {
        return dimension;
    }

    /** Set the direction of the snake. */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /** Update the position of the snake and whether or not to generate an apple. */
    public void update() {
        if (!gameOver) {
            if (direction != Direction.NONE) {
                Cell nextCell = getNextCell(snake.getHead());
                if (!board.isLegal(snake.getHead(), nextCell)) {
                    setDirection(Direction.NONE);
                    setGameOver(true);
                } else {
                    int prev = snake.getLength();
                    snake.move(nextCell);
                    int curr = snake.getLength();
                    if (curr > prev) {
                        this.getBoard().generateFood();
                    }
                }
            }
        }
    }

    /** Get the next cell given the snake's current position and direction.
     * If the next cell would be out of bounds return the current position instead.
     * Thus, when board.isLegal() is called the result will be false. */
    private Cell getNextCell(Cell currentPosition) {
        int row = currentPosition.getRow();
        int col = currentPosition.getCol();

        if (direction == Direction.RIGHT) {
            col++;
        } else if (direction == Direction.LEFT) {
            col--;
        } else if (direction == Direction.UP) {
            row--;
        } else if (direction == Direction.DOWN) {
            row++;
        }

        if (row < 0 || row >= board.getROW_COUNT()) {
            return currentPosition;
        }
        if (col < 0 || col >= board.getCOL_COUNT()) {
            return currentPosition;
        }

        Cell nextCell = board.getCells()[row][col];

        return nextCell;
    }

    /** Create the frame for the gui and add the panels. */
    public void play() {
        this.frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getBoard().generateFood();
        Gui gPanel = new Gui(this);
        frame.getContentPane().add(gPanel);
        gPanel.addKeyListener(this);

        gPanel.setFocusable(true);
        gPanel.requestFocusInWindow();
        frame.setSize(width * dimension + 2, height * dimension+dimension+5);
        frame.setVisible(true);


    }

    public static void main(String[] args) {
        Board board = new Board(width, height);
        Cell[][] list = board.getCells();
        Snake snake = new Snake(list[Game.width/2][Game.height/2]);
        Game newGame = new Game(snake, board);
        newGame.play();
    }

    /** Predefined constants that the direction of a snake can take on. */
    public enum Direction {
        NONE, RIGHT, LEFT, UP, DOWN;
    }
}