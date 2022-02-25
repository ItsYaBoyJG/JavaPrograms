// Justin Goshorn
import java.io.*;
import java.net.*;
import java.util.Scanner;
/*
 *  Provide an overview of what this program does within the application
 * 
 *  this app represents a client making a request to a server 
 * this program will send interest related streams of balance, 
 * rate and term to a server and await the interest total
 */
public class InterestClient {
public static void main(String[] args) {
System.out.println("Client started");
try {
System.out.println("Waiting for a connection to the server");
/*
 *  What is the InetAddress class used for?
 * InetAddress represents an IP address. 
 * in this program it returns address of local host
 * 
 */
InetAddress localAddress = InetAddress.getLocalHost();
/*
 *  What is the Socket used for?  What parameters are required to
instantiate it?

Sockets are endpoints for communication between two machines
this socket connects to the local host address at the specified port 
Socket class requires the host to connect to and the port number. 
 */
Socket clientSocket = new Socket(localAddress, 6000);
System.out.println("Successfully connected to the server");
/*
 *  What are the BufferedReader and PrintWriter classes used for?
 * 
 * BufferedReader reads text from a char input stream. buffering helps to provide efficient reading
 * BufferedReader gets the input stream from the client socket
 * 
 * PrintWriter is used to print formatted representations of objects to a text output stream
 * PrintWriter outputs the stream 
 */
BufferedReader streamIn = new BufferedReader(new 
InputStreamReader(clientSocket.getInputStream()));
PrintWriter streamOut = new 
PrintWriter(clientSocket.getOutputStream(), true);
Scanner consoleIn = new Scanner(System.in);
String ind = "";
/*
 *  What functionality does this loop construct support?
 * 
 *  
 * this will loop until either the user types "X" or all variables are entered 
 * 
 */
do {
/*
 *  What are these variables used for?
 * 
 * set the initial values of these variables
 * these variables are used in the user data entry for interest calculations
 */
double initBal = 0.0;
double rate = 0.0;
int term = 0;
/*
 *  How are these values populated?
 * 
 * user input 
 */
initBal = validatePosDoubleInput("Enter your initial account balance: ", consoleIn);
rate = validatePosDoubleInput("Enter your annual interest rate: ", consoleIn);
term = validatePosIntegerInput("Enter your investment term (in years): ", consoleIn);

//Reset the scanner in the client console
consoleIn.nextLine();
/*
 * What happens with the println() method is invoked?
 *  each println() outputs the validatePosDoubleInput prompt and then takes in the user data for each var
 */
streamOut.println(initBal);
streamOut.println(rate);
streamOut.println(term);
streamOut.println("X");
/*
 *  What is this variable used for?
 *  used to store each line that is read in 
 */
String serverMsg = "";
/*
 *  What functionality does this loop construct support?
 *  this reads in all of the user entered data. Will stop the program if users quits.
 * outputs the server response message 
 * 
 * 
 *  What is the readLine() method used for?
 *  reads in every line from the serverMsg until it is empty.
 * reads in ome line at a time
 * if the user enters X, program will stop
 */
while ((serverMsg = streamIn.readLine()) != null) {
if (serverMsg.toUpperCase().equals("X")) {
break;
}
System.out.println("Server response is: " + serverMsg);
}
/*
 *  What functionality does this loop construct support?
 * 
 * checks for user input as to whether quit program or continue
 * checks for possible errors if correct response isnt given by user 
 */
do {
ind = validateStringInput("Do you want to perform another calculation?.  Enter Y to continue. Enter X to quit.", consoleIn);
} while ((!ind.toUpperCase().equals("Y")) && ! 
ind.toUpperCase().equals("X"));
} while ((!ind.toUpperCase().equals("X")));
System.out.println("Client stopped");
//What is the exit() method used for?
// quits/ stops the program
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
/*  
 *  Describe the functionality of the validatePosIntegerInput() method
 * This method checks to see if the user entered data is a positive integer
 *  returns the prompt if the user entered data is not a positive int
 */
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
/*  
 *  Describe the functionality of the validatePosDoubleInput() method
 * checks to see if the user entered data is a positive double
 * returns the prompt if the user entered data is not a positive double
 */
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
/*  
 *  Describe the functionality of the validateStringInput() method
 * checks if the user enterd data is a string 
 * returns prompt if the entered data is not a string 
 */
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