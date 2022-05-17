package com.scool.notifyme.registration;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;


@Service
public class EmailValidator implements Predicate<String> {
    @Override
    public boolean test(String email) {
        // TODO: check if email is valid
        return true;
    }
}
