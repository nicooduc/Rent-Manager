package com.epf.rentmanager.ui.servlet.rent;

import com.epf.rentmanager.exception.ConstraintException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.VehicleService;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/rents/update")
public class RentUpdateServlet extends HttpServlet {
    @Autowired
    VehicleService vehicleService;
    @Autowired
    ClientService clientService;
    @Autowired
    ReservationService reservationService;
    int resaId;
    Reservation reservation = new Reservation();

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resaId = Integer.parseInt(req.getParameter("id"));
        try {
            reservation = this.reservationService.findResaById(resaId);
            req.setAttribute("reservation", reservation);
            req.setAttribute("vehicles", this.vehicleService.findAll());
            req.setAttribute("clients", this.clientService.findAll());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/update.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String client = req.getParameter("client");
        String car = req.getParameter("car");
        String begin = req.getParameter("begin");
        String end = req.getParameter("end");
        if (!(null == client)) {
            reservation.setClient(new Client(Long.parseLong(client)));
        }
        if (!(null == car)) {
            reservation.setVehicle(new Vehicle(Long.parseLong(car)));
        }
        if (!begin.isEmpty()) {
            reservation.setDebut(LocalDate.parse(begin));
        }
        if (!end.isEmpty()) {
            reservation.setFin(LocalDate.parse(end));
        }

        try {
            this.reservationService.update(reservation);
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (ConstraintException e) {
            req.setAttribute("error", e); //TODO display error
            e.printStackTrace();
        }
        resp.sendRedirect("/rentmanager/rents");
    }
}
