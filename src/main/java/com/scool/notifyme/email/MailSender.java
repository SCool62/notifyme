package com.scool.notifyme.email;

public interface MailSender {
    void send(String to, String subject, String body, String _replyTo);
}
