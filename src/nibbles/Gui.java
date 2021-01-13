package nibbles;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;
/** The GUI controller for a snake game
 * @author Justin Pau
 */
public class Gui extends JPanel implements ActionListener{
    /** Timer for a snake game. */
    private Timer t;
    /** The snake for a snake game. */
    private Snake s;
    /** The game this gui is for. */
    private Game game;

    /** Constructor for a gui. Sets the timer to call the listener's actionPerformed
     * in intervals of delay milliseconds. Also sets attributes to their respective values
     */
    public Gui(Game g) {
        t = new Timer(100, this);
        t.start();

        game = g;
        s = g.getSnake();


        this.setFocusTraversalKeysEnabled(false);
    }
    @Override
    /** override the JPanel method for paintComponent. It gets called every time repaint gets called.
     * Sets empty cells to black, snake node cells to neon, and apple to red. Also does cute stuff like
     * draw dots for eyeballs on the snake. */
    public void paintComponent(java.awt.Graphics g) {
        if (game.isGameOver() == false) {
            int d = game.getDimension();
            Cell[][] cellDList = game.getBoard().getCells();
            for (Cell cellSList[] : cellDList) {
                for (Cell singleCell : cellSList) {
                    Cell.CellType type = singleCell.getCellType();
                    if (type == Cell.CellType.SNAKE_NODE) {
                        g.setColor(Color.CYAN);
                        g.fillRect(singleCell.getCol() * d, singleCell.getRow() * d, d, d);
                    } else if (type == Cell.CellType.APPLE) {
                        g.setColor(Color.BLACK);
                        g.fillRect(singleCell.getCol() * d, singleCell.getRow() * d, d, d);
                        g.setColor(Color.RED);
                        g.fillOval(singleCell.getCol() * d, singleCell.getRow() * d, d, d);
                        g.setColor(Color.GREEN);
                        g.fillRect(singleCell.getCol() * d+5, singleCell.getRow()* d-5, d/5, d/2);
                    } else {
                        g.setColor(Color.BLACK);
                        g.fillRect(singleCell.getCol() * d, singleCell.getRow() * d, d, d);
                    }
                }
            }
            game.getSnake().getHead();
            int row = game.getSnake().getHead().getRow();
            int col = game.getSnake().getHead().getCol();
            g.setColor(Color.black);
            g.fillOval(col * d+3, row * d, d/4, d/4);
            g.fillOval(col * d+12, row * d, d/4, d/4);

            g.setColor(Color.WHITE);
            g.drawString("Apple Count: " + (game.getSnake().getLength() - 1), game.getDimension(), game.getDimension());
        } else {
            g.setColor(Color.BLACK);
            g.fillRect(0,0,game.getWidth()*game.getDimension()+2,game.getHeight()*game.getDimension()+game.getDimension()+5);
            g.setColor(Color.WHITE);
            g.drawString("Apple Count: " + (game.getSnake().getLength() - 1), Game.width/2 * Game.dimension - 40, Game.height / 2 * Game.dimension - 20);
        }
    }

    @Override
    /** Every 100 milliseconds this method gets called because of the timer. Basically updates
     * how the board should look. */
    public void actionPerformed(ActionEvent e) {
        repaint();
        game.update();

    }

}