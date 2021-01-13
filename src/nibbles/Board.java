package nibbles;
import nibbles.Cell.CellType;

/** Represents the state of a snake game.
 *  @author Justin Pau
 */
public class Board {
    /** Number of rows and columns on this board respectively. */
    int ROW_COUNT, COL_COUNT;
    /** A 2D array of cells representing the board. */
    private Cell[][] cells;
    /** Board constructor that assigns number of rows and columns to their
     * respective attributes and also initializes a 2D array of cells. */
    public Board(int rows, int cols) {
        ROW_COUNT = rows;
        COL_COUNT = cols;
        cells = new Cell[ROW_COUNT][COL_COUNT];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }
    /** Return the number of rows on this board. */
    public int getROW_COUNT() {
        return ROW_COUNT;
    }
    /** Return the number of columns on this board. */
    public int getCOL_COUNT() {
        return COL_COUNT;
    }
    /** Return the 2D array of cells representing the board. */
    public Cell[][] getCells() {
        return cells;
    }
    /** Randomly generate an apple on this board. */
    public void generateFood() {
        int row, column;
        while(true) {
            row = (int)(Math.random() * ROW_COUNT);
            column = (int)(Math.random() * COL_COUNT);
            if (cells[row][column].getCellType() != CellType.SNAKE_NODE) {
                break;
            }
        }
        cells[row][column].setCellType(CellType.APPLE);
    }
    /** Checks if a move is legal, but assumes that from and to are not out of bounds. */
    boolean isLegal(Cell from, Cell to) {
        if (from.getCellType() == CellType.SNAKE_NODE) {
            return to.getCellType() != CellType.SNAKE_NODE;
        }
        return true;
    }

}
