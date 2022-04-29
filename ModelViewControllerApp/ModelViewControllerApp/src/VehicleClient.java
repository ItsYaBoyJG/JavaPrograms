/**
 * Justin Goshorn
 * MIS 466
 * Lab: Model View Controller App
 * 2022-04-25
 * */

public class VehicleClient {
    public static void main(String[] args) {
        VehicleModel vehicleModel = new VehicleModel();
        VehicleView vehicleView = new VehicleView(vehicleModel);
        VehicleController vehicleController = new VehicleController(vehicleModel, vehicleView);

        vehicleView.setVisible(true);
    }
}
