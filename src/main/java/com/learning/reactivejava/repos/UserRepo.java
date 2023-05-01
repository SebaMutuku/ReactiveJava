package com.learning.reactivejava.repos;

import com.learning.reactivejava.entities.Users;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepo extends ReactiveCrudRepository<Users, Long> {

    Mono<Users> findById(Long aLong);
}
