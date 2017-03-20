import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GOLGraphics {
   private JFrame mainFrame;
   private JLabel headerLabel;
   private JLabel statusLabel;
   private JPanel controlPanel;
   private JLabel msglabel;

   public GOLGraphics(){
      prepareGUI();
   }
   public static void main(String[] args){
	   GOLGraphics  swingContainerDemo = new GOLGraphics();  
      swingContainerDemo.showJPanelDemo();
   }
   private void prepareGUI(){
      mainFrame = new JFrame("Java Swing Examples");
      mainFrame.setSize(1500,800);
      mainFrame.setLayout(new GridLayout(10,1000));
      
      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });    
      headerLabel = new JLabel("AAAAAAAAAAAAAAAA", JLabel.CENTER);        
      statusLabel = new JLabel("BBBBBB",JLabel.CENTER);    
      statusLabel.setSize(350,100);
      msglabel = new JLabel("Welcome to TutorialsPoint SWING Tutorial.", JLabel.CENTER);
      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());

      mainFrame.add(headerLabel);
      mainFrame.add(controlPanel);
      mainFrame.add(statusLabel);
      mainFrame.setVisible(true);  
   }
   private void showJPanelDemo(){
      headerLabel.setText("Container in action: JPanel");      
      JPanel panel = new JPanel();
      panel.setBackground(Color.magenta);
      panel.setLayout(new FlowLayout());        
      panel.add(msglabel);
      controlPanel.add(panel);        
      mainFrame.setVisible(true);      
   }   
}