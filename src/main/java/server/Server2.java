package server;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.java_websocket.drafts.Draft_6455;
import java.time.format.DateTimeFormatter;
import org.java_websocket.drafts.Draft;
import java.net.UnknownHostException;
import org.java_websocket.WebSocket;
import java.net.InetSocketAddress;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.stream.Stream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Collections;
import java.net.InetAddress;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.Writer;
import java.io.File;
import java.sql.*;

/**
 * A simple WebSocketServer implementation. Keeps track of a "chatroom".
 */
public class Server2 extends WebSocketServer {
	
	static Boolean serverRunning = false;

	static String sourceIp = "";

	public Server2(int port) throws UnknownHostException {
		super(new InetSocketAddress(port));
	}

	public Server2(InetSocketAddress address) {
		super(address);
	}

	public Server2(int port, Draft_6455 draft) {
		super(new InetSocketAddress(port), Collections.<Draft>singletonList(draft));
	}

	@Override
	public void onOpen(WebSocket conn, ClientHandshake handshake) {
		sourceIp = conn.getRemoteSocketAddress().toString();
		conn.send("Welcome to the server!"); //This method sends a message to the new client
		broadcast("new connection: " + handshake
				.getResourceDescriptor()); //This method sends a message to all clients connected
		System.out.println(
				conn.getRemoteSocketAddress().getAddress().getHostAddress() + " entered the room!");
	}

	@Override
	public void onClose(WebSocket conn, int code, String reason, boolean remote) {
		broadcast(conn + " has left the room!");
		System.out.println(conn + " has left the room!");
	}

	@Override
	public void onMessage(WebSocket conn, String message) {
		if(message.contains("shutdown")) {
			try {
				shutdown();
			} catch (RuntimeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if(message.contains("sleep")) {
			try {
				sleep();
			} catch (RuntimeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		int port = 888; // 843 flash policy port
		try {
			port = Integer.parseInt(args[0]);
		} catch (Exception ex) {
		}
		Server2 s = new Server2(port);
		s.start();
		BufferedReader sysin = new BufferedReader(new InputStreamReader(System.in));
		serverRunning = true;
		while (serverRunning) {
			String in = sysin.readLine();
			s.broadcast(in);
			if (in.equals("exit")) {
				s.stop(1000);
				break;
			}
		}
		s.stop();
	}

	@Override
	public void onError(WebSocket conn, Exception ex) {
		ex.printStackTrace();
		if (conn != null) {
			// some errors like port binding failed may not be assignable to a specific websocket
		}
	}

	@Override
	public void onStart() {
		httpServerRunnable httpServer = new httpServerRunnable();
		Thread t = new Thread(httpServer);
		t.start();
		System.out.println("Server started!");
		setConnectionLostTimeout(1000);
	}

	public static void shutdown() throws RuntimeException, IOException {
		String shutdownCommand;        
		String operatingSystem = System.getProperty("os.name");

		if ("Linux".equals(operatingSystem) || "Mac OS X".equals(operatingSystem)) {
			//to fix
			shutdownCommand = "shutdown -h now";
			String[] args = new String[] {"/bin/bash", "-c", shutdownCommand};
		}

		else if (operatingSystem.contains("Windows")) {
			shutdownCommand = "shutdown.exe -s -t 0";
		}

		else {
			throw new RuntimeException("Unsupported operating system.");
		}

		Runtime.getRuntime().exec(shutdownCommand);
		log();
		System.exit(0);
	}


	public static void sleep() throws RuntimeException, IOException {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		String shutdownCommand;        
		String logsFolder = System.getProperty("user.home") + File.separator + "SDOL_Logs";
		Path path = Paths.get(logsFolder);

		if (!Files.exists(path)) {
			Files.createDirectory(path);
		}
		try (Stream<Path> files = Files.list(Paths.get(logsFolder))) {
			try (Writer writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(logsFolder+File.separator+"Log.txt", true), "utf-8"))) {
				writer.append("\nSleep received to "+ InetAddress.getLocalHost() +":" + dtf.format(now));
			}	
		}

		String operatingSystem = System.getProperty("os.name");


		if ("Linux".equals(operatingSystem) || "Mac OS X".equals(operatingSystem)) {
			shutdownCommand = "sleep now";
			String[] args = new String[] {"/bin/bash", "-c", shutdownCommand};
			//To fix
		}
		else if (operatingSystem.contains("Windows")) {
			shutdownCommand = "Rundll32.exe Powrprof.dll,SetSuspendState Sleep";
		}
		else {
			throw new RuntimeException("Unsupported operating system.");
		}

		Runtime.getRuntime().exec(shutdownCommand);
		serverRunning = false;
		System.exit(0);
	}


	public static void log() throws IOException {
		Statement stmt = null;
		Connection conn = null;
		String logsFolder = System.getProperty("user.home") + File.separator + "SDOL_Logs";
		Path path = Paths.get(logsFolder);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  

		if (!Files.exists(path)) {
			Files.createDirectory(path);
		}

		try (Stream<Path> files = Files.list(Paths.get(logsFolder))) {
			LocalDateTime now = LocalDateTime.now(); 
			try (Writer writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(logsFolder+File.separator+"Log.txt", true), "utf-8"))) {
				writer.append("\nShutdown received to "+ InetAddress.getLocalHost() +":" + dtf.format(now));
			}	
		}

		try {
			LocalDateTime now = LocalDateTime.now(); 
			conn = DriverManager.getConnection("jdbc:mysql://192.168.0.48/sdol?" + "user=username&password=password");
			stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO time (time, source) VALUES ('"+ now +"', '" + sourceIp +"')");

			// Do something with the Connection

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode()); 
		}
	}

}

