package com.kevin.webflux_playground.sec02.repository;

import com.kevin.webflux_playground.sec02.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ProductsRepository extends ReactiveCrudRepository<Product, Integer> {
    Flux<Product> findByPriceBetween(int startPrice, int endPrice);

    Flux<Product> findBy(Pageable pageable);


}
