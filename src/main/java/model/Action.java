package model;

import javax.persistence.Embeddable;

/**
 * Created with IntelliJ IDEA.
 * User: thiergeo
 * Date: 27/11/13
 * Time: 12:47
 * To change this template use File | Settings | File Templates.
 */
// Pas sur de ça
@Embeddable
public class Action {
    private float value;

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
