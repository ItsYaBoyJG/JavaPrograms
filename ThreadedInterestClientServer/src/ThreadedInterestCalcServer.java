import java.io.*;
import java.net.*;
import java.text.DecimalFormat;
import java.util.*;

//implements the Runnable interface. Runnable is needed when classes are intended to be executed over threads
//
public class ThreadedInterestCalcServer implements Runnable {

	//Setup the client socket that will be used by each ThreadedInterestCalcServer instance
	private static Socket clientSocket;

	/*
	 * Create a constructor that will assign each client connection to the clientSocket property of the 
	 * ThreadedInterestCalcServer instance
	 */
	public ThreadedInterestCalcServer(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	//used for creating and running the threads when the client(s) connects
	public static void main(String[] args) {        
		try {
			//Display a message that the server started
			System.out.println("Server started");

			//Setup server sockets and listen for client connection on port 6000
			ServerSocket serverSocket = new ServerSocket(6000);
			System.out.println("Waiting for a connection to the client");	
			
			/*
			 * Continuously listen for and accept client requests
			 * 
			 * This construct allows us to have multiple client connections
			 */
			while(true) {  
				//Wait for a client connection
				clientSocket = serverSocket.accept();
				
				//Display successful connection message after accept() method returns a client connection
				System.out.println("Successfully connected to the client");
				
				//creates the server instance when a client connects
				ThreadedInterestCalcServer tics = new ThreadedInterestCalcServer(clientSocket);
				
				//COMMENT HERE
				new Thread(tics).start();				
			} 

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	// needed by Runnable
	// run handles the processing for each thread. handles all reads,writes, and calculations
	@Override
	public void run() {        
		try {
			//Display a message that a thread was started
			System.out.println("Connected to a client using thread: " + Thread.currentThread());

			//Setup to read from and write to client
			BufferedReader streamIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));								
			PrintWriter streamOut = new PrintWriter(clientSocket.getOutputStream(), true);

			//Continue to listen for client messages until client terminates
			do {
				//Variables to hold input coming from client and accumulate final values for processing
				String clientMsg;								
				double rate = 0.0;
				int term = 0;
				int count = 0;
				double totalInt = 0.0;
				double bal = 0.0;
				DecimalFormat df = new DecimalFormat("0.00");

				//Read messages coming from the client until the client says it is done sending messages
				//(indicated by message X)
				while ((clientMsg = streamIn.readLine()) != null) {
					if (clientMsg.toUpperCase().equals("X")) {
						break;
					}					

					System.out.println("Incoming message from the client on thread " + Thread.currentThread() + " is " + clientMsg);					

					if (count == 0) {
						bal = Double.parseDouble(clientMsg);
					}
					else if (count == 1) {
						rate = Double.parseDouble(clientMsg);
					}
					else {
						term = Integer.parseInt(clientMsg);
					}

					count++;
				}				

				//Calculate average grade and determine corresponding letter grade
				for(int t = 0; t < term; t++) {
					totalInt += (bal * (rate/100));
					bal += (bal * (rate/100));					
				}

				//Send average grade and letter grade back to the client				
				streamOut.println("Total interest is: " + df.format(totalInt));
				streamOut.println("New balance is: " + df.format(bal));

				//Tell the client the server is done sending messages
				streamOut.println("X");				
			} while (true);
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
