package pl.bk.pizza.store.domain.exception;

import org.springframework.http.HttpStatus;

public class InvalidEntityException extends AppException
{
    public InvalidEntityException(String message, ErrorCode errorCode)
    {
        super(message, HttpStatus.UNPROCESSABLE_ENTITY, errorCode);
    }
}
