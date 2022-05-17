package com.scool.notifyme.registration;


import lombok.*;

@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class RegistrationRequest {

    private String email;
    private String alias;
}
