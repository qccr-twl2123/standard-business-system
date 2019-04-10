package com.arcsoft.facego.util.date;

import org.apache.commons.lang.StringUtils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 日期工具类
 * @author xierongli
 * @version : crmbugfix, v 0.1 2017/3/30 16:49 Exp $$
 */
public class DateKit {

    public static final String PATTERN_DATE = "yyyy-MM-dd";
    private static final ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>();

    private static final Object                        object      = new Object();

    private static final long                          ONE_MINUTE  = 60;
    private static final long                          ONE_HOUR    = 3600;
    private static final long                          ONE_DAY     = 86400;
    private static final long                          ONE_MONTH   = 2592000;
    private static final long                          ONE_YEAR    = 31104000;

    private DateKit() {
    }

    /**
     * 获取SimpleDateFormat
     *
     * @param pattern 日期格式
     * @return SimpleDateFormat对象
     * @throws RuntimeException 异常：非法日期格式
     */
    private static SimpleDateFormat getDateFormat(String pattern) throws RuntimeException {
        SimpleDateFormat dateFormat = threadLocal.get();
        if (dateFormat == null) {
            synchronized (object) {
                dateFormat = new SimpleDateFormat(pattern);
                dateFormat.setLenient(false);
                threadLocal.set(dateFormat);
            }
        }
        dateFormat.applyPattern(pattern);
        return dateFormat;
    }

