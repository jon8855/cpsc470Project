//package project.old;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;
import java.net.SocketException;

public class BlackJackServer{

    public class Connection implements Runnable {

		@Override
		public void run() {
			//if (hasConnections) {
			//	System.out.println("Connection already established");
			//} else {
            while(!hasConnection){
                try {
                    System.out.println("Attempting connect to player on port " + port);
                    listener = new ServerSocket(port);
                    socket = listener.accept();
                    hasConnection = true;
                    inStream = new ObjectInputStream(socket.getInputStream());
                    player = (SamplePlayer) inStream.readObject();
                    System.out.println("Player received = " + player.getPlayerName());
                    //socket.close();

                    System.out.println("Attempting connect to player on port " + port2);
                    listener = new ServerSocket(port2); //different port number
                    socket = listener.accept();
                    hasConnection = true;
                    inStream2 = new ObjectInputStream(socket.getInputStream());
                    player2 = (SamplePlayer) inStream2.readObject();
                    System.out.println("Player received = " + player2.getPlayerName());
                    socket.close();
                    System.out.println("\nWelcome Players! \n==========\n");
                    //writer = new PrintWriter(socket.getOutputStream(),true);
                    //System.out.println("Connection established");
                } catch (IOException e) {
                    System.out.println("Failure establishing connection");
                    e.printStackTrace();
                } catch (ClassNotFoundException cn) {
                    cn.printStackTrace();
                }
			}
		}
	}
	
	//private boolean hasConnections;
	public ServerSocket listener;
    private int port = 6000;
    private int port2 = 4445;
	public Socket socket;
    private PrintWriter writer;
    private SamplePlayer player;
    private SamplePlayer player2;
    private ObjectInputStream inStream;
    private ObjectInputStream inStream2;
    private boolean hasConnection = false;

	public BlackJackServer() {
        //this.hasConnection = false;
        
		//ExecutorService threadPool = Executors.newFixedThreadPool(1);
        //threadPool.execute(new Connection());
        Connection c = new Connection();
        c.run();
		//System.out.println("Finished initialization for proxy on server side");
	}

	public void write(String msg) {
		
		// if (hasConnections) {
		// 	System.out.println("Writing data to client");
		// 	writer.println(msg);			
		// } else {
		// 	System.out.println("No client to write data");
		// }
    }
    
    public SamplePlayer getPlayerFromServer(){
        return player;
    }

    public SamplePlayer getPlayer2FromServer(){
        return player2;
    }
}