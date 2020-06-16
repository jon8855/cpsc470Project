package project.old;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class BlackJackClientL {

	//private boolean hasConnections = false;
	private String serverIp;
	private int port;
	private Scanner listener;
	private Socket socket;
	private ObjectOutputStream outputStream;
	private boolean hasConnections;
	
	public class Connection implements Runnable {

		@Override
		public void run() {
			System.out.println("Attempting to connect");
			if (hasConnections) {
				System.out.println("Connection already established");
			} else {
				while (!hasConnections) {
					try {
						socket = new Socket(serverIp, port);
						hasConnections = true;
						//listener = new Scanner(socket.getInputStream())
						System.out.println("Connection established");
						outputStream = new ObjectOutputStream(socket.getOutputStream());
						SamplePlayer player = new LambertsonPlayer(100);
						System.out.println("Player sent = " + player.getPlayerName());
                        outputStream.writeObject(player);
                        socket.close();
						//Thread.sleep(2000);
					} catch (IOException e) {
						System.out.println("Failure establishing connection on " + serverIp + ":" + port);
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					System.out.println("Retrying connection");
					} //catch (InterruptedException e) {
					// 	// TODO Auto-generated catch block
					// 	e.printStackTrace();
					// } 
				}
				// System.out.println("Test message received: " + listener.nextLine());
				// while (!socket.isClosed()) {
				// 	String data = listener.nextLine();
				// 	System.out.println("Received Message: " + data);
				// }
			}
		}
	}

	
	public BlackJackClientL(String serverIp, int port) {
		this.serverIp = serverIp;
		this.port = port;
		this.hasConnections = false;
		ExecutorService threadPool = Executors.newFixedThreadPool(1);
		threadPool.execute(new Connection());
	}
	
	public static void main(String[] args) {
		
		new BlackJackClientL("127.0.0.1",6000);
		try {
			Thread.currentThread().join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}