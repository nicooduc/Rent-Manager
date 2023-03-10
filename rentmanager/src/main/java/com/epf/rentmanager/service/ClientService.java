package com.epf.rentmanager.service;

import java.sql.SQLException;
import java.util.List;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;

import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private ClientDao clientDao;

    public ClientService(ClientDao clientDao){
        this.clientDao = clientDao;
    }

    public long create(Client client) throws ServiceException {
        try {
            if (null == client.getNom() || null == client.getPrenom()) {
                throw new ServiceException();
            } else {
                client.setNom(client.getNom().toUpperCase());
                return this.clientDao.create(client);
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
                return this.clientDao.update(client);
            }
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public long delete(Client client) throws ServiceException {
        try {
            return this.clientDao.delete(client);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public long count() throws ServiceException {
        try {
            return this.clientDao.count();
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public Client findById(long id) throws ServiceException {
        try {
            return this.clientDao.findById(id);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public List<Client> findAll() throws ServiceException {
        try {
            return this.clientDao.findAll();
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }

    }

}
