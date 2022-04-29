/**
 * Justin Goshorn
 *  IST411/MIS466
 *  Lab 2
 */

import java.io.*;
import java.net.*;
import java.text.DecimalFormat;
import java.util.*;


public class VehicleClient {
    public static void main(String[] args) {

        try{
            // client started msg
            System.out.println("Client Started.");
            // waiting to connect msg
            System.out.println("Waiting for Server Connection");

            // sets up server and client communication
            InetAddress localAddress = InetAddress.getLocalHost();
            Socket clientSocket = new Socket(localAddress, 7000);

            System.out.println("Successfully connected to server");


            // read from server
            ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            // flushes stream
            outputStream.flush();
            // input stream from socket
            ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());

            // read in from console
            Scanner scanner = new Scanner(System.in);

            // var for checking if client wishes to continue to run
            String input = "";

            do {
                // vehicle instance
                Vehicle vehicle = new Vehicle();
                // sets decimal format to be sure that input from server is displayed correctly
                DecimalFormat decimalFormat = new DecimalFormat("0.00");

                //get user inputs
                vehicle.setVehicleMake(validateString("Enter vehicle make: ", scanner));
                vehicle.setVehicleModel(validateString("Enter vehicle model: ", scanner));
                vehicle.setVehicleYear(validatePositiveInteger("Enter vehicle year: ", scanner));
                vehicle.setVehiclePurchasePrice(validatePositiveDouble("Enter vehicle purchase price: ", scanner));
                vehicle.setFuelTankCapacity(validatePositiveInteger("Enter the fuel tank capacity: ", scanner));
                vehicle.setFuelEfficiency(validatePositiveDouble("Enter the vehicle fuel efficiency: ", scanner));
                vehicle.setAnnualMilesDriven(validatePositiveInteger("Enter the annual miles Driven: ", scanner));
                vehicle.setFuelPrice(validatePositiveDouble("Enter fuel price per gallon:", scanner));
                vehicle.setVehicleLifeExpectancy(validatePositiveInteger("Enter vehicle life expectancy: ", scanner));

                // reset console
                scanner.nextLine();

                // pass vehicle instance to server
                outputStream.writeObject(vehicle);

                // get return values from server
                while ((vehicle = (Vehicle)inputStream.readObject()) != null){
                    // server responses
                    System.out.println(" ");
                    System.out.println("Annual cost of fuel consumed is: $" + decimalFormat.format(vehicle.getAnnualFuelConsumed()));
                    System.out.println("Annual fuel cost is: $" + decimalFormat.format(vehicle.getAnnualFuelCost()));
                    System.out.println("Lifetime operating cost of the Vehicle is: $" + decimalFormat.format(vehicle.getLifetimeOperatingCost()));
                    System.out.println("Vehicle total ownership cost is: $" + decimalFormat.format(vehicle.getTotalVehicleOwnershipCost()));


                    break;

                }
                //Ask the user if they want to continue
                do {
                    input = validateString("Enter 'Y' to continue or 'X' to quit. ", scanner);
                } while ((!input.toUpperCase().equals("Y")) && !input.toUpperCase().equals("X"));
            }while ((!input.toUpperCase().equals("X")));

            // close the connection with the server
            clientSocket.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    // check user input for valid integer
    public static int validatePositiveInteger(String message, Scanner scanner) {
        int num = 0;

        do {
            System.out.println(message);
            while (!scanner.hasNextInt()){
                System.out.println("The server expected a numeric value.\n" + message);
                scanner.next();
            }
            num = scanner.nextInt();
        } while(num < 0);
        return num;
    }
    // check user input for valid double
    public static double validatePositiveDouble(String message, Scanner scanner) {
        double num = 0.0;
        do {
            System.out.println(message);
            while (!scanner.hasNextDouble()) {
                System.out.println("The server expected a numeric value.\n" + message);
                scanner.next();
            }
            num = scanner.nextDouble();
        } while (num < 0);
        return num;
    }
    // check user input for valid strings
    public static String validateString(String message, Scanner scanner) {
        boolean valid = false;
        String var = "";
        do {
            System.out.println(message);
            while (scanner.hasNextDouble() || scanner.hasNextInt()) {
                System.out.println("The server expects a non-numeric value.\n" + message);
                scanner.nextLine();
            }
            var = scanner.nextLine();
            valid = true;
        } while (!valid);
        return var;
    }
}
