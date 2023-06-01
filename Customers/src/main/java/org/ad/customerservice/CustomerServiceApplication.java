package org.ad.customerservice;

import org.ad.customerservice.Entities.CustomerEntity;
import org.ad.customerservice.Repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerRepository customerRepository ) {
        return args -> {
            customerRepository.save(new CustomerEntity(null,"Amine","adrawil@vermeg.com"));
            customerRepository.save(new CustomerEntity(null,"Zied","zied@vermeg.com"));
            customerRepository.save(new CustomerEntity(null,"Ahmed","ahmed@vermeg.com"));
            customerRepository.findAll().forEach(System.out::println);
        };
    }
}
