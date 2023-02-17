package com.epf.rentmanager.model;

import java.time.LocalDate;
public class Client   {
    private String nom;
    private String prenom;
    private long id;
    private String email;
    private LocalDate naissance;

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

    public Client(String lastname, String firstname, long id, String mail, LocalDate birthdate) {
        this.nom = lastname;
        this.prenom = firstname;
        this.id = id;
        this.email = mail;
        this.naissance = birthdate;
    }

    public Client() {
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Client{" +
                "lastname='" + nom + '\'' +
                ", firstname='" + prenom + '\'' +
                ", id=" + id +
                ", mail='" + email + '\'' +
                ", birthdate=" + naissance +
                '}';
    }
}
