import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JButton;

public class OrangeButton extends JButton {
    
    public OrangeButton() {
        this(null);
    }

    public OrangeButton(String text) {
        super(text);

        setForeground(Color.WHITE);        
        setMargin(new Insets(5,5,5,5));
        setFont(new Font("sans-serif",Font.BOLD,18));
        setMinimumSize(new Dimension(50,25));       
        
    }
}
