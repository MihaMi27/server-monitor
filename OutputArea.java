import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;

public class OutputArea extends JLabel {

    public OutputArea() {
        setOpaque(false);        
    }

    public void appendColoredText(String text) {
        text = text+"\n";
        String colored_text = "";
        String[] colors = {"#000000","#f31415","#0af60a","#fefe00","#0d0df8","#0ef3f4","#fd02fe","#ffffff","#fd7d00", "#8a8e93"};
        int prevCaret = text.length()-1;
        for (int i = text.length()-1; i >= 0; i--) {
            if (text.charAt(i) == '^' && (text.charAt(i+1) >= '0' && text.charAt(i+1) <= '9')) {
                int colorNum = Integer.parseInt(text.charAt(i+1)+"");
                String substring = text.substring(i+2, prevCaret);
                prevCaret = i;
                colored_text = "<span style=\"color: "+colors[colorNum]+";\">"+substring+"</span>"+colored_text;
            }
        }
        appendText(colored_text);
        
    }

    public void appendText(String text) {
        if (!getText().isEmpty()) {            
            try {
                String prevTextWithTags = getText();                
                Pattern p = Pattern.compile("<html>(.+?)</html>",Pattern.DOTALL);               
                Matcher m = p.matcher(prevTextWithTags);
                if (m.find()) {
                    String textNoTags = m.group(1);
                    setText("<html>"+textNoTags+text+"<br></html>");
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        } else {
            setText("<html>"+text+"<br></html>");
        }
                
        
    }    
    

    public void clearText() {
        setText("");
    }

    
    
}
