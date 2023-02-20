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
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.persistence.ConnectionManager;

public class ClientDao {

    private static ClientDao instance = null;

    private ClientDao() {
    }

    public static ClientDao getInstance() {
        if (instance == null) {
            instance = new ClientDao();
        }
        return instance;
    }

    private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(nom, prenom, email, naissance) VALUES(?, ?, ?, ?);";
    private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
    private static final String FIND_CLIENT_QUERY = "SELECT nom, prenom, email, naissance FROM Client WHERE id=?;";
    private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";

    public long create(Client client) throws DaoException {
        return 0;
    }

    public long delete(Client client) throws DaoException {
        return 0;
    }

    public Client findById(long id) throws DaoException {
        Client client = new Client();
        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_CLIENT_QUERY);
            preparedStatement.setInt(1, (int) id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                client.setId(id);
                client.setNom(rs.getString("nom"));
                client.setPrenom(rs.getString("prenom"));
                client.setEmail(rs.getString("email"));
                client.setNaissance(rs.getDate("naissance").toLocalDate());
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
        return client;
    }

    public List<Client> findAll() throws DaoException {
        List<Client> clients = new ArrayList<Client>();
        try {
            Connection connection = ConnectionManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(FIND_CLIENTS_QUERY);
            while (rs.next()) {
                long id = (rs.getInt("id"));
                String nom = (rs.getString("nom"));
                String prenom = (rs.getString("prenom"));
                String email = (rs.getString("email"));
                LocalDate naissance = (rs.getDate("naissance").toLocalDate());

                clients.add(new Client(id, nom, prenom, email, naissance));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
        return clients;
    }

}
