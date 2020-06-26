package pl.wsb.oop.model.animal;

import lombok.Getter;
import lombok.Setter;
import pl.wsb.oop.db.DBConnector;
import pl.wsb.oop.model.common.Feedable;
import pl.wsb.oop.model.common.Soldable;

import java.io.File;
import java.sql.SQLException;
import java.time.Instant;

@Getter
@Setter
public abstract class Animal implements Feedable, Soldable, Comparable<Animal> {
    private static final String DEAD = "animal dead";
    private static final String FEED = "yummy thanks";
    private static final String WALK = "thanks for a walk";
    private static final String HUNGRY = "feed me im hungry";

    private static Double DOG_DEF_WEIGHT = 20.0;
    private static Double CAT_DEF_WEIGHT = 5.0;
    private static Double DEF_WEIGHT = 10.0;

    private final String species;
    private Double weight;
    private String name;
    private File photo;


    public Animal(String species) {
        this.species = species;

        switch (species) {
            case "cat": {
                weight = CAT_DEF_WEIGHT;
                break;
            }
            case "dog": {
                weight = DOG_DEF_WEIGHT;
                break;
            }
            default: {
                weight = DEF_WEIGHT;
                break;
            }
        }
        save();
    }

    @Override
    public void feed() {
        if (weight == 0) {
            System.out.println(DEAD);
        } else {
            ++weight;
            System.out.println(FEED);
        }
    }

    void kill() {
        System.out.println("im dying");
        this.weight = 0.0;
    }

    @Override
    public void feed(Double foodWeight) {
        if (weight == 0) {
            System.out.println(DEAD);
        } else {
            weight += foodWeight;
            System.out.println(FEED);
        }

    }

    void takeForAWalk() {
        if (weight > 3.0) {
            System.out.println(WALK);
            --weight;
        } else if (weight < 2) {
            System.out.println(HUNGRY);
            --weight;
        } else {
            System.out.println(DEAD);
        }
    }

    @Override
    public int compareTo(Animal o) {
        return 0;
    }

    private void save() {
//        String sql = "insert into animal values ('" + this.species + "','" + this.name + "'," + this.weight + "'," + this.photo + ");";
        String sql = "insert into animal values ('" + this.species + "','" + this.name + "'," + this.weight + ");";
        System.out.println(sql);
        try {
            DBConnector.executeSQL(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}