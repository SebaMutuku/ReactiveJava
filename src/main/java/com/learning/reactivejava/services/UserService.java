package com.learning.reactivejava.services;

import com.learning.reactivejava.entities.User;
import com.learning.reactivejava.exception.GenericException;
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
                .mapNotNull(u -> new ResponseDTO(u, HttpStatus.OK, "Success"))
                .switchIfEmpty(Mono.just(new ResponseDTO(null, HttpStatus.NOT_FOUND, "User not found")));
    }

    @Override
    public Flux<ResponseDTO> findAllUsers() {
        return userRepo.findAll().delayElements(Duration.ofSeconds(1))
                .map(users -> new ResponseDTO(users, HttpStatus.OK, "success"));
    }

    @Override
    public Mono<ResponseDTO> updateUser(User user) {
        return userRepo.findById(user.getId()).doOnNext(u -> {
                    u.setUserName(user.getUserName());
                    u.setAge(user.getAge());
                    u.setUserName(user.getUserName());
                    u.setLastName(user.getLastName());
                    u.setFullName(u.getFullName());
                }).flatMap(userRepo::save).switchIfEmpty(userRepo.save(user))
                .map(users -> new ResponseDTO(users, HttpStatus.OK, "Success"))
                .switchIfEmpty(Mono.just(new ResponseDTO(null, HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred")));
    }

    @Override
    public Mono<ResponseDTO> deleteUser(Long userId) {
        return userRepo.deleteById(userId)
                .mapNotNull(u -> new ResponseDTO(null, HttpStatus.OK, "Success"))
                .switchIfEmpty(Mono.just(
                        new ResponseDTO(null, HttpStatus.NOT_FOUND, "Failed. User not found")));
    }

    /**
     * @param user
     * @return ResponseDTO
     */
    @Override
    public Mono<ResponseDTO> createUser(User user) {
        return userRepo.findByUserNameIgnoreCase(user.getUserName()).doOnNext(u -> new ResponseDTO(
                u,
                HttpStatus.OK,
                "User already exists"
        )).switchIfEmpty(userRepo.save(user)).map(u -> new ResponseDTO(u, HttpStatus.OK, "Successfully added users"
        )).onErrorMap(error -> new GenericException(error.getMessage(), HttpStatus.EXPECTATION_FAILED));
    }
}
