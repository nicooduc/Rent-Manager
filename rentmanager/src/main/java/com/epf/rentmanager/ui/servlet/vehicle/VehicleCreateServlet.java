package com.epf.rentmanager.ui.servlet.vehicle;

import com.epf.rentmanager.exception.ConstraintException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/vehicles/create")
public class VehicleCreateServlet extends HttpServlet {
    @Autowired
    VehicleService vehicleService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/create.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Vehicle vehicle = new Vehicle(0,
                req.getParameter("constructeur"),
                req.getParameter("modele"),
                Integer.parseInt(req.getParameter("nb_places")));
        try {
            this.vehicleService.create(vehicle);
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (ConstraintException e) {
            req.setAttribute("error", e); //TODO display error
            e.printStackTrace();
        }
        resp.sendRedirect("/rentmanager/vehicles");
    }
}
