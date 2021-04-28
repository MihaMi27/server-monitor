import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;

public class InputField extends JTextField{
    private String field_id;
    

    public InputField(String id) {
        field_id = id;        
    }
    
    public boolean validInfo() {
        boolean valid = false;
        String ip = getText();
        if (field_id.equals("ip")) {            
            Pattern p = Pattern.compile("^(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(\\.(?!$)|$)){4}$");
            Matcher m = p.matcher(ip);
            if (m.matches()) {
                valid = true;
            }

        } else if (field_id.equals("port")) {
            String port = getText();                    
            try {
                int portNum = Integer.parseInt(port);
                if (portNum >= 1 && portNum <= 65536) {
                    valid = true;
                }
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }            
        }

        return valid;
    }
    
}   
