package com.epf.rentmanager.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.h2.tools.DeleteDbFiles;

import com.epf.rentmanager.persistence.ConnectionManager;

public class FillDatabase {


    public static void main(String[] args) throws Exception {
        try {
            DeleteDbFiles.execute("~", "RentManagerDatabase", true);
            insertWithPreparedStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	private static void insertWithPreparedStatement() throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement createPreparedStatement = null;

        List<String> createTablesQueries = new ArrayList<>();
        createTablesQueries.add("CREATE TABLE IF NOT EXISTS Client(id INT primary key auto_increment, client_id INT, nom VARCHAR(100), prenom VARCHAR(100), email VARCHAR(100), naissance DATETIME)");
        createTablesQueries.add("CREATE TABLE IF NOT EXISTS Vehicle(id INT primary key auto_increment, constructeur VARCHAR(100), modele VARCHAR(100), nb_places TINYINT(255))");
        createTablesQueries.add("CREATE TABLE IF NOT EXISTS Reservation(id INT primary key auto_increment, client_id INT, foreign key(client_id) REFERENCES Client(id), vehicle_id INT, foreign key(vehicle_id) REFERENCES Vehicle(id), debut DATETIME, fin DATETIME)");

        try {
            connection.setAutoCommit(false);

            for (String createQuery : createTablesQueries) {
            	createPreparedStatement = connection.prepareStatement(createQuery);
	            createPreparedStatement.executeUpdate();
	            createPreparedStatement.close();
            }

            // Remplissage de la base avec des Vehicules et des Clients
            Statement stmt = connection.createStatement();
            stmt.execute("INSERT INTO Vehicle(constructeur, modele, nb_places) VALUES('Jaguar', 'XE', 5)");
            stmt.execute("INSERT INTO Vehicle(constructeur, modele, nb_places) VALUES('Koenigsegg', 'Agera RS', 2)");
            stmt.execute("INSERT INTO Vehicle(constructeur, modele, nb_places) VALUES('Ford', 'Mustang', 4)");
            stmt.execute("INSERT INTO Vehicle(constructeur, modele, nb_places) VALUES('Audi', 'TT RS Coupé', 4)");
            stmt.execute("INSERT INTO Vehicle(constructeur, modele, nb_places) VALUES('Lamborghini', 'Urus', 5)");
            
            stmt.execute("INSERT INTO Client(nom, prenom, email, naissance) VALUES('DUPONT', 'Jean', 'jean.dupont@email.com', '1988-01-22')");
            stmt.execute("INSERT INTO Client(nom, prenom, email, naissance) VALUES('MORIN', 'Sabrina', 'sabrina.morin@email.com', '1985-07-18')");
            stmt.execute("INSERT INTO Client(nom, prenom, email, naissance) VALUES('AFLECK', 'Steeve', 'steeve.afleck@email.com', '1997-11-12')");
            stmt.execute("INSERT INTO Client(nom, prenom, email, naissance) VALUES('ROUSSEAU', 'Jacques', 'jacques.rousseau@email.com', '1965-09-02')");

            stmt.execute("INSERT INTO Reservation (client_id, vehicle_id, debut, fin) VALUES('3', '2', '2023-07-11', '2023-07-16')");
            stmt.execute("INSERT INTO Reservation (client_id, vehicle_id, debut, fin) VALUES('3', '2', '2024-07-06', '2024-07-12')");
            stmt.execute("INSERT INTO Reservation (client_id, vehicle_id, debut, fin) VALUES('2', '1', '2023-12-20', '2023-12-26')");
            stmt.execute("INSERT INTO Reservation (client_id, vehicle_id, debut, fin) VALUES('2', '5', '2023-12-27', '2024-01-01')");
            stmt.execute("INSERT INTO Reservation (client_id, vehicle_id, debut, fin) VALUES('1', '3', '2023-10-07', '2023-10-09')");
            stmt.execute("INSERT INTO Reservation (client_id, vehicle_id, debut, fin) VALUES('4', '4', '2024-07-13', '2024-07-16')");

            connection.commit();
            System.out.println("Success!");
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
}
