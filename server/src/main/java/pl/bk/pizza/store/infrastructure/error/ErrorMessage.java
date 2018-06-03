package pl.bk.pizza.store.infrastructure.error;

import lombok.NoArgsConstructor;
import pl.bk.pizza.store.domain.exception.ErrorCode;

@NoArgsConstructor
public class ErrorMessage
{
    private ErrorCode errorCode;
    
    public ErrorMessage(ErrorCode errorCode)
    {
        this.errorCode = errorCode;
    }
    
    public ErrorCode getErrorCode()
    {
        return errorCode;
    }
}
