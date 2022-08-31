package com.example.entregable02;

import com.example.entregable02.Entities.Laptop;
import com.example.entregable02.Repositories.ILaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.time.LocalTime;

@EnableWebMvc
@SpringBootApplication
public class Entregable02Application {

    public static void main(String[] args) {

       ApplicationContext context = SpringApplication.run(Entregable02Application.class, args);

       ILaptopRepository laptopRepository = context.getBean(ILaptopRepository.class);

        Laptop laptop1 = new Laptop("Lenovo","4GB","1TB",1000, LocalTime.now());
        Laptop laptop2 = new Laptop("DELL","12GB","2TB",4000, LocalTime.now());

        laptopRepository.save(laptop1);
        laptopRepository.save(laptop2);

    }

}
