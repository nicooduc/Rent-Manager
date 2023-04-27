package com.epf.rentmanager.main;

import com.epf.rentmanager.exception.ConstraintException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Scanner;

@Component
public class Test {

    private VehicleService vehicleService;
    private ClientService clientService;
    private ReservationService reservationService;

    public Test(VehicleService vehicleService, ClientService clientService, ReservationService reservationService) {
        this.vehicleService = vehicleService;
        this.clientService = clientService;
        this.reservationService = reservationService;
    }

    public void afficherClients(){
        try {
            System.out.println(this.clientService.findAll());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
    public void afficherVehicules(){
        try {
            System.out.println(this.vehicleService.findAll());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
    public void afficherReservations(){
        try {
            System.out.println(this.reservationService.findAll());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    public void rechercherClient(){
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Entrez une id client : ");
            String identre = scanner.nextLine();
            System.out.println(this.clientService.findById(Long.parseLong(identre)));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    public void rechercherVehicule(){

        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Entrez une id vehicule : ");
            String identre = scanner.nextLine();
            System.out.println(this.vehicleService.findById(Long.parseLong(identre)));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    public void rechercherReservationParIDClient(){
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Entrez une id client pour trouver ses reservations : ");
            String identre = scanner.nextLine();
            System.out.println(this.reservationService.findResaByClientId(Long.parseLong(identre)));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
    public void rechercherReservationParIDVehicule(){
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Entrez une id vehicule pour trouver ses reservations : ");
            String identre = scanner.nextLine();
            System.out.println(this.reservationService.findResaByVehicleId(Long.parseLong(identre)));
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    public void ajouterClient() {
        try{
            Scanner scanner = new Scanner(System.in);

            System.out.print("Nom: ");
            String lastName = scanner.nextLine();

            System.out.print("Prénom: ");
            String firstName = scanner.nextLine();

            System.out.print("Email: ");
            String email = scanner.nextLine();

            System.out.print("Date de naissance (AAAA-MM-JJ) : ");
            String birthDateString = scanner.nextLine();

            LocalDate birthDate = LocalDate.parse(birthDateString);

            Client client = new Client();
            client.setNom(lastName);
            client.setPrenom(firstName);
            client.setEmail(email);
            client.setNaissance(birthDate);

            long id = this.clientService.create(client);
            System.out.println("Le client " + id + " a été créé avec succès !");

        }catch (ServiceException e) {
            throw new RuntimeException(e);
        } catch (ConstraintException e) {
            System.out.println(e.getMessage());
        }
    }

    public void compterClient() {
        try {
            System.out.println("Nombre de clients : " + this.clientService.count());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    public void compterVehicules() {
        try {
            System.out.println("Nombre de vehicules : " + this.vehicleService.count());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    public void supprimerClient() {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Id du client a supprimer : ");
            long idClient = Long.parseLong(scanner.nextLine());

            Client client = new Client();
            client.setId(idClient);

            this.clientService.delete(client);
            System.out.println("Le client a été supprimé avec succès !");
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateClient() {
        try{
            Scanner scanner = new Scanner(System.in);
            Client client;

            System.out.print("Id client a modifier: ");
            long clientId = Long.parseLong(scanner.nextLine());
            client = this.clientService.findById(clientId);
            System.out.println(client);

            System.out.print("Nom: ");
            String lastName = scanner.nextLine();

            System.out.print("Prénom: ");
            String firstName = scanner.nextLine();

            System.out.print("Email: ");
            String email = scanner.nextLine();

            System.out.print("Date de naissance (AAAA-MM-JJ) : ");
            String birthDateString = scanner.nextLine();

            LocalDate birthDate = LocalDate.parse(birthDateString);

            client.setNom(lastName);
            client.setPrenom(firstName);
            client.setEmail(email);
            client.setNaissance(birthDate);

            long nbModif = this.clientService.update(client);
            System.out.println(nbModif + " clients a été modifié avec succès !");

        }catch (ServiceException e) {
            throw new RuntimeException(e);
        } catch (ConstraintException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createReservation() {
        try{
            Scanner scanner = new Scanner(System.in);
            Reservation reservation = new Reservation();
            Client client  = new Client();
            Vehicle vehicle = new Vehicle();

            System.out.print("Id client réserveur: ");
            long clientId = Long.parseLong(scanner.nextLine());
            client.setId(clientId);

            System.out.print("Id véhicle réservé: ");
            long vehicleId = Long.parseLong(scanner.nextLine());
            vehicle.setId(vehicleId);

            System.out.print("Date de debut (AAAA-MM-JJ) : ");
            String debutString = scanner.nextLine();

            System.out.print("Date de fin (AAAA-MM-JJ) : ");
            String finString = scanner.nextLine();

            LocalDate debut = LocalDate.parse(debutString);
            LocalDate fin = LocalDate.parse(finString);

            reservation.setClient(client);
            reservation.setVehicle(vehicle);
            reservation.setFin(fin);
            reservation.setDebut(debut);

            this.reservationService.create(reservation);
            System.out.println("Reservation bien créé");

        }catch (ServiceException e) {
            throw new RuntimeException(e);
        } catch (ConstraintException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteClient() {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("ID du client à supprimer :");
            long id_to_delete = Long.parseLong(scanner.nextLine());
            Client client = new Client();
            client.setId(id_to_delete);
            this.clientService.delete(client);
            System.out.println("Le client a été supprimé avec succès");
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}
