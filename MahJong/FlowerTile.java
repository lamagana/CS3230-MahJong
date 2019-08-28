import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

public class FlowerTile extends PictureTile
{
    private Image image;

    public FlowerTile(String name) {
        super(name);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        URL source = null;
        switch(super.name)
        {
            case "Bamboo":
                source = FlowerTile.class.getResource("images/Bamboo.png");
                break;
            case "Chrysanthemum":
                source = FlowerTile.class.getResource("images/Chrysanthemum.png");
                break;
            case "Orchid":
                source = FlowerTile.class.getResource("images/Orchid.png");
                break;
            case "Plum":
                source = FlowerTile.class.getResource("images/Plum.png");
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
        frame.setTitle("Flower Tiles");

        frame.add(new FlowerTile("Chrysanthemum"));
        frame.add(new FlowerTile("Orchid"));
        frame.add(new FlowerTile("Plum"));
        frame.add(new FlowerTile("Bamboo"));

        frame.pack();
        frame.setVisible(true);
    }
}
