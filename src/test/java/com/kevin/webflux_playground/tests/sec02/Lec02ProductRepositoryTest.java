package com.kevin.webflux_playground.tests.sec02;

import com.kevin.webflux_playground.sec02.repository.ProductsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import reactor.test.StepVerifier;

public class Lec02ProductRepositoryTest extends AbstractTest{

    private static final Logger log = LoggerFactory.getLogger(Lec02ProductRepositoryTest.class);

    @Autowired
    private ProductsRepository repository;

    @Test
    public void findBetweenTwoPrices() {
        this.repository.findByPriceBetween(300, 1000)
                .doOnNext(p -> log.info("Found {}", p))
                .as(StepVerifier::create)
                .expectNextMatches(product -> product.getPrice() >= 300 && product.getPrice() <= 1000) // Valida el rango
                .expectNextMatches(product -> product.getPrice() >= 300 && product.getPrice() <= 1000)
                .expectNextMatches(product -> product.getPrice() >= 300 && product.getPrice() <= 1000)
                .expectNextMatches(product -> product.getPrice() >= 300 && product.getPrice() <= 1000)
                .expectNextMatches(product -> product.getPrice() >= 300 && product.getPrice() <= 1000)
                .verifyComplete();
    }

    @Test
    public void pageable() {
        this.repository.findBy(PageRequest.of(0,3).withSort(Sort.by("price").ascending()))
                .doOnNext(p -> log.info("Found {}", p))
                .as(StepVerifier::create)
                .assertNext(p -> Assertions.assertEquals(200, p.getPrice()))
                .assertNext(p -> Assertions.assertEquals(250, p.getPrice()))
                .assertNext(p -> Assertions.assertEquals(300, p.getPrice()))
                .verifyComplete();
    }
}
