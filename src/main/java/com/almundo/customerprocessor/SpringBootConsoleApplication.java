package com.almundo.customerprocessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class SpringBootConsoleApplication implements CommandLineRunner {

	@Autowired
	JpaProcessorRepository jpaProcessorRepository;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringBootConsoleApplication.class, args);
	}

	public void run(String... args) throws Exception {
//		new CustomerProcessor().process(jpaProcessorRepository, new CsvParserCsvReader());
		NoDesignCustomerProcessor.main(null);
	}
}