package com.epf.rentmanager.main;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

import java.util.Scanner;


public class main {
    public static void main(String[] args) {
        try {
            System.out.println(ClientService.getInstance().findAll());

            System.out.println(VehicleService.getInstance().findAll());

            Scanner scanner = new Scanner(System.in);
            System.out.print("Entrez une id client : ");
            long idEntree = (long) scanner.nextDouble();
            System.out.println(ClientService.getInstance().findById(idEntree));

            System.out.print("Entrez une id vehicule: ");
            scanner.nextDouble();
            System.out.println(VehicleService.getInstance().findById(idEntree));

            System.out.println(ReservationService.getInstance().findAll());

            System.out.print("Entrez une id client de resa: ");
            scanner.nextDouble();
            System.out.println(ReservationService.getInstance().findResaByClientId(idEntree));

            System.out.print("Entrez une id vehicule de resa: ");
            scanner.nextDouble();
            System.out.println(ReservationService.getInstance().findResaByVehicleId(idEntree));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

    }

}