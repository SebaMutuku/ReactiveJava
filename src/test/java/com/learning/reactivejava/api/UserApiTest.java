package com.learning.reactivejava.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.learning.reactivejava.entities.User;
import com.learning.reactivejava.repos.UserRepo;
import com.learning.reactivejava.services.UserService;
import com.learning.reactivejava.utils.dtos.ResponseDTO;
import java.util.function.Function;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.reactivestreams.Publisher;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ChannelSendOperator;
import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

class UserApiTest {
    /**
     * Method under test: {@link UserApi#findUserById(Long)}
     */
    @Test
    void testFindUserById() {

        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.findById(Mockito.<Long>any())).thenReturn(null);
        (new UserApi(new UserService(userRepo))).findUserById(1L);
    }

    /**
     * Method under test: {@link UserApi#findUserById(Long)}
     */
    @Test
    void testFindUserById2() {

        UserService userService = mock(UserService.class);
        when(userService.findUserById(Mockito.<Long>any())).thenReturn(null);
        ResponseEntity<Mono<ResponseDTO>> actualFindUserByIdResult = (new UserApi(userService)).findUserById(1L);
        assertNull(actualFindUserByIdResult.getBody());
        assertEquals(200, actualFindUserByIdResult.getStatusCode().value());
        assertTrue(actualFindUserByIdResult.getHeaders().isEmpty());
        verify(userService).findUserById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserApi#findUserById(Long)}
     */
    @Test
    void testFindUserById3() {
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.findById(Mockito.<Long>any())).thenReturn(mock(Mono.class));
        ResponseEntity<Mono<ResponseDTO>> actualFindUserByIdResult = (new UserApi(new UserService(userRepo)))
                .findUserById(1L);
        assertTrue(actualFindUserByIdResult.hasBody());
        assertTrue(actualFindUserByIdResult.getHeaders().isEmpty());
        assertEquals(200, actualFindUserByIdResult.getStatusCode().value());
        verify(userRepo).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserApi#updateUser(User)}
     */
    @Test
    void testUpdateUser() {
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.findById(Mockito.<Long>any())).thenReturn(null);
        UserApi userApi = new UserApi(new UserService(userRepo));
        userApi.updateUser(new User());
    }

    /**
     * Method under test: {@link UserApi#updateUser(User)}
     */
    @Test
    void testUpdateUser2() {
        UserService userService = mock(UserService.class);
        when(userService.updateUser(Mockito.<User>any())).thenReturn(null);
        UserApi userApi = new UserApi(userService);
        ResponseEntity<Mono<ResponseDTO>> actualUpdateUserResult = userApi.updateUser(new User());
        assertNull(actualUpdateUserResult.getBody());
        assertEquals(200, actualUpdateUserResult.getStatusCode().value());
        assertTrue(actualUpdateUserResult.getHeaders().isEmpty());
        verify(userService).updateUser(Mockito.<User>any());
    }

    /**
     * Method under test: {@link UserApi#updateUser(User)}
     */
    @Test
    void testUpdateUser3() {
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.save(Mockito.<User>any())).thenReturn(null);
        when(userRepo.findById(Mockito.<Long>any())).thenReturn(mock(Mono.class));
        UserApi userApi = new UserApi(new UserService(userRepo));
        userApi.updateUser(new User());
    }

    /**
     * Method under test: {@link UserApi#updateUser(User)}
     */
    @Test
    void testUpdateUser4() {
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.save(Mockito.<User>any())).thenReturn(mock(Mono.class));
        when(userRepo.findById(Mockito.<Long>any())).thenReturn(mock(Mono.class));
        UserApi userApi = new UserApi(new UserService(userRepo));
        ResponseEntity<Mono<ResponseDTO>> actualUpdateUserResult = userApi.updateUser(new User());
        assertTrue(actualUpdateUserResult.hasBody());
        assertTrue(actualUpdateUserResult.getHeaders().isEmpty());
        assertEquals(200, actualUpdateUserResult.getStatusCodeValue());
        verify(userRepo).findById(Mockito.<Long>any());
        verify(userRepo).save(Mockito.<User>any());
    }

    /**
     * Method under test: {@link UserApi#deleteById(Long)}
     */
    @Test
    void testDeleteById() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.deleteById(Mockito.<Long>any()))
                .thenReturn(new ChannelSendOperator<>(mock(Publisher.class), mock(Function.class)));
        ResponseEntity<Mono<ResponseDTO>> actualDeleteByIdResult = (new UserApi(new UserService(userRepo))).deleteById(1L);
        assertTrue(actualDeleteByIdResult.hasBody());
        assertTrue(actualDeleteByIdResult.getHeaders().isEmpty());
        assertEquals(200, actualDeleteByIdResult.getStatusCode().value());
        verify(userRepo).deleteById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserApi#deleteById(Long)}
     */
    @Test
    void testDeleteById2() {
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.deleteById(Mockito.<Long>any())).thenReturn(null);
        (new UserApi(new UserService(userRepo))).deleteById(1L);
    }

    /**
     * Method under test: {@link UserApi#deleteById(Long)}
     */
    @Test
    void testDeleteById3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        UserService userService = mock(UserService.class);
        when(userService.deleteUser(Mockito.<Long>any())).thenReturn(null);
        ResponseEntity<Mono<ResponseDTO>> actualDeleteByIdResult = (new UserApi(userService)).deleteById(1L);
        assertNull(actualDeleteByIdResult.getBody());
        assertEquals(200, actualDeleteByIdResult.getStatusCode().value());
        assertTrue(actualDeleteByIdResult.getHeaders().isEmpty());
        verify(userService).deleteUser(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserApi#findAll()}
     */
    @Test
    void testFindAll() {
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.findAll()).thenReturn(DirectProcessor.create());
        ResponseEntity<Flux<ResponseDTO>> actualFindAllResult = (new UserApi(new UserService(userRepo))).findAll();
        assertTrue(actualFindAllResult.hasBody());
        assertTrue(actualFindAllResult.getHeaders().isEmpty());
        assertEquals(200, actualFindAllResult.getStatusCode().value());
        verify(userRepo).findAll();
    }

    /**
     * Method under test: {@link UserApi#findAll()}
     */
    @Test
    void testFindAll2() {
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.findAll()).thenReturn(null);
        (new UserApi(new UserService(userRepo))).findAll();
    }

    /**
     * Method under test: {@link UserApi#findAll()}
     */
    @Test
    void testFindAll3() {
        UserService userService = mock(UserService.class);
        when(userService.findAllUsers()).thenReturn(DirectProcessor.create());
        ResponseEntity<Flux<ResponseDTO>> actualFindAllResult = (new UserApi(userService)).findAll();
        assertTrue(actualFindAllResult.hasBody());
        assertEquals(200, actualFindAllResult.getStatusCode().value());
        assertTrue(actualFindAllResult.getHeaders().isEmpty());
        verify(userService).findAllUsers();
    }
}

