package com.wu.util;

import java.util.Calendar;

public class CronExpressionUtil {

    public static String getCronExpression(){
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.MINUTE,1);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int date = calendar.get(Calendar.DATE);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int mm = calendar.get(Calendar.MINUTE);
        int ss = calendar.get(Calendar.SECOND);
        StringBuffer sbf=new StringBuffer();
        //0 0 20 4 3 ? 2019
        sbf.append(ss+" ");
        sbf.append(mm+" ");
        sbf.append(hour+" ");
        sbf.append(date+" ");
        sbf.append(month+" ");
        sbf.append("? ");
        sbf.append(year);
        return sbf.toString();
    }
}
