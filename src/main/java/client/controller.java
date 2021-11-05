package client;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
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
		//Checks if IP has been stored already
		Scanner scanner = new Scanner(dataFile);
		boolean alreadySaved = false;
    	while (scanner.hasNextLine() && alreadySaved == false) {
    	   String line = scanner.nextLine();
    	   if(line.contains(IP)) {
    		  alreadySaved = true;
    	   } 
    	} if(alreadySaved == false) {
	    	myWriter.append(IP + System.getProperty("line.separator"));

    	}
    	myWriter.close();
    	scanner.close();
	}

	@SuppressWarnings("unchecked")
	public void initialize() throws IOException{
		//Loading saved IPs
		Path p1 = Paths.get(System.getProperty("user.home") + File.separator + "SDOL"+File.separator+"savedIPs");
		Files.createDirectories(Paths.get(System.getProperty("user.home") + File.separator + "SDOL"));
		new FileOutputStream((System.getProperty("user.home") + File.separator + "SDOL"+File.separator+"savedIPs"), true).close();

		List<String> lines = Files.readAllLines(p1, StandardCharsets.UTF_8);
		String[] arr = lines.toArray(new String[lines.size()]);
		@SuppressWarnings("rawtypes")
		SuggestionProvider suggestionProvider = SuggestionProvider.create(new ArrayList());
		new AutoCompletionTextFieldBinding<>(ipTextField, suggestionProvider);
		List<String> newSuggestions = new ArrayList();
		newSuggestions = Arrays.asList(arr);
		suggestionProvider.addPossibleSuggestions(newSuggestions);
		suggestionProvider.addPossibleSuggestions("192.168.0.");
		suggestionProvider.addPossibleSuggestions("10.0.0.");

	}

	@FXML
	Boolean isReachable() throws InterruptedException {
		if(ipTextField.getText().isBlank()) {
			return false;
		} else {
			//example ws://192.168.0.15:888
			String IP = "ws://"+ipTextField.getText()+":888";

			try
			{    		 
				URI uri = new URI(IP);
				ClientNew nc = new ClientNew(uri); 
				nc.connectBlocking();
				if(nc.isOpen()) {
					nc.close();
					return true;
				} else {
					return false;
				}
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return false;

	}

	@FXML
	void testConnect(ActionEvent event) {

		if(ipTextField.getText().isBlank()) {
			System.out.println(ipTextField.getText());
			connectionStatusLabel.setText("IP is empty.");
			connectionStatusLabel.setTextFill(Color.RED);
		} else {
			String hostName = ipTextField.getText();

			try
			{ 
				String IP = "ws://"+ipTextField.getText()+":888";
				URI uri = new URI(IP);
				ClientNew cn = new ClientNew(uri);
				cn.connectBlocking();
				System.out.println("Connecting to: " + hostName);

				if(cn.isOpen()) {
					connectionStatusLabel.setText("Server Running!");
				connectionStatusLabel.setTextFill(Color.GREEN);
				System.out.println("Saving");
				saveIP(hostName);

				} 

				// closing resources 
				cn.close();


			} catch(SocketException e){ 
				connectionStatusLabel.setText("Server not found");
				connectionStatusLabel.setTextFill(Color.RED);
				System.out.println("Connection closed"); 

			} catch(IOException e) {
				System.out.println("Connection lost.");


			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@FXML
	void shutdown() throws URISyntaxException, InterruptedException {
		if (isReachable()) {
			System.out.println("Valid Computer, sending shutdown");    		
			try
			{ 
				InetAddress ip = InetAddress.getByName(ipTextField.getText()); 
				String url = "ws:/"+ip.toString() + ":888";
				System.out.println(url);
				URI uri = new URI(url);
				ClientNew cc = new ClientNew(uri);
				cc.connectBlocking();
				cc.send("shutdown");
				//cc.sendShutdown();
				cc.close();

			} catch(IOException e) {
				System.out.println("Connection lost.");


			}



		}  else {
			connectionStatusLabel.setTextFill(Color.RED);
			connectionStatusLabel.setText("IP is not a server.");

		}

	}

	@FXML
	void terminateProgram() {
		System.exit(0);

	}

}