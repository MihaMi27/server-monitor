import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.swing.*;

public class MonitorPanel extends JPanel {
    String response;

    public MonitorPanel(int width, int height) {
        // Init
        Container panel = this;
        OrangeButton btn_queryStatus = new OrangeButton("Status");                      
        JLabel label_ip = new JLabel("Server Address:");
        JLabel label_port = new JLabel("Server Port:");
        JLabel label_players = new JLabel("Players:");
        JLabel label_bots = new JLabel("Bots:");
        InputField field_ip = new InputField("ip"); 
        InputField field_port = new InputField("port");
        OutputArea area_players = new OutputArea();
        JScrollPane scroll_players = new JScrollPane(area_players);
        OutputArea area_bots = new OutputArea();
        JScrollPane scroll_bots = new JScrollPane(area_bots);

        // Element Properties        
        panel.setLayout(null);                       
        area_players.setVisible(true);        
        area_players.setFont(new Font("sans-serif",Font.PLAIN,17));        
        area_players.setHorizontalAlignment(SwingConstants.CENTER);
        area_players.setVerticalAlignment(SwingConstants.TOP);      
        area_bots.setVisible(true);        
        area_bots.setFont(new Font("sans-serif",Font.PLAIN,17));        
        area_bots.setHorizontalAlignment(SwingConstants.CENTER);    
        area_bots.setVerticalAlignment(SwingConstants.TOP);    
        scroll_players.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
        scroll_players.getHorizontalScrollBar().setPreferredSize(new Dimension(0,0));
        scroll_bots.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
        scroll_bots.getHorizontalScrollBar().setPreferredSize(new Dimension(0,0));
        btn_queryStatus.setFocusPainted(false);        
        field_ip.setText("64.94.95.202");
        field_port.setText("29070");
        
        // Coloring
        UIManager.put("Button.select", new Color(255,153,53,130));
        label_players.setForeground(Color.WHITE);
        label_bots.setForeground(Color.WHITE);
        label_ip.setForeground(Color.WHITE);
        label_port.setForeground(Color.WHITE);        
        field_ip.setForeground(Color.WHITE);
        field_port.setForeground(Color.WHITE);        
        area_players.setBorder(BorderFactory.createEmptyBorder());
        area_players.setForeground(Color.WHITE);                
        area_bots.setBorder(BorderFactory.createEmptyBorder());
        area_bots.setForeground(Color.WHITE);                
        scroll_players.setBorder(BorderFactory.createEmptyBorder());                
        scroll_players.setBackground(new Color(240,240,240,0));
        scroll_players.getViewport().setBackground(new Color(240,240,240,0));
        scroll_bots.setBorder(BorderFactory.createEmptyBorder());                
        scroll_bots.setBackground(new Color(240,240,240,0));
        scroll_bots.getViewport().setBackground(new Color(240,240,240,0));        
        btn_queryStatus.setBackground(new Color(255,133,33,100));
        
        
        // Element Positioning
        label_players.setBounds((width/2)-130,15,150,25);
        label_bots.setBounds((width/2)+80,15,150,25);
        area_players.setBounds((width/2)-170, 40, 150, 175);
        scroll_players.setBounds(area_players.getX(), area_players.getY(), area_players.getWidth(), area_players.getHeight());
        area_bots.setBounds((width/2)+20, 40, 150, 175);
        scroll_bots.setBounds(area_bots.getX(), area_bots.getY(), area_bots.getWidth(), area_bots.getHeight());               
        label_ip.setBounds(150,280,300,10);
        field_ip.setBounds(150,295,300,30);
        label_port.setBounds(150,335,300,10);
        field_port.setBounds(150,350,300,30);
        btn_queryStatus.setBounds(150, 410, 300, 35);


        // Listeners        
        btn_queryStatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                area_players.clearText();
                area_bots.clearText();
                if (field_ip.validInfo() && field_port.validInfo()) {                    
                    try {                        
                        String ip = field_ip.getText();
                        int port = Integer.parseInt(field_port.getText());                        
                        response = new String(Logic.queryStatus(ip,port).getBytes(),StandardCharsets.UTF_8);
                        ArrayList<String> player_info = Logic.player_info(response);
                        for(int i = 0; i < player_info.size(); i++) {
                            String player = new String(player_info.get(i).getBytes(),StandardCharsets.UTF_8);                            
                            area_players.appendColoredText(player);                            
                        }
                        ArrayList<String> bot_info = Logic.bot_info(response);
                        for(int i = 0; i < bot_info.size(); i++) {
                            String bot = new String(bot_info.get(i).getBytes(),StandardCharsets.UTF_8);                            
                            area_bots.appendColoredText(bot);                            
                        }                                                                     
                    } catch (NumberFormatException nfe) {
                        nfe.printStackTrace();
                    } 
                } 
            }            
        });
        

        // Alpha Containers
        AlphaContainer ac_players = new AlphaContainer(scroll_players);        
        AlphaContainer ac_bots = new AlphaContainer(scroll_bots);        
        AlphaContainer ac_field_ip = new AlphaContainer(field_ip);
        AlphaContainer ac_field_port = new AlphaContainer(field_port);
        AlphaContainer ac_btn_queryStatus = new AlphaContainer(btn_queryStatus);        
        ac_players.setBounds(scroll_players.getBounds());        
        ac_bots.setBounds(scroll_bots.getBounds());        
        ac_field_ip.setBounds(field_ip.getBounds());
        ac_field_port.setBounds(field_port.getBounds());
        ac_btn_queryStatus.setBounds(btn_queryStatus.getBounds());

       
        // Adding Elements                        
        add(label_players);
        add(label_bots);
        add(label_ip);
        add(field_ip);
        add(label_port);
        add(field_port);       
        add(ac_btn_queryStatus);                
        add(ac_players);
        add(ac_bots);        
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Gradient Background
        GradientPaint gp = new GradientPaint(0, 0, new Color(18,14,46), getWidth(), getHeight(), new Color(150,22,71));
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
}
