package com.epf.rentmanager.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.exception.ConstraintException;
import com.epf.rentmanager.model.Client;

import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private ClientDao clientDao;
    private ReservationDao reservationDao;

    public ClientService(ClientDao clientDao, ReservationDao reservationDao) {
        this.clientDao = clientDao;
        this.reservationDao = reservationDao;
    }

    public long create(Client client) throws ServiceException, ConstraintException {
        try {
            if (Period.between(client.getNaissance(), LocalDate.now()).getYears() < 18) {
                throw new ConstraintException("Vous n'avez pas l'age requis");
            } else if (this.clientDao.verifyEmail(client.getEmail())) {
                throw new ConstraintException("Email déjà existant");
            } else if (client.getPrenom().length() < 3) {
                throw new ConstraintException("Le prénom entré est trop court");
            } else if (client.getNom().length() < 3) {
                throw new ConstraintException("Le nom entré est trop court");
            } else {
                client.setNom(client.getNom().toUpperCase());
                return this.clientDao.create(client);
            }
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public long update(Client client) throws ServiceException, ConstraintException {
        try {
            if (Period.between(client.getNaissance(), LocalDate.now()).getYears() < 18) {
                throw new ConstraintException("Vous n'avez pas l'age requis");
            } else if (!Objects.equals(this.clientDao.findById(client.getId()).getEmail(), client.getEmail()) && this.clientDao.verifyEmail(client.getEmail())) {
                throw new ConstraintException("Email déjà existant");
            } else if (client.getPrenom().length() < 3) {
                throw new ConstraintException("Le prénom entré est trop court");
            } else if (client.getNom().length() < 3) {
                throw new ConstraintException("Le nom entré est trop court");
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
            this.reservationDao.deleteByClientId(client);
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
