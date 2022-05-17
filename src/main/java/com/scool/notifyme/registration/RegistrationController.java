package com.scool.notifyme.registration;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED, code = HttpStatus.CREATED)
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    @GetMapping("confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }




}
