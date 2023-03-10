package com.epf.rentmanager.service;

import java.sql.SQLException;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.VehicleDao;

import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    private VehicleDao vehicleDao;

    private VehicleService(VehicleDao vehicleDao){
        this.vehicleDao = vehicleDao;
    }

    public long create(Vehicle vehicle) throws ServiceException {
        try {
            if (null == vehicle.getConstructeur() || vehicle.getNb_places() < 1) {
                throw new ServiceException();
            } else {
                return this.vehicleDao.create(vehicle);
            }
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public long update(Vehicle vehicle) throws ServiceException {
        try {
            if (null == vehicle.getConstructeur() || vehicle.getNb_places() < 1) {
                throw new ServiceException();
            } else {
                return this.vehicleDao.update(vehicle);
            }
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public long delete(Vehicle vehicle) throws ServiceException {
        try {
            return this.vehicleDao.delete(vehicle);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public long count() throws ServiceException {
        try {
            return this.vehicleDao.count();
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public Vehicle findById(long id) throws ServiceException {
        try {
            return this.vehicleDao.findById(id);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public List<Vehicle> findAll() throws ServiceException {
        try {
            return this.vehicleDao.findAll();
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

}
