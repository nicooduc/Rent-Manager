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

@WebServlet("/vehicles/update")
public class VehicleUpdateServlet extends HttpServlet {
    @Autowired
    VehicleService vehicleService;
    int vehicleId;
    Vehicle vehicle = new Vehicle();

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        vehicleId = Integer.parseInt(req.getParameter("id"));
        try {
            vehicle = this.vehicleService.findById(vehicleId);
            req.setAttribute("vehicle", vehicle);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/update.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String constructeur = req.getParameter("constructeur");
        String modele = req.getParameter("modele");
        String nb_places = req.getParameter("nb_places");
        if (!constructeur.isEmpty()) {
            vehicle.setConstructeur(constructeur);
        }
        if (!modele.isEmpty()) {
            vehicle.setModele(modele);
        }
        if (!nb_places.isEmpty()) {
            vehicle.setNb_places(Integer.parseInt(nb_places));
        }

        try {
            this.vehicleService.update(vehicle);
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (ConstraintException e) {
            req.setAttribute("error", e); //TODO display error
            e.printStackTrace();
        }
        resp.sendRedirect("/rentmanager/vehicles");
    }
}
