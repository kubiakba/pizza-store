package pl.bk.pizza.store.domain.exception;

import lombok.NoArgsConstructor;

import java.util.function.Supplier;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
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
