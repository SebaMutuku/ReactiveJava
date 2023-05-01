package com.learning.reactivejava.services;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.learning.reactivejava.entities.User;
import com.learning.reactivejava.repos.UserRepo;
import java.util.function.Function;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.reactivestreams.Publisher;
import org.springframework.http.server.reactive.ChannelSendOperator;
import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.Mono;

class UserServiceTest {
    /**
     * Method under test: {@link UserService#findUserById(Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFindUserById() {
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.findById(Mockito.<Long>any())).thenReturn(null);
        (new UserService(userRepo)).findUserById(1L);
    }

    /**
     * Method under test: {@link UserService#findUserById(Long)}
     */
    @Test
    void testFindUserById2() {
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.findById(Mockito.<Long>any())).thenReturn(mock(Mono.class));
        (new UserService(userRepo)).findUserById(1L);
        verify(userRepo).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserService#findAllUsers()}
     */
    @Test
    void testFindAllUsers() {
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.findAll()).thenReturn(DirectProcessor.create());
        (new UserService(userRepo)).findAllUsers();
        verify(userRepo).findAll();
    }

    /**
     * Method under test: {@link UserService#findAllUsers()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFindAllUsers2() {
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.findAll()).thenReturn(null);
        (new UserService(userRepo)).findAllUsers();
    }

    /**
     * Method under test: {@link UserService#updateUser(User)}
     */
    @Test
    void testUpdateUser() {
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.findById(Mockito.<Long>any())).thenReturn(null);
        UserService userService = new UserService(userRepo);
        userService.updateUser(new User());
    }

    /**
     * Method under test: {@link UserService#updateUser(User)}
     */
    @Test
    void testUpdateUser2() {
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.findById(Mockito.<Long>any())).thenReturn(null);
        (new UserService(userRepo)).updateUser(null);
    }

    /**
     * Method under test: {@link UserService#updateUser(User)}
     */
    @Test
    void testUpdateUser3() {
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.save(Mockito.<User>any())).thenReturn(null);
        when(userRepo.findById(Mockito.<Long>any())).thenReturn(mock(Mono.class));
        UserService userService = new UserService(userRepo);
        userService.updateUser(new User());
    }

    /**
     * Method under test: {@link UserService#updateUser(User)}
     */
    @Test
    void testUpdateUser4() {
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.save(Mockito.<User>any())).thenReturn(mock(Mono.class));
        when(userRepo.findById(Mockito.<Long>any())).thenReturn(mock(Mono.class));
        UserService userService = new UserService(userRepo);
        userService.updateUser(new User());
        verify(userRepo).findById(Mockito.<Long>any());
        verify(userRepo).save(Mockito.<User>any());
    }

    /**
     * Method under test: {@link UserService#deleteUser(Long)}
     */
    @Test
    void testDeleteUser() {
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.deleteById(Mockito.<Long>any()))
                .thenReturn(new ChannelSendOperator<>(mock(Publisher.class), mock(Function.class)));
        (new UserService(userRepo)).deleteUser(1L);
        verify(userRepo).deleteById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserService#deleteUser(Long)}
     */
    @Test
    void testDeleteUser2() {
        UserRepo userRepo = mock(UserRepo.class);
        when(userRepo.deleteById(Mockito.<Long>any())).thenReturn(null);
        (new UserService(userRepo)).deleteUser(1L);
    }
}

