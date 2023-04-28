package com.epf.rentmanager.ui.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
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

@WebServlet("/vehicles/details")
public class VehicleDetailsServlet extends HttpServlet {
    //private static final long serialVersionUID = 1L; //delete if everything still work
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
        int vehicleId = Integer.parseInt(req.getParameter("id"));

        try {
            List<Reservation> reservation = this.reservationService.findResaByVehicleId(vehicleId);
            List<Client> clients = new ArrayList<>();;
            for (Reservation r : reservation) {
                r.setClient(this.clientService.findById(r.getClient().getId()));
                if (!clients.contains(r.getClient())) {
                    clients.add(r.getClient());
                }
            }
            req.setAttribute("clients", clients);
            req.setAttribute("vehicle", this.vehicleService.findById(vehicleId));
            req.setAttribute("reservations", reservation);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/details.jsp").forward(req, resp);
    }
}
