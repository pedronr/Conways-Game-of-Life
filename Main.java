import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JFrame;

public class Main{
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
        DrawHexagons p = new DrawHexagons();
        f.setContentPane(p);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
       // Point origin = new Point(p.getWidth(),p.getHeight());
       // Hexagon[][] grid = p.makeHexGrid(origin, 10, 40, 0);
        //grid[5][5].toggle();
        f.setVisible(true);
        Point origin = new Point(p.getWidth(),p.getHeight());
        Buttons[][] grid = p.getHexGrid();
        
        for(int i = 0; i < grid.length; i++) {
        	for(int j = 0; j < grid[0].length; j++) {
        		//System.out.print("("+grid[i][j].getCenter().getX()+", "+
        	//grid[i][j].getCenter().getY()+")");
        	}
        	System.out.println();
        }
        //grid[14][0].toggle();
        //grid[0][7].toggle();
        //grid[0][6].toggle();
        grid[7][4].toggle();
        grid[7][5].toggle();
        grid[6][5].toggle();
        int i = 7;
        int j = 4;
        
      //  grid[i][j-1].toggle();
     //   grid[i][j+1].toggle();
      //  grid[i-1][j].toggle();
     //   grid[i+1][j].toggle();
     //   grid[i-1][j-1].toggle();
     //   grid[i+1][j-1].toggle();
    //    grid[i][j].toggle();
        for(int k = 0; k < 1000; k++) {
        	iterate(p);
        }
       // grid[7][3].toggle();
       // grid[14][1].toggle();
       // grid[14][7].toggle();
        //grid[6][14].toggle();
        //grid[5][6].toggle();
	}

	static void iterate(DrawHexagons p) {
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
	}
}
