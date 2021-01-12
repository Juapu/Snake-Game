package nibbles;

import nibbles.Cell.CellType;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// To represent Snake Game
public class Game implements KeyListener{

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public enum Direction {
        NONE, RIGHT, LEFT, UP, DOWN;
    }
    private Snake snake;
    private Board board;
    private Direction direction;
    private boolean gameOver;

    public Game(Snake snake, Board board)
    {
        this.snake = snake;
        this.board = board;
        direction = Direction.NONE;
    }

    public Snake getSnake()
    {
        return snake;
    }

    public void setSnake(Snake snake)
    {
        this.snake = snake;
    }

    public Board getBoard()
    {
        return board;
    }

    public void setBoard(Board board)
    {
        this.board = board;
    }

    public boolean isGameOver()
    {
        return gameOver;
    }

    public void setGameOver(boolean gameOver)
    {
        this.gameOver = gameOver;
    }

    public Direction getDirection()
    {
        return direction;
    }

    public void setDirection(Direction direction)
    {
        this.direction = direction;
    }

    // We need to update the game at regular intervals,
    // and accept user input from the Keyboard.
    public void update()
    {
        System.out.println("Going to update the game");
        if (!gameOver) {
            if (direction != Direction.NONE) {
                Cell nextCell = getNextCell(snake.getHead());

                if (!board.isLegal(snake.getHead(), nextCell)) {
                    setDirection(Direction.NONE);
                    setGameOver(true);
                }
                else {
                    snake.move(nextCell);
                }
            }
        }
    }

    private Cell getNextCell(Cell currentPosition)
    {
        int row = currentPosition.getRow();
        int col = currentPosition.getCol();

        if (direction == Direction.RIGHT) {
            col++;
        }
        else if (direction == Direction.LEFT) {
            col--;
        }
        else if (direction == Direction.UP) {
            row--;
        }
        else if (direction == Direction.DOWN) {
            row++;
        }

        Cell nextCell = board.getCells()[row][col];

        return nextCell;
    }

    public void play() {
        Cell initPos = new Cell(0,0);
        snake = new Snake(initPos);
        board = new Board(10, 10);
        Game newGame = new Game(snake, board);
        newGame.setGameOver(false);
        newGame.setDirection(Direction.RIGHT);
        while(!newGame.gameOver) {
            newGame.board.generateFood();

        }

    }


}