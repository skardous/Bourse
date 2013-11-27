package model;

import javax.persistence.*;

@Entity(name = "Client")
public class Client {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String lastName;
    private String password;
    @OneToOne
    private Portefeuille portefeuille;
    @OneToOne
    private Compte compte;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }
}
