import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class RealDrawing extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int size = 50;
    private static int radius = 10;
	private Buttons[][] hexGrid = new Buttons[size][size]; //FIRST ENTRY X POS, SECOND ENTRY Y POS
	private static Looper looper;
	private static int speed;
	
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
//		super.paintComponent(g);
		Hexagon hexago = null;
		Graphics2D g2 = (Graphics2D) g;
		for (int i = 0; i < size; i++){
			for (int j = 0; j < size; j++) {
				if (hexGrid[i][j] != null) {
					hexago = hexGrid[i][j].getHex();
					g2.setColor(Color.black);
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

    	//System.out.println("Added to grid[" + i + "][" + j + "]");
	}
	
	public static void main(String[] args) {
		
		Buttons[][] grid = new Buttons[size][size];
		double ang30 = Math.toRadians(30);
        double xOff = Math.cos(ang30) * (radius + 2);
        double yOff = Math.sin(ang30) * (radius + 2 );
        int half = size / 2;
		
        JFrame f = new JFrame();
        JPanel p = new JPanel();
        f.setContentPane(p);
        f.setLayout(null);
        f.setSize(1500,1500);   
        p.setLayout(null);
        p.setSize(500,500);
        p.setBackground(new Color(0x006606));
         f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Point origin = new Point((int)f.getSize().getHeight()/2,(int)f.getSize().getWidth()/3);
        int maxY = 0;
        for (int row = 0; row < size; row++) {
            int cols = size - java.lang.Math.abs(row - half);
            for (int col = 0; col < cols; col++) {
                int xLbl = row < half ? col - row : col - half;
                int yLbl = row - half;
                int x = (int) (origin.x + xOff * (col * 2 + 1 - cols));
                int y = (int) (origin.y + yOff * (row - half) * 3);
                grid[xLbl+half][yLbl+half] = new Buttons(x,y,radius);
                if (y > maxY) {
                	maxY = y;
                }
                addListener(grid[xLbl+half][yLbl+half],f);
                f.add(grid[xLbl+half][yLbl+half]);
                //System.out.println((xLbl+half)+","+(yLbl+half));
            }
        }
        
        
          JButton nextButton = new JButton();
          nextButton.setBounds((int)origin.getX() - 70, maxY + 30, 40, 20);
          nextButton.setText("Next");
          
          JButton startButton = new JButton();
          startButton.setBounds((int)origin.getX() - 20, maxY + 30, 40, 20);
          startButton.setText("Start");
          
          JButton endButton = new JButton();
          endButton.setBounds((int)origin.getX() + 30, maxY + 30, 40, 20);
          endButton.setText("End");
          
          JSlider speedSlider = new JSlider(JSlider.HORIZONTAL, 1, 10, 1);
          
          speedSlider.setMajorTickSpacing(10);
          speedSlider.setMinorTickSpacing(1);
          speedSlider.setLabelTable(speedSlider.createStandardLabels(1));
          speedSlider.setPaintTicks(true);
          speedSlider.setPaintLabels(true);
          
          speedSlider.addChangeListener(new ChangeListener() { 
        	  @Override
        	    public void stateChanged(ChangeEvent e) {
        	        JSlider source = (JSlider)e.getSource();
        	        if (!source.getValueIsAdjusting()) {
        	            speed = (int)source.getValue();
        	        }
        	    }
        	  
          });
          
          speedSlider.setBounds(1100, 60, 300,40);
          
          JLabel sliderLabel = new JLabel("Speed");
          sliderLabel.setBounds(1230,30,100,30);
          
          JButton bigHexButton = new JButton();
          bigHexButton.setBounds(100, 100, 150, 30);
          bigHexButton.setText("Big Hexagon");
          
          JButton clearButton = new JButton();
          clearButton.setBounds(100,50,150,30);
          clearButton.setText("Clear");
          
          f.add(nextButton);
          f.add(startButton);
          f.add(endButton);
          f.add(speedSlider);
          f.add(sliderLabel);
          f.add(bigHexButton);
          f.add(clearButton);
          
          f.setLocationRelativeTo(null);
          f.setVisible(true);
          
          nextButton.addMouseListener(new MouseListener() {
			@Override
    		public void mouseClicked(MouseEvent e) {
    			if (nextButton.contains(e.getPoint())) {
    		        iterateNoDelay(grid,f);
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
          
          startButton.addMouseListener(new MouseListener() {
  			@Override
      		public void mouseClicked(MouseEvent e) {
      			if (startButton.contains(e.getPoint())) {
      				if (looper == null) {
      					looper = new Looper(grid, f);
      					Thread t = new Thread(looper);
      					t.start();
      				}
      		  //     going.set(true);
      		  //     iteration(grid, f);
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
          
          endButton.addMouseListener(new MouseListener() {
    			@Override
        		public void mouseClicked(MouseEvent e) {
        			if (startButton.contains(e.getPoint())) {
        		        looper.stop();
        		        looper = null;
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
          bigHexButton.addMouseListener(new MouseListener() {
    			@Override
        		public void mouseClicked(MouseEvent e) {
        			if (bigHexButton.contains(e.getPoint())) {
        		        //color in big hexagon
        				for(int k = 0; k < 11; k++) {
        		        	grid[3+2*k][24-2*k].toggle();
        		        }
        				for(int k = 0; k < 10; k++) {
        		        	grid[3][26+2*k].toggle();
        		        }
        				for(int k = 0; k < 12; k++) {
        		        	grid[3+2*k][46].toggle();
        		        }
        				for(int k = 0; k < 10; k++) {
        		        	grid[27+2*k][44-2*k].toggle();
        		        }
        				for(int k = 0; k < 11; k++) {
        					grid[47][24-2*k].toggle();
        				}
        				for(int k = 0; k < 12; k++) {
        					grid[25+2*k][2].toggle();
        				}
        				//grid[5][24].toggle();
        				//grid[3][24].toggle();
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
            clearButton.addMouseListener(new MouseListener() {
    			@Override
        		public void mouseClicked(MouseEvent e) {
        			if (clearButton.contains(e.getPoint())) {
        		        for(int m = 0; m < size; m++) {
        		        	for(int n = 0; n < size; n++) {
        		        		if(grid[m][n]!=null&&grid[m][n].isOn()) {
        		        			grid[m][n].toggle();
        		        		}
        		        	}
        		        }
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
	}
	
	public static void iterateNoDelay(Buttons[][] hexGrid, JFrame f) {
		int size = hexGrid.length;
		boolean[][] newTurnedOn = new boolean[size][size];
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
	            if(hexGrid[i][j] == null) {
	            	//System.out.println("radius 0");
	            }
	            else if(i==0||i==(size-1)||j==0||j==(size-1)||i+j==size/2||i+j==size+size/2) {
	            	//this checks borders
	            	//System.out.println("border");
	            }
	            else {
	            	//System.out.println("hello");
	            	int count = 0;
	            	if(hexGrid[i][j-1] != null && hexGrid[i][j-1].isOn()) {
	            		count++;
	            	}
	            	if(hexGrid[i][j+1] != null && hexGrid[i][j+1].isOn()) {
	            		count++;
	            	}
	            	if(hexGrid[i-1][j] != null && hexGrid[i-1][j].isOn()) {
	            		count++;
		            }
	            	if(hexGrid[i+1][j] != null && hexGrid[i+1][j].isOn()) {
	            		count++;
		            }
	            	if(hexGrid[i-1][j+1] != null && hexGrid[i-1][j+1].isOn()) {
	            		count++;
	            	}
	            	if(hexGrid[i+1][j-1] != null && hexGrid[i+1][j-1].isOn()) {
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
				if(hexGrid[i][j] != null && hexGrid[i][j].getRadius()!=0) {
					if(hexGrid[i][j].isOn()!=newTurnedOn[i][j]) {
	            		hexGrid[i][j].toggle();
	            		//System.out.println("Hexagon at "+i+", "+j+" has been toggled");
	            	}
				}
			}
		}
		f.repaint();
	}

	public static void iterate(Buttons[][] hexGrid, JFrame f) {
		
		int size = hexGrid.length;
		boolean[][] newTurnedOn = new boolean[size][size];
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
	            if(hexGrid[i][j] == null) {
	            	//System.out.println("radius 0");
	            }
	            else if(i==0||i==(size-1)||j==0||j==(size-1)||i+j==size/2||i+j==size+size/2) {
	            	//this checks borders
	            	//System.out.println("border");
	            }
	            else {
	            	//System.out.println("hello");
	            	int count = 0;
	            	if(hexGrid[i][j-1] != null && hexGrid[i][j-1].isOn()) {
	            		count++;
	            	}
	            	if(hexGrid[i][j+1] != null && hexGrid[i][j+1].isOn()) {
	            		count++;
	            	}
	            	if(hexGrid[i-1][j] != null && hexGrid[i-1][j].isOn()) {
	            		count++;
		            }
	            	if(hexGrid[i+1][j] != null && hexGrid[i+1][j].isOn()) {
	            		count++;
		            }
	            	if(hexGrid[i-1][j+1] != null && hexGrid[i-1][j+1].isOn()) {
	            		count++;
	            	}
	            	if(hexGrid[i+1][j-1] != null && hexGrid[i+1][j-1].isOn()) {
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
				if(hexGrid[i][j] != null && hexGrid[i][j].getRadius()!=0) {
					if(hexGrid[i][j].isOn()!=newTurnedOn[i][j]) {
	            		hexGrid[i][j].toggle();
	            		//System.out.println("Hexagon at "+i+", "+j+" has been toggled");
	            	}
				}
			}
		}
		f.repaint();
		try{
			Thread.sleep(100*(11 - speed));
			//System.out.println("sleep");
			}catch(InterruptedException ex) {
				System.out.println("no");
			}
	}
}
