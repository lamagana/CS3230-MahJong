import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.beans.*;

public class MahJongModel extends JFrame {
    //Java regions only work as a part of intellij

    //region Global Variables
    //Size of my tiles structure
    private final int xSize = 9;
    private final int ySize = 16;
    private final int layerSize = 5;

    //Array list of the tiles in the deck
    private ArrayList<Tile> deck;

    //Current Selected Tile - Highlighted Tile - Used so I can compare with another tile
    private Tile selected;

    private ScrollDemo removedTilesPane;

    //Holds the current game number - used so I can restart at the same game
    private int gameNumber;

    //PlayClip given to us - Chapter 14 demonstrations
    private PlayClip stoneSound;
    private boolean soundOn = true;

    private GameBoard board;
    //endregion

    //region Constructor
    public MahJongModel(int gameNumber) {

        this.gameNumber = gameNumber;

        deck = new ArrayList<Tile>(144);
        removedTilesPane = new ScrollDemo();
        stoneSound = new PlayClip("audio/stone-scraping.wav", true);

        setTitle("Mah Jong - Lawrence Magana - Game Number: " + gameNumber);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(960, 773);
        //Height w/o bottom scroll pane: 578

        createMenu();
        createBottomPane();
        setUpDeck();
        board = new GameBoard();
        add(board);

        setVisible(true);
    }
    //endregion

    //region Deck
    //Adds all available tiles to the deck then shuffles
    private void setUpDeck() {
        for (int i = 0; i < 4; i++) {
            deck.add(new CharacterTile('1'));
            deck.add(new CharacterTile('2'));
            deck.add(new CharacterTile('3'));
            deck.add(new CharacterTile('4'));
            deck.add(new CharacterTile('5'));
            deck.add(new CharacterTile('6'));
            deck.add(new CharacterTile('7'));
            deck.add(new CharacterTile('8'));
            deck.add(new CharacterTile('9'));

            deck.add(new CharacterTile('N'));
            deck.add(new CharacterTile('S'));
            deck.add(new CharacterTile('E'));
            deck.add(new CharacterTile('W'));

            for (int j = 1; j < 10; j++)
                deck.add(new CircleTile(j));

            deck.add(new Bamboo1Tile());

            for (int k = 2; k < 10; k++)
                deck.add(new BambooTile(k));

            deck.add(new CharacterTile('C'));
            deck.add(new CharacterTile('F'));
            deck.add(new WhiteDragonTile());
        }

        deck.add(new FlowerTile("Chrysanthemum"));
        deck.add(new FlowerTile("Orchid"));
        deck.add(new FlowerTile("Plum"));
        deck.add(new FlowerTile("Bamboo"));

        deck.add(new SeasonTile("Spring"));
        deck.add(new SeasonTile("Summer"));
        deck.add(new SeasonTile("Fall"));
        deck.add(new SeasonTile("Winter"));

        Random rand = new Random(gameNumber);
        Collections.shuffle(deck, rand); //Shuffles deck after tiles are loaded in
    }

    //Returns a tile from the deck
    private Tile dealTile() {
        if (deck.size() > 0) {
            return deck.remove(deck.size() - 1);
        }
        return null;
    }
    //endregion

    //region Gameboard
    // ----------------------------------------------
    // Gameboard
    // Sets up out gameboard - Adds tiles from our deck
    // to the board. Drawing components (background and tiles)
    // are managed within the gameboard
    // Functions: setUpGameBoard, positionTile, MouseEvents
    // ----------------------------------------------
    public class GameBoard extends JPanel implements MouseListener {

        private Image backgroundImage;

        //Data structure which holds positions of tiles
        private Tile[][][] tiles;

        public GameBoard() {
            setLayout(null);

            tiles = new Tile[xSize][ySize][layerSize];

            clearTiles();
            setUpGameBoard(); //Comment this out if you want to see the background
        }

