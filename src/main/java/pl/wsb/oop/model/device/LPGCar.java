package pl.wsb.oop.model.device;

public class LPGCar extends Car {
    private static final String REFUEL = "LPG car refueld";
    private static final String LPG = "smells like an owen ";

    public LPGCar(String producer, String model, Double value, Integer yearOfProduction, Double sizeOfAnEngine) {
        super(producer, model, value, yearOfProduction, sizeOfAnEngine);
    }

    @Override
    public void refuel() {
        System.out.println(REFUEL);
    }

    public String toString() {
        return LPG + super.toString();
    }

}
