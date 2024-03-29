package com.epf.rentmanager.model;

import java.time.LocalDate;
import java.util.Objects;

public class Reservation {
    private long id;
    Client client;
    Vehicle vehicle;
    private LocalDate debut;
    private LocalDate fin;

    public Reservation() {
    }

    public Reservation(long id) {
        this.id = id;
    }

    public Reservation(long id, Client client, Vehicle vehicle, LocalDate debut, LocalDate fin) {
        this.id = id;
        this.client = client;
        this.vehicle = vehicle;
        this.debut = debut;
        this.fin = fin;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDate getDebut() {
        return debut;
    }

    public void setDebut(LocalDate debut) {
        this.debut = debut;
    }

    public LocalDate getFin() {
        return fin;
    }

    public void setFin(LocalDate fin) {
        this.fin = fin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation)) return false;
        Reservation that = (Reservation) o;
        return id == that.id
                && client == that.client
                && vehicle == that.vehicle
                && Objects.equals(debut, that.debut)
                && Objects.equals(fin, that.fin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, vehicle, debut, fin);
    }

    @Override
    public String toString() {
        return "Reservation {" +
                "id = " + id +
                ", client = " + client +
                ", vehicule = " + vehicle +
                ", debut = " + debut +
                ", fin = " + fin +
                '}';
    }
}