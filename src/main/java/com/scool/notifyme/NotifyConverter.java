package com.scool.notifyme;


// Converts encoded form into NotifyRequest object
public class NotifyConverter {

    public static NotifyRequest convert(String encodedForm) {
        String[] params = encodedForm.split("&");
        String body = null;
        String subject = null;
        String replyTo = null;
        String redirectUrl = null;

        for (String param : params) {
            String[] keyValue = param.split("=");
            String key = keyValue[0];
            String value = keyValue[1];
            switch (key) {
                case "body" -> body = value;
                case "_subject" -> subject = value;
                case "_replyTo" -> replyTo = value;
                case "_redirect" -> redirectUrl = value;
            }
        }

        if (body == null || subject == null) {
            throw new IllegalArgumentException("Body or subject is missing");
        }

        body = body.replaceAll("\\+", " ");

        return new NotifyRequest(body, subject, replyTo, redirectUrl);
    }



}
