package com.scool.notifyme;


import com.scool.notifyme.email.EmailService;
import com.scool.notifyme.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class NotifiyController {

    private final EmailService emailService;
    private final UserService userService;

    @PostMapping(path = "/send/{to}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> sendEmail(@RequestBody String rawRequest,
                                    @PathVariable(name = "to") String to) {
        NotifyRequest request = NotifyConverter.convert(rawRequest);
        if (userService.findByEmail(to).isEmpty() || userService.findByAlias(to).isEmpty()) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        } else if (userService.findByAlias(to).isPresent()) {
            to = userService.findByAlias(to).get().getEmail();
        }
        emailService.send(to, request.get_subject(), request.getBody(), request.get_replyTo());
        if (request.get_redirect() != null) {
            return new ResponseEntity<>("redirect:" + request.get_redirect(), HttpStatus.OK);
        }
        return new ResponseEntity<>("redirect:/success.html", HttpStatus.OK);

    }



}
