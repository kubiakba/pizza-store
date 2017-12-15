package pl.bk.pizza.store.domain.service;

import java.time.LocalDateTime;
import java.time.ZoneId;

public final class NowProvider {

    private NowProvider() {
    }

    private static final String DEFAULT_ZONE_NAME = "Europe/Warsaw";

    public static LocalDateTime now() {
        return LocalDateTime.now(ZoneId.of(DEFAULT_ZONE_NAME));
    }
}
