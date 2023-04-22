package com.neo.neouserservice.common.execption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class VerificationException extends BusinessException {
    public VerificationException() {
        super(ErrorsEnum.invalidInputException);
    }

    public VerificationException(String message) {
        super(ErrorsEnum.invalidInputException, message);
    }

}
