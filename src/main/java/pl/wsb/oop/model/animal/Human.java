package pl.wsb.oop.model.animal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.wsb.oop.model.device.Car;
import pl.wsb.oop.model.device.Phone;

@Getter
@Setter
@ToString
public class Human extends Animal {

    private static final String HUMAN = "homo sapiens";
    private static final String FORBIDDEN = "cannot do this to human";
    private static final String SALARY_15 = "Congratulations your salary increased 15% | Current Salary: ";
    private static final String SALARY_10 = "Congratulations your salary increased 10% | Current Salary: ";
    private static final String SALARY_5 = "You salary increased 5% | Current Salary: ";
    private static final String SALARY_0 = "You dint't get enough points for the raise | Current Salary: ";
    private static final String WRONG_ARG = "The annual mark is an Integer from 2 to 5";
    private static final String HUMAN_EAT = "I'm a human I will use fork and knife";
    private static final String NEGATIVE = "Hey what's wrong with you salary cannot be negative";
    private static final String BLA_BLA = "New data was sent to accounting system \nIt's necessary to take annex to agreement of Ms. Joanna from the office \nHealthy Insurence and Tax Office got to know of salary change and there is no use to hide your real income";
    private static final String BY_CASH = "Bought by cash";
    private static final String ON_CREDIT = "bought on credit";
    private static final String GET_BETTER_JOB = "Get better job u cannot buy this car";
    private static final String NO_SPACE = "no space for new car in garage";

    private String firstName;
    private String lastName;
    private Pet pet;
    private Phone mobile;
    private Car[] garage;

    public Animal[] farm;

    protected String phoneNumber;
    private Double salary;
    public Double cash = 200.0;

    private static final int DEFAULT_FARM_SIZE = 3;
    private static final int DEFAULT_GARAGE_SIZE = 3;
    private static Double DEFAULT_FEED_WEIGHT = 1.5;

    public Human(Integer farmSize, Integer garageSize) {
        super(HUMAN);
        this.farm = new Animal[farmSize];
        this.garage = new Car[garageSize];
    }

    public Human(Integer farmSize) {
        super(HUMAN);
        this.farm = new Animal[farmSize];
        this.garage = new Car[DEFAULT_GARAGE_SIZE];
    }

    public Human() {
        super(HUMAN);
        this.farm = new Animal[DEFAULT_FARM_SIZE];
        this.garage = new Car[DEFAULT_GARAGE_SIZE];
    }

    public void raise(int annualMark) {
        if (annualMark == 5) {
            Double raisePercentage = 0.15;
            setSalary((this.salary * raisePercentage) + this.salary);
            System.out.println(SALARY_15 + this.salary);
        } else if (annualMark == 4) {
            Double raisePercentage = 0.1;
            setSalary((this.salary * raisePercentage) + this.salary);
            System.out.println(SALARY_10 + this.salary);
        } else if (annualMark == 3) {
            Double raisePercentage = 0.05;
            setSalary((this.salary * raisePercentage) + this.salary);
            System.out.println(SALARY_5 + this.salary);
        } else if (annualMark == 2) {
            Double raisePercentage = 0.0;
            setSalary((this.salary * raisePercentage) + this.salary);
            System.out.println(SALARY_0 + this.salary);
        } else {
            System.out.println(WRONG_ARG);
        }
    }

    public void setSalary(Double salary) {
        if (salary < 0) {
            throw new IllegalArgumentException(NEGATIVE);
        }
        System.out.println(BLA_BLA);

        this.salary = salary;
    }

    public void eat() throws Exception {
        throw new Exception(FORBIDDEN);
    }

    public void feed() {
        System.out.println(HUMAN_EAT);
        super.feed(DEFAULT_FEED_WEIGHT);
    }

    public Double valueOfCars() {
        Double value = 0.0;
        for (Car car : garage) {
            if (car != null) {
                value += car.getValue();
            }
        }
        return value;
    }

    public Car getCar(Integer index) {
        return this.garage[index];
    }

    public void setCar(Car car, int index) {
        payFor(car, index);
    }

    public void setCar(Car car) {
        if (hasAFreePlace()) {
            payFor(car, getFreeSpace());
        }
    }

    private void payFor(Car car, int index) {
        Double carV = car.getValue();
        if (index <0) {
            System.out.println(NO_SPACE);
        } else if (carV < cash) {
            System.out.println(BY_CASH);
            this.garage[index] = car;
        } else if (salary > carV / 12) {
            System.out.println(ON_CREDIT);
            this.garage[index] = car;
        } else {
            System.out.println(GET_BETTER_JOB);
        }
    }

    private int getFreeSpace() {
        for (int i = 0; i < garage.length; i++) {
            if (garage[i] == null) {
                return i;
            }
        }
        return -1;
    }

    public boolean hasACar(Car newCar) {
        for (Car car : garage) {
            if (car == newCar) {
                return true;
            }
        }
        return false;
    }

    public boolean hasAFreePlace() {
        for (Car car : garage) {
            if (car == null) {
                return true;
            }
        }
        return false;
    }

    public boolean hasAFarmAnimal(Animal farmAnimal) {
        for (Animal fa : farm) {
            if (fa == farmAnimal) {
                return true;
            }
        }
        return false;
    }

    public void removeAnimal(Animal animal) {
        for (int i = 0; i < farm.length; i++) {
            if (farm[i] == animal) {
                farm[i] = null;
            }
        }
    }

    public void addAnimal(Animal animal) {
        for (int i = 0; i < farm.length; i++) {
            if (farm[i] == null) {
                farm[i] = animal;
                break;
            }
        }
    }

    public boolean hasAPhone(Phone cp) {
        return mobile == cp;
    }

    public void removeCar(Car car) {
        for (int i = 0; i < garage.length; i++) {
            if (garage[i] == car) {
                garage[i] = null;
            }
        }
    }

    public void addCar(Car car) {
        for (int i = 0; i < garage.length; i++) {
            if (garage[i] == null) {
                garage[i] = car;
                break;
            }
        }
    }

    @Override
    public void sell(Human buyer, Human seller, Double price) throws Exception {
        throw new Exception(FORBIDDEN);
    }

    public boolean hasAPet(Pet p) {
        return pet == p;
    }
}
