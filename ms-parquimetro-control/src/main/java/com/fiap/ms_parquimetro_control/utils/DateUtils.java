package com.fiap.ms_parquimetro_control.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.Duration.between;
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
        final var builder = new StringBuilder();

        final var days = between(date1, date2).toDays();
        if (days > 0) builder.append(days).append("d ");

        final var hours = between(date1, date2).toHours();
        if (hours > 0) builder.append(hours).append("h ");

        final var minutes = between(date1, date2).toMinutes();
        if (minutes > 0) builder.append(minutes).append("m ");

        final var seconds = between(date1, date2).toSeconds();
        if (seconds > 0) builder.append(seconds).append("s");

        return builder.toString();
    }
}
