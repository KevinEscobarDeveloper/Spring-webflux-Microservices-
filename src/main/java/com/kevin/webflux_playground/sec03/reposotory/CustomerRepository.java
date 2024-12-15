package com.kevin.webflux_playground.sec03.reposotory;

import com.kevin.webflux_playground.sec02.entity.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Customer,  Integer> {

}
