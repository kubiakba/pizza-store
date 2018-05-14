package pl.bk.pizza.store.domain.exception;

import org.springframework.http.HttpStatus;

public class UnsupportedTypeException extends AppException
{
    public UnsupportedTypeException(String message, ErrorCode errorCode)
    {
        super(message, HttpStatus.UNPROCESSABLE_ENTITY, errorCode);
    }
}
