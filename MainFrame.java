import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.swing.*;

public class MainFrame extends JFrame {
    public static void main(String[] args) {
        new MainFrame();
    }
    
    public MainFrame() {
        // Variables
        String title = "Server Monitor";
        int width = 600;
        int height = 500;

        // Initialization        
        Container frame_cp = getContentPane();
        JButton btn_queryStatus = new JButton("Status");
        JButton btn_ue = new JButton("UE");        
        OutputArea textArea_res = new OutputArea();
        JScrollPane scr_res = new JScrollPane(textArea_res);
        JPanel panel_params = new JPanel();
        InputField field_ip = new InputField("ip"); 
        InputField field_port = new InputField("port");
        

        // Frame Properties
        setResizable(false);
        setSize(new Dimension(width,height));
        setMinimumSize(getSize());       
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle(title);

        // Element Properties
        frame_cp.setLayout(null);
        textArea_res.setEditable(false);
        textArea_res.setVisible(true);
        textArea_res.setBackground(new Color(180, 180, 180));
        textArea_res.setFont(new Font("Verdana",Font.PLAIN,12));        
        panel_params.setLayout(new BoxLayout(panel_params, BoxLayout.PAGE_AXIS));


        // Element Positioning
        btn_queryStatus.setBounds(150, 400, 300, 33);
        btn_ue.setBounds(475, 400, 100, 33);        
        scr_res.setBounds(75, 30, 450, 175);
        panel_params.setBounds(150, 266, 300, 100);

        // Listeners
        btn_queryStatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (field_ip.validInfo() && field_port.validInfo()) {
                    System.out.println("valid");
                    try {                        
                        String ip = field_ip.getText();
                        int port = Integer.parseInt(field_port.getText());
                        textArea_res.setText("");
                        String response = new String(Logic.queryStatus(ip,port).getBytes(),StandardCharsets.UTF_8);                        
                        ArrayList<String> player_info = Logic.player_info(response);
                        for(int i = 0; i < player_info.size(); i++) {
                            String player = new String(player_info.get(i).getBytes(),StandardCharsets.UTF_8);
                            //System.out.println(player);
                            textArea_res.appendColoredText(player);
                        }
                    } catch (NumberFormatException nfe) {
                        nfe.printStackTrace();
                    } 
                } 
            }            
        });

        btn_ue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                field_ip.setText("64.94.95.202");
                field_port.setText("29070");
                
            }            
        });

        

        // Adding Elements
        panel_params.add(new JLabel("Server Address:"));
        panel_params.add(field_ip);
        panel_params.add(new JLabel("Server Port:"));        
        panel_params.add(field_port);
        frame_cp.add(btn_queryStatus);
        frame_cp.add(btn_ue);        
        frame_cp.add(scr_res);
        frame_cp.add(panel_params);

        // Post
        pack();
    }

    
    
}