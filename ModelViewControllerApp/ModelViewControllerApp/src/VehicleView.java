/**
 * Justin Goshorn
 * MIS 466
 * Lab: Model View Controller App
 * 2022-04-25
 * */

import javax.swing.*;
import java.awt.event.ActionListener;

public class VehicleView extends JFrame {
    // text fields for user entry
    JTextField vehicleMakeAndModelTextField;
    JTextField vehicleYearTextField;
    JTextField vehicleLifeExpectancyTextField;
    JTextField annualMilesDrivenTextField;
    JTextField fuelPricePerGalTextField;
    JTextField fuelEfficiencyTextField;
    JTextField fuelTankCapacityTextField;
    JTextField purchasePriceTextField;

    // calculation output fields
    JTextField annualFuelCostTextField;
    JTextField lifetimeOperatingCostTextField;
    JTextField totalVehicleOwnershipCostTextField;
    JTextField annualFuelConsumptionTextField;

    // labels for questions to users
    JLabel vehicleMakeAndModelLabel = new JLabel("Enter vehicle make and model");
    JLabel vehicleYearLabel = new JLabel("Enter vehicle year");
    JLabel vehicleLifeExpectancyLabel = new JLabel("Enter vehicle life expectancy");
    JLabel annualMilesDrivenLabel = new JLabel("Enter annual miles driven");
    JLabel fuelPricePerGalLabel = new JLabel("Enter fuel price per gallon ($)");
    JLabel fuelEfficiencyLabel = new JLabel("Enter fuel efficiency (mpg)");
    JLabel fuelTankCapacityLabel = new JLabel("Enter fuel tank capacity");
    JLabel purchasePriceLabel = new JLabel("Enter vehicle purchase price");
    JLabel lifetimeOperatingCostLabel = new JLabel("Lifetime vehicle operating costs are:");
    JLabel totalVehicleOwnershipCostLabel = new JLabel("Total vehicle ownership cost is: ");
    JLabel annualFuelCostLabel = new JLabel("The annual fuel cost is:");
    JLabel annualFuelConsumptionLabel = new JLabel("The annual fuel consumption is:");

    // calculate button
    JButton calculateButton;



    VehicleModel vehicleModel;

    VehicleView(VehicleModel vehicleModel) {
        this.vehicleModel = vehicleModel;

        //create panel
        JPanel panel = new JPanel();
        //sets layout
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // adds text fields and labels to panel
        panel.add(vehicleMakeAndModelLabel);
        vehicleMakeAndModelTextField = new JTextField();
        panel.add(vehicleMakeAndModelTextField);

        panel.add(vehicleYearLabel);
        vehicleYearTextField = new JTextField();
        panel.add(vehicleYearTextField);

        panel.add(purchasePriceLabel);
        purchasePriceTextField = new JTextField();
        panel.add(purchasePriceTextField);

        panel.add(fuelTankCapacityLabel);
        fuelTankCapacityTextField = new JTextField();
        panel.add(fuelTankCapacityTextField);

        panel.add(fuelEfficiencyLabel);
        fuelEfficiencyTextField = new JTextField();
        panel.add(fuelEfficiencyTextField);

        panel.add(annualMilesDrivenLabel);
        annualMilesDrivenTextField = new JTextField();
        panel.add(annualMilesDrivenTextField);

        panel.add(fuelPricePerGalLabel);
        fuelPricePerGalTextField = new JTextField();
        panel.add(fuelPricePerGalTextField);

        panel.add(vehicleLifeExpectancyLabel);
        vehicleLifeExpectancyTextField = new JTextField();
        panel.add(vehicleLifeExpectancyTextField);

        //adds calculate button to panel
        calculateButton = new JButton("Calculate");
        panel.add(calculateButton);

        //creates separators for better gui display of results
        panel.add(new JSeparator(SwingConstants.HORIZONTAL));
        panel.add(new JSeparator(SwingConstants.HORIZONTAL));

        // adds calculation result text fields and labels to panel
        panel.add(annualFuelConsumptionLabel);
        annualFuelConsumptionTextField = new JTextField();
        panel.add(annualFuelConsumptionTextField);

        panel.add(annualFuelCostLabel);
        annualFuelCostTextField = new JTextField();
        panel.add(annualFuelCostTextField);

        panel.add(lifetimeOperatingCostLabel);
        lifetimeOperatingCostTextField = new JTextField();
        panel.add(lifetimeOperatingCostTextField);

        panel.add(totalVehicleOwnershipCostLabel);
        totalVehicleOwnershipCostTextField = new JTextField();
        panel.add(totalVehicleOwnershipCostTextField);

        this.setContentPane(panel);
        this.pack();
        this.setSize(400, 650);
        this.setTitle("Vehicle Cost Calculator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // getter methods to getText() from gui text fields
    public String getLifeExpectancy() {
        return vehicleLifeExpectancyTextField.getText();
    }
    public String getVehicleMakeAndModel() {
        return vehicleMakeAndModelTextField.getText();
    }
    public String getVehicleYear() {
        return vehicleYearTextField.getText();
    }
    public String getAnnualMilesDriven() {
        return annualMilesDrivenTextField.getText();
    }
    public String getFuelEfficiency() {
        return fuelEfficiencyTextField.getText();
    }
    public String getFuelPricePerGallon() {
        return fuelPricePerGalTextField.getText();
    }
    public String getFuelTankCapacity() {
        return fuelTankCapacityTextField.getText();
    }
    public String getVehiclePurchasePrice() {
        return purchasePriceTextField.getText();
    }

    // setter methods to setText() of the calculation fields in gui
    public void setAnnualFuelCostString(String annualFuelCost) {//

        annualFuelCostTextField.setText(annualFuelCost);
    }
    public void setAnnualFuelConsumedString(String annualFuelConsumed) {

        annualFuelConsumptionTextField.setText(annualFuelConsumed);
    }
    public void setLifetimeOperatingCostString(String lifetimeCost) {

        lifetimeOperatingCostTextField.setText(lifetimeCost);
    }
    public void setVehicleOwnershipCostString(String ownershipCost) {

        totalVehicleOwnershipCostTextField.setText(ownershipCost);
    }
    //
    public void addCalcButton(ActionListener listener) {
        calculateButton.addActionListener(listener);
    }
}
