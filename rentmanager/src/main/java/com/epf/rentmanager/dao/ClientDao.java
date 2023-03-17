package com.epf.rentmanager.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.persistence.ConnectionManager;

import org.springframework.stereotype.Repository;

@Repository
public class ClientDao {

    public ClientDao() {

    }

    private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(nom, prenom, email, naissance) VALUES(?, ?, ?, ?);";
    private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
    private static final String FIND_CLIENT_QUERY = "SELECT nom, prenom, email, naissance FROM Client WHERE id=?;";
    private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";
    private static final String COUNT_CLIENTS_QUERY = "SELECT COUNT(*) FROM Client;";
    private static final String VERIFY_EMAIL_QUERY = "SELECT 1 FROM client WHERE email = ? LIMIT 1;";

    public long create(Client client) throws DaoException {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_CLIENT_QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, client.getNom());
            preparedStatement.setString(2, client.getPrenom());
            preparedStatement.setString(3, client.getEmail());
            preparedStatement.setDate(4, Date.valueOf(client.getNaissance()));
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

    public long update(Client client) throws DaoException {
        // TODO update un client
        return 0;
    }

    public long delete(Client client) throws DaoException {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CLIENT_QUERY); //, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, (int) client.getId());
            preparedStatement.executeUpdate();

//            ResultSet rs = preparedStatement.getGeneratedKeys();
//            int delId = 0;
//            if (rs.next()) {
//                delId = rs.getInt(1);
//            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
    }

    public long count() throws DaoException {
        try (Connection connection = ConnectionManager.getConnection()) {
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(COUNT_CLIENTS_QUERY);
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

    public Client findById(long id) throws DaoException {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_CLIENT_QUERY);
            preparedStatement.setInt(1, (int) id);

            ResultSet rs = preparedStatement.executeQuery();
            Client client = new Client();
            while (rs.next()) {
                client.setId(id);
                client.setNom(rs.getString("nom"));
                client.setPrenom(rs.getString("prenom"));
                client.setEmail(rs.getString("email"));
                client.setNaissance(rs.getDate("naissance").toLocalDate());
                // TODO ajouter Mapper pour eviter de dupliquer les lignes ci desssus - idem autres DAO
            }
            return client;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
    }

    public List<Client> findAll() throws DaoException {
        try (Connection connection = ConnectionManager.getConnection()) {
            List<Client> clients = new ArrayList<Client>();
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
            return clients;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
    }

    public boolean verifyEmail(String email) throws DaoException {
        try {
            boolean exist = false;
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(VERIFY_EMAIL_QUERY);
            preparedStatement.setString(1, email);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                exist = rs.getBoolean(1);
            }
            connection.close();
            return exist;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
    }

}
