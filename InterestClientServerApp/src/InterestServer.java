// Justin Goshorn

import java.io.*;
import java.net.*;
import java.text.DecimalFormat;
/*
 *  Provide an overview of what this program does within the application
 * 
 *  This program represents the server interaction with the client side. 
 * will take in the client side string message and return a response to the client
 */
public class InterestServer {
public static void main(String[] args) {
try {
System.out.println("Server started");
/*
 *  What is the ServerSocket class used for?
 *  ServerSocket it used to take in the request from the client side
 *  and then returns a string message to the client
 */
ServerSocket serverSocket = new ServerSocket(6000);

System.out.println("Waiting for a connection to the client");
/*
 *  What is the Socket used for?  What parameters are required to
instantiate it?
this socket is used to wait for and accept the request from the server
the server socket and returns the connection to the client


 */
Socket clientSocket = serverSocket.accept();
System.out.println("Successfully connected to the client");
/*
 *  What are the BufferedReader and PrintWriter classes used for?
 * 
 * Buffered reader reads in the input from the client socket
 * 
 * PrintWriter ouputs a stream from the socket
 */
BufferedReader streamIn = new BufferedReader(new 
InputStreamReader(clientSocket.getInputStream()));
PrintWriter streamOut = new 
PrintWriter(clientSocket.getOutputStream(), true);
/*
 *  What functionality does this loop construct support?
 *  this loop takes in the user entered data from the client side
 * performs the calculations and then returns the total interest
 * the loop stops all user data is read in and then outputted
 */

do {
/*
 *  What are these variables used for?
 * these variables are used to match up with the client side user entered data variables
 * as the stream is read in the varaibles are populated with the user entered data
 * 
 */
String clientMsg;
double rate = 0.0;
int term = 0;
int count = 0;
double totalInt = 0.0;
double bal = 0.0;
DecimalFormat df = new DecimalFormat("0.00");
/*
 * What functionality does this loop construct support?
 * this loop takes the data read in from the BufferedReader line by line and 
 * then assigns it to a variable from above
 */
while ((clientMsg = streamIn.readLine()) != null) {
if (clientMsg.toUpperCase().equals("X")) {
break;
}
System.out.println("The incoming message from the client is: " + clientMsg);
/*
 *  How are these values populated?
 * the values are populated from the streamIn.readLine() 
 * where each count represents a line of the stream 
 * the variables are given the values from the stream
 */
if (count == 0) {
bal = Double.parseDouble(clientMsg);
}
else if (count == 1) {
rate = Double.parseDouble(clientMsg);
}
else {
term = Integer.parseInt(clientMsg);
}
/*
 *  What is the count variable used for?
 * count represents each line read in from the streamIn.readLine()
 */
count++;
}
/*
 *  What functionality does this loop construct support?
 * the function of this loop is to calculate the total value of the interest
 * for the given term length 
 */
for(int t = 0; t < term; t++) {
totalInt += (bal * (rate/100));
bal += (bal * (rate/100));
}
/*
 * What happens with the println() method is invoked?
 * the total interest value is printed with the df formatting of "0.00"
 * the new balance is printed with the df formatting of "0.00"
 * "X" is then printed to allow the user to end the program and close the connection
 */
streamOut.println("Total interest is: " + 
df.format(totalInt));
streamOut.println("New balance is: " + df.format(bal));
streamOut.println("X");
} while (true);
} 
catch (SocketException ex) {
System.out.println("Client closed. Server stopped.");
System.exit(0);
}
catch (IOException ex) {
System.out.println("Exception occurred");
ex.printStackTrace();
}
}
}