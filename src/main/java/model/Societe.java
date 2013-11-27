package model;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: thiergeo
 * Date: 27/11/13
 * Time: 13:29
 * To change this template use File | Settings | File Templates.
 */

@Entity(name = "Societe")
public class Societe {
    @Id
    @GeneratedValue
    private int id;

    private String nom;
    private String code;

    @OneToOne
    private Historique historique;

    @ManyToOne(targetEntity = model.Bourse.class)
    private Bourse bourse;

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Historique getHistorique() {
        return historique;
    }

    public void setHistorique(Historique historique) {
        this.historique = historique;
    }

    public Bourse getBourse() {
        return bourse;
    }

    public void setBourse(Bourse bourse) {
        this.bourse = bourse;
    }
}
