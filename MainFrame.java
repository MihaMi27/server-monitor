import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        JButton btn_servMon = new JButton("Query Server");
        JTextArea textArea_res = new JTextArea();
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
        panel_params.setLayout(new BoxLayout(panel_params, BoxLayout.PAGE_AXIS));


        // Element Positioning
        btn_servMon.setBounds(150, 400, 300, 33);
        scr_res.setBounds(75, 30, 450, 175);
        panel_params.setBounds(150, 266, 300, 100);

        // Listeners
        btn_servMon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (field_ip.validInfo() && field_port.validInfo()) {
                    System.out.println("valid");
                    try {                        
                        String ip = field_ip.getText();
                        int port = Integer.parseInt(field_port.getText());
                        String response = Logic.query(ip,port);                        
                        for(String player : Logic.player_info(response)) {
                            textArea_res.append(player+"\n");
                        }
                    } catch (NumberFormatException nfe) {
                        nfe.printStackTrace();
                    } 
                } 
            }            
        });

        // Adding Elements
        panel_params.add(new JLabel("Server Address:"));
        panel_params.add(field_ip);
        panel_params.add(new JLabel("Server Port:"));        
        panel_params.add(field_port);
        frame_cp.add(btn_servMon);
        frame_cp.add(scr_res);
        frame_cp.add(panel_params);

        // Post
        pack();
    }

    
    
}