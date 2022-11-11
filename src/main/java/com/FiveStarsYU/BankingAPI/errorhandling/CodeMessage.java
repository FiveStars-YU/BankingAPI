package com.FiveStarsYU.BankingAPI.errorhandling;

import org.springframework.http.HttpStatus;


public class CodeMessage {

    private int code;
    private String message;

    public CodeMessage(String message){
        this.code = HttpStatus.NOT_FOUND.value();
        this.message = message;
    }

    public CodeMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
