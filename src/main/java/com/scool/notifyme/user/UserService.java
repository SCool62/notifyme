package com.scool.notifyme.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private UserRepository repository;

    public Optional<User> findByAlias(String alias) {
        return repository.findByAlias(alias);
    }

    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }



}
