import java.awt.Color;
import java.awt.Graphics;
import java.awt.FlowLayout;
import javax.swing.JFrame;

public class BambooTile extends RankTile
{

    public BambooTile(int rank)
    {
        super(rank);
        this.setToolTipText("Bamboo " + rank);
    }
    public String toString()
    {
        return "Bamboo " + rank;
    }

    public void drawBambooStick(Graphics g, int x, int y, Color color)
    {
        //draws a colored bamboo at the specified pixels
        g.setColor(color);

        //Draw 1 rectangle to connect all stalks
        g.fillRect(x + 3, y + 4, 4, 13);

        //Draw Bamboo arcs
        //fillArc(int x, int y, int width, int height, int startAngle, int arcAngle)
        g.fillArc(x, y, 9, 10, 0, 180);
        g.fillArc(x, y + 8, 9, 10, 0, 180);
        g.fillArc(x, y + 14, 9, 10, 0, 180);

        //Draw two white lines
        g.setColor(WHITE);
        g.drawLine(x + 5, y + 2, x + 5, y+6);
        g.drawLine(x + 5, y + 12, x + 5, y+16);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        switch(rank)
        {
            case 2:
                drawBambooStick(g,40,10,BLUE);
                drawBambooStick(g,40,30,GREEN);
                break;
            case 3:
                drawBambooStick(g,41,11,BLUE);
                drawBambooStick(g,31,31,GREEN);
                drawBambooStick(g,51,31,GREEN);
                break;
            case 4:
                drawBambooStick(g,31,10,BLUE);
                drawBambooStick(g,51,10,GREEN);
                drawBambooStick(g,31,30,GREEN);
                drawBambooStick(g,51,30,BLUE);
                break;
            case 5:
                drawBambooStick(g,25,11,GREEN);
                drawBambooStick(g,25,31,BLUE);
                drawBambooStick(g,40,19,RED);
                drawBambooStick(g,54,11,BLUE);
                drawBambooStick(g,54,31,GREEN);
                break;
            case 6:
                for (int x = 25; x < 58; x+= 16) {
                    drawBambooStick(g,x,10,GREEN);
                    drawBambooStick(g,x,30,BLUE);
                }
                break;
            case 7:
                drawBambooStick(g,41,1,RED);
                for (int y = 20; y < 40; y+= 19) {
                    drawBambooStick(g,25,y,GREEN);
                    drawBambooStick(g,41,y,BLUE);
                    drawBambooStick(g,57,y,GREEN);
                }
                break;
            case 8:
                for (int x = 24; x < 59; x+= 17) {
                    drawBambooStick(g,x,1,GREEN);
                    drawBambooStick(g,x,39,BLUE);
                }
                drawBambooStick(g,32,20,RED);
                drawBambooStick(g,49,20,RED);
                break;
            case 9:
                for (int y = 1; y < 40; y+=19) {
                    drawBambooStick(g,25,y, RED);
                    drawBambooStick(g,41,y, BLUE);
                    drawBambooStick(g,57,y, GREEN);
                }
                break;
        }
    }

    public static void main(String[] args)
    {
        JFrame	frame = new JFrame();

        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Bamboo Tiles");

        frame.add(new BambooTile(2));
        frame.add(new BambooTile(3));
        frame.add(new BambooTile(4));
        frame.add(new BambooTile(5));
        frame.add(new BambooTile(6));
        frame.add(new BambooTile(7));
        frame.add(new BambooTile(8));
        frame.add(new BambooTile(9));

        frame.pack();
        frame.setVisible(true);
    }
}

