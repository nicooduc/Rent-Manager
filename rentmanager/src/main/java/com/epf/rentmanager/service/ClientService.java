package com.epf.rentmanager.service;

import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.dao.ClientDao;

public class ClientService {

	private ClientDao clientDao;
	public static ClientService instance;
	
	private ClientService() {
		this.clientDao = ClientDao.getInstance();
	}
	
	public static ClientService getInstance() {
		if (instance == null) {
			instance = new ClientService();
		}
		
		return instance;
	}
	
	
	public long create(Client client) throws ServiceException {
		// TODO: créer un client
		return 0;
	}

	public Client findById(long id) throws ServiceException {
		// TODO: récupérer un client par son id
		return new Client();
	}

	public List<Client> findAll() throws ServiceException {
		// TODO: récupérer tous les clients
		return new ArrayList<Client>();
	}
	
}
