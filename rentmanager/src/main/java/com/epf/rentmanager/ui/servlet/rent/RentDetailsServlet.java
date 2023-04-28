package com.epf.rentmanager.ui.servlet.rent;

import com.epf.rentmanager.exception.ServiceException;
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

@WebServlet("/rents/details")
public class RentDetailsServlet extends HttpServlet {
    @Autowired
    ReservationService reservationService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int resaId = Integer.parseInt(req.getParameter("id"));
        try {
            Reservation reservation = this.reservationService.findResaById(resaId);
            req.setAttribute("reservation", reservation);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/details.jsp").forward(req, resp);
    }
}
