package com.epf.rentmanager.service;

import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.ConstraintException;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;

import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private ReservationDao reservationDao;
    private VehicleDao vehicleDao;
    private ClientDao clientDao;

    public ReservationService(ReservationDao reservationDao, VehicleDao vehicleDao, ClientDao clientDao){
        this.reservationDao = reservationDao;
        this.vehicleDao = vehicleDao;
        this.clientDao = clientDao;
    }

    public long create(Reservation reservation) throws ServiceException, ConstraintException {
        try {
            List<Reservation> vehicleReservations = this.reservationDao.findResaByVehicleId(reservation.getVehicle().getId());
            int userConsRes = Period.between(reservation.getDebut(), reservation.getFin()).getDays() + 1; // include both first and last day - reservation are for entire days
            int vehicleConsRes = Period.between(reservation.getDebut(), reservation.getFin()).getDays() + 1;

            // order reservation for future test improvements
//            Collections.sort(vehicleReservations, new Comparator<Reservation>() {
//                public int compare(Reservation o1, Reservation o2) {
//                    return o1.getDebut().compareTo(o2.getDebut());
//                }
//            });

            for (Reservation vehicleReservation : vehicleReservations) {
                // verification : non-overlapping of reservations
                if (!reservation.getDebut().isAfter(vehicleReservation.getDebut()) && !reservation.getFin().isBefore(vehicleReservation.getDebut())) {
                    throw new ConstraintException("La réservation se supperpose à la reservation : " + vehicleReservation);
                } else if (!reservation.getFin().isBefore(vehicleReservation.getFin()) && !reservation.getDebut().isAfter(vehicleReservation.getFin())) {
                    throw new ConstraintException("La réservation se supperpose à la reservation : " + vehicleReservation);
                } else if (!reservation.getDebut().isBefore(vehicleReservation.getDebut()) && !reservation.getFin().isAfter(vehicleReservation.getFin())) {
                    throw new ConstraintException("La réservation se supperpose à la reservation : " + vehicleReservation);
                }
                // calculate consecutive days
                if (reservation.getDebut().isEqual(vehicleReservation.getFin().plusDays(1)) || reservation.getFin().isEqual(vehicleReservation.getDebut().minusDays(1))) {
                    if (vehicleReservation.getClient().getId() == reservation.getClient().getId()) {
                        userConsRes += Period.between(vehicleReservation.getDebut(), vehicleReservation.getFin()).getDays() + 1;
                    }
                    vehicleConsRes += Period.between(vehicleReservation.getDebut(), vehicleReservation.getFin()).getDays() + 1;
                }
                // verification : no more than 7 consecutive days for the same user
                if (userConsRes > 7) {
                    throw new ConstraintException("Cet utilisateur réserve ce véhicule plus de 7 jours consécutifs");
                }
                // verification : no more than 30 consecutive days
                if (vehicleConsRes > 30) {
                    throw new ConstraintException("Ce véhicule est réservé plus de 30 jours consécutifs");
                }
            }
            return this.reservationDao.create(reservation);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public long update(Reservation reservation) throws ServiceException, ConstraintException {
        try {
            List<Reservation> vehicleReservations = this.reservationDao.findResaByVehicleId(reservation.getVehicle().getId());
            int userConsRes = Period.between(reservation.getDebut(), reservation.getFin()).getDays() + 1; // include both first and last day - reservation are for entire days
            int vehicleConsRes = Period.between(reservation.getDebut(), reservation.getFin()).getDays() + 1;

            for (Reservation vehicleReservation : vehicleReservations) {
                if (vehicleReservation.getId() != reservation.getId()) {
                    if (!reservation.getDebut().isAfter(vehicleReservation.getDebut()) && !reservation.getFin().isBefore(vehicleReservation.getDebut())) {
                        throw new ConstraintException("La réservation se supperpose à la reservation : " + vehicleReservation);
                    } else if (!reservation.getFin().isBefore(vehicleReservation.getFin()) && !reservation.getDebut().isAfter(vehicleReservation.getFin())) {
                        throw new ConstraintException("La réservation se supperpose à la reservation : " + vehicleReservation);
                    } else if (!reservation.getDebut().isBefore(vehicleReservation.getDebut()) && !reservation.getFin().isAfter(vehicleReservation.getFin())) {
                        throw new ConstraintException("La réservation se supperpose à la reservation : " + vehicleReservation);
                    }
                    // calculate consecutive days
                    if (reservation.getDebut().isEqual(vehicleReservation.getFin().plusDays(1)) || reservation.getFin().isEqual(vehicleReservation.getDebut().minusDays(1))) {
                        if (vehicleReservation.getClient().getId() == reservation.getClient().getId()) {
                            userConsRes += Period.between(vehicleReservation.getDebut(), vehicleReservation.getFin()).getDays() + 1;
                        }
                        vehicleConsRes += Period.between(vehicleReservation.getDebut(), vehicleReservation.getFin()).getDays() + 1;
                    }
                    // verification : no more than 7 consecutive days for the same user
                    if (userConsRes > 7) {
                        throw new ConstraintException("Cet utilisateur réserve ce véhicule plus de 7 jours consécutifs");
                    }
                    // verification : no more than 30 consecutive days
                    if (vehicleConsRes > 30) {
                        throw new ConstraintException("Ce véhicule est réservé plus de 30 jours consécutifs");
                    }
                }
            }
            return this.reservationDao.update(reservation);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public long delete(Reservation reservation) throws ServiceException {
        try {
            return this.reservationDao.delete(reservation);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public long count() throws ServiceException {
        try {
            return this.reservationDao.count();
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public List<Reservation> findResaByClientId(long id) throws ServiceException {
        try {
            List<Reservation> reservation = new ArrayList<Reservation>();
            reservation = this.reservationDao.findResaByClientId(id);
            for (Reservation r : reservation) {
                r.setVehicle(this.vehicleDao.findById(r.getVehicle().getId()));
                r.setClient(this.clientDao.findById(r.getClient().getId()));
            }
            return reservation;
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public List<Reservation> findResaByVehicleId(long id) throws ServiceException {
        try {
            List<Reservation> reservation = new ArrayList<Reservation>();
            reservation = this.reservationDao.findResaByVehicleId(id);
            for (Reservation r : reservation) {
                r.setVehicle(this.vehicleDao.findById(r.getVehicle().getId()));
                r.setClient(this.clientDao.findById(r.getClient().getId()));
            }
            return reservation;
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public List<Reservation> findAll() throws ServiceException {
        try {
            List<Reservation> reservations = new ArrayList<Reservation>();
            reservations = this.reservationDao.findAll();
            for (Reservation r : reservations) {
                r.setVehicle(this.vehicleDao.findById(r.getVehicle().getId()));
                r.setClient(this.clientDao.findById(r.getClient().getId()));
            }
            return reservations;
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

}
