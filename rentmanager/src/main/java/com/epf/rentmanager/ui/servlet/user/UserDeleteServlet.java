package com.epf.rentmanager.ui.servlet.user;

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

@WebServlet("/users/delete")
public class UserDeleteServlet extends HttpServlet {
    @Autowired
    ClientService clientService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Client client = new Client(Integer.parseInt(req.getParameter("id")));
        try {
            this.clientService.delete(client);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/rentmanager/users");
    }
}
