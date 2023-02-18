package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.persistence.ConnectionManager;

import javax.swing.plaf.nimbus.State;

public class ClientDao {
	
	private static ClientDao instance = null;
	private ClientDao() {}
	public static ClientDao getInstance() {
		if(instance == null) {
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
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.createStatement(id);
			ResultSet rs = preparedStatement.executeQuery(FIND_CLIENT_QUERY);
			while (rs.next()) {
				long id2 = (rs.getInt("id"));
				String nom = (rs.getString("nom"));
				String prenom = (rs.getString("prenom"));
				String email = (rs.getString("email"));
				LocalDate naissance = (rs.getDate("naissance").toLocalDate());

				//clients.add(new Client(id, nom, prenom, email, naissance));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		return new Client();
	}

	public List<Client> findAll() throws DaoException, SQLException {
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
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		return clients;
	}

}
