package com.eversmile.eve.app.common;

import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
public class AppHelper {

    public LocalDateTime getDateTime(@NonNull Instant instant){
        return instant.atZone(ZoneId.of(AppConstants.APP_TIME_ZONE)).toLocalDateTime();
    }
    public String generateUuid(){
        return UUID.randomUUID().toString();
    }
    public Instant getInstant(String date, String dateFormat){
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormat);
        LocalDate localDate = LocalDate.parse(date, dateFormatter);
        return localDate.atStartOfDay(ZoneId.of(AppConstants.APP_TIME_ZONE)).toInstant();
    }
    public String getUsername(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}
