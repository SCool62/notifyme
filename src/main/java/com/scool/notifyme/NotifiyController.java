package com.scool.notifyme;


import com.scool.notifyme.email.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class NotifiyController {

    private final EmailService emailService;

    @PostMapping(path = "/send/{to}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String sendEmail(@RequestBody String rawRequest,
                            @PathVariable(name = "to") String to) {
        NotifyRequest request = NotifyConverter.convert(rawRequest);
        emailService.send(to, request.get_subject(), request.getBody(), request.get_replyTo());
        if (request.get_redirect() != null) {
            return "redirect:" + request.get_redirect();
        }
        return "redirect:/success.html";

    }



}
