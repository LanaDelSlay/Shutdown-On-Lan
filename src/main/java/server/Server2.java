package server;

import java.io.*; 
import java.text.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Stream;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths; 
import javafx.scene.control.TextArea;

public class Server2  
{ 
    public static void main() throws IOException  
    { 
       	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
    	LocalDateTime now = LocalDateTime.now(); 
        ServerSocket ss = new ServerSocket(888); 
        while (true)  
        { 
            Socket s = null; 
              
            try 
            { 
                s = ss.accept(); 
                DataInputStream dis = new DataInputStream(s.getInputStream()); 
                DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
                System.out.println("[LOG]"+ now +": Incoming Connection" + s.getInetAddress()); 
                Thread t = new ClientHandler(s, dis, dos); 
                t.start(); 
            } 
            catch (Exception e){ 
                s.close(); 
            } 
        } 
    } 
} 
 