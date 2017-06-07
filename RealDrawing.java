import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;


public class RealDrawing extends JPanel{
	
	private static int size = 15;
    private static int radius = 30;
	private Buttons[][] hexGrid = new Buttons[size][size]; //FIRST ENTRY X POS, SECOND ENTRY Y POS
	private static int i;
	private static int j;
//	private static Buttons button1;
	
	public RealDrawing(){
		makeHexGrid(new Point(500,500), size, radius, 0);
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
                hexGrid[xLbl+half][yLbl+half] = new Buttons(new Hexagon(x,y,radius));
                //System.out.println(hexGrid[xLbl+5][yLbl+5].getCenter().getX());
            }
        }
     /*   for(int i = 0; i < size; i++) {
        	for(int j = 0; j < size; j++) {
        		if(hexGrid[i][j]==null) {
        			hexGrid[i][j] = new Buttons((new Hexagon(new Point(0,0),radius)));
        		}
        	}
        }*/
		
		/* 	double ang30 = Math.toRadians(30);
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
                hexGrid[row][col] = new Buttons(new Hexagon(x,y,radius));
                System.out.println("Created (" + row + "," + col + ") at (" + x + "," + y + ")");
                //System.out.println(hexGrid[xLbl+5][yLbl+5].getCenter().getX());
            }
        }
        for(int i = 0; i < size; i++) {
        	for(int j = 0; j < size; j++) {
        		if(hexGrid[i][j]==null) {
        			hexGrid[i][j] = new Buttons((new Hexagon(new Point(40,40),radius)));
        			System.out.println("There");
        		}
        	}
        }*/
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	//	super.paintComponent(g);
		Hexagon hexago = null;
		Graphics2D g2 = (Graphics2D) g;
		for (int i = 0; i < size; i++){
			for (int j = 0; j < size; j++) {
				if (hexGrid[i][j] != null) {
					hexago = hexGrid[i][j].getHex();
					g2.setColor(hexGrid[i][j].getColoring());
					g2.fill(hexago);
				}
			}
		}
		
	}
	
	public int getGridSize() {
		return size;
	}
	
	public Buttons[][] getGrid() {
		return hexGrid;
	}
	
	public static void addListener(Buttons button1, JFrame f) {
		button1.addMouseListener(new MouseListener() {
			@Override
    		public void mouseClicked(MouseEvent e) {
    			if (button1.getHex().contains(e.getPoint())) {
    				button1.toggle();
    				System.out.println("Button is clicked at " + button1.getBounds());
    				f.repaint();
    			}
    		}

   			@Override
   			public void mousePressed(MouseEvent e) {
   				// TODO Auto-generated method stub
   				
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

    	System.out.println("Added to grid[" + i + "][" + j + "]");
	}
	
	public static void main(String[] args) {
	/*	Buttons[][] grid = new Buttons[size][size];
		double ang30 = Math.toRadians(30);
        double xOff = Math.cos(ang30) * (radius + 0);
        double yOff = Math.sin(ang30) * (radius + 0);
        int half = size / 2;
        Point origin = new Point(300,300);

        for (int row = 0; row < size; row++) {
            int cols = size - java.lang.Math.abs(row - half);

            for (int col = 0; col < cols; col++) {
                int xLbl = row < half ? col - row : col - half;
                int yLbl = row - half;
                int x = (int) (origin.x + xOff * (col * 2 + 1 - cols));
                int y = (int) (origin.y + yOff * (row - half) * 3);
                grid[xLbl+half][yLbl+half] = new Buttons(new Hexagon(x,y,radius));
                //System.out.println(hexGrid[xLbl+5][yLbl+5].getCenter().getX());
            }
        }*/
		
		Buttons[][] grid = {{new Buttons(120,120,50),new Buttons(50,50,50)},{new Buttons(190,190,50),new Buttons(260,260,50)}};
		JFrame f = new JFrame();
		f.setPreferredSize(new Dimension(1500,1000));
	//	RealDrawing hexes = new RealDrawing();
		
		for (int eye = 0; eye < 2; eye++) {
			for (int jay = 0; jay < 2; jay++) {
				if(grid[eye][jay] != null) {
					f.add(grid[eye][jay]);
					System.out.println(grid[eye][jay].getBounds());
				}
			}
		}
		f.add(new Buttons(100,100,50));
		f.revalidate();
		f.repaint();
	//	f.add(new Buttons(500,500,70));
	//	f.add(hexes);
        Buttons button1 = null;
       
        for (i = 0; i < 2; i++) {
            for (j = 0; j < 2; j++) {
            	button1 = grid[i][j];
            	if (button1 != null) {
		        	addListener(button1,f);
            	}
            }
        }
        

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
       // Point origin = new Point(p.getWidth(),p.getHeight());
       // Hexagon[][] grid = p.makeHexGrid(origin, 10, 40, 0);
        //grid[5][5].toggle();
        f.setVisible(true);
    /*    for(int i = 0; i < grid.length; i++) {
        	for(int j = 0; j < grid[0].length; j++) {
        		//System.out.print("("+grid[i][j].getCenter().getX()+", "+
        	//grid[i][j].getCenter().getY()+")");
        	}
        	System.out.println();
        }*/
        //grid[14][0].toggle();
        //grid[0][7].toggle();
        //grid[0][6].toggle();
     //   grid[7][4].toggle();
    //    grid[7][5].toggle();
     //   grid[6][5].toggle();
     //   i = 7;
     //   int j = 4;
        
      //  grid[i][j-1].toggle();
     //   grid[i][j+1].toggle();
      //  grid[i-1][j].toggle();
     //   grid[i+1][j].toggle();
     //   grid[i-1][j-1].toggle();
     //   grid[i+1][j-1].toggle();
    //    grid[i][j].toggle();
       /* for(int k = 0; k < 1000; k++) {
        	iterate(p);
        }*/
       // grid[7][3].toggle();
       // grid[14][1].toggle();
       // grid[14][7].toggle();
        //grid[6][14].toggle();
        //grid[5][6].toggle();
	}

/*	static void iterate(DrawHexagons p) {
		try{
			Thread.sleep(1000);
			//System.out.println("sleep");
			}catch(InterruptedException ex) {
				System.out.println("no");
			}
		int size = p.Size();
		boolean[][] newTurnedOn = new boolean[size][size];
        Buttons[][] hexGrid = p.getHexGrid();
		for(int i = 0; i < size; i++) {

			for(int j = 0; j < size; j++) {

	            if(hexGrid[i][j].getRadius()==0) {
	            	//System.out.println("radius 0");
	            }
	            else if(i==0||i==(size-1)||j==0||j==(size-1)||i+j==size/2||i+j==size+size/2) {
	            	//this checks borders
	            	//System.out.println("border");
	            }
	            else {
	            	//System.out.println("hello");
	            	int count = 0;
	            	if(hexGrid[i][j-1].isOn()) {
	            		count++;
	            	}
	            	if(hexGrid[i][j+1].isOn()) {
	            		count++;
	            	}
	            	if(hexGrid[i-1][j].isOn()) {
	            		count++;
		            }
	            	if(hexGrid[i+1][j].isOn()) {
	            		count++;
		            }
	            	if(hexGrid[i-1][j-1].isOn()) {
	            		count++;
	            	}
	            	if(hexGrid[i+1][j+1].isOn()) {
	            		count++;
	            	}
	            	//rules implemented below
	            	//System.out.println(count);
	            	if(hexGrid[i][j].isOn()) {
	            		
	            		if(!(count==2)&&!(count==3)) {
	            			newTurnedOn[i][j]=false;
	            			//System.out.println("something been turned off");
	            		}
	            	}
	            	else if(!hexGrid[i][j].isOn()) {
	            		if(count==2)  {
	            			newTurnedOn[i][j]=true;
	            			//System.out.println(p.getHexGrid()[i][j].getCenter());
	            			//System.out.println("Hexagon "+i+","+j+" been turn on");
	            		}
	            	}
	            }
			}
		}
		//copy newTurnedOn to hexGrid
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(hexGrid[i][j].getRadius()!=0) {
					if(hexGrid[i][j].isOn()!=newTurnedOn[i][j]) {
	            		hexGrid[i][j].toggle();
	            		//System.out.println("Hexagon at "+i+", "+j+" has been toggled");
	            	}
				}
			}
		}
		
		//color hexagons here:
		//
		//for(int i = 0; i < 10; i++) {
		//	for(int j = 0; j < 10; j++) {
		//		if(hexGrid[i][j].isOn()) {
		//			System.out.println("Hexagon at "+i+", "+j+" is on");
		//		}
		//	}
		//}
		p.repaint();
	}*/
}
