package pl.wsb.oop.model.device;

public class ElectricCar extends Car {
    private static final String JUICED = "Juiced";
    private static final String BATTERY = "battery kids toy";

    public ElectricCar(String producer, String model, Integer yearOfProduction) {
        super(producer, model, null, yearOfProduction, null);
    }

    @Override
    public void refuel() {
        System.out.println(JUICED);
    }

    public String toString() {
        return BATTERY + super.toString();
    }
}
