package nibbles;
import nibbles.Cell.CellType;

public class Board {
    int ROW_COUNT, COL_COUNT;
    private Cell[][] cells;
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

    public Cell[][] getCells() {
        return cells;
    }
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

    boolean isLegal(Cell from, Cell to) {
        if (from.getCellType() == CellType.SNAKE_NODE) {
            return false;
        }
        if (to.getRow() < 0 || to.getCol() >= this.ROW_COUNT) {
            return false;
        }
        if (to.getCol() < 0 || to.getCol() >= this.COL_COUNT) {
            return false;
        }
        return true;
    }

}
