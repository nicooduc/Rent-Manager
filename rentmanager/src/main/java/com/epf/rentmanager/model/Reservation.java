package com.epf.rentmanager.model;

import java.time.LocalDate;
public class Reservation {
    private long id;
    long client_id;
    long vehicule_id;
    private LocalDate debut;
    private LocalDate fin;
}