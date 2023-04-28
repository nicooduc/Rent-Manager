package com.epf.rentmanager.ui.servlet.vehicle;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/vehicles")
public class VehicleListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Autowired
    VehicleService vehicleService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("vehicles", this.vehicleService.findAll());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/list.jsp").forward(req, resp);
    }
}
