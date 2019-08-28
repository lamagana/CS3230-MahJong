import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.JFrame;
public class SeasonTile extends PictureTile
{
    private Image image;

    public SeasonTile(String name)
    {
        super(name);
        this.setToolTipText(name);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        URL source = null;
        switch (super.name) {
            case "Spring":
                source = SeasonTile.class.getResource("images/Spring.png");
                break;
            case "Summer":
                source = SeasonTile.class.getResource("images/Summer.png");
                break;
            case "Fall":
                source = SeasonTile.class.getResource("images/Fall.png");
                break;
            case "Winter":
                source = SeasonTile.class.getResource("images/Winter.png");
                break;
        }
        if (source != null)
        {
            image = Toolkit.getDefaultToolkit().getImage(source);
            g.drawImage(image, 20, 5, 50,50, this);
        }
        else {
            System.exit(0);
        }
    }

    public static void main(String[] args)
    {
        JFrame	frame = new JFrame();

        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Season Tiles");

        frame.add(new SeasonTile("Spring"));
        frame.add(new SeasonTile("Summer"));
        frame.add(new SeasonTile("Fall"));
        frame.add(new SeasonTile("Winter"));

        frame.pack();
        frame.setVisible(true);
    }

}
