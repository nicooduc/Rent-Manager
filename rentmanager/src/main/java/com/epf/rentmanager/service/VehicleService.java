package com.epf.rentmanager.service;

import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.exception.ConstraintException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.dao.ReservationDao;

import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    private VehicleDao vehicleDao;
    private ReservationDao reservationDao;

    public VehicleService(VehicleDao vehicleDao, ReservationDao reservationDao) {
        this.vehicleDao = vehicleDao;
        this.reservationDao = reservationDao;
    }

    public long create(Vehicle vehicle) throws ServiceException, ConstraintException {
        try {
            if (null == vehicle.getConstructeur()) {
                throw new ConstraintException("Le véhicule doit avoir un constructeur");
            } else if (null == vehicle.getModele()) {
                throw new ConstraintException("Le véhicule doit avoir un modèle");
            } else if (vehicle.getNb_places() < 2 || vehicle.getNb_places() > 9) {
                throw new ConstraintException("Le véhicule doit comporter entre 2 et 9 places");
            } else {
                return this.vehicleDao.create(vehicle);
            }
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public long update(Vehicle vehicle) throws ServiceException, ConstraintException {
        try {
            if (null == vehicle.getConstructeur()) {
                throw new ConstraintException("Le véhicule doit avoir un constructeur");
            } else if (null == vehicle.getModele()) {
                throw new ConstraintException("Le véhicule doit avoir un modèle");
            } else if (vehicle.getNb_places() < 2 || vehicle.getNb_places() > 9) {
                throw new ConstraintException("Le véhicule doit comporter entre 2 et 9 places");
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
            this.reservationDao.deleteByVehicleId(vehicle);
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
