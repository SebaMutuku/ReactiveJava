package com.learning.reactivejava.services.impl;

import com.learning.reactivejava.entities.User;
import com.learning.reactivejava.utils.dtos.ResponseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public abstract class UserServiceImpl {
    public abstract Mono<ResponseDTO> findUserById(Long userId);
    public abstract Flux<ResponseDTO> findAllUsers();
    public abstract Mono<ResponseDTO> updateUser(User user);
    public abstract Mono<ResponseDTO> deleteUser(Long userId);
    public abstract Mono<ResponseDTO> createUser(User user);
}