        //Clears tiles[] to make sure there's no garbage data
        private void clearTiles() {
            for (int row = 0; row < xSize; row++) {
                for (int col = 0; col < ySize; col++) {
                    for (int layer = 0; layer < layerSize; layer++) {
                        tiles[row][col][layer] = null;
                    }
                }
            }
        }

        //Deals tiles to specific positions, sets the coordinates so individual tiles know
        //their own location, and draws the tiles on the gameboard w/ positionTile
        private void setUpGameBoard() {

            //---------------- Add Layer 4 Tiles - Just the top tile ----------------
            tiles[4][7][4] = dealTile();
            tiles[4][7][4].setCoordinates(4, 7, 4);
            positionTile(tiles[4][7][4], 4, 7, 4);
            //-------------------------- Add Layer 3 Tiles --------------------------
            for (int col = 6; col < 8; col++) {
                for (int row = 4; row > 2; row--) {
                    tiles[row][col][3] = dealTile();
                    tiles[row][col][3].setCoordinates(row, col, 3);
                    positionTile(tiles[row][col][3], row, col, 3);
                }
            }
            //-------------------------- Add Layer 2 Tiles --------------------------
            for (int col = 5; col < 9; col++) {
                for (int row = 5; row > 1; row--) {
                    tiles[row][col][2] = dealTile();
                    tiles[row][col][2].setCoordinates(row, col, 2);
                    positionTile(tiles[row][col][2], row, col, 2);
                }
            }
            //-------------------------- Add Layer 1 Tiles --------------------------
            for (int col = 4; col < 10; col++) {
                for (int row = 6; row > 0; row--) {
                    tiles[row][col][1] = dealTile();
                    tiles[row][col][1].setCoordinates(row, col, 1);
                    positionTile(tiles[row][col][1], row, col, 1);
                }
            }
            //-------------------------- Add Layer 0 Tiles --------------------------
            //Row 7
            for (int col = 1; col < 13; col++) {
                tiles[7][col][0] = dealTile();
                tiles[7][col][0].setCoordinates(7, col, 0);
                positionTile(tiles[7][col][0], 7, col, 0);
            }
            //Row 6
            for (int col = 3; col < 11; col++) {
                tiles[6][col][0] = dealTile();
                tiles[6][col][0].setCoordinates(6, col, 0);
                positionTile(tiles[6][col][0], 6, col, 0);
            }
            //Row 5
            for (int col = 2; col < 12; col++) {
                tiles[5][col][0] = dealTile();
                tiles[5][col][0].setCoordinates(5, col, 0);
                positionTile(tiles[5][col][0], 5, col, 0);
            }
            //Special Scenario: Tile on very left - Considered on row 4
            tiles[4][0][0] = dealTile();
            tiles[4][0][0].setCoordinates(4, 0, 0);
            positionTile(tiles[4][0][0], 4, 0, 0);
            //Row 4 & Row 3
            for (int col = 1; col < 13; col++) {
                for (int row = 4; row > 2; row--) {
                    tiles[row][col][0] = dealTile();
                    tiles[row][col][0].setCoordinates(row, col, 0);
                    positionTile(tiles[row][col][0], row, col, 0);
                }
            }
            //Special Scenario: Two tiles on very right - Considered on row 3
            for (int col = 13; col < 15; col++) {
                tiles[3][col][0] = dealTile();
                tiles[3][col][0].setCoordinates(3, col, 0);
                positionTile(tiles[3][col][0], 3, col, 0);
            }
            //Row 2
            for (int col = 2; col < 12; col++) {
                tiles[2][col][0] = dealTile();
                tiles[2][col][0].setCoordinates(2, col, 0);
                positionTile(tiles[2][col][0], 2, col, 0);
            }
            //Row 1
            for (int col = 3; col < 11; col++) {
                tiles[1][col][0] = dealTile();
                tiles[1][col][0].setCoordinates(1, col, 0);
                positionTile(tiles[1][col][0], 1, col, 0);
            }
            //Row 0
            for (int col = 1; col < 13; col++) {
                tiles[0][col][0] = dealTile();
                tiles[0][col][0].setCoordinates(0, col, 0);
                positionTile(tiles[0][col][0], 0, col, 0);
            }

        }


