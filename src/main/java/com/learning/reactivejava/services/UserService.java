package com.learning.reactivejava.services;

import com.learning.reactivejava.entities.Users;
import com.learning.reactivejava.repos.UserRepo;
import com.learning.reactivejava.services.impl.UserServiceImpl;
import com.learning.reactivejava.utils.dtos.ResponseDTO;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class UserService extends UserServiceImpl {

    private final UserRepo userRepo;

    public UserService(@Autowired UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public Mono<ResponseDTO> findUserById(Long userId) {
        return userRepo.findById(userId)
                .mapNotNull(user -> new ResponseDTO(user, HttpStatus.OK, "Success"))
                .onErrorMap(error -> new Throwable(error.getMessage()));
    }

    @Override
    public Flux<ResponseDTO> findAllUsers() {
        return userRepo.findAll().delayElements(Duration.ofSeconds(1))
                .map(users -> new ResponseDTO(users, HttpStatus.OK, "success"));
    }

    @Override
    public Mono<ResponseDTO> updateUser(Users userInstance) {
        return userRepo.findById(userInstance.getId()).doOnNext(user -> {
                    user.setUserName(userInstance.getUserName());
                    user.setAge(userInstance.getAge());
                    user.setUserName(userInstance.getUserName());
                    user.setLastName(userInstance.getLastName());
                    user.setFullName(user.getFullName());
                }).flatMap(userRepo::save).switchIfEmpty(userRepo.save(userInstance))
                .map(users -> new ResponseDTO(users, HttpStatus.OK, "Success"))
                .onErrorMap(error -> new Throwable(error.getMessage()));
    }

    @Override
    public Mono<ResponseDTO> deleteUser(Long userId) {
        return userRepo.deleteById(userId)
                .mapNotNull(u -> new ResponseDTO(null, HttpStatus.OK, "Success"))
                .switchIfEmpty(Mono.just(
                        new ResponseDTO(null, HttpStatus.EXPECTATION_FAILED, "Failed. User not found")));
    }
}
