package nibbles;

/** Represents a position on the board. Positions are indexed from (0,0)
 * (lower-left corner) to (BOARD_SIZE - 1, BOARD_SIZE - 1) (upper-right).
 * @author Justin Pau
 */
public class Cell {
    /** The row and column that this cell is located. */
    private final int row, col;
    /** The type that is contained in the cell*/
    private CellType cellType;
    /** The total number of possible rows or columns */
    static final int BOARD_SIZE = 8;
    /** The cache of all created squares, by row and column. */
    private static final Cell[][] CELLS = new Cell[BOARD_SIZE][BOARD_SIZE];


    /** Constructor for creating a new cell. */
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.cellType = CellType.EMPTY;
    }

    /** Return the row of this cell. */
    public int getRow() {
        return this.row;
    }

    /** Return the column of this cell. */
    public int getCol() {
        return this.col;
    }

    /** Set this cell to be of type cellType. */
    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }

    /** Get the cellType of this cell */
    public CellType getCellType() {
        return this.cellType;
    }

    /** Predefined constants that a cell an contain. */
    public enum CellType {
        EMPTY, APPLE, SNAKE_NODE;
    }



}
