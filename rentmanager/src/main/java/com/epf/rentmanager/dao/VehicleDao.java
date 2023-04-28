package com.epf.rentmanager.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.persistence.ConnectionManager;
import com.epf.rentmanager.model.Vehicle;

import org.springframework.stereotype.Repository;

@Repository
public class VehicleDao {

    public VehicleDao() {

    }

    private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, modele, nb_places) VALUES(?, ?, ?);";
    private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
    private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, modele, nb_places FROM Vehicle WHERE id=?;";
    private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur, modele, nb_places FROM Vehicle;";
    private static final String COUNT_VEHICLES_QUERY = "SELECT COUNT(*) FROM Vehicle;";
    private static final String UPDATE_VEHICLE_QUERY = "UPDATE Vehicle SET constructeur = ?, modele = ?, nb_places = ? WHERE id = ?;";

    public long create(Vehicle vehicle) throws DaoException {
        try (Connection connection = ConnectionManager.getConnection()) {
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

            return newId;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
    }

    public long update(Vehicle vehicle) throws DaoException {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_VEHICLE_QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, vehicle.getConstructeur());
            preparedStatement.setString(2, vehicle.getModele());
            preparedStatement.setInt(3, vehicle.getNb_places());
            preparedStatement.setInt(4, (int) vehicle.getId());
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            int NbIdChange = 0;
            if (rs.next()) {
                NbIdChange = rs.getInt(1);
            }

            return NbIdChange;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
    }

    public long delete(Vehicle vehicle) throws DaoException {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_VEHICLE_QUERY);
            preparedStatement.setInt(1, (int) vehicle.getId());
            preparedStatement.executeUpdate();

            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
    }

    public long count() throws DaoException {
        try (Connection connection = ConnectionManager.getConnection()) {
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(COUNT_VEHICLES_QUERY);
            int nbLines = 0;
            if (rs.next()) {
                nbLines = rs.getInt(1);
            }

            return nbLines;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
    }

    public Vehicle findById(long id) throws DaoException {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_VEHICLE_QUERY);
            preparedStatement.setInt(1, (int) id);

            ResultSet rs = preparedStatement.executeQuery();
            Vehicle vehicle = new Vehicle();
            if (rs.next()) {
                vehicle.setId(id);
                vehicle.setConstructeur(rs.getString("constructeur"));
                vehicle.setModele(rs.getString("modele"));
                vehicle.setNb_places(rs.getInt("nb_places"));
            }

            return vehicle;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
    }

    public List<Vehicle> findAll() throws DaoException {
        try (Connection connection = ConnectionManager.getConnection()) {
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(FIND_VEHICLES_QUERY);
            List<Vehicle> vehicles = new ArrayList<Vehicle>();
            while (rs.next()) {
                long id = (rs.getInt("id"));
                String constructeur = (rs.getString("constructeur"));
                String modele = (rs.getString("modele"));
                int nb_places = (rs.getInt("nb_places"));
                vehicles.add(new Vehicle(id, constructeur, modele, nb_places));
            }

            return vehicles;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
    }

}
