package com.neo.neouserservice.common.execption;

import java.util.List;

public class BusinessException extends RuntimeException {
    protected ErrorsEnum errorsEnum;
    private List<Object> params;

    public BusinessException(ErrorsEnum errorsEnum) {
        super(errorsEnum.name());
        this.errorsEnum = errorsEnum;

    }

    public BusinessException(ErrorsEnum errorsEnum, String message) {
        super(message);
        this.errorsEnum = errorsEnum;

    }

    public BusinessException(ErrorsEnum errorsEnum, String message, List<Object> params) {
        super(message);
        this.errorsEnum = errorsEnum;
        this.params = params;
    }

    public ErrorsEnum getErrorsEnum() {
        return errorsEnum;
    }

    public List<Object> getParams() {
        return params;
    }
}
