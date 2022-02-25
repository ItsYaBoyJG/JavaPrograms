/**
 *
 * Justin Goshorn
 * IST 411/MIS 466
 *
 * 1/25/2022
 */

import java.net.*;
import java.io.*;
import java.text.DecimalFormat;

public class serverApp {

    public static void main(String[] args) {
        try {
            //  string for user input from client side
            String clientInput;
            // variable for user entered grade numbers. conversion of clientInput to a double
            double userEnteredGrade = 0.0;
            // calculation variable total/count
            double average = 0.0;
            // for tracking number of grades entered
            int count = 0;
            // letter grade
            String letterGrade = "";
            //total of all grades entered
            double total = 0.0;
            // sets decimal format
            DecimalFormat decimalFormat = new DecimalFormat("0.00");



            // prints when server is started
            System.out.println("The server has started");
            // creates the socket that will listen for requests at this port
            ServerSocket serverSocket = new ServerSocket(6000);

            System.out.println("Waiting to connect...");
            // waiting for the request from the client
            Socket clientSocket = serverSocket.accept();
            System.out.println("Connection is successful to a client");
            // accesses incoming msg from client
            InputStreamReader inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
            BufferedReader streamInReader = new BufferedReader(inputStreamReader);
            // access outgoing msg to client
            PrintWriter streamOutput = new PrintWriter(clientSocket.getOutputStream(), true);

            //
            do {
                //
                while ((clientInput = streamInReader.readLine()) != null) {
                    // ends while if user enters N
                    if (clientInput.toUpperCase().equals("N")) {
                        break;
                    }
                        // print statement to track incoming msg from client side
                        System.out.println("Incoming message from client is: " + clientInput);

                        // converts clientInput to a double
                        userEnteredGrade = Double.parseDouble(clientInput);
                        // adds userEnteredGrade to total after each entry
                        total =  total + userEnteredGrade;
                        // counts times user entered a grade
                        count++;
                        // calculates average
                        average = total / count;
                        // print statements to track variables
                        System.out.println("Grade " + userEnteredGrade);
                        System.out.println("Total " + total);
                        System.out.println("Count " + count);
                        System.out.println("AVG " + average);


                }
               // sends average and letter grade to client
                streamOutput.println("The average grade percentage is: " + decimalFormat.format(average));
                streamOutput.println("The letter grade is : " + getLetterGrade(average,letterGrade));

                // ends server
                streamOutput.println("N");

            } while (true);

        } catch (SocketException exception) {
            exception.printStackTrace();
            System.out.println("Server has stopped");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static String getLetterGrade(double average, String letterGrade){
            if(average >= 90 && average <= 100 ) {
                letterGrade = "A";
            } else if(average >= 80 && average <= 89.99 ) {
                letterGrade = "B";
            } else if(average >= 70 && average <= 79.99 ) {
                letterGrade = "C";
            } else if(average >= 60 && average <= 69.99 ) {
                letterGrade = "D";
            } else
                letterGrade = "F";
        return letterGrade;
    }


}

