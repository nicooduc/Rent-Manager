package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
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

    private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, nb_places) VALUES(?, ?);";
    private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
    private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle WHERE id=?;";
    private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle;";

    public long create(Vehicle vehicle) throws DaoException {
        return 0;
    }

    public long delete(Vehicle vehicle) throws DaoException {
        return 0;
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
                // vehicle.setModele(rs.getString("prenom"));
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
                // String modele = (rs.getString("modele"));
                int nb_places = (rs.getInt("nb_places"));

                vehicles.add(new Vehicle(id, constructeur, nb_places));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
        return vehicles;
    }


}
