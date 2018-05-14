package pl.bk.pizza.store.domain.service;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public final class NowProvider
{
    private static final String DEFAULT_ZONE_NAME = "Europe/Warsaw";
    
    private NowProvider()
    {
    }
    
    public static LocalDateTime now()
    {
        return LocalDateTime.now(ZoneId.of(DEFAULT_ZONE_NAME));
    }
}
