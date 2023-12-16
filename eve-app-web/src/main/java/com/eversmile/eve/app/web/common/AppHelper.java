package com.eversmile.eve.app.web.common;

import org.springframework.stereotype.Component;

import java.time.*;
import java.time.format.DateTimeFormatter;

@Component
public class AppHelper {

    public Instant convertHtmlDateToInstant(String htmlDate, boolean atEndOfDay) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(htmlDate, formatter);
        LocalTime localTime = LocalTime.MIDNIGHT;
        if(atEndOfDay){
            localTime = LocalTime.of(23,59,59);
        }

        return LocalDateTime.of(localDate, localTime).atZone(ZoneId.of("Africa/Johannesburg")).toInstant();
    }

}
