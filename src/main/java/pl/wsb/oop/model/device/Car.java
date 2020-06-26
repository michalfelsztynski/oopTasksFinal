package pl.wsb.oop.model.device;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.wsb.oop.model.animal.Human;
import pl.wsb.oop.model.common.Soldable;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@ToString
public abstract class Car extends Device implements Soldable, Comparable<Car> {
    private static final String TURN_ON = "engine running";
    private static final String CAR_NOT_FOUND = "car not found in seller garage";
    private static final String NO_SPACE = "no free space at buyer garage";
    private static final String TO_EXPENSIVE = "buyer cannot afford";
    private static final String SUCCESSFUL = "car sold - transaction successful";
    private static final String NOT_ACTUAL_OWNER = "seller is not an actual owner";

    private final Integer yearOfProduction;
    private final Double sizeOfAnEngine;
    private String plates;
    private List<Human> owners = new LinkedList<>();

    public Car(String producer, String model, Double value, Integer yearOfProduction, Double sizeOfAnEngine) {
        super(producer, model, value);
        this.yearOfProduction = yearOfProduction;
        this.sizeOfAnEngine = sizeOfAnEngine;

    }

    abstract public void refuel();

    @Override
    public void turnOn() {
        System.out.println(TURN_ON);
    }

    @Override
    public int compareTo(Car otherCar) {
        return this.yearOfProduction - otherCar.yearOfProduction;
    }

    @Override
    public void sell(Human buyer, Human seller, Double price) throws Exception {
        if (!seller.hasACar(this)) {
            throw new Exception(CAR_NOT_FOUND);
        }
        if (!buyer.hasAFreePlace()) {
            throw new Exception(NO_SPACE);
        }
        if (buyer.cash < price) {
            throw new Exception(TO_EXPENSIVE);
        }
        if (isActualOwner(seller)){
            throw new Exception(NOT_ACTUAL_OWNER);
        }

        owners.add(owners.size(),buyer);
        buyer.removeCar(this);
        seller.setCar(this);
        buyer.cash -= price;
        seller.cash += price;
        System.out.println(SUCCESSFUL);
    }

    private boolean isActualOwner(Human owner){
        return owners.get(owners.size()-1)!=owner;
    }

    private boolean isHistoricalOwner(Human owner){
        return owners.contains(owner);
    }

    public boolean hadTransationBefore(Human seller, Human buyer){
        return owners.containsAll(List.of(seller,buyer));
    }

    public int numberOfSales(){
        return owners.size();
    }


}
