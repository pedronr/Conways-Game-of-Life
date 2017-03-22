import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		createGrid();
	}
	
	
	static void createGrid() {
		JFrame f = new JFrame();
        DrawHexagons p = new DrawHexagons();
        f.setContentPane(p);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
	}
}
