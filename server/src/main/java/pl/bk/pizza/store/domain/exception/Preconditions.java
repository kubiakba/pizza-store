package pl.bk.pizza.store.domain.exception;

import java.util.function.Supplier;

public class Preconditions
{
    public static void check(Boolean condition, Supplier<AppException> exceptionToThrow)
    {
        if(condition)
        {
            throw exceptionToThrow.get();
        }
    }
}
