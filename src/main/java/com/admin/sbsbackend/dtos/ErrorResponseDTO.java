package com.admin.sbsbackend.dtos;

public class ErrorResponseDTO {
    private final String type = "ERROR";
    private String message;
    private int messageCode;

    public String getType() {
        return type;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(int messageCode) {
        this.messageCode = messageCode;
    }
}
