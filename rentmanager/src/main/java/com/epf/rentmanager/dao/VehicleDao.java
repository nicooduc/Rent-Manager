package com.epf.rentmanager.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;

public class VehicleDao {

    private static VehicleDao instance = null;

    private VehicleDao() {
    }

    public static VehicleDao getInstance() {
        if (instance == null) {
            instance = new VehicleDao();
        }
        return instance;
    }

    private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, modele, nb_places) VALUES(?, ?);";
    private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
    private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, modele, nb_places FROM Vehicle WHERE id=?;";
    private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur, modele, nb_places FROM Vehicle;";
    private static final String COUNT_VEHICLES_QUERY = "SELECT COUNT(*) FROM Vehicle;";

    public long create(Vehicle vehicle) throws DaoException {
        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_VEHICLE_QUERY, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, vehicle.getConstructeur());
            preparedStatement.setString(2, vehicle.getModele());
            preparedStatement.setInt(3, vehicle.getNb_places());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            int newId = 0;
            if (rs.next()) {
                newId = rs.getInt(1);
            }
            connection.close();
            return newId;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
    }
    public long update(Vehicle vehicle) throws DaoException {
        // TODO update un vehicule
        return 0;
    }

    public long delete(Vehicle vehicle) throws DaoException {
        // TODO supprimer un vehicule
        return 0;
    }

    public long count() throws DaoException {
        try {
            Connection connection = ConnectionManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(COUNT_VEHICLES_QUERY);
            int nbLines = 0;
            if (rs.next()) {
                nbLines = rs.getInt(1);
            }
            connection.close();
            return nbLines;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
    }

    public Vehicle findById(long id) throws DaoException {
        Vehicle vehicle = new Vehicle();
        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_VEHICLE_QUERY);
            preparedStatement.setInt(1, (int) id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                vehicle.setId(id);
                vehicle.setConstructeur(rs.getString("constructeur"));
                vehicle.setModele(rs.getString("modele"));
                vehicle.setNb_places(rs.getInt("nb_places"));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
        return vehicle;
    }

    public List<Vehicle> findAll() throws DaoException {
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        try {
            Connection connection = ConnectionManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(FIND_VEHICLES_QUERY);
            while (rs.next()) {
                long id = (rs.getInt("id"));
                String constructeur = (rs.getString("constructeur"));
                String modele = (rs.getString("modele"));
                int nb_places = (rs.getInt("nb_places"));

                vehicles.add(new Vehicle(id, constructeur, modele, nb_places));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
        return vehicles;
    }


}
