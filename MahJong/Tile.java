import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Polygon;

public class Tile extends JPanel
{
	private static final Dimension SIZE = new Dimension(76, 76); 	//75 BY 75. Extra 1 pixel so the 75th pixel doesn't get removed

	//Colors
	//Face
	private static final Color bisque3 = new Color(238, 213, 183); 		//Bisque 3 from cloford.com
	private static final Color bisque4 = new Color(139, 125, 107); 		//Bisque 4 from cloford.com - Darker than bisque 3

	//When highlighted
	private static final Color selected = new Color(169, 120, 77);
	private static final Color selected1 = new Color(129, 76, 88);

	//Gradient
	private static final Color bisqueWhite = new Color(255, 230, 200); 	//For a nicer gradient, needed something lighter than bisque3

	//Colors used throughout for tiles - Slightly darker than the standard colors
	public static final Color GREEN = new Color(21, 155, 22);
	public static final Color RED = new Color(191, 0, 0);
	public static final Color BLUE = new Color(0, 17, 170);
	public static final Color WHITE = new Color(0xD0D0D0);

	//Shapes
	private static Polygon face;
	private static Polygon leftSide;
	private static Polygon bottomEdge;
	private static Polygon leftSideGrad;
	private static Polygon bottomEdgeGrad;

	//Gradients
	private static GradientPaint gradient1;
	private static GradientPaint selectedGradient;
	private static GradientPaint gradient2;
	private static GradientPaint gradient3;
	private static GradientPaint gradient4;
	private static GradientPaint gradient5;

	//Series of points that represent each points of my tile
	private static int[] P1 = {15, 0};
	private static int[] P2 = {75, 0};
	private static int[] P3 = {15, 60};
	private static int[] P4 = {75, 60};
	private static int[] P5 = {0, 15};
	private static int[] P6 = {0, 75};
	private static int[] P7 = {60, 75};
	private static int[] P8 = {7, 7};
	private static int[] P9 = {7, 67};
	private static int[] P10 = {67, 67};

	//Coordinates for Tile's location
	public int x;
	public int y;
	public int z;
	private int zOrder = 0;

	//Highlight tile if it's selected - or if the tile is the same as the selected tile
	public boolean highlight = false;

	public Tile() 
	{
		super();
		createTile();
		this.setPreferredSize(SIZE);
		this.setSize(SIZE);				//Lab7 Overview Video - must specify setSize when no layout is present
		setOpaque(false);				//Lab7 Overview Video - use to eliminate sketchy things occurring to tiles
	}

	public void createTile()
	{
		//Sets the points for the face of the tile (the face)
		face = new Polygon();
		face.addPoint(P1[0], P1[1]); 		//Top Left
		face.addPoint(P3[0], P3[1]); 		//Top Right
		face.addPoint(P4[0], P4[1]); 		//Bottom Left
		face.addPoint(P2[0], P2[1]);		//Bottom Right

		//Creates 3D edge on the left side of the face
		int[] LeftEdge_x = {P8[0], P9[0], P3[0], P1[0]};
		int[] LeftEdge_y = {P8[1], P9[1], P3[1], P1[1]};
		leftSide = new Polygon(LeftEdge_x, LeftEdge_y, 4);

		//Creates 3D edge on the bottom side of the face
		int[] BottomEdge_x = {P3[0] , P9[0], P10[0], P4[0]};
		int[] BottomEdge_y = {P3[1] , P9[1], P10[1], P4[1]};
		bottomEdge = new Polygon(BottomEdge_x, BottomEdge_y, 4);

		//Creates 3D edge on the left side of the face - The gradient portion
		int[] LeftEdgeGrad_x = {P5[0] , P8[0], P9[0], P6[0]};
		int[] LeftEdgeGrad_y = {P5[1] , P8[1], P9[1], P6[1]};
		leftSideGrad = new Polygon(LeftEdgeGrad_x, LeftEdgeGrad_y, 4);

		//Creates 3D edge on the bottom side of the face - The gradient portion
		int[] BottomEdgeGrad_x = {P6[0] , P9[0], P10[0], P7[0]};;
		int[] BottomEdgeGrad_y = {P6[1] , P9[1], P10[1], P7[1]};
		bottomEdgeGrad = new Polygon(BottomEdgeGrad_x, BottomEdgeGrad_y, 4);

		//Gradient for tile face
		gradient1 = new GradientPaint(P2[0], P2[1], bisque4, P3[0], P3[1], bisqueWhite);
		selectedGradient = new GradientPaint(P2[0], P2[1], selected1, P3[0], P3[1], selected);

		//Gradient for bottom 3D edge of tile
		gradient2 = new GradientPaint(P8[0], P8[1], bisque4, P3[0], P3[1], bisque3);

		//Gradient for bottom 3D edge of tile
		gradient3 = new GradientPaint(P10[0], P10[1], bisque4, P3[0], P3[1], bisque3);

		//Gradient for left green gradient
		gradient4 = new GradientPaint(6, 36, GREEN, 0, 36, Color.GREEN);

		//Gradient for bottom green gradient
		gradient5 = new GradientPaint(33, 66, GREEN, 33, 72 ,Color.GREEN);
	}

