import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;

public class DrawHexagons extends JPanel {
    private static final long serialVersionUID = 1L;
    private final int WIDTH = 1500;
    private final int HEIGHT = 800;
    private int size = 10;
    private int radius = 40;
    public Hexagon[][] hexGrid = new Hexagon[size][size];
    private Font font = new Font("Arial", Font.BOLD, 18);
    FontMetrics metrics;
    
    public DrawHexagons() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }
    public int Size(){
    	return size;
    }
    public int Radius(){
    	return radius;
    }
    public Hexagon[][] HexGrid(){
    	return hexGrid;
    }
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Point origin = new Point(WIDTH / 2, HEIGHT / 2);

        g2d.setStroke(new BasicStroke(4.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
        g2d.setFont(font);
        metrics = g.getFontMetrics();

        //drawCircle(g2d, origin, 400, true, true, 0x4488FF, 0);
        drawHexGridLoop(g2d, origin, size, 40, 0);
    }
    
    private void drawHexGridLoop(Graphics g, Point origin, int size, int radius, int padding) {
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

                drawHex(g, xLbl, yLbl, x, y, radius);
                hexGrid[xLbl+5][yLbl+5] = new Hexagon(x,y,radius);
                System.out.println(hexGrid[xLbl+5][yLbl+5].getCenter().getX());
            }
        }
    }

    private void drawHex(Graphics g, int posX, int posY, int x, int y, int r) {
        Graphics2D g2d = (Graphics2D) g;

        Hexagon hex = new Hexagon(x, y, r);
        
        int xVal = Integer.parseInt(coord(posX))+5;
        int yVal = Integer.parseInt(coord(posY))+5;
        String text = String.format("%s : %s", xVal, yVal);
        int w = metrics.stringWidth(text);
        int h = metrics.getHeight();

        hex.draw(g2d, x, y, 0, 0xFFFFFF, true);
        hex.draw(g2d, x, y, 1, 0x000000, false);

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
        //System.out.println(p.HexGrid().length);
        //System.out.println(p.HexGrid()[0].length);
        for(int i = 0; i < p.HexGrid().length; i++) {
        	for(int j = 0; j < p.HexGrid()[0].length; j++) {
        	//	System.out.println("("+p.HexGrid()[i][j].getCenter().getX()+", "+
        	//p.HexGrid()[i][j].getCenter().getY()+")");
        		
        	}
        }
    }
}
