package com.example.fitnessapp;

import com.google.firebase.Timestamp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateFormat {
    public static String convertTimestampToString(Timestamp timestamp) {
        if(timestamp == null){
            return null;
        }
        Date date = timestamp.toDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());

        return sdf.format(date);
    }

    public static Timestamp convertStringToTimestamp(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        try {
            Date date = sdf.parse(dateString);
            return new Timestamp(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Timestamp getStartOfDay(Timestamp timestamp) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(timestamp.toDate());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return new Timestamp(cal.getTime());
    }

    public static Timestamp getEndOfDay(Timestamp timestamp) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(timestamp.toDate());
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return new Timestamp(cal.getTime());
    }

}
