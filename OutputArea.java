import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class OutputArea extends JTextPane {
    
    public void appendColoredText(String text) {
        StyledDocument document = getStyledDocument();
        Style style = addStyle("", null);       
        
        for (int i = 0; i < text.length(); i++) {            
            if (text.charAt(i) == '^' && (text.charAt(i+1) >= '0' && text.charAt(i+1) <= '9')) {                
                int color = Integer.parseInt(text.charAt(i+1)+"");                    
                switch (color) {
                    case 0:
                        StyleConstants.setForeground(style, Color.BLACK);
                        break;
                    case 1:
                        StyleConstants.setForeground(style, Color.RED);
                        break;
                    case 2:
                        StyleConstants.setForeground(style, Color.GREEN);
                        break;
                    case 3:
                        StyleConstants.setForeground(style, Color.YELLOW);
                        break;
                    case 4:
                        StyleConstants.setForeground(style, Color.BLUE);
                        break;
                    case 5:
                        StyleConstants.setForeground(style, Color.CYAN);
                        break;
                    case 6:
                        StyleConstants.setForeground(style, Color.MAGENTA);
                        break;
                    case 7:
                        StyleConstants.setForeground(style, Color.WHITE);
                        break;
                    case 8:
                        StyleConstants.setForeground(style, Color.ORANGE);
                        break;
                    case 9:
                        StyleConstants.setForeground(style, Color.GRAY);
                        break;
                }
            } else if (text.charAt(i) >= '0' && text.charAt(i) <= '9') {

            } else {
                try {                
                    document.insertString(document.getLength(),text.charAt(i)+"", style);
                } catch (BadLocationException ble) {                
                    ble.printStackTrace();
                }
            }
             
            
        }
        // New line
        try {
            document.insertString(document.getLength(), "\n", style);
        } catch (BadLocationException ble) {            
            ble.printStackTrace();
        }
    }
}