	public void setCoordinates(int x, int y, int z)
	{
		//Setter for Tile positioning
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void setZOrder(int zOrder)
	{
		//zOrder = getParent().getComponentZOrder(this);
		this.zOrder = zOrder;
	}

	public int getZOrder()
	{
		return zOrder;
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g; //Downcasting Graphics into Graphics2D

		//Paints default - Not selected
		//Highlights if the tile is selected or matches selected tile
		if (!highlight) { g2.setPaint(gradient1); }
		else { g2.setPaint(selectedGradient); }

		g2.fill(face);

		//Draws the outline to make the tile look 3D
		//Draws left edge of the tile (3D)
		g2.setPaint(gradient2);
		g2.fill(leftSide);

		//Draws bottom edge of the tile (3D)
		g2.setPaint(gradient3);
		g2.fill(bottomEdge);

		//Draws the colored gradient to the tile
		//Draws left gradient of the tile (3D)
		g2.setPaint(gradient4);
		g2.fill(leftSideGrad);

		//Draws bottom gradient of the tile (3D)
		g2.setPaint(gradient5);
		g2.fill(bottomEdgeGrad);

		//Draws the outlines around the 3D edges and the gradients
		g2.setColor(Color.DARK_GRAY);
		g2.drawLine(P1[0], P1[1], P2[0], P2[1]);  			//Top Line
		g2.drawLine(P1[0], P1[1], P3[0], P3[1]);  			//Left Line
		g2.drawLine(P2[0], P2[1], P4[0], P4[1]);  			//Right Line
		g2.drawLine(P3[0], P3[1], P4[0], P4[1]);  			//Bottom Line

		g2.drawLine(P5[0], P5[1], P6[0], P6[1]);			//Left Vertical Line
		g2.drawLine(P8[0], P8[1], P9[0], P9[1]);			//Left Vertical Line between 3d and gradient
		g2.drawLine(P5[0], P5[1], P1[0], P1[1]);			//Top Diagonal

		g2.drawLine(P6[0], P6[1], P3[0], P3[1]); 			//Bottom Diagonal
		g2.drawLine(P9[0], P9[1], P10[0], P10[1]); 			//Bottom Horizontal Line between 3D and gradient
		g2.drawLine(P6[0], P6[1], P7[0], P7[1]); 			//Bottom Horizontal Line

		g2.drawLine(P7[0], P7[1], P4[0], P4[1]); 			//Bottom Right Diagonal

	}

    public boolean matches(Tile otherObj)
	{
		return getClass() == otherObj.getClass();
    }

	public static void main(String[] args)
	{
		JFrame	frame = new JFrame();

		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Tile");

		frame.add(new Tile());

		frame.pack();
		frame.setVisible(true);
	}
	
}
