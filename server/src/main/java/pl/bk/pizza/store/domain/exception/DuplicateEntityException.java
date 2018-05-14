package pl.bk.pizza.store.domain.exception;

import org.springframework.http.HttpStatus;

public class DuplicateEntityException extends AppException
{
    public DuplicateEntityException(String message, ErrorCode errorCode)
    {
        super(message, HttpStatus.UNPROCESSABLE_ENTITY, errorCode);
    }
}
