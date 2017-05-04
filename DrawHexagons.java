import java.awt.*;
import javax.swing.*;

public class DrawHexagons extends JPanel {
    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 1500;
    public static final int HEIGHT = 800;
    private int size = 15;
    private int radius = 30;
    private Hexagon[][] hexGrid = new Hexagon[size][size]; //FIRST ENTRY X POS, SECOND ENTRY Y POS
    private Font font = new Font("Arial", Font.BOLD, 18);
    FontMetrics metrics;
    public Hexagon[][] getHexGrid() {
    	return hexGrid;
    }
    public int getWidth() {
    	return WIDTH;
    }
    public int getHeight() {
    	return HEIGHT;
    }
    public DrawHexagons() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        makeHexGrid(new Point(WIDTH/2,HEIGHT/2),size,40,0);
    }
    public int Size(){
    	return size;
    }
    public int Radius(){
    	return radius;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Point origin = new Point(WIDTH / 2, HEIGHT / 2);

        g2d.setStroke(new BasicStroke(4.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
        g2d.setFont(font);
        metrics = g.getFontMetrics();
        
        //drawCircle(g2d, origin, 400, true, true, 0x4488FF, 0);
        drawHexGridLoop(hexGrid,g2d, origin, size, radius, 0);
    }

	public void makeHexGrid(Point origin,int size, int radius, int padding){
    	double ang30 = Math.toRadians(30);
        double xOff = Math.cos(ang30) * (radius + padding);
        double yOff = Math.sin(ang30) * (radius + padding);
        int half = size / 2;

        for (int row = 0; row < size; row++) {
            int cols = size - java.lang.Math.abs(row - half);

            for (int col = 0; col < cols; col++) {
                int xLbl = row < half ? col - row : col - half;
                int yLbl = row - half;
                int x = (int) (origin.x + xOff * (col * 2 + 1 - cols));
                int y = (int) (origin.y + yOff * (row - half) * 3);
                hexGrid[xLbl+half][yLbl+half] = new Hexagon(x,y,radius);
                //System.out.println(hexGrid[xLbl+5][yLbl+5].getCenter().getX());
            }
        }
        for(int i = 0; i < size; i++) {
        	for(int j = 0; j < size; j++) {
        		if(hexGrid[i][j]==null) {
        			hexGrid[i][j]=new Hexagon(new Point(0,0),radius);
        		}
        	}
        }
        //hexGrid[5][5].toggle();
    }
    private void drawHexGridLoop(Hexagon[][] hexGrid,Graphics g, Point origin, int size, int radius, int padding) {
        double ang30 = Math.toRadians(30);
        double xOff = Math.cos(ang30) * (radius + padding);
        double yOff = Math.sin(ang30) * (radius + padding);
        int half = size / 2;

        for (int row = 0; row < size; row++) {
        	int cols = size - java.lang.Math.abs(row - half);

            for (int col = 0; col < cols; col++) {

                int yLbl = row - half;
            	int xLbl = col-half;
                
                int x = (int) (origin.x + xOff * (col * 2 + 1 - cols));
                int y = (int) (origin.y + yOff * (row - half) * 3);
                
                drawHex(hexGrid[row][col],g, yLbl, xLbl, x, y, radius);
                //System.out.println(hexGrid[xLbl+5][yLbl+5].getCenter().getX());
            }
        }
    }

    private void drawHex(Hexagon hexagon,Graphics g, int posX, int posY, int x, int y, int r) {
        Graphics2D g2d = (Graphics2D) g;
        hexagon.setCenter(x,y);
        hexagon.setRadius(r);
        int xVal = Integer.parseInt(coord(posX))+size/2;
        int yVal = Integer.parseInt(coord(posY))+size/2;
        String text = String.format("%s : %s", xVal, yVal);
        int w = metrics.stringWidth(text);
        int h = metrics.getHeight();
        //hexagon.toggle();
        if(!hexagon.isOn()) {
        	//System.out.println("A hexagon is on");
    		//change from yellow to white
    		hexagon.draw(g2d,x,y,0,0xFFFFFF,true);
    		//System.out.println("hexagon drawn ")
    	}
        else if(hexagon.isOn()) {
    		//change from white to yellow
    		hexagon.draw(g2d,x,y,0,0x0FFC00,true);
    		//System.out.println("A hexagon is on");
    	}

        hexagon.draw(g2d, x, y, 1, 0x000000, false);

        g.setColor(new Color(0x000000));
        g.drawString(text, x - w/2, y + h/2);
    }

    private String coord(int value) {
        return (value > 0 ? "+" : "") + Integer.toString(value);
    }

    public void drawCircle(Graphics2D g, Point origin, int radius,
            boolean centered, boolean filled, int colorValue, int lineThickness) {
        // Store before changing.
        Stroke tmpS = g.getStroke();
        Color tmpC = g.getColor();

        g.setColor(new Color(colorValue));
        g.setStroke(new BasicStroke(lineThickness, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND));

        int diameter = radius * 2;
        int x2 = centered ? origin.x - radius : origin.x;
        int y2 = centered ? origin.y - radius : origin.y;

        if (filled)
            g.fillOval(x2, y2, diameter, diameter);
        else
            g.drawOval(x2, y2, diameter, diameter);

        // Set values to previous when done.
        g.setColor(tmpC);
        g.setStroke(tmpS);
    }
    

	public static void main(String[] args) {
        JFrame f = new JFrame();
        DrawHexagons p = new DrawHexagons();
        
        f.setContentPane(p);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        Point origin = new Point(WIDTH / 2, HEIGHT / 2);
        p.makeHexGrid(origin, p.Size(), 40, 0);
        Hexagon[][] grid = p.getHexGrid();
        //System.out.println(p.HexGrid().length);
        //System.out.println(p.HexGrid()[0].length);
        for(int i = 0; i < grid.length; i++) {
        	for(int j = 0; j < grid[0].length; j++) {
        		System.out.println("("+grid[i][j].getCenter().getX()+", "+
        	grid[i][j].getCenter().getY()+")");
        		
        	}
        }
    }
}
