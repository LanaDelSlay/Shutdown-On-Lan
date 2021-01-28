package shutdownOnLan;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import org.controlsfx.control.textfield.TextFields;

import impl.org.controlsfx.autocompletion.AutoCompletionTextFieldBinding;
import impl.org.controlsfx.autocompletion.SuggestionProvider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class controller {

    @FXML
    private TextField  ipTextField;

    @FXML
    private Button connectButton;

    @FXML
    private Button shutdownButton;

    @FXML
    private Button disconnectButton;

    @FXML
    private Button closeButton;

    @FXML
    private Label connectionStatusLabel;
    
    public static void saveIP(String IP) throws IOException {
    	String dataFolder = System.getProperty("user.home") + File.separator + "SDOL";
	    File dataFile = new File(dataFolder+File.separator+"savedIPs");
	    FileWriter myWriter = new FileWriter(dataFolder+File.separator+"savedIPs",true);
	    BufferedReader dataInput = new BufferedReader(new FileReader(dataFolder+File.separator+"savedIPs"));
	    String nextLine;
	    //Checks if IP has been stored already
	    System.out.println(IP);
	    if(dataFile.length() < 1) {
	    	myWriter.append(IP + System.getProperty("line.separator"));
	  	    myWriter.close();
	    }
	    while ((nextLine = dataInput.readLine()) != null){
	    if (nextLine.contains(IP)) {
	        System.out.println("Duplicate IP found");
	        myWriter.close();
	      }else {
	    	  System.out.println("Writing");
	    	myWriter.append(IP + System.getProperty("line.separator"));
	  	    myWriter.close();
	      }
	    }
    }
    
    public void initialize() throws IOException{
    	//Loading saved IPs
        String dataFolder = System.getProperty("user.home") + File.separator + "SDOL";
	    Path p1 = Paths.get(System.getProperty("user.home") + File.separator + "SDOL"+File.separator+"savedIPs");
	    
	    Files.createDirectories(Paths.get(System.getProperty("user.home") + File.separator + "SDOL"));
	    new FileOutputStream((System.getProperty("user.home") + File.separator + "SDOL"+File.separator+"savedIPs"), true).close();
	    
	    List<String> lines = Files.readAllLines(p1, StandardCharsets.UTF_8);
    	String[] arr = lines.toArray(new String[lines.size()]);
    	SuggestionProvider suggestionProvider = SuggestionProvider.create(new ArrayList());
    	new AutoCompletionTextFieldBinding<>(ipTextField, suggestionProvider);
    	List<String> newSuggestions = new ArrayList();
    	newSuggestions = Arrays.asList(arr);
    	suggestionProvider.addPossibleSuggestions(newSuggestions);
    	suggestionProvider.addPossibleSuggestions("192.168.0.");
    	suggestionProvider.addPossibleSuggestions("10.0.0.");

    }

    @FXML
    Boolean isReachable() {
    	if(ipTextField.getText().isBlank()) {
    		return false;
    	} else {
    	String IP = ipTextField.getText();
    	
    	 try
         {    		 
             InetAddress ip = InetAddress.getByName(IP); 
             Socket s = new Socket(ip, 888); 
             DataInputStream dis = new DataInputStream(s.getInputStream()); 
             DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 

       if(s.isConnected()) {
           dis.close(); 
           dos.close(); 
       	   s.close();
    	   return true;
       } else {
    	   dis.close(); 
           dos.close(); 
       	   s.close();
    	   return false;
       }
       
                    
         } catch(SocketException e){ 
         	System.out.println("Connection closed"); 
         	return false;
         	
         } catch(IOException e) {
         	System.out.println("Connection lost.");
         	
         	
         }
    	}
		return true;

    }
    
    @FXML
    void testConnect(ActionEvent event) {

    	if(ipTextField.getText().isBlank()) {
    		System.out.println(ipTextField.getText());
    		connectionStatusLabel.setText("IP is empty.");
    		connectionStatusLabel.setTextFill(Color.RED);
    	} else {
    	String IP = ipTextField.getText();
    	
    	 try
         { 
    		 
             InetAddress ip = InetAddress.getByName(IP); 
             Socket s = new Socket(ip, 888); 
             DataInputStream dis = new DataInputStream(s.getInputStream()); 
             DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
             System.out.println("Connecting to: " + IP);

       if(s.isConnected()) {connectionStatusLabel.setText("Server Running!");
		connectionStatusLabel.setTextFill(Color.GREEN);
		System.out.println("Saving");
        saveIP(IP);
	    
		} 
			
             // closing resources 
             dis.close(); 
             dos.close(); 
         	s.close();
         	
             
         } catch(SocketException e){ 
        	 connectionStatusLabel.setText("Server not found");
     		connectionStatusLabel.setTextFill(Color.RED);
         	System.out.println("Connection closed"); 
         	
         } catch(IOException e) {
         	System.out.println("Connection lost.");
         	
         	
         }
    	}
    }
    	
    @FXML
    void shutdown() {
    	if (isReachable()) {
    		System.out.println("Hello2");
    		
            try
            { 
                Scanner scn = new Scanner(System.in); 
                InetAddress ip = InetAddress.getByName(ipTextField.getText()); 
                Socket s = new Socket(ip, 888); 
                DataInputStream dis = new DataInputStream(s.getInputStream()); 
                DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
                Boolean stop = false;
          
                // the following loop performs the exchange of 
                // information between client and client handler 
               
                    dos.writeUTF("shutdown"); 
                	scn.close(); 
                	dis.close(); 
                	dos.close(); 
                	s.close();
                
                  
                // closing resources 
              
                
            } catch(SocketException e){ 
            	System.out.println("Connection closed"); 
            	
            } catch(IOException e) {
            	System.out.println("Connection lost.");
            	
            	
            }
    		
    		
    		
    	}  else {
    		connectionStatusLabel.setTextFill(Color.RED);
    		connectionStatusLabel.setText("IP is not a server.");
    		
    	}
    	
    }
    	
    }