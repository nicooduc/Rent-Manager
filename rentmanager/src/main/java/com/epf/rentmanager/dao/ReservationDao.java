package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;

import org.springframework.stereotype.Repository;

@Repository
public class ReservationDao {

    private ReservationDao() {

    }

    private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
    private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
    private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
    private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
    private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";

    public long create(Reservation reservation) throws DaoException {
        // TODO creer un reservation
        return 0;
    }

    public long update(Reservation reservation) throws DaoException {
        // TODO update une reservation
        return 0;
    }

    public long delete(Reservation reservation) throws DaoException {
        // TODO supprimer une reservation
        return 0;
    }

    public long count() throws DaoException {
        // TODO compter le nombre de reservations
        return 0;
    }

    public List<Reservation> findResaByClientId(long clientId) throws DaoException {
        List<Reservation> reservation = new ArrayList<Reservation>();
        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY);
            preparedStatement.setInt(1, (int) clientId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                long id = (rs.getInt("id"));
                long vehicleId = (rs.getInt("vehicle_id"));

                Client client = new Client(clientId);
                Vehicle vehicle = new Vehicle(vehicleId);

                LocalDate debut = (rs.getDate("debut").toLocalDate());
                LocalDate fin = (rs.getDate("fin").toLocalDate());

                reservation.add(new Reservation(id, client, vehicle, debut, fin));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
        return reservation;
    }

    public List<Reservation> findResaByVehicleId(long vehicleId) throws DaoException, ServiceException {
        List<Reservation> reservation = new ArrayList<Reservation>();
        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_RESERVATIONS_BY_VEHICLE_QUERY);
            preparedStatement.setInt(1, (int) vehicleId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                long id = (rs.getInt("id"));
                long clientId = (rs.getInt("client_id"));

                //Client client = ClientDao.getInstance().findById(clientId);
                //Vehicle vehicle = VehicleDao.getInstance().findById(vehicleId);
                Client client = new Client(clientId);
                Vehicle vehicle = new Vehicle(vehicleId);

                LocalDate debut = (rs.getDate("debut").toLocalDate());
                LocalDate fin = (rs.getDate("fin").toLocalDate());

                reservation.add(new Reservation(id, client, vehicle, debut, fin));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
        return reservation;
    }

    public List<Reservation> findAll() throws DaoException {
        List<Reservation> reservation = new ArrayList<Reservation>();
        try {
            Connection connection = ConnectionManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(FIND_RESERVATIONS_QUERY);
            while (rs.next()) {
                long id = (rs.getInt("id"));
                long clientId = (rs.getInt("client_id"));
                long vehicleId = (rs.getInt("vehicle_id"));

                Client client = new Client(clientId);
                Vehicle vehicle = new Vehicle(vehicleId);

                LocalDate debut = (rs.getDate("debut").toLocalDate());
                LocalDate fin = (rs.getDate("fin").toLocalDate());

                reservation.add(new Reservation(id, client, vehicle, debut, fin));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
        return reservation;
    }
}
