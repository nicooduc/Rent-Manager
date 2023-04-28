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

@WebServlet("/rents/create")
public class RentCreateServlet extends HttpServlet {
    @Autowired
    VehicleService vehicleService;
    @Autowired
    ClientService clientService;
    @Autowired
    ReservationService reservationService;
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("vehicles", this.vehicleService.findAll());
            req.setAttribute("clients", this.clientService.findAll());
        } catch (ServiceException e) {
            req.setAttribute("error", e); //TODO display error
            e.printStackTrace();
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/create.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Reservation reservation = new Reservation(0,
                new Client(Long.parseLong(req.getParameter("client"))),
                new Vehicle(Long.parseLong(req.getParameter("car"))),
                LocalDate.parse(req.getParameter("begin")),
                LocalDate.parse(req.getParameter("end")));
        try {
            this.reservationService.create(reservation);
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (ConstraintException e) {
            req.setAttribute("error", e); //TODO display error
            e.printStackTrace();
        }
        resp.sendRedirect("/rentmanager/rents");
    }
}
