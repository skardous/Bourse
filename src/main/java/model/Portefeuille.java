package model;

import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: thiergeo
 * Date: 27/11/13
 * Time: 12:44
 * To change this template use File | Settings | File Templates.
 */
@Entity(name = "Portefeuille")
public class Portefeuille {
    @Id
    @GeneratedValue
    private int id;
    @Embedded // Pas sur
    @OneToMany(targetEntity = model.Action.class)
    private List<Action> actions;

    public int getId() {
        return id;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }
}
