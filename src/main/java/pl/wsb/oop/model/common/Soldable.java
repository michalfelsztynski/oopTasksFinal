package pl.wsb.oop.model.common;

import pl.wsb.oop.model.animal.Human;

public interface Soldable {
    void sell(Human buyer, Human seller, Double price) throws Exception;
}
