package com.scool.notifyme;

import lombok.*;

@Getter
@EqualsAndHashCode
@ToString
public class NotifyRequest {

    private final String body;
    private final String _subject;
    private final String _replyTo;
    private final String _redirect;


    public NotifyRequest(String body, String _subject, String _replyTo, String _redirect) {
        this.body = body;
        this._subject = _subject;
        this._replyTo = _replyTo;
        this._redirect = _redirect;
    }
}
