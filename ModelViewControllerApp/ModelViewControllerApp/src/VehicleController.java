import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Justin Goshorn
 * MIS 466
 * Lab: Model View Controller App
 * 2022-04-25
 * */

public class VehicleController {
    private VehicleModel vehicleModel;
    private VehicleView vehicleView;

    VehicleController(VehicleModel vehicleModel, VehicleView vehicleView){
        this.vehicleModel = vehicleModel;
        this.vehicleView = vehicleView;


        class ButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent event){
                //get user input
                vehicleModel.setVehicleMakeAndModel(vehicleView.getVehicleMakeAndModel());
                vehicleModel.setVehicleYear(Integer.parseInt(vehicleView.getVehicleYear()));
                vehicleModel.setAnnualMilesDriven(Integer.parseInt(vehicleView.getAnnualMilesDriven()));
                vehicleModel.setVehiclePurchasePrice(Double.parseDouble(vehicleView.getVehiclePurchasePrice()));
                vehicleModel.setFuelTankCapacity(Integer.parseInt(vehicleView.getFuelTankCapacity()));
                vehicleModel.setFuelPrice(Double.parseDouble(vehicleView.getFuelPricePerGallon()));
                vehicleModel.setFuelEfficiency(Double.parseDouble(vehicleView.getFuelEfficiency()));
                vehicleModel.setVehicleLifeExpectancy(Double.parseDouble(vehicleView.getLifeExpectancy()));

                //perform calculations
                vehicleModel.calculateAnnualFuelConsumed();
                vehicleModel.calculateAnnualFuelCost();
                vehicleModel.calculateLifetimeOperatingCost();
                vehicleModel.calculateVehicleOwnershipCost();

                // outputs calculation results as strings
                vehicleView.setAnnualFuelConsumedString(vehicleModel.getAnnualFuelConsumedAsString());
                vehicleView.setAnnualFuelCostString(vehicleModel.getAnnualFuelCostAsString());
                vehicleView.setLifetimeOperatingCostString(vehicleModel.getLifeTimeOperatingCostAsString());
                vehicleView.setVehicleOwnershipCostString(vehicleModel.getTotalOwnershipCostAsString());

            }
        }
        vehicleView.addCalcButton(new ButtonListener());


    }
}
