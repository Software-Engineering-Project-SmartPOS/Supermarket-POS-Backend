package com.SupermarketPOS.Backend.service.common_services;

import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.time.LocalDateTime;

@Service
public class DateTimeService {
    public String convertTimeStampIntoString(Timestamp timestamp ){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timpeStampInString = sdf.format(timestamp);
        return timpeStampInString;
    }


    public String convertLocalDateTimeIntoString(LocalDateTime timeStamp){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timeStampInString = timeStamp.format(formatter); // format the time stamp(LocalDateTime)
        return timeStampInString;

    }
    public String convertLocalDateIntoString(LocalDate localDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String localDateInString = localDate.format(formatter);
        return localDateInString;

    }


    public LocalDateTime convertStringIntoLocalDateTime(String timeStampInString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(timeStampInString,formatter);
        return localDateTime;
    }

    public LocalDate convertStringIntoLocalDate(String localDateInString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(localDateInString,formatter);
        return localDate;
    }

}
