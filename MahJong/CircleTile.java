import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import javax.swing.JFrame;

public class CircleTile extends RankTile
{
    //Able to modify the diameter here - Doesn't look neat after a diameter of 18
    private static int SIZE = 14;

    public CircleTile(int rank)
    {
        super(rank);
        this.setToolTipText("Circle " + rank);
    }

    class Pancake extends CircleTile
    {
        public Pancake(int rank)
        {
            super(rank);
            this.setToolTipText("Circle " + rank);
        }

        public void drawCircle(Graphics g, int x, int y) {
            g.setColor(Color.BLACK);
            g.fillOval(x, y, SIZE * 3, SIZE * 3);
            g.setColor(GREEN);
            g.fillOval(x+1, y+1, SIZE * 3 - 2, SIZE * 3 - 2);

            //Draws all the small circles in the Pancake circle
            g.setColor(WHITE);
            g.fillOval(42, 15, 3, 3);
            g.fillOval(47, 17, 3, 3);
            g.fillOval(51, 20, 3, 3);
            g.fillOval(55, 24, 3, 3);
            g.fillOval(57, 30, 3, 3);
            g.fillOval(55, 36, 3, 3);
            g.fillOval(51, 41, 3, 3);
            g.fillOval(47, 43, 3, 3);
            g.fillOval(42, 45, 3, 3);
            g.fillOval(36, 44, 3, 3);
            g.fillOval(32, 40, 3, 3);
            g.fillOval(29, 35, 3, 3);
            g.fillOval(27, 30, 3, 3);
            g.fillOval(29, 26, 3, 3);
            g.fillOval(33, 21, 3, 3);
            g.fillOval(37, 17, 3, 3);

        }
    }

    public String toString()
    {
        return "Circle " + rank;
    }

    //Custom function to easily redraw ovals
    public void drawCircle(Graphics g, int x, int y, Color color)
    {
        //Draws the circle
        g.setColor(color);
        g.fillOval(x, y, SIZE, SIZE);

        //Used for drawing the white line in the center of the circle
        g.setColor(WHITE);
        //Draws a white back slash
        g.drawLine(x + (SIZE / 3) , y + (SIZE / 3), x + SIZE - (SIZE / 3), y + SIZE - (SIZE / 3));
        //Draws a white forward slash
        g.drawLine(x + SIZE - (SIZE / 3), y + (SIZE / 3), x + (SIZE / 3), y + SIZE - (SIZE / 3));
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        switch (this.rank) {
            case 1 :
                Pancake p = new Pancake(rank);
                p.drawCircle(g, 22, 11);
                drawCircle(g, 37,25,RED);
                break;
            case 2 :
                drawCircle(g,37,13,GREEN);
                drawCircle(g,37,33,RED);
                break;
            case 3 :
                drawCircle(g,19,4,BLUE);
                drawCircle(g,37,23,RED);
                drawCircle(g,55,40,GREEN);
                break;
            case 4 :
                drawCircle(g,25,8,BLUE);
                drawCircle(g,25,35,GREEN);
                drawCircle(g,53,8,GREEN);
                drawCircle(g,53,35,BLUE);
                break;
            case 5 :
                drawCircle(g,20,5,BLUE);
                drawCircle(g,53,5, GREEN);
                drawCircle(g,37,22,RED);
                drawCircle(g,20,37,GREEN);
                drawCircle(g,53,37,BLUE);
                break;
            case 6 :
                drawCircle(g,26,3,GREEN);
                drawCircle(g,49,3,GREEN);
                for (int x = 26; x < 50; x+=23) {
                    for (int y = 22; y < 42; y+=19) {
                        drawCircle(g,x,y,RED);
                    }
                }
                break;
            case 7 :
                drawCircle(g,21,4,GREEN);
                drawCircle(g,38,7,GREEN);
                drawCircle(g,55,10,GREEN);
                drawCircle(g,28,27,RED);
                drawCircle(g,47,27,RED);
                drawCircle(g,28,42,RED);
                drawCircle(g,47,42,RED);
                break;
            case 8 :
                for (int y = 1; y < 45; y+=14) {
                    drawCircle(g,25,y,BLUE);
                    drawCircle(g,25,y,BLUE);
                    drawCircle(g,50,y,BLUE);
                    drawCircle(g,25,y,BLUE);
                }
                break;
            case 9 :
                for (int x = 18; x < 57; x+=19){
                    drawCircle(g,x,2,GREEN);
                    drawCircle(g,x,21,RED);
                    drawCircle(g,x,40,BLUE);
                }
                break;
            default :
        }
    }

    public static void main(String[] args)
    {
        JFrame	frame = new JFrame();

        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Circle Tiles");

        frame.add(new CircleTile(1));
        frame.add(new CircleTile(2));
        frame.add(new CircleTile(3));
        frame.add(new CircleTile(4));
        frame.add(new CircleTile(5));
        frame.add(new CircleTile(6));
        frame.add(new CircleTile(7));
        frame.add(new CircleTile(8));
        frame.add(new CircleTile(9));

        frame.pack();
        frame.setVisible(true);
    }
}
