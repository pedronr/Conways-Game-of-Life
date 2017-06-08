import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.*;

public class Looper implements Runnable {

    private AtomicBoolean keepRunning;
    private Buttons[][] grid;
    private JFrame f;
    RealDrawing iterator = new RealDrawing();

    public Looper() {
        keepRunning = new AtomicBoolean(true);
    }
    
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
