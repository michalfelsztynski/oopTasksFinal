package pl.wsb.oop.model.device;

public class DieselCar extends Car {
    private static final String REFUEL = "diesel car refueld";
    private static final String DIESEL = "real torque beast - diesel car ";

    public DieselCar(String producer, String model, Double value, Integer yearOfProduction, Double sizeOfAnEngine) {
        super(producer, model, value, yearOfProduction, sizeOfAnEngine);
    }

    @Override
    public void refuel() {
        System.out.println(REFUEL);
    }

    public String toString() {
        return DIESEL + super.toString();
    }
}
