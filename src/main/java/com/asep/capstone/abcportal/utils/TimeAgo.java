package com.asep.capstone.abcportal.utils;

import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimeAgo {




    public static String convertTime(Date past) throws ParseException {


//        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy'T'HH:mm:ss");
        Date now = new Date();

        String returnFormat = null;
        long timeCount = TimeUnit.SECONDS.toMillis(now.getTime() - past.getTime());
        long minuteCount = TimeUnit.SECONDS.toMinutes(now.getTime() - past.getTime());
        long hoursCount = TimeUnit.SECONDS.toHours(now.getTime() - past.getTime());
        long daysCount = TimeUnit.SECONDS.toDays(now.getTime() - past.getTime());


        System.out.println(TimeUnit.MILLISECONDS.toMillis(now.getTime() - past.getTime()) + " milliseconds ago");
        System.out.println(TimeUnit.MILLISECONDS.toMinutes(now.getTime() - past.getTime()) + " minutes ago");
        System.out.println(TimeUnit.MILLISECONDS.toHours(now.getTime() - past.getTime()) + " hours ago");
        System.out.println(TimeUnit.MILLISECONDS.toDays(now.getTime() - past.getTime()) + " days ago");

        if(timeCount < 60){
            returnFormat =  " just posted";
        }else if(timeCount > 60 && minuteCount < 60){
            returnFormat =  minuteCount + " minutes ago";
        }else if(minuteCount > 60 && hoursCount < 24){
            returnFormat =  minuteCount + " hours ago";
        }else if(hoursCount > 24){
            returnFormat =  minuteCount + " days ago";
        }

        return returnFormat;

//        System.out.println(TimeUnit.MILLISECONDS.toMillis(now.getTime() - past.getTime()) + " milliseconds ago");
//        System.out.println(TimeUnit.MILLISECONDS.toMinutes(now.getTime() - past.getTime()) + " minutes ago");
//        System.out.println(TimeUnit.MILLISECONDS.toHours(now.getTime() - past.getTime()) + " hours ago");
//        System.out.println(TimeUnit.MILLISECONDS.toDays(now.getTime() - past.getTime()) + " days ago");


    }


}
