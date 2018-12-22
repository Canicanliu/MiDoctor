package com.can.minidoctor.api.utils;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author: ltm
 * @Descripion:
 * @Date: Created in 22:57 2018/12/18
 */
public class DateUtils {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(DateUtils.class);
	
    private static String format = "yyyy-MM-dd";
    private static String formatYYYYMM = "yyyyMM";
    private static String formatYYYYMMDD = "yyyy-MM-dd";
    private static String formatyyyymmdd="yyyy/MM/dd";
    private static String formatyyyymm="yyyy-MM";
    private static String formatMMDD="MM-dd";
    public static String FORMAT_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

    public static String formatDate(Date date){
        return new SimpleDateFormat(format).format(date);
    }

    public static Date getCurrentDate(){
        return new Date();
    }
    
    public static String getDateStr(Date date, String format){
		DateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

    public static Date parseDate(String date)  {
        try {
            if(StringUtils.isEmpty(date)){
                return null;
            }else{
                return new SimpleDateFormat(format).parse(date);
            }
        }catch (Exception e){
            LOGGER.error("解析日期异常,{}",date,e);
            return null;
        }

    }

    public static Date parseStrToDate(String date){
        if(StringUtils.isEmpty(date)){
            return null;
        }else{
            try{
                return new SimpleDateFormat(format).parse(date);
            }catch(Exception e){
                return new Date();
            }
        }
    }
    
    public static Date parseStrToDate(String date,String formatted){
        if(StringUtils.isEmpty(date)){
            return null;
        }else{
            try{
                return new SimpleDateFormat(formatted).parse(date);
            }catch(Exception e){
                LOGGER.error("error parsing date String:{},format:{}",date,formatted,e);
                return null;
            }
        }
    }

    public static List<Date[]> splitTimeByHours(Date start, Date end, int hours) {
        List<Date[]> dl = new ArrayList<Date[]>();
        while (start.compareTo(end) < 0) {
            Date _end = addHours(start, hours);
            if (_end.compareTo(end) > 0) {
                _end = end;
            }
            Date[] dates = new Date[] { (Date) start.clone(), (Date) _end.clone() };
            dl.add(dates);

            start = _end;
        }
        return dl;
    }

    public static List<Date[]> splitTimeByMinus(Date start, Date end, int minus) {
        List<Date[]> dl = new ArrayList<Date[]>();
        while (start.compareTo(end) < 0) {
            Date _end = addMinus(start, minus);
            if (_end.compareTo(end) > 0) {
                _end = end;
            }
            Date[] dates = new Date[] { (Date) start.clone(), (Date) _end.clone() };
            dl.add(dates);
            start = _end;
        }
        return dl;
    }


    /**
     * //切分时间片段后，首位不交叉
     * 00：00：00-00：59：59
     * 01：00：00-01：59：59
     */
    public static List<Date[]> splitTimeBySeconds(Date start, Date end, int Seconds) {
        List<Date[]> dl = new ArrayList<Date[]>();
        while (start.compareTo(end) < 0) {
            Date _end= addSeconds(start, Seconds);
            if (_end.compareTo(end) > 0) {
                _end = end;
            }
            Date[] dates = new Date[] { (Date) start.clone(), (Date) _end.clone() };
            dl.add(dates);

            start = addSeconds(_end,1);
//			start = _end;
        }
        return dl;
    }

    public static Date getTimeOf000000() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DAY_OF_MONTH, 0);
        return  cal.getTime();
    }
    public static Date getTomorrow000000() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return  cal.getTime();
    }

    public static Date getYestoday235959() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getTimeOf000000());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, -1);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DAY_OF_MONTH, 0);
        return  cal.getTime();
    }
    public static Date getTimeOf235000() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getTimeOf000000());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, -10);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return  cal.getTime();
    }

    public static Date addDays(Date date, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, amount);
        return c.getTime();
    }

    public static Date addHours(Date date, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR_OF_DAY, amount);
        return c.getTime();
    }

    public static Date addMonths(Date date, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, amount);
        return c.getTime();
    }

    public static Date addSeconds(Date date, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.SECOND, amount);
        return c.getTime();
    }

    public static Date addMinus(Date date, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, amount);
        return c.getTime();
    }

    public static String getYYYYMM() {
        return getYYYYMM(new Date());
    }

    public static String getYYYYMMDD() {
        return getYYYYMMDD(new Date());
    }

    public static String getYYYYMM(Date date) {
        return new SimpleDateFormat(formatYYYYMM).format(date);
    }

    public static String getYYYY_MM(Date date) {
        return new SimpleDateFormat(formatyyyymm).format(date);
    }

    public static String getYYYYMMDD(Date date) {
        return new SimpleDateFormat(formatYYYYMMDD).format(date);
    }

    public static String getMMDD(Date date) {
        return new SimpleDateFormat(formatMMDD).format(date);
    }

    public static String getyyyymmdd(Date date){
        return new SimpleDateFormat(formatyyyymmdd).format(date);
    }

    public static String getFormat(Date date, String format){
        return new SimpleDateFormat(format).format(date);
    }

    //10位时间戳转时间
    public static Date getDate(Long time){
        if(time==null){
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time*1000);
        return cal.getTime();
    }

    public static Date getDateByJavaTime(Long time){
        if(time==null){
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        return cal.getTime();
    }

    public static Date getTimeOf000000(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DAY_OF_MONTH, 0);
        return  cal.getTime();
    }

    public static long getCeilingGap(Date fromDate, Date toDate, int type) {
        long fromDateMilli = fromDate.getTime();
        long toDateMilli = toDate.getTime();
        long minus = toDateMilli - fromDateMilli;
        long result = 0;
        long remainder = 0;
        if (Calendar.SECOND == type) {
            result = minus/1000;
            remainder = minus%1000;
        }
        if (Calendar.MINUTE == type) {
            result = minus/(1000*60);
            remainder = minus%(1000*60);
        }
        if (Calendar.HOUR == type) {
            result = minus/(1000*3600);
            remainder = minus%(1000*3600);
        }
        if (Calendar.DATE == type) {
            result = minus/(1000*3600*24);
            remainder = minus%(1000*3600*24);
        }

        if (remainder > 0) {
            result +=1;
        }
        if (remainder < 0) {
            result -=1;
        }

        return result;
    }
    
    public static Date parseTimestampToDate (Long time){
    	SimpleDateFormat sd =  new SimpleDateFormat(format);
        String d  = sd.format(time);
		try {
			Date date = sd.parse(d);
			return date ;
		} catch (ParseException e) {
			e.printStackTrace();
		}
        return null ;
    }
    
    public static void main(String[] args) throws ParseException {
//		System.out.println(getCeilingGap(getTimeOf235000(),getTomorrow000000(),13));
//		System.out.println(getTomorrow000000().getTime() - getTimeOf235000().getTime());
//		System.out.println(formatDate(getTimeOf000000()));
//		System.out.println(formatDate(new Date()).substring(11, 16));
        SimpleDateFormat sm = new SimpleDateFormat(format) ;
        Date date =  sm.parse("2018-03-14 17:40:00") ;
        System.out.println(getCeilingGap(date,new Date(),12));

    }
}
