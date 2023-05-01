package com.learning.reactivejava.api;

import com.learning.reactivejava.entities.User;
import com.learning.reactivejava.services.UserService;
import com.learning.reactivejava.utils.dtos.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/app/vi/users")
@CrossOrigin()
public class UserApi {

    private final UserService userService;

    public UserApi(@Autowired UserService userService) {
        this.userService = userService;

    }

    @GetMapping(value = "/findbyid/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<ResponseDTO>> findUserById(@PathVariable(value = "userId") Long userId) {
        return ResponseEntity.ok(userService.findUserById(userId));
    }

    @PutMapping(value = "/updateuser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<ResponseDTO>> updateUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(user));
    }

    @DeleteMapping(value = "/deletebyid/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mono<ResponseDTO>> deleteById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.deleteUser(userId));
    }

    @GetMapping(value = "/findall", consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Flux<ResponseDTO>> findAll() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

}
