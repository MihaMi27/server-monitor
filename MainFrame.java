import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MainFrame extends JFrame {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame();
            }
        });
    }
    
    public MainFrame() {
        // Variables
        String title = "Server Monitor";
        int width = 600;
        int height = 500;        

        // Initialization        
        Container cp = getContentPane();
        MonitorPanel bg = new MonitorPanel(width,height);        
        

        // Frame Properties
        setResizable(false);
        setSize(new Dimension(width,height));
        setMinimumSize(getSize());       
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle(title);
        
        // Adding
        cp.add(bg);
        
        // Set Visible
        setVisible(true);
        
    }

    
    
}