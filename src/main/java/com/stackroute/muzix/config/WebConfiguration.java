package com.stackroute.muzix.config;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component // marks a java class as a bean so the component-scanning mechanism of spring can pick it up and pull it into the application context
public class WebConfiguration {
    @Bean // indicates that a method produces a bean to be managed by Spring.
    ServletRegistrationBean h2servletRegistration()
    {
        ServletRegistrationBean registrationBean=new ServletRegistrationBean(new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }


}
