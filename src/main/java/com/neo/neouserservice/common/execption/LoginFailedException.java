package com.neo.neouserservice.common.execption;

public class LoginFailedException extends BusinessException {
    public LoginFailedException() {
        super(ErrorsEnum.LOGIN_FAILED);
    }

    public LoginFailedException(String message) {
        super(ErrorsEnum.LOGIN_FAILED, message);
    }
}
