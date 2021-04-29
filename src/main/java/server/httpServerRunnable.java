package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class httpServerRunnable implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		ProcessBuilder pb = new ProcessBuilder("python","custom_server.py").inheritIO();
		Process p;
		try {
			p = pb.start();
		
		p.waitFor();
		BufferedReader bfr = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line = "";
		while ((line = bfr.readLine()) != null) {
		System.out.println(line);
		} } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
