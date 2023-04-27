package com.epf.rentmanager.ui.servlet;

import com.epf.rentmanager.exception.ConstraintException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/users/create")
public class UserCreateServlet extends HttpServlet {
    //private static final long serialVersionUID = 1L; //delete if everything still work
    @Autowired
    private ClientService clientService;
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/create.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Client client = new Client(0, req.getParameter("lastname"), req.getParameter("firstname"), req.getParameter("email"), LocalDate.parse(req.getParameter("birthdate")));

        try {
            this.clientService.create(client);
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (ConstraintException e) {
            req.setAttribute("error", e); //TODO display error
            e.printStackTrace();
        }

        resp.sendRedirect("/rentmanager/users");
    }
}
