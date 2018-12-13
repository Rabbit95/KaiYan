package com.rabbit.kaiyan.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by hzj on 2018/1/12.
 * 将Long类型的Date装换为日期
 */

public class DateUtil {

    public static String timeFormat(long date) {
        Date time = new Date(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        String year = calendar.get(Calendar.YEAR) + "/";
        String month;
        String day;
        int monthnum = calendar.get(Calendar.MONTH) + 1;
        if (monthnum < 10) {
            month = "0" + monthnum + "/";
        } else {
            month = monthnum + "/";
        }
        if (calendar.get(Calendar.DAY_OF_MONTH) < 10) {
            day = "0" + calendar.get(Calendar.DAY_OF_MONTH);
        } else {
            day = calendar.get(Calendar.DAY_OF_MONTH) + "";
        }
        return year + month + day;
    }

    public static String dateFormat(long date) {
        String week, month, day;
        week = DateLib(new SimpleDateFormat("E", Locale.ENGLISH).format(date));
        month = DateLib(new SimpleDateFormat("MMM", Locale.ENGLISH).format(date));
        day = new SimpleDateFormat("d", Locale.ENGLISH).format(date);
        return week + "," + month + " " + day;
    }

    public static String date2week(long date) {
        String nowDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String dataDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
        if (dataDate.equals(nowDate)) {
            return "今日开眼精选";
        } else if (getWeekNum(nowDate) - getWeekNum(dataDate) == 0) {
            return new SimpleDateFormat("EEEE",Locale.CHINA).format(date);
        }else {
            return new SimpleDateFormat("MM/dd yyyy").format(date);
        }
    }

    public static int getWeekNum(String dates){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(dates);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);

        return calendar.get(Calendar.WEEK_OF_YEAR);
    }
//    public static int daysBetween( smdate, String bdate) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Calendar cal = Calendar.getInstance();
//        long time1 = 0;
//        long time2 = 0;
//
//        try {
//            cal.setTime(sdf.parse(smdate));
//            time1 = cal.getTimeInMillis();
//            cal.setTime(sdf.parse(bdate));
//            time2 = cal.getTimeInMillis();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        long between_days = (time2 - time1) / (1000 * 3600 * 24);
//        return Integer.parseInt(String.valueOf(between_days));
//    }
    private static String DateLib(String date) {
        String dateABLib[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        String dateLib[] = {"JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};
        for (int i = 0; i < dateABLib.length; i++) {
            if (date.equals(dateABLib[i])) {
                return dateLib[i];
            }
        }
        return null;
    }




//    public static void test(){
//        //12-7-星期五,11-10-星期六,10-14-星期日,9-10-星期一,8-7-星期二,7-11-星期三,6-7-星期四,5,4,3,2,1
//        String date[] = {"1544112000000","1541779200000","1539446400000","1536508800000","1533571200000","1531238400000","1528300800000","1525622400000","1523030400000","1520352000000","1517932800000","1515254400000"};
//        for (String s : date) {
//            Long t = Long.parseLong(s);
//            Log.d("date:",new SimpleDateFormat("E,MMM",Locale.ENGLISH).format(t).toString());
//        }
//    }
}
