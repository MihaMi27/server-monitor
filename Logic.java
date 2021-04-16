import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
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
            result = new String(resBuffer);
            socket.close();
        } catch (SocketException se) {
            se.printStackTrace();
        } catch (IOException ioe) {            
            ioe.printStackTrace();
        } 

        return result;
    }

    public static String queryInfo(String ip, int port) {
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
            for (char x : "getinfo".toCharArray()) {
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
            result = new String(resBuffer);
            socket.close();
        } catch (SocketException se) {
            se.printStackTrace();
        } catch (IOException ioe) {            
            ioe.printStackTrace();
        } 

        return result;
    }

    public static ArrayList<String> player_info(String response) {
        ArrayList<String> playerInfo = new ArrayList<String>();
        ArrayList<String> rawPlayerInfo = new ArrayList<String>();
        String[] response_array = response.split("\n");       
        for (int i = 2; i < response_array.length; i++) {
            rawPlayerInfo.add(response_array[i]);            

        }
        for (String player : rawPlayerInfo) {
            Pattern player_name_pattern = Pattern.compile("\"(.{1,})\"");
            Matcher m = player_name_pattern.matcher(player);            
            while (m.find()) {
                String player_name = m.group(1); 
                playerInfo.add(player_name);                
            }
        }
        

        return playerInfo;
    }

}
