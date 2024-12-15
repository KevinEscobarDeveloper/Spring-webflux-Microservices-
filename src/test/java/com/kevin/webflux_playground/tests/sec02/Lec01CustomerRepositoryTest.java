package com.kevin.webflux_playground.tests.sec02;

import com.kevin.webflux_playground.sec02.entity.Customer;
import com.kevin.webflux_playground.sec02.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger; // Cambia a SLF4J
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

public class Lec01CustomerRepositoryTest extends AbstractTest{

    private static final Logger log = LoggerFactory.getLogger(Lec01CustomerRepositoryTest.class);

    @Autowired
    private CustomerRepository repository;

    @Test
    public void findAll() {
        this.repository.findAll()
                .doOnNext(c -> log.info("Cliente: {}", c)) // Registro adecuado
                .as(StepVerifier::create)
                .expectNextCount(10) // Espera 10 elementos
                .verifyComplete(); // Verifica que el flujo se complete
    }

    @Test
    public void findById() {
        this.repository.findById(2)
                .doOnNext(c -> log.info("Cliente: {}", c)) // Registro adecuado
                .as(StepVerifier::create)
                .assertNext(c -> Assertions.assertEquals("mike", c.getName()))// Espera 10 elementos
                .verifyComplete(); // Verifica que el flujo se complete
    }

    @Test
    public void findByName() {
        this.repository.findByName("jake")
                .doOnNext(c -> log.info("Client by email: {}", c)) // Registro adecuado
                .as(StepVerifier::create)
                .assertNext(c -> Assertions.assertEquals("jake@gmail.com", c.getEmail()))// Espera 10 elementos
                .verifyComplete(); // Verifica que el flujo se complete
    }

    @Test
    public void findByEmailEndingWith() {
        this.repository.findByEmailEndingWith("gmail.com")
                .doOnNext(c -> log.info("Client with gmail: {}", c)) // Registro adecuado
                .as(StepVerifier::create)
                .expectNextCount(3)// Espera 10 elementos
                .verifyComplete(); // Verifica que el flujo se complete
    }

    @Test
    public void insertAndDeleteCustomer(){
        //insert
        var customer = new Customer();
        customer.setName("Kevin");
        customer.setEmail("kevin@gmail.com");
        this.repository.save(customer)
                .doOnNext(c -> log.info("Saving client data: {}", c)) // Registro adecuado
                .as(StepVerifier::create)
                .assertNext(c -> Assertions.assertNotNull(c.getId()))
                .verifyComplete();

        //count
        this.repository.count()
                .as(StepVerifier::create)
                .expectNext(11L)
                .expectComplete()
                .verify();

        this.repository.deleteById(11).then(this.repository.count()) .as(StepVerifier::create)
                .expectNext(10L)
                .expectComplete()
                .verify();

    }

    @Test
    public void updateCustomer(){
        this.repository.findByName("ethan")
                .doOnNext(c -> c.setName("Noel"))
                .flatMap(c -> this.repository.save(c)).doOnNext(c -> log.info("update client: {}", c)) // Registro adecuado
                .as(StepVerifier::create)
                .assertNext(c -> Assertions.assertEquals("Noel", c.getName()))// Espera 10 elementos
                .verifyComplete();
    }
}
