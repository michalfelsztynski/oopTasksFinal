package pl.wsb.oop.model.device;

import lombok.Getter;
import lombok.Setter;
import pl.wsb.oop.model.animal.Human;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class Phone extends Device {
    private static final String TURNED_ON = "phone is turned on";
    private static final String SOLD = "already sold";
    private static final String CANNOT_AFFORD = "not enough money";
    private static final String INSTALLED = "Application %s installed";
    private static final String INSTALLED_VERSION = "The app %s was installed in version %s";
    private static final String PHONE_NOT_FOUND = "seller dont own this phone";
    private static final String NO_SPACE = "buyer have a phone";
    private static final String TO_EXPENSIVE = "buyer cannot afford";
    private static final String SUCCESSFUL = "phone sold - transaction successful";

    private static final String PRODUCTION_CODE = "2edf44f";
    private static final String PROCESSOR_TYPE = "SNAPDRAGON 865";

    private final Double screenSize;
    private List<App> applications;

    public Phone(String producer, String model, Double value, Double screenSize) {
        super(producer, model, value);
        this.screenSize = screenSize;
        applications = new LinkedList<>();
    }

    @Override
    public void turnOn() {
        System.out.println(TURNED_ON);
    }

    public void installAnApp(App app, Human owner) throws Exception {
        if (app.getPrice() > 0) {
            if (owner.cash < app.getPrice()) {
                throw new Exception(CANNOT_AFFORD);
            }
            owner.cash -= app.getPrice();
        }
        applications.add(app);
        System.out.println(String.format(INSTALLED, app.getName()));
    }

    public void installAnApp(String name) {
        installAnApp(name, 0.0);
    }


    public void installAnApp(String[] apps) {
        for (String app : apps) {
            installAnApp(app);
        }
    }

    public void installAnApp(String name, Double version) {
        System.out.println(String.format(INSTALLED_VERSION, name, version));
    }

    public boolean isInstalled(App app) {
        return applications.contains(app);
    }

    public boolean isInstalled(String appName) {
        return applications.stream().anyMatch(a -> a.getName().equals(appName));
    }

    void printAllFreeAppsInstalled(){
        applications.stream().filter(app -> app.getPrice()==0).map(App::getName).forEach(System.out::println);
            }
    void printAllAppsOrderedByName(){
        applications.stream().sorted(Comparator.comparing(App::getName)).forEach(System.out::println);
    }

    void printAllAppsOrderedByPrice(){
        applications.stream().sorted(Comparator.comparing(App::getPrice)).forEach(System.out::println);
    }

    @Override
    public void sell(Human buyer, Human seller, Double price) throws Exception {
        if (!seller.hasAPhone(this)) {
            throw new Exception(PHONE_NOT_FOUND);
        }
        if (buyer.getMobile() != null) {
            throw new Exception(NO_SPACE);
        }
        if (buyer.cash < price) {
            throw new Exception(TO_EXPENSIVE);
        }
        buyer.setMobile(this);
        seller.setMobile(null);
        buyer.cash -= price;
        seller.cash += price;
        System.out.println(SUCCESSFUL);
    }
}
