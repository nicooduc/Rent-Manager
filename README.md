# Rent-Manager
Projet Java 4A EPF

Ce projet permet de gérer une liste de clients, de véhicules et de réservations dans une agence de location de voitures.  
Les fonctionnalités principales sont les suivantes :

* **CLIENT**  
  * Voir la liste des clients  
  * Ajouter des client  
  * Voir le détail des informations d'un client (réservations en son nom et véhicules correspondants)
  * Modifier les informations d'un client  
  * Supprimer un client  

* **VEHICLE**  
  * Voir la liste des véhicules  
  * Ajouter un véhicule  
  * Voir le détail des informations d'un véhicule (réservations et clients correspondants)  
  * Modifier les informations d'un véhicule  
  * Supprimer un véhicule  

* **RESERVATION**  
  * Voir la liste des réservations  
  * Créer des réservation  
  * Voir le détail des informations concernant une réservation (client et véhicule correspondant)  
  * Modifier une réservation  
  * Supprimer une réservation    

---
### Améliorations possibles  
* Regrouper contraintes appliquées dans les Services en fonctions. Cela eviterais les doublons entre les fonction 'create' et 'update'.  
* Avoir une gestion des contraintes plus complètes, pour le moment les erreurs liées au contraintes sont remontées au frontEnd mais ne sont pas affichées ni traitées.
* Ajouter la possibilité de stocker des documents relatifs aux clients (Carte d'identité, permis ...)
* Ajouter des photos des véhicules
