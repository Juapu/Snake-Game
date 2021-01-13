package nibbles;

import java.util.LinkedList;
import nibbles.Cell.CellType;


/** Represents a snake on the board.
 * Snakes are linked lists of multiple cells of CellType snake.
 * @author Justin Pau
 */
public class Snake {
    private LinkedList<Cell> body;
    public Snake(Cell init) {
        init.setCellType(CellType.SNAKE_NODE);
        body = new LinkedList<>();
        body.add(init);
    }

    /** Move snake to cell dest. Return 1 upon success
     * and 0 upon failure. Will grow if the cell contains
     * an apple. */
    public void move(Cell dest) {
        CellType prev = dest.getCellType();
        body.addFirst(dest);
        dest.setCellType(CellType.SNAKE_NODE);
        /** Nibbles ate an apple time for him to grow. */
        if (prev != CellType.APPLE) {
            Cell tail = body.removeLast();
            tail.setCellType(CellType.EMPTY);
        }
    }

    /** Return the length of the snake. */
    public int getLength() {
        return body.size();
    }

    /** Return the cell that head is located. */
    public Cell getHead() {
        return this.body.getFirst();
    }


}
