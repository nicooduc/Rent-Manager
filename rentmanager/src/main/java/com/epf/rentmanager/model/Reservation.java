package com.epf.rentmanager.model;

import java.time.LocalDate;
import java.util.Objects;

public class Reservation {
    private long id;
    Client client_id;
    Vehicle vehicle_id;
    private LocalDate debut;
    private LocalDate fin;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Client getClient_id() {
        return client_id;
    }

    public void setClient_id(Client client_id) {
        this.client_id = client_id;
    }

    public Vehicle getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(Vehicle vehicle_id) {
        this.vehicle_id = vehicle_id;
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

    public Reservation(long id, Client client_id, Vehicle vehicle_id, LocalDate debut, LocalDate fin) {
        this.id = id;
        this.client_id = client_id;
        this.vehicle_id = vehicle_id;
        this.debut = debut;
        this.fin = fin;
    }

    public Reservation() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation)) return false;
        Reservation that = (Reservation) o;
        return id == that.id && client_id == that.client_id && vehicle_id == that.vehicle_id && Objects.equals(debut, that.debut) && Objects.equals(fin, that.fin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client_id, vehicle_id, debut, fin);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", client_id=" + client_id +
                ", vehicule_id=" + vehicle_id +
                ", debut=" + debut +
                ", fin=" + fin +
                '}';
    }
}