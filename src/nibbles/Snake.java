package nibbles;

import java.awt.Rectangle;
import java.util.LinkedList;
import nibbles.Cell.CellType;

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
        body.addFirst(dest);
        dest.setCellType(CellType.SNAKE_NODE);
        /** Nibbles ate an apple time for him to grow. */
        if (dest.getCellType() != CellType.APPLE) {
            Cell tail = body.removeLast();
            tail.setCellType(CellType.EMPTY);
        }
    }

    public LinkedList<Cell> getBody() {
        return body;
    }

    public Cell getHead() {
        return this.body.getFirst();
    }

    public void setBody(LinkedList<Cell> Body) {
        this.body = body;
    }


}
