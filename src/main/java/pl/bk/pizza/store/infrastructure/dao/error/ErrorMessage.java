package pl.bk.pizza.store.infrastructure.dao.error;

import pl.bk.pizza.store.domain.exception.ErrorCode;

public class ErrorMessage {

    private final ErrorCode errorCode;

    public ErrorMessage(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
