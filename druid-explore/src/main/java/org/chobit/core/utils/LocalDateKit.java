package org.chobit.core.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.MILLIS;
import static java.util.Calendar.MILLISECOND;

/**
 * 用来处理LocalDate和LocalDateTime相关的工具包
 *
 * @author robin
 */
public final class LocalDateKit {

    /**
     * 默认日期格式
     */
    public static final String COMMON_DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 默认日期时间格式
     */
    public static final String COMMON_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 默认日期格式
     */
    private static final DateTimeFormatter COMMON_DATE_FORMATTER = DateTimeFormatter.ofPattern(COMMON_DATE_PATTERN);
    /**
     * 默认日期时间格式
     */
    private static final DateTimeFormatter COMMON_DATETIME_FORMATTER = DateTimeFormatter.ofPattern(COMMON_DATETIME_PATTERN);


    /**
     * 按默认格式（yyyy-MM-dd HH:mm:ss）格式化日期时间
     *
     * @param dateTime LocalDateTime实例
     * @return 日期时间字符串
     */
    public static String formatTime(LocalDateTime dateTime) {
        return COMMON_DATETIME_FORMATTER.format(dateTime);
    }


    /**
     * 按指定格式格式化时间
     *
     * @param pattern  时间格式
     * @param dateTime 时间对象
     * @return 格式化后的时间字符串
     */
    public static String formatTime(String pattern, LocalDateTime dateTime) {
        return DateTimeFormatter.ofPattern(pattern).format(dateTime);
    }


    /**
     * 按默认格式（yyyy-MM-dd HH:mm:ss）解析日期时间字符串
     *
     * @param dateTime 日期时间字符串
     * @return LocalDateTime实例
     */
    public static LocalDateTime parseTime(String dateTime) {
        return LocalDateTime.parse(dateTime, COMMON_DATETIME_FORMATTER);
    }


    /**
     * 按指定格式解析日期时间
     *
     * @param pattern  日期时间格式
     * @param dateTime 日期时间字符串
     * @return LocalDateTime实例
     */
    public static LocalDateTime parseTime(String pattern, String dateTime) {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(pattern));
    }


    /**
     * 按默认格式（yyyy-MM-dd）格式化日期实例
     *
     * @param date LocalDate实例
     * @return 日期字符串
     */
    public static String formatDate(LocalDate date) {
        return COMMON_DATE_FORMATTER.format(date);
    }


    /**
     * 按指定格式格式化日期实例
     *
     * @param pattern 日期格式
     * @param date    日期实例
     * @return 格式化后的日期实例
     */
    public static String formatDate(String pattern, LocalDate date) {
        return DateTimeFormatter.ofPattern(pattern).format(date);
    }


    /**
     * 根据默认格式（yyyy-MM-dd）解析日期字符串
     *
     * @param date 日期字符串
     * @return 日期实例
     */
    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date, COMMON_DATE_FORMATTER);
    }


    /**
     * 根据指定格式解析日期字符串
     *
     * @param pattern 日期格式
     * @param date    日期字符串
     * @return 日期实例
     */
    public static LocalDate parseDate(String pattern, String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern));
    }


    /**
     * 将毫秒级的时间戳转为LocalDateTime对象
     *
     * @param milli 时间戳
     * @return LocalDateTime对象
     */
    public static LocalDateTime fromEpochMilli(long milli) {
        return Instant.ofEpochMilli(milli).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * 将本地时间转为毫秒级长整型
     *
     * @param dateTime 时间对象
     * @return 毫秒级长整型
     */
    public static long toEpochMilli(LocalDateTime dateTime) {
        return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }


    /**
     * 秒级时间戳转换成Datetime
     *
     * @param epochSeconds 秒级时间戳
     * @return datetime格式字符串
     */
    public static LocalDateTime fromEpochSecond(Long epochSeconds) {
        Instant instant = Instant.ofEpochSecond(epochSeconds);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }


    /**
     * 将本地时间转为秒级长整型
     *
     * @param dateTime 时间对象
     * @return 秒级长整型
     */
    public static long toEpochSecond(LocalDateTime dateTime) {
        return dateTime.atZone(ZoneId.systemDefault()).toEpochSecond();
    }

    /**
     * 将LocalDateTime实例转为Date实例
     *
     * @param dateTime LocalDateTime实例
     * @return Date实例
     */
    public static Date toDate(LocalDateTime dateTime) {
        long time = toEpochMilli(dateTime);
        time = time / 1000 * 1000;
        return new Date(time);
    }


    /**
     * 将LocalDate实例转为Date实例
     *
     * @param date LocalDate实例
     * @return Date实例
     */
    public static Date toDate(LocalDate date) {

        Calendar cal = Calendar.getInstance();
        cal.set(date.getYear(),
                date.getMonthValue() - 1,
                date.getDayOfMonth(), 0, 0, 0);
        cal.set(MILLISECOND, 0);
        return cal.getTime();
    }


    /**
     * 计算当天剩余的时间
     *
     * @return 当天剩余的时间，单位是毫秒
     */
    public static long remainsOfToday() {
        return remainsOfDay(LocalDateTime.now());
    }


    /**
     * 计算一天剩余的时间
     *
     * @param time 指定的时间
     * @return 指定的时间在当天剩余的时间，单位是毫秒
     */
    public static long remainsOfDay(LocalDateTime time) {
        LocalDateTime midNightTime = time.plusDays(1).truncatedTo(DAYS);
        return time.until(midNightTime, MILLIS);
    }


    private LocalDateKit() {
        throw new UnsupportedOperationException("Private constructor, cannot be accessed.");
    }
}
