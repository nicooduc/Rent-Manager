package com.epf.rentmanager.main;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.*;
import com.epf.rentmanager.service.ClientService;

public class main {
    public static void main(String[] args) {
    try {
        System.out.println(ClientService.getInstance().findAll());
    } catch (ServiceException e) {
        e.printStackTrace();
    }



    }

}