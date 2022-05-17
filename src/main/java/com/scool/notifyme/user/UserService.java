package com.scool.notifyme.user;

import com.scool.notifyme.registration.token.ConfirmationToken;
import com.scool.notifyme.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class UserService {

    private UserRepository repository;
    private ConfirmationTokenService confirmationTokenService;

    public Optional<User> findByAlias(String alias) {
        return repository.findByAlias(alias);
    }

    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public String signUpUser(User user) {
        boolean userExists = findByEmail(user.getEmail()).isPresent();
        if (userExists) {
            throw new IllegalStateException("User already exists");
        }
        repository.save(user);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken =
                new ConfirmationToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), user);
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return token;
    }

    public void enableUser(String email) {
        Optional<User> user = findByEmail(email);
        if (user.isEmpty()) {
            throw new IllegalStateException("User does not exist");
        }
        user.get().setEnabled(true);
        repository.save(user.get());
    }



}
