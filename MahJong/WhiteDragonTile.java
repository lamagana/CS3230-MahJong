import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.*;

public class WhiteDragonTile extends Tile {


    public WhiteDragonTile()
    {
        super();
        setToolTipText("White Dragon");
    }

    public String toString() {
        return "White Dragon";
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        //5 pixels from faces of edge on both sides - 50 pixels left
        //3 blue rectangles and 3 white rectangles - 6
        //50 / 6 = 8.33 -> 8 pixels on width

        /* ---------- TOP  ---------- */
        g.setColor(BLUE);
        g.fillRect(20, 6, 8, 5);
        g.fillRect(36, 6, 8, 5);
        g.fillRect(52, 6, 8, 5);


        //Comment in to enable white version
        /*
        g.setColor(WHITE);
        g.fillRect(28, 6, 8, 5);
        g.fillRect(44, 6, 8, 5);
        g.fillRect(60, 6, 8, 5);
        */

        /* ---------- LEFT  ---------- */
        g.setColor(BLUE);
        g.fillRect(20,19, 5, 8);
        g.fillRect(20,35, 5, 8);


        //Comment in to enable white version
        /*
        g.setColor(WHITE);
        g.fillRect(20,11, 5, 8);
        g.fillRect(20,27, 5, 8);
        g.fillRect(20,43, 5, 8);
        */

        /* ---------- RIGHT  ---------- */
        g.setColor(BLUE);
        g.fillRect(63,10, 5, 8);
        g.fillRect(63,27, 5, 8);
        g.fillRect(63,42, 5, 8);


        //Comment in to enable white version
        /*
        g.setColor(WHITE);
        g.fillRect(63,19, 5, 8);
        g.fillRect(63,35, 5, 8);
        */

        /* ---------- BOTTOM  ---------- */
        g.setColor(BLUE);
        g.fillRect(20, 49, 8, 6);
        g.fillRect(36, 50, 8, 5);
        g.fillRect(52, 50, 8, 5);


        //Comment in to enable white version
        /*
        g.setColor(WHITE);
        g.fillRect(28, 50, 8, 5);
        g.fillRect(44, 50, 8, 5);
        g.fillRect(60, 50, 8, 5);
        */

        g.setColor(Color.BLACK);
        g.drawRect(19, 5, 49, 50);
        g.drawRect(25, 10, 37, 39);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Circle Tiles");

        frame.add(new WhiteDragonTile());

        frame.pack();
        frame.setVisible(true);
    }
}

