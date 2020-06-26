package pl.wsb.oop.model.animal;


public class Pet extends Animal {
    private static final String PET_NOT_FOUND = "seller dont own this pet";
    private static final String NO_SPACE = "buyer have a pet";
    private static final String TO_EXPENSIVE = "buyer cannot afford";
    private static final String SUCCESSFUL = "pet sold - transaction successful";


    public Pet(String species) {
        super(species);
    }

    @Override
    public void sell(Human buyer, Human seller, Double price) throws Exception {
        if (!seller.hasAPet(this)) {
            throw new Exception(PET_NOT_FOUND);
        }
        if (buyer.getPet()!=null) {
            throw new Exception(NO_SPACE);
        }
        if (buyer.cash < price) {
            throw new Exception(TO_EXPENSIVE);
        }
        buyer.setPet(this);
        seller.setPet(null);
        buyer.cash -= price;
        seller.cash += price;
        System.out.println(SUCCESSFUL);
    }
}
