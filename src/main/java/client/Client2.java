package client;

import java.io.*; 
import java.net.*; 
import java.util.Scanner; 
  
// Client class 
public class Client2  
{ 
    public static void main(String[] args)  
    { 
        try
        { 
            Scanner scn = new Scanner(System.in); 
            InetAddress ip = InetAddress.getByName(args[0]); 
            Socket s = new Socket(ip, 888); 
            DataInputStream dis = new DataInputStream(s.getInputStream()); 
            DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
            Boolean connectionClosed = false;
      
            // the following loop performs the exchange of 
            // information between client and client handler 
            while (s.isConnected()&&connectionClosed)  
            { 
            	
                String tosend = scn.nextLine(); 
                dos.writeUTF(tosend); 
                            
            }  
            // closing resources 
            scn.close(); 
            dis.close(); 
            dos.close(); 
        	s.close();
            
        } catch(SocketException e){ 
        	System.out.println("Connection closed"); 
        	
        } catch(IOException e) {
        	System.out.println("Connection lost.");
        	
        	
        }
    } 
} 