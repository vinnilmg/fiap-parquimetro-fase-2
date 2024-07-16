package com.fiap.ms_parquimetro_control.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import static java.util.Objects.isNull;

@Slf4j
public class DateUtils {
    private static final String DEFAULT_PATTERN = "dd/MM/yyyy HH:mm:ss";

    private DateUtils() {
    }

    public static String toDefaultPattern(final LocalDateTime time) {
        if (isNull(time)) return null;
        return DateTimeFormatter.ofPattern(DEFAULT_PATTERN)
                .format(time);
    }

    public static String calculaPeriodoEntreDatas(final LocalDateTime date1, final LocalDateTime date2) {
        var millis = Duration.between(date1, date2).toMillis();

        final var days = TimeUnit.MILLISECONDS.toDays(millis);
        millis -= TimeUnit.DAYS.toMillis(days);

        final var hours = TimeUnit.MILLISECONDS.toHours(millis);
        millis -= TimeUnit.HOURS.toMillis(hours);

        final var minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        millis -= TimeUnit.MINUTES.toMillis(minutes);

        final var seconds = TimeUnit.MILLISECONDS.toSeconds(millis);

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
