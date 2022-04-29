/**
 * Justin Goshorn
 *  IST411/MIS466
 *  Lab 2
 */

import java.io.*;

public class Vehicle implements Serializable {
    private String vehicleMake;
    private String vehicleModel;
    private int vehicleYear;
    private double vehiclePurchasePrice;
    private int annualMilesDriven;
    private int fuelTankCapacity;
    private double fuelPrice;
    private double fuelEfficiency;
    private double vehicleLifeExpectancy;
    private double annualFuelConsumed;
    private double annualFuelCost;
    private double lifetimeOperatingCost;
    private double totalVehicleOwnershipCost;



    public Vehicle(String vMake, String vModel, int vYear, double pPrice, int miles,
                   int fCapacity, double fPrice, double fEfficiency, double vLife, double fCost, double fConsumed,
                   double operatingCost, double ownershipCost) {
        vehicleMake = vMake;
        vehicleModel = vModel;
        vehicleYear = vYear;
        vehiclePurchasePrice = pPrice;
        annualMilesDriven = miles;
        fuelTankCapacity = fCapacity;
        fuelPrice = fPrice;
        fuelEfficiency = fEfficiency;
        vehicleLifeExpectancy = vLife;
        annualFuelConsumed = fConsumed;
        annualFuelCost = fCost;
        lifetimeOperatingCost = operatingCost;
        totalVehicleOwnershipCost = ownershipCost;
    }
    // class constructor
    public Vehicle() {

    }

    public String getVehicleMake(){

        return vehicleMake;
    }

    public void setVehicleMake(String vehicleMake){

        this.vehicleMake = vehicleMake;
    }

    public String getVehicleModel() {

        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {

        this.vehicleModel = vehicleModel;
    }

    public int getVehicleYear() {

        return vehicleYear;
    }

    public void setVehicleYear(int vehicleYear) {

        this.vehicleYear = vehicleYear;
    }

    public double getAnnualMilesDriven() {

        return annualMilesDriven;
    }

    public double getVehiclePurchasePrice() {

        return vehiclePurchasePrice;
    }

    public void setVehiclePurchasePrice(double vehiclePurchasePrice) {

        this.vehiclePurchasePrice = vehiclePurchasePrice;
    }

    public void setAnnualMilesDriven(int annualMilesDriven) {

        this.annualMilesDriven = annualMilesDriven;
    }

    public int getFuelTankCapacity() {

        return fuelTankCapacity;
    }

    public void setFuelTankCapacity(int fuelTankCapacity) {

        this.fuelTankCapacity = fuelTankCapacity;
    }

    public double getFuelPrice(){

        return fuelPrice;
    }

    public void setFuelPrice(double fuelPrice) {

        this.fuelPrice = fuelPrice;
    }

    public double getFuelEfficiency() {

        return fuelEfficiency;
    }

    public void setFuelEfficiency(double fuelEfficiency) {

        this.fuelEfficiency = fuelEfficiency;
    }

    public double getVehicleLifeExpectancy() {

        return vehicleLifeExpectancy;
    }

    public void setVehicleLifeExpectancy(int vehicleLifeExpectancy) {

        this.vehicleLifeExpectancy = vehicleLifeExpectancy;
    }

    public double getAnnualFuelConsumed(){

        return annualFuelConsumed;
    }

    public void setAnnualFuelConsumed(double annualFuelConsumed) {

        this.annualFuelConsumed = annualFuelConsumed;
    }

    public double getAnnualFuelCost() {

        return annualFuelCost;
    }

    public void setAnnualFuelCost(double annualFuelCost) {

        this.annualFuelCost = annualFuelCost;
    }

    public double getLifetimeOperatingCost() {

        return lifetimeOperatingCost;
    }

    public void setLifetimeOperatingCost(double lifetimeOperatingCost) {

        this.lifetimeOperatingCost = lifetimeOperatingCost;
    }

    public double getTotalVehicleOwnershipCost() {

        return totalVehicleOwnershipCost;
    }

    public void setTotalVehicleOwnershipCost(double totalVehicleOwnershipCost) {

        this.totalVehicleOwnershipCost = totalVehicleOwnershipCost;
    }
}
