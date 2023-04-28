package com.epf.rentmanager.ui.servlet.user;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/users/details")
public class UserDetailsServlet extends HttpServlet {
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
        int clientId = Integer.parseInt(req.getParameter("id"));
        try {
            List<Reservation> reservation = this.reservationService.findResaByClientId(clientId);
            List<Vehicle> vehicles = new ArrayList<>();
            for (Reservation r : reservation) {
                if (!vehicles.contains(r.getVehicle())) {
                    vehicles.add(r.getVehicle());
                }
            }
            req.setAttribute("vehicles", vehicles);
            req.setAttribute("client", this.clientService.findById(clientId));
            req.setAttribute("reservations", reservation);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/details.jsp").forward(req, resp);
    }
}
