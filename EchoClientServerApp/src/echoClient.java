import java.io.*;
import java.net.*;
import java.util.Scanner;
/*
 *  Servers provide a service/response to a client after receiving a request. They 
are identified by an IP address 
 *  and a port number (since multiple servers can run on the same machine, and the 
machine only has one IP address)
 * 
 *  Clients request something from a server and receive a response back.  
 *  
 *  The client/server communication can be as simple as messages, which we'll see 
in this example.
 *  
 *  Sockets are used to facilitate the client/server communication.  Both the 
client and the server each have a socket.
 *  These sockets must be connected in order for the communication to happen.  
There are different types of sockets that you
 *  can use depending on the type of communication that is happening, but we will 
be working with TCP sockets in this example.
 *  
 *  This program will represent the client component. It will send a string to the 
server as a request, and await a string
 *  response from the server. 
 */
public class echoClient {
public static void main(String[] args) {
try {
System.out.println("Client started");
System.out.println("Waiting for a connection to the server");
/*
 * STEP 1: InetAddress class represents an IP address, in this 
case the IP address of the localhost (this computer, with
 * IP address 127.0.0.1) 
 */

 InetAddress nAddress = InetAddress.getLocalHost();
/*
 *  STEP 2: Use the ServerSocket to create a socket on the client
that will connect to the localhost (this computer, 
 *  with IP address 127.0.0.1) and send requests on port 6000
 *  
 *  This is the IP address of the machine running the server 
program, and references the port that the server
 *  will be listening on
 */

Socket cliSocket = new Socket(nAddress, 6000);
/*
 *  STEP 3: Use the PrintWriter to write the requests to send to 
the server
 * 
 *  Call the getOutputStream() method on the client socket to 
access the outgoing messages to the server
 */

PrintWriter cWriter = new PrintWriter(cliSocket.getOutputStream(), true);

/*
 *  STEP 4: Use the BufferedReader to read the messages coming 
from the client
 * 
 *  Call the getInputStream() method on the client socket to 
access the incoming messages from the server
 */
BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(cliSocket.getInputStream()));

System.out.println("Successfully connected to the server");
//STEP 5: Use the Scanner to read user input from the client's 
//console
Scanner consoleIn = new Scanner(System.in);
while (true) {
//STEP 6: Prompt user to enter input
System.out.print("Enter a message to send to the server or enter X to exit: ");
//STEP 7: Variable will store user's input
String inputLine = consoleIn.nextLine();
//STEP 8: Determines if the user wants to exit
if ("X".equalsIgnoreCase(inputLine)) {
break;
}
//STEP 9: Sends outgoing message to the server

cWriter.println(inputLine);
//STEP 10: Uses the BufferedReader to retrieve the incoming
//(response) messages from the server
String response = bufferedReader.readLine();
//STEP 11: Displays the incoming message from the server in
//the client's console
System.out.println("Server response is: " + response);
}
}
catch (IOException ex) {
System.out.println("Exception occurred");
ex.printStackTrace();
}
}
}