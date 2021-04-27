package client;

import java.net.URI;
import java.net.URISyntaxException;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

public class ClientNew extends WebSocketClient {

	public ClientNew(URI serverUri) {
		super(serverUri);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onOpen(ServerHandshake handshakedata) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onMessage(String message) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onClose(int code, String reason, boolean remote) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onError(Exception ex) {
		// TODO Auto-generated method stub
		
	}
	
	public void sendShutdown() throws InterruptedException {
		this.connectBlocking();
		this.send("shutdown");
	}
		
}
