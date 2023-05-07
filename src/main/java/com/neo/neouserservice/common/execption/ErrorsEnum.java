package com.neo.neouserservice.common.execption;

public enum ErrorsEnum {

    BAD_REQUEST(40001, 400, "Bad Request!"),

    DUPLICATE_USERNAME(42201, 422, "Duplicate Entity!"),

    RESOURCE_NOT_FOUND(41301, 413, "Resource Not Found!"),

    NOT_VERIFIED(41302, 413, "Resource Not Found!"),

    USER_NOT_ACTIVATED(40302, 403, "Account Not Activated!"),

    USER_NOT_FOUND(41303, 413, "User Not Found!"),

    LOGIN_FAILED(40103, 401, "User Not Found!"),

    SAME_PASSWORD_ON_RESET(40303, 403, "Your password is same as previous!"),

    ACCESS_DENIED(40304, 403, "You have not permission to action this page"),

    INSERT_ALL_FIELDS(40601, 406, "Your password is same as previous"),

    PAYMENT_METHOD_NOT_SUPPORTED(40602, 406, "Payment hasn't been accepted!"),

    SERVER_ERROR(50001, 500, "Sorry! There is a problem in server side!"),

    SMS_ERROR(50002, 500, "There is problem with sending verification sms"),

    NOT_SAVED_FILE(50003, 500, "Sorry! There is a problem in saving file!"),

    OPERATION_FAILED_EXCEPTION(50004, 500, "Operation is not completed!"),

    PERMISSION_ERROR(40303, 403, "You Have Not Enough Permission!"),

    CONVERSION_ERROR(41501, 415, "Unsupported media type!"),

    INVALID_INPUT_EXCEPTION(40601, 406, "Input Invalid!");


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