        //Function to draw tiles based on their coordinates
        private void positionTile(Tile t, int row, int col, int layer) {

            int x;
            int y;

            //Calculates the pixels where tile will be drawn
            x = (col * 60) + (layer * 15);
            y = (row * 60) - (layer * 15);

            //Special Scenario: Top Tile - centers tile on top
            if (row == 4 && col == 7 && layer == 4) {
                y -= 30;
                x -= 30;
            }

            //Special Scenario: Very left tile - Shift up so it's in between row 3 and 4
            if (row == 4 && col == 0)
                y -= 30;

            //Special Scenario: 2 Tiles - Shifts tiles down so it's in between row 3 and 4
            if (row == 3 && (col == 13 || col == 14))
                y += 30;

            //Shifts tiles so they aren't right on the edge, provides space for shading
            x += 10;
            y += 10;

            t.setLocation(x, y);
            t.addMouseListener(this);
            add(t);
            t.setZOrder(getComponentZOrder(t));
        }
        //Overloaded positionTile used for undo-ing a tile, directly specifies the proper zorder
        private void positionTile(Tile t, int row, int col, int layer, int zOrder) {

            int x;
            int y;

            x = (col * 60) + (layer * 15);
            y = (row * 60) - (layer * 15);

            if (row == 4 && col == 7 && layer == 4) {
                y -= 30;
                x -= 30;
            }
            if (row == 4 && col == 0) {
                y -= 30;
            }
            if (row == 3 && (col == 13 || col == 14)) {
                y += 30;
            }

            x += 10;
            y += 10;

            t.setLocation(x, y);
            t.addMouseListener(this);
            System.out.println(zOrder);
            add(t, zOrder);
            setComponentZOrder(t, t.getZOrder());
            repaint();
        }

        //region Unused EventHandlers
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
        //endregion

        //Event handler for what happens when a user clicks on a tile
        //Handles logic for determining whether or not tile needs to be removed
        @Override
        public void mousePressed(MouseEvent e) {

            Tile tile = (Tile) e.getSource();

            System.out.println(tile.toString() + " : [" + tile.x + "][" + tile.y + "][" + tile.z + "] at zOrder: " + tile.getZOrder());

            /*
            //This code by itself just removes tiles w/ a single click. Used for testing
            this.remove(tile);
            tiles[tile.x][tile.y][tile.z] = null;
            tile.removeMouseListener(this);
            */

            if (e.getButton() == MouseEvent.BUTTON1) {

                if (isTileOpen(tile)) {
                    // No previously selected tile - select it
                    if (selected == null) {
                        selected = tile;
                        selected.highlight = true;
                        selected.repaint();
                    }
                    //Doesn't match with another tile or it matched with itself, deselect our selected
                    else if (!(selected.matches(tile)) || (selected.x == tile.x && selected.y == tile.y && selected.z == tile.z)) {
                        selected.highlight = false;
                        selected.repaint();
                        selected = null;
                    }
                    //Matches with another tile that is not itself - Delete both of them
                    else if (selected.matches(tile) && !(selected.x == tile.x && selected.y == tile.y && selected.z == tile.z)) {

                        //Removed the highlight, and removes the action listeners
                        selected.highlight = false;
                        tile.highlight = false;
                        selected.removeMouseListener(this);
                        tile.removeMouseListener(this);

                        //Adds the tiles to the bottom pane
                        removedTilesPane.addToUndo(selected, tile);

                        //Sets the locations of those tiles to null - no tiles exist in that location
                        tiles[selected.x][selected.y][selected.z] = null;
                        tiles[tile.x][tile.y][tile.z] = null;

                        //Removes the tiles from the board
                        this.remove(selected);
                        this.remove(tile);

                        //No tile is selected anymore
                        selected = null;

                        //If there are 144 tiles in the bottom pane, it means the board is empty and the user won
                        if (removedTilesPane.undoStack.size() != 144) {
                            if (soundOn)
                                stoneSound.play();
                        } else {
                            revalidate();
                            repaint();
                            startReward();
                        }

                    }

                    //Really gross way of highlighting all potential matches so it's easy to find tiles
                    for (int row = 0; row < xSize; row++) {
                        for (int col = 0; col < ySize; col++) {
                            for (int layer = 0; layer < layerSize; layer++) {
                                if (tiles[row][col][layer] != null) {
                                    if (selected != null) {
                                        if (tiles[row][col][layer].matches(selected))
                                            tiles[row][col][layer].highlight = true;
                                        else
                                            tiles[row][col][layer].highlight = false;
                                    } else {
                                        tiles[row][col][layer].highlight = false;
                                    }
                                }
                            }
                        }
                    }

                    revalidate();
                    repaint();
                }

            }
        }

