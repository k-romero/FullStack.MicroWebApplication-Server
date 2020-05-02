package com.cjk.stackcast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.context.annotation.Bean;
//
//import org.h2.server.web.WebServlet;

@SpringBootApplication
public class StackCastApplication {
    public static void main(String[] args) {
        SpringApplication.run(StackCastApplication.class, args);
    }

//    @Bean
//    ServletRegistrationBean<?> h2servletRegistration(){
//        ServletRegistrationBean<?> registrationBean = new ServletRegistrationBean<>( new WebServlet());
//        registrationBean.addUrlMappings("/console/*");
//        return registrationBean;
//    }
}
