package pl.wsb.oop.model.device;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.wsb.oop.model.common.Soldable;

@Getter
@Setter
@AllArgsConstructor
public abstract class Device implements Soldable {
    final private String producer;
    final private String model;
    private Double value;

    public abstract void turnOn();

    public String getModel() {
        return this.model;
    }

}
