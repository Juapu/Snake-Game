package nibbles;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Graphics
        extends JPanel
        implements ActionListener{
    private Timer t = new Timer(100, this);
    public String state;

    private Snake s;
    private Game game;

    public Graphics(Game g) {
        t.start();
        state = "START";

        game = g;
        s = g.getSnake();


        //add a keyListner
        this.addKeyListener(g);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        game.update();
    }

}