import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.*;

public class Looper implements Runnable {
	
	/* The purpose of this class is to use multi-threaded programming to be able to create the loop for the animation between pressing "start" and "end".
	 * This class largely comes from the Internet as I do not know about volatile variables and multi-threaded programming.
	 */
    private AtomicBoolean keepRunning;
    private Buttons[][] grid;
    private JFrame f;
    RealDrawing iterator = new RealDrawing();
    
    //Default, no-argument constructor.
    public Looper() {
        keepRunning = new AtomicBoolean(true);
    }
    
    //This constructor is the one that is used in practice as it is the one that takes in the actual values for the grid and the JFrame.
    public Looper(Buttons[][] grid1, JFrame f1) {
    	grid = grid1;
    	f = f1;
    	keepRunning = new AtomicBoolean(true);
    }
    
    public void start() {
    	keepRunning.set(true);
    }

    public void stop() {
        keepRunning.set(false);
    }

    @Override
    public void run() {
        while (keepRunning.get()) {
            RealDrawing.iterate(grid, f);
        }
    }

}
