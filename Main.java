import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;

import javax.swing.JFrame;

public class Main{
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
        DrawHexagons p = new DrawHexagons();
        f.setContentPane(p);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        
        Hexagon[][] grid = p.HexGrid();
        for(int i = 0; i < grid.length; i++) {
        	for(int j = 0; j < grid[0].length; j++) {
        		System.out.println("("+p.HexGrid()[i][j].getCenter().getX()+", "+
        	p.HexGrid()[i][j].getCenter().getY()+")");
        	}
        }
        //grid[5][0].changeColor((Graphics2D)f.getGraphics(), 0);
	}

	static void iterate(DrawHexagons p) {
		int size = p.Size();
		int half = size / 2;
		boolean[][] newTurnedOn = new boolean[size][size];
		Hexagon[][] hexGrid = p.HexGrid();
		for(int row = 0; row < 9; row++) {
			int cols = size - java.lang.Math.abs(row - half);
			for(int col = 0; col < cols; col++) {
				int x = row < half ? col - row : col - half; //x val in array = col
	            int y = row - half; //y val in array = row
	            if(hexGrid[x][y] == null) {
	            	break;
	            }
	            else if(x==0||x==9||y==0||y==9||x==5-y||x+y==14) {
	            	//this checks borders
	            }
	            else {
	            	int count = 0;
	            	if(hexGrid[x][y-1].isOn()) {
	            		count++;
	            	}
	            	if(hexGrid[x][y+11].isOn()) {
	            		count++;
	            	}
	            	if(hexGrid[x-1][y].isOn()) {
	            		count++;
		            }
	            	if(hexGrid[x+1][y].isOn()) {
	            		count++;
		            }
	            	if(hexGrid[x+1][y-1].isOn()) {
	            		count++;
	            	}
	            	if(hexGrid[x-1][y+1].isOn()) {
	            		count++;
	            	}
	            	//rules implemented below
	            	if(hexGrid[x][y].isOn()) {
	            		if(!(count==2)&&!(count==3)) {
	            			newTurnedOn[x][y]=false;
	            		}
	            	}
	            	else if(!hexGrid[x][y].isOn()) {
	            		if(count==2||count==3)  {
	            			newTurnedOn[x][y]=true;
	            		}
	            	}
	            }
			}
		}
		//copy newTurnedOn to hexGrid
		for(int row = 0; row < 9; row++) {
			int cols = size - java.lang.Math.abs(row - half);
			for(int col = 0; col < cols; col++) {
				int x = row < half ? col - row : col - half; //x val in array = col
	            int y = row - half; //y val in array = row
	            if(hexGrid[x][y].isOn()!=newTurnedOn[x][y]) {
	            	hexGrid[x][y].toggle();
	            }
			}
		}
		//color hexagons here:
		//
	}
}
