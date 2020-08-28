package com.imyeego.frame.concurrent_modification_exception;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static Date getDateByFormat(String time, String format) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);

        try {
            date =  dateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }
}
