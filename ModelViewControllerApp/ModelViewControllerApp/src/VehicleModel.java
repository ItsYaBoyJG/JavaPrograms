import java.text.DecimalFormat;

/**
 * Justin Goshorn
 * MIS 466
 * Lab: Model View Controller App
 * 2022-04-25
 * */

public class VehicleModel {
    private String vehicleMakeAndModel;
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



    public VehicleModel(String vMakeModel, int vYear, double pPrice, int miles,
                        int fCapacity, double fPrice, double fEfficiency, double vLife, double fCost, double fConsumed,
                        double operatingCost, double ownershipCost) {
        setVehicleMakeAndModel(vMakeModel);
        setVehicleYear(vYear);
        setVehiclePurchasePrice(pPrice);
        setAnnualMilesDriven(miles);
        setFuelTankCapacity(fCapacity);
        setFuelPrice(fPrice);
        setFuelEfficiency(fEfficiency);
        setVehicleLifeExpectancy(vLife);
        setAnnualFuelConsumed(fConsumed);
        setAnnualFuelCost(fCost);
        setLifetimeOperatingCost(operatingCost);
        setTotalVehicleOwnershipCost(ownershipCost);
    }
    // class constructor
    public VehicleModel() {

    }

    public void setVehicleMakeAndModel(String vehicleMakeAndModel) {
        //
        this.vehicleMakeAndModel = vehicleMakeAndModel;
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

    public void setVehicleLifeExpectancy(double vehicleLifeExpectancy) {

        this.vehicleLifeExpectancy = vehicleLifeExpectancy;
    }

    public double getAnnualFuelConsumed(){

        return annualFuelConsumed;
    }

    public void setAnnualFuelConsumed(double annualFuelConsumed) {

        this.annualFuelConsumed = annualFuelConsumed;
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

    public void setTotalVehicleOwnershipCost(double totalVehicleOwnershipCost) {

        this.totalVehicleOwnershipCost = totalVehicleOwnershipCost;
    }

    public String getTotalOwnershipCostAsString() {
        System.out.print("Total Cost Ran \n");
        System.out.println(totalVehicleOwnershipCost + "\n");
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(totalVehicleOwnershipCost);
    }
    public String getLifeTimeOperatingCostAsString() {
        System.out.print("Lifetime Ran \n");
        System.out.println(lifetimeOperatingCost + "\n");
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(lifetimeOperatingCost);
    }
    public String getAnnualFuelConsumedAsString() {
        System.out.print("Annual fuel consumption ran \n");
        System.out.println(annualFuelConsumed + "\n");
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(annualFuelConsumed);
    }
    public String getAnnualFuelCostAsString() {
        System.out.print("Annual Fuel Cost Ran \n");
        System.out.println(annualFuelCost + "\n");
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(annualFuelCost);
    }

    public void calculateLifetimeOperatingCost() {

        this.lifetimeOperatingCost = this.getVehicleLifeExpectancy() * this.getAnnualFuelConsumed();
    }
    public void calculateAnnualFuelConsumed() {

        this.annualFuelConsumed = this.getAnnualMilesDriven() / this.getFuelEfficiency();
    }
    public void calculateAnnualFuelCost() { //

        this.annualFuelCost = this.getFuelPrice() * this.getAnnualFuelConsumed();
    }
    public void calculateVehicleOwnershipCost() {

        this.totalVehicleOwnershipCost = this.getVehiclePurchasePrice() + this.getLifetimeOperatingCost();
    }
}
