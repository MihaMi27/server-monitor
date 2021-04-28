import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Logic {
    public static String queryStatus(String ip, int port) {
        String result = "";
        try {
            InetAddress address = InetAddress.getByName(ip);
            // Transmit UDP Packet
            DatagramSocket socket = new DatagramSocket();
            byte[] reqBuffer = new byte[14];
            reqBuffer[0] = (byte)255;
            reqBuffer[1] = (byte)255;
            reqBuffer[2] = (byte)255;
            reqBuffer[3] = (byte)255;            
            int i = 4;          
            for (char x : "getstatus".toCharArray()) {
                reqBuffer[i] = (byte)x;
                i++;
            }
            reqBuffer[13] = (byte)10;
            DatagramPacket request = new DatagramPacket(reqBuffer, reqBuffer.length,address,port);           
    
            socket.send(request);
            socket.setSoTimeout(5000);
            
            // Receive UDP Packet
            byte[] resBuffer = new byte[65536];
            DatagramPacket response = new DatagramPacket(resBuffer, resBuffer.length);
            socket.receive(response);                       
            result = new String(resBuffer,StandardCharsets.ISO_8859_1);            
            
            socket.close();
        } catch (SocketException se) {
            se.printStackTrace();
        } catch (IOException ioe) {            
            ioe.printStackTrace();
        } 

        return result;
    }

    public static ArrayList<String> server_info(String response) {
        ArrayList<String> serverInfo = new ArrayList<String>();               
        String[] response_array = response.split("\n");        
        
        String[] cvars = response_array[1].split("\\\\");        
        for (int i = 1; i < cvars.length;  i++) {
            if (i % 2 != 0) {                
                if (cvars[i].equals("sv_hostname")) {
                    String s = "";
                    for (char x : cvars[i+1].toCharArray()) {
                        if (x != 128 && !(x >= 1 && x <= 31)) {
                            s+=x;
                        }
                    }
                    cvars[i+1] = s;                  
                }
                serverInfo.add(cvars[i]+": "+cvars[i+1]);                
            }            
        }
        

        return serverInfo;
    }
    
    public static ArrayList<String> player_info(String response) {
        ArrayList<String> playerInfo = new ArrayList<String>();
        ArrayList<String> rawPlayerInfo = new ArrayList<String>();
        String[] response_array = response.split("\n");       
        for (int i = 2; i < response_array.length; i++) {
            String s = new String(response_array[i].getBytes(),StandardCharsets.UTF_8);
            rawPlayerInfo.add(s);

        }
        for (String player : rawPlayerInfo) {            
            Pattern player_pattern = Pattern.compile("\"(.{1,})\"");
            Pattern ping_pattern = Pattern.compile("\\d+\\s(\\d+)");
            Matcher matcher_player = player_pattern.matcher(player);
            Matcher matcher_ping = ping_pattern.matcher(player);
            while (matcher_player.find()) {
                String player_name = new String(matcher_player.group(1).getBytes(),StandardCharsets.UTF_8);
                if (matcher_ping.find()) {
                    if (!matcher_ping.group(1).equals("0")) {
                        playerInfo.add(player_name);
                    }                    
                }                 
            }
        }
        

        return playerInfo;
    }

    public static ArrayList<String> bot_info(String response) {
        ArrayList<String> botInfo = new ArrayList<String>();
        ArrayList<String> rawBotInfo = new ArrayList<String>();
        String[] response_array = response.split("\n");       
        for (int i = 2; i < response_array.length; i++) {
            String s = new String(response_array[i].getBytes(),StandardCharsets.UTF_8);
            rawBotInfo.add(s);

        }
        for (String player : rawBotInfo) {            
            Pattern player_pattern = Pattern.compile("\"(.{1,})\"");
            Pattern ping_pattern = Pattern.compile("\\d+\\s(\\d+)");
            Matcher matcher_player = player_pattern.matcher(player);
            Matcher matcher_ping = ping_pattern.matcher(player);
            while (matcher_player.find()) {
                String player_name = new String(matcher_player.group(1).getBytes(),StandardCharsets.UTF_8);
                if (matcher_ping.find()) {
                    if (matcher_ping.group(1).equals("0")) {
                        botInfo.add(player_name);
                    }                    
                }                 
            }
        }
        

        return botInfo;
    }
    

}
