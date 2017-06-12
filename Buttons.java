import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.*;

public class Buttons extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Hexagon hex;
	private Color thisColor = Color.white;
	private Point center;
	private int radius;
	
	//There are four constructors -- a no-argument constructor that uses default values, one that takes in a point for the center and a radius,
	//one that takes in an x and y value for the center and a radius, and one that takes in a Hexagon type object.
	public Buttons() {
		setOpaque(true);
		radius = 40;
		center = new Point(radius,radius);
		hex = new Hexagon(center,radius);
		setPreferredSize(new Dimension(100, 100));
		this.setBounds((int)center.getX() - radius,(int)center.getY()-radius,2*radius,2*radius);
	
	}
	
	public Buttons(Point p, int r) {
		setOpaque(true);
		center = new Point(r,r);
		radius = r;
		this.setBounds((int)p.getX()-r,(int)p.getY()-r,2*r,2*r);
		hex = new Hexagon(center,radius);
	
	}
	
	public Buttons(int x, int y, int r) {
		setOpaque(true);
		center = new Point(r,r);
		radius = r;
		hex = new Hexagon(center,radius);
		setPreferredSize(new Dimension(100, 100));
		this.setBounds(x-r,y-r,2*r,2*r);
	
	}
	
	public Buttons (Hexagon hexag) {
		setOpaque(true);
		hex = hexag;
		radius = hexag.getRadius();
		center = new Point (radius, radius);
		this.setBounds((int)hexag.getCenter().getX()-radius, (int)hexag.getCenter().getY()-radius,2*radius,2*radius);
	}
	
	//Checks if a Buttons object is on
	public Boolean isOn() {
		return this.thisColor.equals(Color.black);
	}
	
	//This method is not used.
	public void draw(Graphics2D g, int x, int y, int lineThickness, int colorValue, boolean filled) {
        // Store before changing.
        Stroke tmpS = g.getStroke();
        Color tmpC = g.getColor();

        if (filled)
            g.fillPolygon(hex.xpoints, hex.ypoints, hex.npoints);
        else
            g.drawPolygon(hex.xpoints, hex.ypoints, hex.npoints);

        // Set values to previous when done.
        g.setColor(tmpC);
        g.setStroke(tmpS);
    }
	
	//Many functions for setting and getting private instance variables
	
	public Hexagon getHex() {
		return hex;
	}
	
	public void setRadius(int r) {
		radius = r;
	}
	
	public int getRadius() {
		return radius;
	}
	
	public Point getCenter() {
		return center;
	}
	
	public void setCenter(Point p) {
		center = p;
	}
	
	public void setCenter(int x, int y) {
		center = new Point(x,y);
	}
	
	public void setColoring(Color choice) {
		thisColor = choice;
	}
	
	
	//toggles color of Buttons object
	public void toggle() {
		if (isOn()) {
			this.setColoring(Color.white);
		}
		else
			this.setColoring(Color.black);
	}
	
	public Color getColoring() {
		return thisColor;
	}
	
	public String getColorString() {
		return thisColor.toString();
	}
	
	// paintComponent draws a hexagon instead of a square when the Buttons object is added.
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(thisColor);
		g2.fill(hex);
	}
	
	
	//not used
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setLayout(null);
        f.setSize(500,500);
        f.setBackground(Color.blue);
        f.setTitle("A hexagonal button");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Buttons newbutton = new Buttons(120,120,50);
        Buttons newbutton1 = new Buttons(50,50,50);
        f.add(newbutton);
        f.add(newbutton1);
        newbutton.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (newbutton.getHex().contains(e.getPoint())) {
					newbutton.toggle();
					System.out.println("newbutton: " + newbutton.getBounds());
					f.repaint();
				}
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
       
        newbutton1.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (newbutton1.getHex().contains(e.getPoint())) {
					newbutton1.toggle();
					System.out.println("newbutton1: " + newbutton1.getBounds());
					f.repaint();
				}
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
        f.setVisible(true);
    }
    
}
