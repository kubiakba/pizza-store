package pl.bk.pizza.store.domain.service;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public final class NowProvider
{
    private static final String DEFAULT_ZONE_NAME = "Europe/Warsaw";
    
    private NowProvider()
    {
    }
    
    public static ZonedDateTime now()
    {
        final ZoneId zoneId = ZoneId.of(DEFAULT_ZONE_NAME);
        return LocalDateTime.now().atZone(zoneId);
    }
}
