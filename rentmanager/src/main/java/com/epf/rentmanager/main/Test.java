package com.epf.rentmanager.main;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
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
            ClientService clientService = this.clientService;
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

            long id = clientService.create(client);
            System.out.println("Le client a été créé avec succès !"+ id);

        }catch (ServiceException e) {
            throw new RuntimeException(e);
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
}
