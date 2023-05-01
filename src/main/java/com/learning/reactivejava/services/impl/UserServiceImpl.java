package com.learning.reactivejava.services.impl;

import com.learning.reactivejava.entities.Users;
import com.learning.reactivejava.utils.dtos.RequestDTO;
import com.learning.reactivejava.utils.dtos.ResponseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public abstract class UserServiceImpl {
    public abstract Mono<ResponseDTO> findUserById(Long userId);
    public abstract Flux<ResponseDTO> findAllUsers();
    public abstract Mono<ResponseDTO> updateUser(Users userInstance);
    public abstract Mono<ResponseDTO> deleteUser(Long userId);
}
