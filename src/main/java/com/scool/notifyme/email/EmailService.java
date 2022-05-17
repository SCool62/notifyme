package com.scool.notifyme.email;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
@AllArgsConstructor
public class EmailService implements MailSender {

    private final JavaMailSender javaMailSender;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    @Override
    @Async
    public void send(String to, String subject, String body, String _replyTo) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
            helper.setSubject(subject);
            helper.setText(body, false);
            helper.setTo(to);
            helper.setFrom("mail@notify.me");
            if (_replyTo != null) {
                helper.setReplyTo(_replyTo);
            }
            javaMailSender.send(message);
        } catch (MessagingException e) {
            LOGGER.error("Failed to send email", e);
            throw new IllegalStateException("Failed to send email");
        }
    }

    @Async
    public void send(String to, String subject, String body) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
            helper.setSubject(subject);
            helper.setText(body, false);
            helper.setTo(to);
            helper.setFrom("confirm@notify.me");
            javaMailSender.send(message);
        } catch (MessagingException e) {
            LOGGER.error("Failed to send email", e);
            throw new IllegalStateException("Failed to send email");
        }
    }


}
