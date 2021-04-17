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
class ClientHandler extends Thread  
{ 
    DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd"); 
    DateFormat fortime = new SimpleDateFormat("hh:mm:ss"); 
    final DataInputStream dis; 
    final DataOutputStream dos; 
    final Socket s; 
     
  
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos)  
    { 
        this.s = s; 
        this.dis = dis; 
        this.dos = dos; 
    } 
  
    @SuppressWarnings("deprecation")
	@Override
    public void run()  
    { 	
    	LocalDateTime now = LocalDateTime.now(); 
    	Boolean firstTime = true;
        String received; 
        String toreturn; 
        while (true)  
        	
        {  try { if (firstTime) {
        	dos.writeUTF("shutdown to shutdown\n"+ 
                    "exit to leave.");
        } firstTime = false;
        	
        dos.writeUTF("");
        //System.out.print("");
                received = dis.readUTF(); 

                if((received.equals("exit")) || received.equals("quit") || received.equals("Exit") || received.equals("Quit")) 
                {  
                    System.out.println("[LOG]"+ now + ": Client " + this.s + " sends exit..."); 
                    System.out.println("Closing this connection."); 
                    this.s.close(); 
                    System.out.println("Connection closed"); 
                    break; 
                } 
                  
                Date date = new Date(); 
 
                switch (received) { 
                  
                    case "shutdown" :
                    	shutdown(); 
                        break; 
                        
                    case "timer":
                    	Scanner scan = new Scanner(System.in);
                    	
                    	dos.writeUTF("Enter a time to wait! (Minutes)");
                    	int time = dis.read();
                    	Thread.sleep(time * 1000 * 60);
                    	shutdown();
                    	break;
                          
                    case "time" : 
                        toreturn = fortime.format(date); 
                        dos.writeUTF(toreturn); 
                        break; 
                        
                    case "ping" : 
                    	dos.writeUTF("pong"); 
                        break;
                          
                    default: 
                    dos.writeUTF("Invalid input"); 
                    break; 
                }     
            } catch (SocketException e) { 
                //e.printStackTrace();
            	
            		System.out.println("[LOG]"+ now + s.getInetAddress() + " lost connection.");
         			Thread.currentThread().stop();                
            } catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
        } 
          
        try
        { 
           //closing resources 
            this.dis.close(); 
            this.dos.close(); 
              
        }catch(IOException e){ 
            e.printStackTrace(); 
        } 
    } 

    public static void shutdown() throws RuntimeException, IOException {
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
    	LocalDateTime now = LocalDateTime.now();  
    	System.out.println(dtf.format(now));  
        String shutdownCommand;        
        String logsFolder = System.getProperty("user.home") + File.separator + "SDOL_Logs";
        Path path = Paths.get(logsFolder);
        
        if (!Files.exists(path)) {
        	Files.createDirectory(path);
        }
        try (Stream<Path> files = Files.list(Paths.get(logsFolder))) {
            long count = files.count();
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(logsFolder+File.separator+"Log"+" " + count +".txt"), "utf-8"))) {
         writer.write("Shutdown received to "+ InetAddress.getLocalHost() +":" + dtf.format(now) );
      }

        }
        
        String operatingSystem = System.getProperty("os.name");

       
		if ("Linux".equals(operatingSystem) || "Mac OS X".equals(operatingSystem)) {
			System.out.println("Shutting down");
            shutdownCommand = "shutdown -h now";
            String[] args = new String[] {"/bin/bash", "-c", shutdownCommand};
            Process proc = new ProcessBuilder(args).start();
        }
        else if (operatingSystem.contains("Windows")) {
            shutdownCommand = "shutdown.exe -s -t 0";
        }
        else {
            throw new RuntimeException("Unsupported operating system.");
        }

        Runtime.getRuntime().exec(shutdownCommand);
        System.exit(0);
    }

} 
    