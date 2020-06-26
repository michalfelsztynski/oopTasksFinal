package pl.wsb.oop.model.animal;

import pl.wsb.oop.model.common.Edible;

public class FarmAnimal extends Animal implements Edible {

    private static final String ANIMAL_NOT_FOUND = "animal not found in seller garage";
    private static final String NO_SPACE = "no free space at buyer farm";
    private static final String TO_EXPENSIVE = "buyer cannot afford";
    private static final String SUCCESSFUL = "animal sold - transaction successful";

    public FarmAnimal(String species) {
        super(species);
    }

    @Override
    public void sell(Human buyer, Human seller, Double price) throws Exception {
        if (!seller.hasAFarmAnimal(this)) {
            throw new Exception(ANIMAL_NOT_FOUND);
        }
        if (!buyer.hasAFreePlace()) {
            throw new Exception(NO_SPACE);
        }
        if (buyer.cash < price) {
            throw new Exception(TO_EXPENSIVE);
        }
        buyer.addAnimal(this);
        seller.removeAnimal(this);
        buyer.cash -= price;
        seller.cash += price;
        System.out.println(SUCCESSFUL);
    }

    @Override
    public void getEaten() throws Exception {
        this.kill();
        System.out.println("farm animal eaten");
    }
}
