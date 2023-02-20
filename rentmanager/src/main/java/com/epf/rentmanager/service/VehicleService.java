package com.epf.rentmanager.service;

import java.sql.SQLException;
import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.VehicleDao;

public class VehicleService {

    private VehicleDao vehicleDao;
    public static VehicleService instance;

    private VehicleService() {
        this.vehicleDao = VehicleDao.getInstance();
    }

    public static VehicleService getInstance() {
        if (instance == null) {
            instance = new VehicleService();
        }

        return instance;
    }


    public long create(Vehicle vehicle) throws ServiceException {
        try {
            if (null == vehicle.getConstructeur() || vehicle.getNb_places() < 1) {
                throw new ServiceException();
            } else {
                return VehicleDao.getInstance().create(vehicle);
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
                return VehicleDao.getInstance().update(vehicle);
            }
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public long delete(Vehicle vehicle) throws ServiceException {
        try {
            return VehicleDao.getInstance().delete(vehicle);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public long count() throws ServiceException {
        try {
            return VehicleDao.getInstance().count();
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public Vehicle findById(long id) throws ServiceException {
        try {
            return VehicleDao.getInstance().findById(id);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public List<Vehicle> findAll() throws ServiceException {
        try {
            return VehicleDao.getInstance().findAll();
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

}
