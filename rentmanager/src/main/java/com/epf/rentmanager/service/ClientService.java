package com.epf.rentmanager.service;

import java.sql.SQLException;
import java.util.List;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;

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
        try {
            if (null == client.getNom() || null == client.getPrenom()) {
                throw new ServiceException();
            } else {
                client.setNom(client.getNom().toUpperCase());
                return ClientDao.getInstance().create(client);
            }
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public long update(Client client) throws ServiceException {
        try {
            if (null == client.getNom() || null == client.getPrenom()) {
                throw new ServiceException();
            } else {
                client.setNom(client.getNom().toUpperCase());
                return ClientDao.getInstance().update(client);
            }
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public long delete(Client client) throws ServiceException {
        try {
            return ClientDao.getInstance().delete(client);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public long count() throws ServiceException {
        try {
            return ClientDao.getInstance().count();
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public Client findById(long id) throws ServiceException {
        try {
            return ClientDao.getInstance().findById(id);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public List<Client> findAll() throws ServiceException {
        try {
            return ClientDao.getInstance().findAll();
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }

    }

}