        //Determines whether a tile is considered "Open" or not
        //An open tile does not have a tile on top and it's left/right side
        private boolean isTileOpen(Tile t) {
            //Covers our 4 special tiles:
            //Very left, Top, and the two very right tiles
            if (t.z == 4 || t.y == 0 || t.y == 14)
                return true;
            if (t.z == 3 && (tiles[4][7][4] != null))
                return false;
            if (t.x == 3 && t.y == 1 && t.z == 0 && (tiles[4][0][0] != null))
                return false;
            if (t.x == 4 && t.y == 12 && t.z == 0 && (tiles[3][13][0] != null))
                return false;

            //Tile a layer above needs to be null, then at least one on its left or right needs to be null
            return ((tiles[t.x][t.y][t.z + 1] == null) && ((tiles[t.x][t.y - 1][t.z] == null) || (tiles[t.x][t.y + 1][t.z] == null)));
        }


        //Paints the background color and image
        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;

            Color boardColorWhiter = new Color(109, 95, 77);
            g2d.setColor(boardColorWhiter);

            int[] x = {0, 0, 960, 960};
            int[] y = {0, 530, 530, 0};
            Polygon background = new Polygon(x, y, 4);
            g2d.fill(background);

            URL url = Tile.class.getResource("images/dragon_background.png");
            backgroundImage = Toolkit.getDefaultToolkit().getImage(url);
            g.drawImage(backgroundImage, 250, 40, this);
        }

    }
    //endregion

    //region Menu - Creation
    //Factory methods to create menus and menuitems
    private JMenu makeMenu(String label, char mnemonic) {
        JMenu menu = new JMenu(label);
        menu.setMnemonic(mnemonic);
        return menu;
    }

    private JMenuItem makeMenuItem(String label, String tip, String accelerator, char mnemonic, String method, Object target) {
        JMenuItem menuItem = new JMenuItem(label, mnemonic);
        menuItem.setToolTipText(tip);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(accelerator)); //adds tool tip text
        // sets up event handling: "method" is called when"menuItem" is selected
        menuItem.addActionListener((ActionListener) EventHandler.create(ActionListener.class, target, method));
        return menuItem;
    }

    //Creates
    // the menu
    private void createMenu() {
        JMenuBar menu = new JMenuBar();
        setJMenuBar(menu);

        //Creates the Menu and adds the necessary menu items to that menu

        //---------------------------- Game Tab ----------------------------
        JMenu gameMenu = makeMenu("Game", 'G');
        menu.add(gameMenu);

        gameMenu.add(makeMenuItem("Play", "Begins a New Game", "ctrl P", 'P', "play", this));
        gameMenu.add(makeMenuItem("Restart", "Restarts the Current Game", "ctrl R", 'R', "restart", this));
        gameMenu.add(makeMenuItem("Numbered         ", "Play a Specific Game", "ctrl N", 'N', "numbered", this));

        //---------------------------- Sound Tab ----------------------------
        JMenu soundMenu = makeMenu("Sound", 'S');
        menu.add(soundMenu);

        soundMenu.add(makeMenuItem("Enable - On", "Enable Sound", "ctrl E", 'E', "enableSound", this));
        soundMenu.add(makeMenuItem("Disable - Off    ", "Disable Sound", "ctrl D", 'D', "disableSound", this));

        //---------------------------- Move Tab ----------------------------
        JMenu moveMenu = makeMenu("Move", 'M');
        menu.add(moveMenu);

        moveMenu.add(makeMenuItem("Undo    ", "Undo Last Move", "ctrl Z", 'U', "undo", this));
        //moveMenu.add(makeMenuItem("Redo", "Redo", "ctrl Y", 'R', "redo", this));

        //---------------------------- Help Tab ----------------------------
        JMenu helpMenu = makeMenu("Help", 'H');
        menu.add(helpMenu);

        helpMenu.add(makeMenuItem("Operation", "How to use Mah Jong Application", "ctrl O", 'O', "helpOperation", this));
        helpMenu.add(makeMenuItem("Game Rules    ", "How to play Mah Jong", "ctrl G", 'G', "gameRules", this));

        //---------------------------- About Tab ----------------------------
        menu.add(makeMenuItem("About", "Version Information", "", 'A', "about", this));
    }
    //endregion

    //region Menu - Functionality
    //---------------------------- Menu Functionality ----------------------------
    //Game Menu - Play/Restart/Numbered
    public void play() {
        //Brand New Game - Random
        if (JOptionPane.showConfirmDialog(this, "Begin a new game? (This will remove any existing progress)", "Mah Jong - Play New Game", JOptionPane.YES_NO_CANCEL_OPTION) == JOptionPane.YES_OPTION) {
            Random rand = new Random();
            int randNum = rand.nextInt(1000) + 1;
            getContentPane().removeAll();
            setVisible(false);

            new MahJongModel(randNum);
        }
    }

    public void restart() {
        //Restarts the current game
        if (JOptionPane.showConfirmDialog(this, "Restart Game? (This will remove existing progress)", "Mah Jong - Restart Game", JOptionPane.YES_NO_CANCEL_OPTION) == JOptionPane.YES_OPTION) {
            getContentPane().removeAll();
            setVisible(false);
            new MahJongModel(gameNumber);
        }
    }

    public void numbered() {
        //Brand New Game - Selected
        String input = JOptionPane.showInputDialog("(This will remove existing progress)\nEnter in a Game Number: ");
        if (input != null) {
            int gameNum;
            try {
                gameNum = Integer.parseInt(input);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "-- Invalid Game Number -- \nChoosing a Game for You... ");
                Random rand = new Random();
                gameNum = rand.nextInt(1000) + 1;
            }
            getContentPane().removeAll();
            setVisible(false);

            new MahJongModel(gameNum);
        }
    }

    public void enableSound() {
        soundOn = true;
        JOptionPane.showMessageDialog(this, "Sound Enabled", "Sound", JOptionPane.PLAIN_MESSAGE);
    }

    public void disableSound() {
        soundOn = false;
        JOptionPane.showMessageDialog(this, "Sound Disabled", "Sound", JOptionPane.PLAIN_MESSAGE);
    }

    public void undo() {

        if (removedTilesPane.undoStack.empty()) {
            JOptionPane.showMessageDialog(this, "Nothing to undo...");
            return;
        }

        //Undo Function:
        //It does undo the action, and the zOrders are correct, but the tiles incorrectly
        // overlap sometimes with other drawn tiles
        //Also when you are in the game for a bit and have a lot of removed tiles, when attempting
        //to add it back to the original zOrder, it says "illegal component position"

        Tile t1 = removedTilesPane.undoStack.pop();
        Tile t2 = removedTilesPane.undoStack.pop();

        //Add the tiles back to our tiles datastructure
        board.tiles[t1.x][t1.y][t1.z] = t1;
        board.tiles[t2.x][t2.y][t2.z] = t2;

        //Add the tiles back to the board and draw them w/ an overloaded positionTile
        //which allows specification of the zOrder
        board.positionTile(t1, t1.x, t1.y, t1.z, t1.getZOrder());
        board.positionTile(t2, t2.x, t2.y, t2.z, t2.getZOrder());


    }

    //Help Menu - Operation Action
    public void helpOperation() {
        JOptionPane.showMessageDialog(this,
                "Mah Jong Menu Operations: \n\n" +
                        "----------------------- Game ------------------------ \n" +
                        "\t\t~ Play            -- Starts a new game\n" +
                        "\t\t~ Restart      -- Restarts current game\n" +
                        "\t\t~ Numbered -- Play a specific game\n\n" +
                        "----------------------- Sound -----------------------\n" +
                        "\t\t~ On  -- Enables audio\n" +
                        "\t\t~ Off -- Disables audio\n\n" +
                        "----------------------- Move -------------------------\n" +
                        "\t\t~ Undo -- Restores the last tile\n\n" +
                        "----------------------- Help --------------------------\n" +
                        "\t\t~ Operation       -- Show Menu Options\n" +
                        "\t\t~ Games Rules -- How to Play\n\n",
                "Mah Jong Operations", JOptionPane.PLAIN_MESSAGE);
    }

    //Help Menu - Game Rules Action
    public void gameRules() {
        JOptionPane.showMessageDialog(this,
                "----------------------------------  Mah Jong  ----------------------------------\n" +
                        "'The objective of Mah Jong is to match tiles into pairs, \n" +
                        "and gradually remove tiles from the board – each time \n" +
                        "a pair is formed, those tiles can be removed – but tiles \n" +
                        "can only be removed when each tile of the pair has a blank \n" +
                        "space to the left or right. This makes it more challenging \n" +
                        "for the player – the objective of mahjong solitaire is not \n" +
                        "only to find matching pairs of tiles as quickly as possible, \n" +
                        "but also to strategically remove tiles in a way that will \n" +
                        "“free up” the most tiles, without blocking future options \n" +
                        "to match pairs.'\n-- Mahjong.com\n",
                "How to Play MahJong", JOptionPane.PLAIN_MESSAGE);
    }

    public void about() {
        JOptionPane.showMessageDialog(this,
                "--------------------- Mah Jong ---------------------\n" +
                        "                      Lawrence Magaña \n" +
                        "            CS3230 - Object Oriented Java \n" +
                        "                              Version 1.0\n",
                "About", JOptionPane.PLAIN_MESSAGE);
    }
    //endregion

    //region BottomPane
    //Creates the bottom pane where removed tiles go
    private void createBottomPane() {
        this.setLayout(new BorderLayout());
        this.add(removedTilesPane, BorderLayout.SOUTH);
    }
    //endregion

    //region Firework Animations
    private void startReward() {
        JFrame frame = new JFrame();
        Fireworks fw = new Fireworks();

        frame.setSize(960, 678);
        frame.add(fw.getPanel());
        frame.setVisible(true);

        if (soundOn)
            fw.setSound(true);
        else
            fw.setSound(false);

        fw.setExplosions(0, 1000);
        fw.fire();

        try {
            Thread.sleep(10000);
            fw.stop();
            frame.setVisible(false);
        } catch (InterruptedException ie) { }
    }
    //endregion

    public static void main(String[] args) {
        //Automatically randomizes the game for you from the beginning.
        Random begin = new Random();
        int n = begin.nextInt(1000) + 1;
        new MahJongModel(n);
    }

}
