package com.epf.rentmanager.main;

import java.util.Scanner;

public class Main {

    public static boolean quitter = false;

    public static void main(String[] args) {
        //createCLient();
        //createVehicle();
        //createReservation();
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Que voulez vous faire ? : ");
            System.out.println("0-    Quitter");
            System.out.println("1-    Afficher liste clients");
            System.out.println("2-    Afficher liste vehicules");
            System.out.println("3-    Afficher liste reservations");
            System.out.println("4-    Chercher client");
            System.out.println("5-    Chercher vehicule");
            System.out.println("6-    Chercher reservation par id client");
            System.out.println("7-    Chercher reservation par id vehicule");
            System.out.println("8-    Ajouter client");
            System.out.println("9-    Compter clients");
            System.out.println("10-   Compter vehicules");

            String choix = scanner.nextLine();
            Test test = new Test();

            switch (choix) {
                case "1":
                    test.afficherClients();
                    break;
                case "2":
                    test.afficherVehicules();
                    break;
                case "3":
                    test.afficherReservations();
                    break;
                case "4":
                    test.rechercherClient();
                    break;
                case "5":
                    test.rechercherVehicule();
                    break;
                case "6":
                    test.rechercherReservationParIDClient();
                    break;
                case "7":
                    test.rechercherReservationParIDVehicule();
                    break;
                case "0":
                    quitter = true;
                    break;
                case "8":
                    test.ajouterClient();
                    break;
                case "9":
                    test.compterClient();
                    break;
                case "10":
                    test.compterVehicules();
                    break;
                default:
                    System.out.println("Erreur, paramètre non correct");
            }
        }while(!quitter);
    }
}