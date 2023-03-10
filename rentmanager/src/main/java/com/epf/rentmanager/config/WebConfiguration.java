package com.epf.rentmanager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.servlet.config.annotation.EnableWebMvc;
import org.springframework.WebApplicationInitializer;
import org.springframework.context.ContextLoaderListener;
import org.springframework.context.support.AnnotationConfigWebApplicationContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;


@Configuration
@EnableWebMvc
public class WebConfiguration implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(AppConfiguration.class);
        servletContext.addListener(new ContextLoaderListener(rootContext));
    }
}
