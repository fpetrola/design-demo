package com.design.customerprocessor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(considerNestedRepositories = true)
@SpringBootApplication
public class ProcessorApplication implements CommandLineRunner {

//    @Autowired
//    Jpa2Repository jpa2Repository;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ProcessorApplication.class, args);
    }

    public void run(String... args) throws Exception {

        new NoDesignCustomerProcessor().main(null);
    }

}