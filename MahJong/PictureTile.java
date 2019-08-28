import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.JFrame;

public class PictureTile extends Tile {

    protected String name;
    private Image image;

    PictureTile(String name) {
        this.name = name;
        this.setToolTipText("Picture - " + name);
    }

    public String toString() {
        return name + "";
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        URL source = null;
        switch(name)
        {
            case "Bamboo":
                source = FlowerTile.class.getResource("images/Bamboo.png");
                break;
            case "Chrysanthemum":
                source = FlowerTile.class.getResource("images/Chrysanthemum.png");
                break;
            case "Fall":
                source = SeasonTile.class.getResource("images/Fall.png");
                break;
            case "Orchid":
                source = FlowerTile.class.getResource("images/Orchid.png");
                break;
            case "Plum":
                source = FlowerTile.class.getResource("images/Plum.png");
                break;
            case "Sparrow":
                source = Bamboo1Tile.class.getResource("images/Sparrow.png");
                break;
            case "Spring":
                source = SeasonTile.class.getResource("images/Spring.png");
                break;
            case "Summer":
                source = SeasonTile.class.getResource("images/Summer.png");
                break;
            case "Winter":
                source = SeasonTile.class.getResource("images/Winter.png");
                break;
            default:
                System.exit(0);
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
        frame.setTitle("Picture Tiles");

        frame.add(new Bamboo1Tile());

        frame.add(new FlowerTile("Chrysanthemum"));
        frame.add(new FlowerTile("Orchid"));
        frame.add(new FlowerTile("Plum"));
        frame.add(new FlowerTile("Bamboo"));

        frame.add(new SeasonTile("Spring"));
        frame.add(new SeasonTile("Summer"));
        frame.add(new SeasonTile("Fall"));
        frame.add(new SeasonTile("Winter"));

        frame.pack();
        frame.setVisible(true);
    }
}
