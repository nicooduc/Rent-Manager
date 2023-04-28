package com.epf.rentmanager.model;

import java.time.LocalDate;
import java.util.Objects;

public class Client {

    private long id;
    private String nom;
    private String prenom;
    private String email;
    private LocalDate naissance;

    public Client() {
    }

    public Client(long clientId) {
        this.id = clientId;
    }

    public Client(long id, String lastname, String firstname, String mail, LocalDate birthdate) {
        this.nom = lastname;
        this.prenom = firstname;
        this.id = id;
        this.email = mail;
        this.naissance = birthdate;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getNaissance() {
        return naissance;
    }

    public void setNaissance(LocalDate naissance) {
        this.naissance = naissance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return id == client.id
                && Objects.equals(nom, client.nom)
                && Objects.equals(prenom, client.prenom)
                && Objects.equals(email, client.email)
                && Objects.equals(naissance, client.naissance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, prenom, id, email, naissance);
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Client {" +
                "lastname = " + nom +
                ", firstname = " + prenom +
                ", id = " + id +
                ", mail = " + email +
                ", birthdate = " + naissance +
                '}';
    }
}
