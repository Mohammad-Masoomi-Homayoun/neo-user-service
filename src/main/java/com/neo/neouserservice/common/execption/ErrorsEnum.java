package com.neo.neouserservice.common.execption;

public enum ErrorsEnum {

    badRequest(40001, 400, "Bad Request!"),

    duplicateUsername(42201, 422, "Duplicate Entity!"),

    resourceNotFound(41301, 413, "Resource Not Found!"),

    notVerified(41302, 413, "Resource Not Found!"),

    userNotActivated(40302, 403, "Account Not Activated!"),

    userNotFound(41303, 413, "User Not Found!"),

    loginFailed(40103, 401, "User Not Found!"),

    samePasswordOnReset(40303, 403, "Your password is same as previous!"),

    accessDenied(40304, 403, "You have not permission to action this page"),

    insertAllFields(40601, 406, "Your password is same as previous"),

    paymentMethodNotSupported(40602, 406, "Payment hasn't been accepted!"),

    serverError(50001, 500, "Sorry! There is a problem in server side!"),

    smsError(50002, 500, "There is problem with sending verification sms"),

    notSavedFile(50003, 500, "Sorry! There is a problem in saving file!"),

    operationFailedException(50004, 500, "Operation is not completed!"),

    permissionError(40303, 403, "You Have Not Enough Permission!"),

    conversionError(41501, 415, "Unsupported media type!"),

    invalidInputException(40601, 406, "Input Invalid!");


    private String message;
    private Integer code;
    private Integer status;

    ErrorsEnum(Integer code, Integer status, String msg) {
        this.code = code;
        this.status = status;
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }

    public Integer getStatus() {
        return status;
    }
}
