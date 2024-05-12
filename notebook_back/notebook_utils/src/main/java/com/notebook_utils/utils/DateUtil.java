package com.notebook_utils.utils;

import java.util.Calendar;
import java.util.Date;

//日期类
public final class DateUtil {
    private static long millisecondsOfOneDay = 1000*60*60*24;
    /**
     * 获取今天的日历Calendar对象，将Calendar对象的时、分、秒都设置为零
     * @return Calendar
     */
    private static Calendar getCalendar(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        return calendar;
    }

    /**
     * 获取created 、 now 两个日期间的天数
     * @param created
     * @param now
     * @return
     */
    public static int getDayBetweenTwoDate(Date created,Date now){
        return (int) ((now.getTime() - created.getTime())/millisecondsOfOneDay);
    }

    /**
     * 获取今天 00:00:00（零点：零分：零秒） 的时间
     * @return Calendar
     */
    public static Calendar today(){
        return getCalendar();
    }

    /**
     * 获取当前日期的这个月的第一天 00:00:00（零点：零分：零秒） 的时间
     * @return Calendar
     */
    public static Calendar monthBegin(){
        Calendar c = getCalendar();
        c.set(Calendar.DATE,1);
        return c;
    }

    /**
     * 获取当前日期的这个月的最后一天 00:00:00（零点：零分：零秒） 的时间
     * @return Calendar
     */
    public static Calendar monthEnd(){
        Calendar c = monthBegin();
        c.add(Calendar.MONTH,1);
        c.add(Calendar.DATE,-1);
        return c;
    }

    /**
     * 获取这个月的天数
     * 最后一天 00:00:00（零点：零分：零秒） 的时间   【减去】   第一天 00:00:00（零点：零分：零秒） 的时间  【加上】  1
     * @return int 天数
     */
    public static int thisMonthTotalDay(){
        return (int) ((monthEnd().getTime().getTime() - monthBegin().getTime().getTime()) / millisecondsOfOneDay + 1);
    }

    /**
     * 这个月剩余的天数
     * @return int
     */
    public static int thisMonthLeftDay(){
        return (int) ((monthEnd().getTime().getTime() - today().getTime().getTime()) / millisecondsOfOneDay + 1);
    }
}
