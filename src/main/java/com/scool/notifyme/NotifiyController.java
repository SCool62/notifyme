package com.scool.notifyme;


import com.scool.notifyme.email.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class NotifiyController {

    private final EmailService emailService;

    @PostMapping("/send/{to}")
    public String sendEmail(@RequestBody NotifyRequest request, @PathVariable(name = "to") String to) {
        emailService.send(to, request.get_subject(), request.getBody(), request.get_replyTo());
        if (request.get_redirect() != null) {
            return "redirect:" + request.get_redirect();
        }
        return "redirect:/success.html";
    }



}
