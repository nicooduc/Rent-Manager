package com.epf.rentmanager.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;

@Configuration
@ComponentScan({"com.epf.rentmanager.service", "com.epf.rentmanager.dao", "com.epf.rentmanager.persistence"})

public class AppConfiguration {

}