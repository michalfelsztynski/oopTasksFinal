package pl.wsb.oop.model.device;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class App {
    private String name;
    private String version;
    private Double price;

    public App(String name, String version, Double price) {
        this.name = name;
        this.version = version;
        this.price = price;
    }
}
