package com.learning.reactivejava.repos;

import com.learning.reactivejava.entities.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepo extends ReactiveCrudRepository<User, Long> {

    Mono<User> findById(Long aLong);
    Mono<User> findByUserNameIgnoreCase(String username);
}
