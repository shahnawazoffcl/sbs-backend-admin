package com.admin.sbsbackend.exceptions;

public class MessageNotSentException extends RuntimeException {
    public MessageNotSentException(String s) {
        super(s);
    }
}
