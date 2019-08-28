import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class CharacterTile extends Tile
{


    protected char symbol;

    public CharacterTile(char symbol)
    {
        super();
        this.symbol = symbol;
        this.setToolTipText("Character " + symbol);
    }

    public boolean matches(Tile other)
    {
        if(!super.matches(other)) return false;

        CharacterTile otherObj = (CharacterTile)other;
        return (symbol == otherObj.symbol);
    }

    public String toString()
    {
        switch(symbol){
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                return "Character " + symbol + "";
            case 'N': return "North Wind";
            case 'E': return "East Wind";
            case 'W': return "West Wind";
            case 'S': return "South Wind";
            case 'C': return "Red Dragon";
            case 'F': return "Green Dragon";
            default: return "Invalid Operation";
        }

    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        String chineseChar = "";

        switch (symbol) {
            case '1' :
                chineseChar = Character.toString('\u4E00');
                break;
            case '2' :
                chineseChar = Character.toString('\u4E8C');
                break;
            case '3' :
                chineseChar = Character.toString('\u4E09');
                break;
            case '4' :
                chineseChar = Character.toString('\u56DB');
                break;
            case '5' :
                chineseChar = Character.toString('\u4E94');
                break;
            case '6' :
                chineseChar = Character.toString('\u516D');
                break;
            case '7' :
                chineseChar = Character.toString('\u4E03');
                break;
            case '8' :
                chineseChar = Character.toString('\u516B');
                break;
            case '9' :
                chineseChar = Character.toString('\u4E5D');
                break;
            case 'N' :
                chineseChar = Character.toString('\u5317');
                break;
            case 'E' :
                chineseChar = Character.toString('\u6771');
                break;
            case 'S' :
                chineseChar = Character.toString('\u5357');
                break;
            case 'W' :
                chineseChar = Character.toString('\u897F');
                break;
            case 'C' :
                g.setColor(RED);
                chineseChar = Character.toString('\u4E2D');
                break;
            case 'F' :
                g.setColor(GREEN);
                chineseChar = Character.toString('\u767C');
                break;
            default :
                break;
        }

        FontMetrics font = g.getFontMetrics();
        Font font0 = g.getFont();
        font0 = font0.deriveFont(font0.getSize2D() * 3.3F);
        Font font1 = g.getFont();
        font1 = font1.deriveFont(font1.getSize2D() * 1.9F);
        Font font2 = g.getFont();
        font1 = font1.deriveFont(font1.getSize2D() * 1.0F);

        switch(symbol)
        {
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                g.setFont(font1);
                g.drawString(chineseChar, 32, 25);
                g.setColor(RED);
                chineseChar = Character.toString('\u842C');
                g.drawString(chineseChar, 32, 50);
                g.setFont(font2);
                g.drawString(Character.toString(symbol), 60, 15);
                break;
            case 'N':
            case 'E':
            case 'W':
            case 'S':
            case 'C':
            case 'F':
                g.setFont(font0);
                g.drawString(chineseChar, 25, 48);
                g.setColor(RED);
                g.setFont(font2);
                g.drawString(Character.toString(symbol), 60, 15);
                break;
        }
    }

    public static void main(String[] args)
    {
        JFrame		frame = new JFrame();
        JPanel		tiles = new JPanel();
        JScrollPane	scroller = new JScrollPane(tiles);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Character Tiles");
        frame.add(scroller);

        // Try something like this if your tiles don't fit on the screen.
        // Replace "tile width" and "tile height" with your values.
        //scroller.setPreferredSize(new Dimension(8 * tile width, 40 + tile height));

        tiles.add(new CharacterTile('1'));
        tiles.add(new CharacterTile('2'));
        tiles.add(new CharacterTile('3'));
        tiles.add(new CharacterTile('4'));
        tiles.add(new CharacterTile('5'));
        tiles.add(new CharacterTile('6'));
        tiles.add(new CharacterTile('7'));
        tiles.add(new CharacterTile('8'));
        tiles.add(new CharacterTile('9'));
        tiles.add(new CharacterTile('N'));
        tiles.add(new CharacterTile('E'));
        tiles.add(new CharacterTile('W'));
        tiles.add(new CharacterTile('S'));
        tiles.add(new CharacterTile('C'));
        tiles.add(new CharacterTile('F'));

        frame.pack();
        frame.setVisible(true);
    }
}