    /**
     * 获取日期中的某数值。如获取月份
     *
     * @param date 日期
     * @param dateType 日期格式
     * @return 数值
     */
    private static int getInteger(Date date, int dateType) {
        int num = 0;
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
            num = calendar.get(dateType);
        }
        return num;
    }
    // /
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"SUNDAY","MONDAY","TUESDAY","WEDNESDAY","THURSDAY","FRIDAY","SATURDAY"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }
    /**
     * 增加日期中某类型的某数值。如增加日期
     *
     * @param date 日期字符串
     * @param dateType 类型
     * @param amount 数值
     * @return 计算后日期字符串
     */
    private static String addInteger(String date, int dateType, int amount) {
        String dateString = null;
        DateStyle dateStyle = getDateStyle(date);
        if (dateStyle != null) {
            Date myDate = StringToDate(date, dateStyle);
            myDate = addInteger(myDate, dateType, amount);
            dateString = DateToString(myDate, dateStyle);
        }
        return dateString;
    }

    /**
     * 增加日期中某类型的某数值。如增加日期
     *
     * @param date 日期
     * @param dateType 类型
     * @param amount 数值
     * @return 计算后日期
     */
    private static Date addInteger(Date date, int dateType, int amount) {
        Date myDate = null;
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(dateType, amount);
            myDate = calendar.getTime();
        }
        return myDate;
    }

    /**
     * 获取精确的日期
     *
     * @param timestamps 时间long集合
     * @return 日期
     */
    private static Date getAccurateDate(List<Long> timestamps) {
        Date date = null;
        long timestamp = 0;
        Map<Long, long[]> map = new HashMap<Long, long[]>();
        List<Long> absoluteValues = new ArrayList<Long>();

        if (timestamps != null && timestamps.size() > 0) {
            if (timestamps.size() > 1) {
                for (int i = 0; i < timestamps.size(); i++) {
                    for (int j = i + 1; j < timestamps.size(); j++) {
                        long absoluteValue = Math.abs(timestamps.get(i) - timestamps.get(j));
                        absoluteValues.add(absoluteValue);
                        long[] timestampTmp = { timestamps.get(i), timestamps.get(j) };
                        map.put(absoluteValue, timestampTmp);
                    }
                }

                // 有可能有相等的情况。如2012-11和2012-11-01。时间戳是相等的。此时minAbsoluteValue为0
                // 因此不能将minAbsoluteValue取默认值0
                long minAbsoluteValue = -1;
                if (!absoluteValues.isEmpty()) {
                    minAbsoluteValue = absoluteValues.get(0);
                    for (int i = 1; i < absoluteValues.size(); i++) {
                        if (minAbsoluteValue > absoluteValues.get(i)) {
                            minAbsoluteValue = absoluteValues.get(i);
                        }
                    }
                }

                if (minAbsoluteValue != -1) {
                    long[] timestampsLastTmp = map.get(minAbsoluteValue);

                    long dateOne = timestampsLastTmp[0];
                    long dateTwo = timestampsLastTmp[1];
                    if (absoluteValues.size() > 1) {
                        timestamp = Math.abs(dateOne) > Math.abs(dateTwo) ? dateOne : dateTwo;
                    }
                }
            } else {
                timestamp = timestamps.get(0);
            }
        }

        if (timestamp != 0) {
            date = new Date(timestamp);
        }
        return date;
    }

    /**
     * 判断字符串是否为日期字符串
     *
     * @param date 日期字符串
     * @return true or false
     */
    public static boolean isDate(String date) {
        boolean isDate = false;
        if (date != null) {
            if (getDateStyle(date) != null) {
                isDate = true;
            }
        }
        return isDate;
    }

    /**
     * 获取日期字符串的日期风格。失敗返回null。
     *
     * @param date 日期字符串
     * @return 日期风格
     */
    public static DateStyle getDateStyle(String date) {
        DateStyle dateStyle = null;
        Map<Long, DateStyle> map = new HashMap<Long, DateStyle>();
        List<Long> timestamps = new ArrayList<Long>();
        for (DateStyle style : DateStyle.values()) {
            if (style.isShowOnly()) {
                continue;
            }
            Date dateTmp = null;
            if (date != null) {
                try {
                    ParsePosition pos = new ParsePosition(0);
                    dateTmp = getDateFormat(style.getValue()).parse(date, pos);
                    if (pos.getIndex() != date.length()) {
                        dateTmp = null;
                    }
                } catch (Exception e) {
                }
            }
            if (dateTmp != null) {
                timestamps.add(dateTmp.getTime());
                map.put(dateTmp.getTime(), style);
            }
        }
        Date accurateDate = getAccurateDate(timestamps);
        if (accurateDate != null) {
            dateStyle = map.get(accurateDate.getTime());
        }
        return dateStyle;
    }

    /**
     * 将日期字符串转化为日期。失败返回null。
     *
     * @param date 日期字符串
     * @return 日期
     */
    public static Date StringToDate(String date) {
        DateStyle dateStyle = getDateStyle(date);
        return StringToDate(date, dateStyle);
    }

    /**
     * 将日期字符串转化为日期。失败返回null。
     *
     * @param date 日期字符串
     * @param pattern 日期格式
     * @return 日期
     */
    public static Date StringToDate(String date, String pattern) {
        Date myDate = null;
        if (date != null) {
            try {
                myDate = getDateFormat(pattern).parse(date);
            } catch (Exception e) {
                //logger.info("",e);
            }
        }
        return myDate;
    }

    /**
     * 将日期字符串转化为日期。失败返回null。
     *
     * @param date 日期字符串
     * @param dateStyle 日期风格
     * @return 日期
     */
    public static Date StringToDate(String date, DateStyle dateStyle) {
        Date myDate = null;
        if (dateStyle != null) {
            myDate = StringToDate(date, dateStyle.getValue());
        }
        return myDate;
    }

    /**
     * 获取日期对象
     *
     * @param millisTime 毫秒格式时间
     * @return Date 日期对象
     */
    public static Date getDate(long millisTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millisTime);
        return calendar.getTime();
    }

    /**
     * 将日期转化为日期字符串。失败返回null。
     *
     * @param date 日期
     * @param pattern 日期格式
     * @return 日期字符串
     */
    public static String DateToString(Date date, String pattern) {
        String dateString = null;
        if (date != null) {
            try {
                dateString = getDateFormat(pattern).format(date);
            } catch (Exception e) {
                //logger.info("",e);
            }
        }
        return dateString;
    }

    /**
     * 将日期转化为日期字符串。失败返回null。
     *
     * @param date 日期
     * @param dateStyle 日期风格
     * @return 日期字符串
     */
    public static String DateToString(Date date, DateStyle dateStyle) {
        String dateString = null;
        if (dateStyle != null) {
            dateString = DateToString(date, dateStyle.getValue());
        }
        return dateString;
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date 旧日期字符串
     * @param newPattern 新日期格式
     * @return 新日期字符串
     */
    public static String StringToString(String date, String newPattern) {
        DateStyle oldDateStyle = getDateStyle(date);
        return StringToString(date, oldDateStyle, newPattern);
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date 旧日期字符串
     * @param newDateStyle 新日期风格
     * @return 新日期字符串
     */
    public static String StringToString(String date, DateStyle newDateStyle) {
        DateStyle oldDateStyle = getDateStyle(date);
        return StringToString(date, oldDateStyle, newDateStyle);
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date 旧日期字符串
     * @param olddPattern 旧日期格式
     * @param newPattern 新日期格式
     * @return 新日期字符串
     */
    public static String StringToString(String date, String olddPattern, String newPattern) {
        return DateToString(StringToDate(date, olddPattern), newPattern);
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date 旧日期字符串
     * @param olddDteStyle 旧日期风格
     * @param newParttern 新日期格式
     * @return 新日期字符串
     */
    public static String StringToString(String date, DateStyle olddDteStyle, String newParttern) {
        String dateString = null;
        if (olddDteStyle != null) {
            dateString = StringToString(date, olddDteStyle.getValue(), newParttern);
        }
        return dateString;
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date 旧日期字符串
     * @param olddPattern 旧日期格式
     * @param newDateStyle 新日期风格
     * @return 新日期字符串
     */
    public static String StringToString(String date, String olddPattern, DateStyle newDateStyle) {
        String dateString = null;
        if (newDateStyle != null) {
            dateString = StringToString(date, olddPattern, newDateStyle.getValue());
        }
        return dateString;
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date 旧日期字符串
     * @param olddDteStyle 旧日期风格
     * @param newDateStyle 新日期风格
     * @return 新日期字符串
     */
    public static String StringToString(String date, DateStyle olddDteStyle, DateStyle newDateStyle) {
        String dateString = null;
        if (olddDteStyle != null && newDateStyle != null) {
            dateString = StringToString(date, olddDteStyle.getValue(), newDateStyle.getValue());
        }
        return dateString;
    }

    /**
     * 增加日期的年份。失败返回null。
     *
     * @param date 日期
     * @param yearAmount 增加数量。可为负数
     * @return 增加年份后的日期字符串
     */
    public static String addYear(String date, int yearAmount) {
        return addInteger(date, Calendar.YEAR, yearAmount);
    }

    /**
     * 增加日期的年份。失败返回null。
     *
     * @param date 日期
     * @param yearAmount 增加数量。可为负数
     * @return 增加年份后的日期
     */
    public static Date addYear(Date date, int yearAmount) {
        return addInteger(date, Calendar.YEAR, yearAmount);
    }

    /**
     * 增加日期的月份。失败返回null。
     *
     * @param date 日期
     * @param monthAmount 增加数量。可为负数
     * @return 增加月份后的日期字符串
     */
    public static String addMonth(String date, int monthAmount) {
        return addInteger(date, Calendar.MONTH, monthAmount);
    }

    /**
     * 增加日期的月份。失败返回null。
     *
     * @param date 日期
     * @param monthAmount 增加数量。可为负数
     * @return 增加月份后的日期
     */
    public static Date addMonth(Date date, int monthAmount) {
        return addInteger(date, Calendar.MONTH, monthAmount);
    }

    /**
     * 增加日期的天数。失败返回null。
     *
     * @param date 日期字符串
     * @param dayAmount 增加数量。可为负数
     * @return 增加天数后的日期字符串
     */
    public static String addDay(String date, int dayAmount) {
        return addInteger(date, Calendar.DATE, dayAmount);
    }

    /**
     * 增加日期的天数。失败返回null。
     *
     * @param date 日期
     * @param dayAmount 增加数量。可为负数
     * @return 增加天数后的日期
     */
    public static Date addDay(Date date, int dayAmount) {
        return addInteger(date, Calendar.DATE, dayAmount);
    }

    /**
     * 增加日期的小时。失败返回null。
     *
     * @param date 日期字符串
     * @param hourAmount 增加数量。可为负数
     * @return 增加小时后的日期字符串
     */
    public static String addHour(String date, int hourAmount) {
        return addInteger(date, Calendar.HOUR_OF_DAY, hourAmount);
    }

    /**
     * 增加日期的小时。失败返回null。
     *
     * @param date 日期
     * @param hourAmount 增加数量。可为负数
     * @return 增加小时后的日期
     */
    public static Date addHour(Date date, int hourAmount) {
        return addInteger(date, Calendar.HOUR_OF_DAY, hourAmount);
    }

    /**
     * 增加日期的分钟。失败返回null。
     *
     * @param date 日期字符串
     * @param minuteAmount 增加数量。可为负数
     * @return 增加分钟后的日期字符串
     */
    public static String addMinute(String date, int minuteAmount) {
        return addInteger(date, Calendar.MINUTE, minuteAmount);
    }

    /**
     * 增加日期的分钟。失败返回null。
     *
     * @param date 日期
     * @param minuteAmount 增加数量。可为负数
     * @return 增加分钟后的日期
     */
    public static Date addMinute(Date date, int minuteAmount) {
        return addInteger(date, Calendar.MINUTE, minuteAmount);
    }

    /**
     * 增加日期的秒钟。失败返回null。
     *
     * @param date 日期字符串
     * @param secondAmount 增加数量。可为负数
     * @return 增加秒钟后的日期字符串
     */
    public static String addSecond(String date, int secondAmount) {
        return addInteger(date, Calendar.SECOND, secondAmount);
    }

    /**
     * 增加日期的秒钟。失败返回null。
     *
     * @param date 日期
     * @param secondAmount 增加数量。可为负数
     * @return 增加秒钟后的日期
     */
    public static Date addSecond(Date date, int secondAmount) {
        return addInteger(date, Calendar.SECOND, secondAmount);
    }

    /**
     * 获取日期的年份。失败返回0。
     *
     * @param date 日期字符串
     * @return 年份
     */
    public static int getYear(String date) {
        return getYear(StringToDate(date));
    }

    /**
     * 获取日期的年份。失败返回0。
     *
     * @param date 日期
     * @return 年份
     */
    public static int getYear(Date date) {
        return getInteger(date, Calendar.YEAR);
    }

    /**
     * 获取日期的月份。失败返回0。
     *
     * @param date 日期字符串
     * @return 月份
     */
    public static int getMonth(String date) {
        return getMonth(StringToDate(date));
    }

    /**
     * 获取日期的月份。失败返回0。
     *
     * @param date 日期
     * @return 月份
     */
    public static int getMonth(Date date) {
        return getInteger(date, Calendar.MONTH) + 1;
    }

    /**
     * 获取日期的天数。失败返回0。
     *
     * @param date 日期字符串
     * @return 天
     */
    public static int getDay(String date) {
        return getDay(StringToDate(date));
    }

    /**
     * 获取日期的天数。失败返回0。
     *
     * @param date 日期
     * @return 天
     */
    public static int getDay(Date date) {
        return getInteger(date, Calendar.DATE);
    }

    /**
     * 获取日期的小时。失败返回0。
     *
     * @param date 日期字符串
     * @return 小时
     */
    public static int getHour(String date) {
        return getHour(StringToDate(date));
    }

    /**
     * 获取日期的小时。失败返回0。
     *
     * @param date 日期
     * @return 小时
     */
    public static int getHour(Date date) {
        return getInteger(date, Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取日期的分钟。失败返回0。
     *
     * @param date 日期字符串
     * @return 分钟
     */
    public static int getMinute(String date) {
        return getMinute(StringToDate(date));
    }

    /**
     * 获取日期的分钟。失败返回0。
     *
     * @param date 日期
     * @return 分钟
     */
    public static int getMinute(Date date) {
        return getInteger(date, Calendar.MINUTE);
    }

    /**
     * 获取日期的秒钟。失败返回0。
     *
     * @param date 日期字符串
     * @return 秒钟
     */
    public static int getSecond(String date) {
        return getSecond(StringToDate(date));
    }

    /**
     * 获取日期的秒钟。失败返回0。
     *
     * @param date 日期
     * @return 秒钟
     */
    public static int getSecond(Date date) {
        return getInteger(date, Calendar.SECOND);
    }

    /**
     * 获取日期 。默认yyyy-MM-dd格式。失败返回null。
     *
     * @param date 日期字符串
     * @return 日期
     */
    public static String getDate(String date) {
        return StringToString(date, DateStyle.YYYY_MM_DD);
    }

    /**
     * 获取日期。默认yyyy-MM-dd格式。失败返回null。
     *
     * @param date 日期
     * @return 日期
     */
    public static String getDate(Date date) {
        return DateToString(date, DateStyle.YYYY_MM_DD);
    }

    /**
     * 获取日期。默认yyyy-MM-dd HH:mm:ss Z 格式。失败返回null。
     *
     * @param date 日期
     * @return 日期
     */
    public static String getDateZone(Date date) {
        return DateToString(date, DateStyle.YYYY_MM_DD_HH_MM_SS_Z);
    }

    /**
     * 获取日期。默认yyyy-MM-dd HH:mm:ss Z 格式。失败返回null。
     *
     * @param date 日期
     * @return 日期
     */
    public static String getTimeZone(String date) {
        return StringToString(date, DateStyle.YYYY_MM_DD_HH_MM_SS_Z);
    }

    /**
     * 获取日期的时间。默认HH:mm:ss格式。失败返回null。
     *
     * @param date 日期字符串
     * @return 时间
     */
    public static String getTime(String date) {
        return StringToString(date, DateStyle.HH_MM_SS);
    }

    /**
     * 获取日期的时间。默认HH:mm:ss格式。失败返回null。
     *
     * @param date 日期
     * @return 时间
     */
    public static String getTime(Date date) {
        return DateToString(date, DateStyle.HH_MM_SS);
    }

    /**
     * 获取日期的星期。失败返回null。
     *
     * @param date 日期字符串
     * @return 星期
     */
    public static Week getWeek(String date) {
        Week week = null;
        DateStyle dateStyle = getDateStyle(date);
        if (dateStyle != null) {
            Date myDate = StringToDate(date, dateStyle);
            week = getWeek(myDate);
        }
        return week;
    }

    /**
     * 获取日期的星期。失败返回null。
     *
     * @param date 日期
     * @return 星期
     */
    public static Week getWeek(Date date) {
        Week week = null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int weekNumber = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        switch (weekNumber) {
            case 0:
                week = Week.SUNDAY;
                break;
            case 1:
                week = Week.MONDAY;
                break;
            case 2:
                week = Week.TUESDAY;
                break;
            case 3:
                week = Week.WEDNESDAY;
                break;
            case 4:
                week = Week.THURSDAY;
                break;
            case 5:
                week = Week.FRIDAY;
                break;
            case 6:
                week = Week.SATURDAY;
                break;
        }
        return week;
    }

    /**
     * 获取两个日期相差的天数
     *
     * @param date 日期字符串
     * @param otherDate 另一个日期字符串
     * @return 相差天数。如果失败则返回-1
     */
    public static int getIntervalDays(String date, String otherDate) {
        return getIntervalDays(StringToDate(date), StringToDate(otherDate));
    }

    /**
     * @param date 日期
     * @param otherDate 另一个日期
     * @return 相差天数。如果失败则返回-1
     */
    public static int getIntervalDays(Date date, Date otherDate) {
        int num = -1;
        Date dateTmp = DateKit.StringToDate(DateKit.getDate(date), DateStyle.YYYY_MM_DD);
        Date otherDateTmp = DateKit.StringToDate(DateKit.getDate(otherDate), DateStyle.YYYY_MM_DD);
        if (dateTmp != null && otherDateTmp != null) {
            long time = Math.abs(dateTmp.getTime() - otherDateTmp.getTime());
            num = (int) (time / (24 * 60 * 60 * 1000));
        }
        return num;
    }

    public static int getIntervalHours(Date newTime, Date oldTime) {
        if (oldTime == null || newTime == null) {
            return 0;
        }
        int hours = 0;

        long time = newTime.getTime() - oldTime.getTime();
        if (time >= 0) {
            // 不足一小时按一小时算，计算尺度到毫秒
            hours = (int) ((time + 59 * 60 * 1000 + 59 * 1000 + 999) / (60 * 60 * 1000));
            return hours;
        } else {
            return -1;
        }
    }

    /**
     * 相差几分钟
     *
     * @param newTime
     * @param oldTime
     *
     */
    public static int getIntervalMinute(Date newTime, Date oldTime) {
        if (oldTime == null || newTime == null) {
            return 0;
        }

        long time = newTime.getTime() - oldTime.getTime();
        if (time >= 0) {
            // 不足一分钟按一分钟算，计算尺度到毫秒
            return (int) (time + 59 * 1000 + 999) / (60 * 1000);
        } else {
            return -1;
        }
    }

    public static int getIntervalSecond(Date newTime, Date oldTime) {
        if (oldTime == null || newTime == null) {
            return 0;
        }

        long time = newTime.getTime() - oldTime.getTime();
        if (time >= 0) {
            // 相差的秒
            return (int) (time / 1000);
        } else {
            return 0;
        }
    }

    /**
     *
     * 方法getIntervalDDHHMM的功能描述：计算2个日期相差几天，几时，几秒
     *
     * @param newTime
     * @param oldTime
     * @return
     * @return int
     * @since v1.0.0
     *
     * <PRE>
     * author ShenQuan He
     * Date 2015年3月20日
     * </PRE>
     */
    public static String getIntervalDDHHMM(Date newTime, Date oldTime) {
        if (oldTime == null || newTime == null) {
            return null;
        }

        StringBuffer timeStr = new StringBuffer();

        long time = newTime.getTime() - oldTime.getTime();
        if (time >= 0) {
            long day = time / (24 * 60 * 60 * 1000);
            if (day > 0) {
                timeStr.append(day + "天");
            }
            long hour = (time / (60 * 60 * 1000) - day * 24);
            if (day > 0 || hour > 0) {
                timeStr.append(hour + "小时");
            }
            if (day <= 0) {
                long min = ((time / (60 * 1000)) - day * 24 * 60 - hour * 60);
                timeStr.append(min + "分钟");
            }

        }

        return timeStr.toString();
    }

    /**
     *
     * 方法fromToday的功能描述：距离今天多久
     *
     * @param date
     * @return
     * @return String
     * @since v1.3.0
     *
     * <PRE>
     * author huzhanju
     * Date 2015年3月3日
     * </PRE>
     */
    public static String fromToday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        long time = date.getTime() / 1000;
        long now = new Date().getTime() / 1000;
        long ago = now - time;
        if (ago <= ONE_HOUR) {
            return ago / ONE_MINUTE + "分钟前";
        } else if (ago <= ONE_DAY) {
            return ago / ONE_HOUR + "小时" + (ago % ONE_HOUR / ONE_MINUTE) + "分钟前";
        } else if (ago <= ONE_MONTH) {
            long day = ago / ONE_DAY;
            return day + "天前" + calendar.get(Calendar.HOUR_OF_DAY) + "点" + calendar.get(Calendar.MINUTE) + "分";
        } else if (ago <= ONE_YEAR) {
            long month = ago / ONE_MONTH;
            long day = ago % ONE_MONTH / ONE_DAY;
            return month + "个月" + day + "天前" + calendar.get(Calendar.HOUR_OF_DAY) + "点" + calendar.get(Calendar.MINUTE)
                    + "分";
        } else {
            long year = ago / ONE_YEAR;
            int month = calendar.get(Calendar.MONTH) + 1;// JANUARY which is 0 so month+1
            return year + "年前" + month + "月" + calendar.get(Calendar.DATE) + "日";
        }
    }

    /**
     *
     * 方法isSameDay的功能描述：判断两个日期是否同一天
     *
     * @param day1
     * @param day2
     * @return Boolean
     * @since v1.3.0
     *
     * <PRE>
     * author huzhanju
     * Date 2015年3月21日
     * </PRE>
     */
    public static Boolean isSameDay(Date day1, Date day2) {
        if (null == day1 || null == day2) {
            return false;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String ds1 = sdf.format(day1);
        String ds2 = sdf.format(day2);
        if (ds1.equals(ds2)) {
            return true;
        }
        return false;
    }

    /**
     * 方法getIntervalTimeAsString的功能描述 计算 两个日期 相差的时间 返回 eg <1天2小时10分钟6秒>
     *
     * @param startTime
     * @param endTime
     * @return
     * @return String
     * @since v1.3.0
     *
     * <PRE>
     * author Luo,Jingyu
     * Date 2015年4月1日
     * </PRE>
     */
    public static String getIntervalTimeAsString(Date startTime, Date endTime) {
        if (startTime == null || endTime == null) {
            return "";
        }
        long longTime = endTime.getTime() - startTime.getTime();
        long secondTime = longTime / 1000;// 多少秒
        long day = secondTime / ONE_DAY;// 计算差多少天
        long hour = secondTime % ONE_DAY / ONE_HOUR;// 计算差多少小时
        long min = secondTime % ONE_DAY % ONE_HOUR / ONE_MINUTE;// 计算差多少分钟
        long sec = secondTime % ONE_DAY % ONE_HOUR % ONE_MINUTE;// 计算差多少秒
        StringBuilder sb = new StringBuilder();
        sb.append(day).append("天").append(hour).append("小时").append(min).append("分钟").append(sec).append("秒");
        return sb.toString();

    }

    public static Integer getMinuteFromString(String dateString)
    {
        if(StringUtils.isEmpty(dateString))
            return null;
        String[] time = dateString.split(":");

        return Integer.parseInt(time[0])*60+Integer.parseInt(time[1]);
    }


    public static Integer formatDateString(String dateString)
    {
        if(StringUtils.isEmpty(dateString))
            return null;
        String[] time = dateString.split(":");

        return Integer.parseInt(time[0])*60+Integer.parseInt(time[1]);
    }

    /**
     * 获取两个日期之间的日期集合
     * @param startDay 2017-10-1
     * @param endDay 2017-10-1
     * @return
     */
    public static List<String> getDatesBetweenTwoDays(String startDay,String endDay) {
        List<String> datesList=new ArrayList<String>();

        Date start = StringToDate(startDay, DateStyle.YYYY_MM_DD);
        Date end = StringToDate(endDay, DateStyle.YYYY_MM_DD);
        Calendar calendarTemp = Calendar.getInstance();
        calendarTemp.setTime(start);
        while (calendarTemp.getTime().getTime()!= end.getTime()) {
            datesList.add(getDate(calendarTemp.getTime()));
            calendarTemp.add(Calendar.DAY_OF_YEAR, 1);
        }
        return datesList;
    }

    /**
     * 获取像个日期之间的日期集合
     *
     * @param start 2018-10-10
     * @param end 2018-10-12
     * @return
     */
    public static List<String> getDatesBetweenTwoDays(Date start,Date end) {
        List<String> datesList=new ArrayList<String>();

        Calendar calendarTemp = Calendar.getInstance();
        calendarTemp.setTime(start);
        while (calendarTemp.getTime().getTime()!= end.getTime()) {
            datesList.add(getDate(calendarTemp.getTime()));
            calendarTemp.add(Calendar.DAY_OF_YEAR, 1);
        }
        return datesList;
    }

    /**
     * 获取当前日期(格式:yyyy_mm_dd)
     *
     * @return
     */
    public static String  getCurrentDateString(){
        Date date = new Date();
        return DateToString(date, DateStyle.YYYY_MM_DD);
    }

    /**
     * 获取当前日期(格式:yyyy_mm_dd)
     *
     * @return
     */
    public static Date  getCurrentDate(){
        String todayDate= getCurrentDateString();

        return StringToDate(todayDate);
    }

    public static  Date getCurrentDateTime(){
       String currentDateTime =  DateToString(new Date(), DateStyle.YYYY_MM_DD_HH_MM_SS);
        return StringToDate(currentDateTime);
    }

    public static Date parseDate(String date,String pattern) {
        try {
            if(pattern == null)
                return new SimpleDateFormat(PATTERN_DATE).parse(date);
            return new SimpleDateFormat(pattern).parse(date);
        } catch (Exception e) {
            //logger.error("parseDate error,time:" + date, e);
            return null;
        }
    }

    public static String format2Date(Date date,String pattern){
        if(pattern == null)
            return new SimpleDateFormat(PATTERN_DATE).format(date);
        return new SimpleDateFormat(pattern).format(date);
    }


    //获取后D天
    public static Date getDayAfter(Date date, int d) {
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + d-1);
        return c.getTime();
    }

    //获取后m月
    public static Date getMonthAfter(Date date, int m) {
        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH);
        c.set(Calendar.MONTH, month + m);
        return c.getTime();
    }

    //获取前d天
    public static Date getDayBefore(Date date ,int d) {//可以用new Date().toLocalString()传递参数
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - d);
        return c.getTime();
    }

    //获取前d天
    public static String getDayBeforeReturnString(int d) {//可以用new Date().toLocalString()传递参数
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - d);
        String dayBefore = DateToString(c.getTime(), DateStyle.YYYY_MM_DD_HH_MM_SS);
        return dayBefore ;
    }

    //获取前m月
    public static Date getMonthBefore(Date date, int m) {
        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH);
        c.set(Calendar.MONTH, month - m);
        return c.getTime();
    }

    //获取后y年
    public static Date getYearAfter(Date date, int y) {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        c.set(Calendar.YEAR, year + y);
        return c.getTime();
    }

    //获取前y年
    public static Date getYearBefore(Date date, int y) {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        c.set(Calendar.YEAR, year - y);
        return c.getTime();
    }

    public static String getTimeDifferenceSomeMinute(int minute){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE)-minute);
        String currentDateTime =  DateToString(cal.getTime(), DateStyle.YYYY_MM_DD_HH_MM_SS);
        return currentDateTime;
    }

}
