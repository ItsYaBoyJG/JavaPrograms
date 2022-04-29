import java.io.*;
import java.net.*;
import java.util.*;

//Multiple instances of the client can now connect to the server
// each instance is independently running on a thread
public class ThreadedInterestCalcClient {

	public static void main(String[] args) {
		System.out.println("Client started");

		try {	
			//Setup client sockets and establish server connection on port localhost port 6000
			System.out.println("Waiting for a connection to the server");
			InetAddress localAddress = InetAddress.getLocalHost();
			Socket clientSocket = new Socket(localAddress, 6000);

			//Setup to read from and write to client
			BufferedReader streamIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			PrintWriter streamOut = new PrintWriter(clientSocket.getOutputStream(), true);

			//Display successful connection message after socket has been created
			System.out.println("Successfully connected to the server");

			//Setup to read from the client console
			Scanner consoleIn = new Scanner(System.in);

			//Variable to indicate whether or not to continue running
			String ind = "";

			//Continue to run the program and calculate interest until the user says their done (Enters X)
			do {
				//Variables to hold input coming from client console				
				double initBal = 0.0;
				double rate = 0.0;
				int term = 0;				

				//Read and validate input from the client console						
				initBal = validatePosDoubleInput("Enter your initial account balance: ", consoleIn);
				rate = validatePosDoubleInput("Enter your annual interest rate: ", consoleIn);
				term = validatePosIntegerInput("Enter your investment term (in years): ", consoleIn);

				//Reset scanner in client console
				consoleIn.nextLine();																

				//Pass each input to the server for processing
				streamOut.println(initBal);
				streamOut.println(rate);
				streamOut.println(term);

				//Tell the server the client is done sending messages
				streamOut.println("X");

				//Variable to hold the server's response message
				String serverMsg = "";

				//Read messages coming from the client until the server says it is done sending messages
				//(indicated by message X)
				while ((serverMsg = streamIn.readLine()) != null) {	
					if (serverMsg.toUpperCase().equals("X")) {
						break;
					}

					//Displays responses from the server
					System.out.println("Server response is: " + serverMsg);
				}	

				//Ask the user if they're done
				do {
					ind = validateStringInput("Enter Y to continue. Enter X to quit.", consoleIn);
				} while ((!ind.toUpperCase().equals("Y")) && ! ind.toUpperCase().equals("X"));				
			} while ((!ind.toUpperCase().equals("X")));

			//streamOut.println("Z");
			clientSocket.close();

			//When the user is done, stop the client
			System.out.println("Client stopped");
			System.exit(0);
		}
		catch (SocketException ex) {
			System.out.println("Client stopped");
			System.exit(0);
		}
		catch (IOException ex) {
			System.out.println("Exception occurred");
			ex.printStackTrace();
		}		
	}

	//Method repeatedly displays the prompt until valid positive int input is entered
	public static int validatePosIntegerInput(String prompt, Scanner in) {
		int var = 0;
		do {
			System.out.println(prompt);
			while (!in.hasNextInt() || !in.hasNextDouble()) {
				System.out.println(prompt);
				in.next();
			}
			var = in.nextInt();
		} while (var < 0);
		return var;
	}

	//Method repeatedly displays the prompt until valid positive double input is entered
	public static double validatePosDoubleInput(String prompt, Scanner in) {
		double var = 0.0;
		do {
			System.out.println(prompt);
			while (!in.hasNextDouble()) {
				System.out.println(prompt);
				in.next();
			} 
			var = in.nextDouble();
		} while (var < 0);
		return var;
	}

	//Method repeatedly displays the prompt until valid String input is entered
	public static String validateStringInput(String prompt, Scanner in) {
		boolean valid = false;
		String var = "";
		do {
			System.out.println(prompt);
			while (in.hasNextDouble() || in.hasNextInt()) {
				System.out.println(prompt);
				in.nextLine();
			}
			var = in.nextLine();
			valid = true;
		} while (!valid);
		return var;
	}
}
