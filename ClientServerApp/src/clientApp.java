/**
 *
 * Justin Goshorn
 * IST 411/MIS 466
 *
 * 1/25/2022
 */

import java.net.*;
import java.io.*;
import java.util.*;

public class clientApp {

    public static void main(String[] args) {
        System.out.println("Client started");
        try {
            System.out.println("Waiting for connection...");

            // gets local host address
            InetAddress localAddress = InetAddress.getLocalHost();

            // connects to the address of the local server
            Socket clientSocket = new Socket(localAddress, 6000);
            System.out.println("Connected to the server");

            // access incoming server messages
            InputStreamReader inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
            BufferedReader streamIn = new BufferedReader(inputStreamReader);

            // outgoing messages to server
            PrintWriter streamOut = new PrintWriter(clientSocket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);

            // user input string for Y or X
            String userInput = "";
            // string for the messages from the server
            String serverMessage;
            //
            performGradeCalc(scanner, streamOut, userInput);
            // prints return message from server until 'N' is received
            while ((serverMessage = streamIn.readLine()) != null){
                // will rerun grade calculator when server prints N
                // there has to be a better way to do this...
                if(serverMessage.toUpperCase().equals("N")){
                    userInput = validateString("Perform another calculation? Enter Y or N. ", scanner);
                    if (userInput.toUpperCase().equals("Y")){
                        performGradeCalc(scanner,streamOut,userInput);
                    }
                    else {
                        break;
                    }
                }
                // prints the messages from the server to the console
                System.out.println("Server response is: " + serverMessage);

            }

        }catch (SocketException exception){
            exception.printStackTrace();
            System.out.println("Client stopped");
            System.exit(0);

        } catch (UnknownHostException e) {
            e.printStackTrace();

        } catch (IOException e) {
            System.out.println("An exception occurred");
            e.printStackTrace();
        }
    }

    // main program function
    public static void performGradeCalc(Scanner scanner, PrintWriter streamOut,String userInput){
        // loop runs until user enters X
        do {
            // variable for holding the user entered integers
            int grade;
            // out msg and read in from scanner of user entered input
            grade = validatePositiveInt("Enter an assignment grade (ex: 85): ", scanner);
            // goes to next line
            scanner.nextLine();
            //sends entered grade to server side
            streamOut.println(grade);

            // do loops runs until user enters a Y or N
            do {
                // out msg to continue entering variables or end input and perform calculation
                userInput = validateString("Enter Y to add another grade or N to calculate final grade?",scanner);

            }while ((!userInput.toUpperCase().equals("Y")) && !userInput.toUpperCase().equals("N"));




        }while ((!userInput.toUpperCase().equals("N")));
        // sends N to the server
        streamOut.println("N");
    }

    // positive int validation
    public static int validatePositiveInt(String message, Scanner scanner) {
        int num = 0;
        do {
            System.out.println(message);
            while (!scanner.hasNextInt() || !scanner.hasNextDouble()) {
                System.out.println("The server was looking for a number as a response. \n" + message);
                scanner.next();
            }
            num = scanner.nextInt();
        } while (num < 0);
        return num;
    }
    // string validation
    public static String validateString(String message, Scanner scanner) {
        boolean isValid = false;
        String c;
        do {
            System.out.println(message);
            while (scanner.hasNextDouble() || scanner.hasNextInt()) {
                System.out.println("The server is looking for either a 'Y' or a 'N' as a response. \n" + message);
                scanner.nextLine();
            }
            c = scanner.nextLine();
            isValid = true;
        } while (!isValid);
        return c;
    }

}


