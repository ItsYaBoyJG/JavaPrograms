/**
 * Justin Goshorn
 *  IST411/MIS466
 *  Lab 2
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.text.DecimalFormat;

public class VehicleServer implements Runnable {

    public static Socket clientSocket;

    // constructor for client connection
    public VehicleServer(Socket vClientSocket){

        clientSocket = vClientSocket;
    }

    public static void main(String[] args) {

        try {
            // server started msg
            System.out.println("Server started.");

            // sets up socket to listen to clients
            ServerSocket serverSocket = new ServerSocket(7000);

            System.out.println("Waiting for a connection to the client");

            while (true){
                // assigns current client connection to socket
                clientSocket = serverSocket.accept();

                // display message on successful connection
                System.out.println("Successfully connect to the client");

                // server instance and new thread for each client
                VehicleServer vehicleServer = new VehicleServer(clientSocket);

                // to call run method
                new Thread(vehicleServer).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // run() creates the threads for the server
    @Override
    public void run() {

        try{
            // thread started msg
            System.out.println("Connected to a client using thread: " + Thread.currentThread());

            // read from client
            ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());
            // write to client
            ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());


            do {
                // Vehicle instance
                Vehicle vehicle = new Vehicle();
                // sets decimal format for server outputs
                DecimalFormat decimalFormat = new DecimalFormat("0.00");


                try {
                    // get instance
                    while ((vehicle = (Vehicle)inputStream.readObject()) != null) {
                        // Display values passed from client
                        System.out.println("Incoming vehicle make is: " + Thread.currentThread() + " is " + vehicle.getVehicleMake());
                        System.out.println("Incoming vehicle model is: " + Thread.currentThread() + " is " + vehicle.getVehicleModel());
                        System.out.println("Incoming vehicle year is: " + Thread.currentThread() + " is " + vehicle.getVehicleYear());
                        System.out.println("Incoming vehicle purchase price is: " + Thread.currentThread() + " is " + vehicle.getVehiclePurchasePrice());
                        System.out.println("Incoming vehicle fuel tank capacity is: " + Thread.currentThread() + " is " + vehicle.getFuelTankCapacity());
                        System.out.println("Incoming vehicle fuel efficiency is: " + Thread.currentThread() + " is " + vehicle.getFuelEfficiency());
                        System.out.println("Incoming vehicle annual miles driven is: " + Thread.currentThread() + "is " + vehicle.getAnnualMilesDriven());
                        System.out.println("Incoming fuel price per gallon is: " + Thread.currentThread() + " is " + vehicle.getFuelPrice());
                        System.out.println("Incoming vehicle life expectancy is: " + Thread.currentThread() + " is " + vehicle.getVehicleLifeExpectancy());

                        // Calculate values to send to client
                        vehicle.setAnnualFuelConsumed(vehicle.getAnnualMilesDriven() / vehicle.getFuelEfficiency());
                        vehicle.setAnnualFuelCost(vehicle.getFuelPrice() * vehicle.getAnnualFuelConsumed());
                        vehicle.setLifetimeOperatingCost(vehicle.getVehicleLifeExpectancy() * vehicle.getAnnualFuelCost());
                        vehicle.setTotalVehicleOwnershipCost(vehicle.getVehiclePurchasePrice() + vehicle.getLifetimeOperatingCost());

                        // prints calculations to server
                        System.out.println(" ");
                        System.out.println("Calculated totals for client: ");
                        System.out.println("Outgoing Fuel Consumed" + decimalFormat.format(vehicle.getAnnualFuelConsumed()));
                        System.out.println("Outgoing fuel cost: " + decimalFormat.format(vehicle.getAnnualFuelCost()));
                        System.out.println("Outgoing lifetime cost: " + decimalFormat.format(vehicle.getLifetimeOperatingCost()));
                        System.out.println("Outgoing ownership cost: " + decimalFormat.format(vehicle.getTotalVehicleOwnershipCost()));

                        // sends instance to client
                        outputStream.writeObject(vehicle);




                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();

                    break;
                }
            }while (true);

            System.out.println(Thread.currentThread() + " terminated");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
