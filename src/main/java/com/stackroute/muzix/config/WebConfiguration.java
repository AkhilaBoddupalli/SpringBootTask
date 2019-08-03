package com.stackroute.muzix.config;

import com.stackroute.muzix.exceptions.UserAlreadyExistsException;
import com.stackroute.muzix.model.User;
import com.stackroute.muzix.service.UserService;
import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component // marks a java class as a bean so the component-scanning mechanism of spring can pick it up and pull it into the application context
public class WebConfiguration {
    private final
    UserService userService;

    @Autowired
    public WebConfiguration(UserService trackService) {
        this.userService = trackService;
    }



    @Bean // indicates that a method produces a bean to be managed by Spring.
    ServletRegistrationBean h2servletRegistration()
    {
        ServletRegistrationBean registrationBean=new ServletRegistrationBean(new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }


}
