import java.io.*;
import java.net.*;
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
public class echoServer {
public static void main(String[] args) {
System.out.println("Server started");
try {
/*
 *  STEP 1: Use the ServerSocket to create a socket on the server
that will listen for requests on port 6000
 * 
 *  The server socket doesn't need to reference the server's IP 
address, but the client socket will need this 
 *  information so that it knows where to send its requests
 */
ServerSocket serverSocket = new ServerSocket(6000);


System.out.println("Waiting for a connection to the client");
/*
 *  STEP 2: Call the accept() method on the server socket to wait
for a request from the client once its connected
 * 
 *  This suspends the server program until a request is received 
from the client, the ServerSocket that is 
 *  returned represents the connection between the client 
application and the server application
 */
Socket clientSocket = serverSocket.accept();

System.out.println("Successfully connected to the client");
/*
 *  STEP 3: Use the BufferedReader to read the messages coming 
from the client
 * 
 *  Call the getInputStream() method on the client socket to 
access the incoming messages from the client
 */
BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

/*
 *  STEP 4: Use the PrintWriter to write the responses to send to
the client
 * 
 *  Call the getOutputStream() method on the client socket to 
access the outgoing messages to the client
 */
PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

//STEP 5: Variable will store each line that is read from the  BufferedReader
String clientMsg;
//STEP 6: Use the BufferedReader to incoming messages from the 
//client and loop until there is nothing left to read
while ((clientMsg = bufferedReader.readLine()) != null) {
//STEP 7: Displays the incoming message from the client in the server's console
System.out.println("The incoming message from the client is: " + clientMsg);
//STEP 8: Sends outgoing message back to the client

    writer.println("The server echoes your message: " + clientMsg + "\n" + clientMsg + "\n" + clientMsg );
}
}
catch (SocketException ex) {
System.out.println("Server stopped");
System.exit(0);
}
catch (IOException ex) {
System.out.println("Exception occurred");
ex.printStackTrace();
}
}
}