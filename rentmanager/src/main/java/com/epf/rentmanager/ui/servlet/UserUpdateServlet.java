package com.epf.rentmanager.ui.servlet;

import com.epf.rentmanager.exception.ConstraintException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
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

@WebServlet("/users/update")
public class UserUpdateServlet extends HttpServlet {
    //private static final long serialVersionUID = 1L; //delete if everything still work
    @Autowired
    private ClientService clientService;
    int clientId;
    Client client = new Client();
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        clientId = Integer.parseInt(req.getParameter("id"));
        try {
            client = this.clientService.findById(clientId);
            req.setAttribute("client", client);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/update.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String lastname = req.getParameter("lastname");
        String firstname = req.getParameter("firstname");
        String email = req.getParameter("email");
        String birthdate = req.getParameter("birthdate");
        if (!lastname.isEmpty()) {
            client.setNom(lastname);
        }
        if (!firstname.isEmpty()) {
            client.setPrenom(firstname);
        }
        if (!email.isEmpty()) {
            client.setEmail(email);
        }
        if (!birthdate.isEmpty()) {
            client.setNaissance(LocalDate.parse(birthdate));
        }

        try {
            this.clientService.update(client);
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (ConstraintException e) {
            req.setAttribute("error", e); //TODO display error
            e.printStackTrace();
        }

        resp.sendRedirect("/rentmanager/users");
    }
}
