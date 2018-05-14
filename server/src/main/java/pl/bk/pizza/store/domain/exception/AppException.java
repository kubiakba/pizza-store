package pl.bk.pizza.store.domain.exception;

import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException
{
    private final HttpStatus status;
    private final ErrorCode errorCode;
    
    public AppException(String message, HttpStatus status, ErrorCode errorCode)
    {
        super(message);
        this.status = status;
        this.errorCode = errorCode;
    }
    
    public HttpStatus getHttpStatus()
    {
        return status;
    }
    
    public ErrorCode errorCode()
    {
        return errorCode;
    }
}
