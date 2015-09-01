package cn.imethan.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 日期工具类
 *
 * @author ETHAN
 * @email huangyf@suncco.com
 * @datetime 2013-3-6下午4:36:49
 * @copyright 2011-2015
 * @version 1.0
 *
 */
public class DateUtil {
	
	
	public static Calendar calendar = Calendar.getInstance();
	
	/**
	 * 日期格式yyyy-MM-dd HH:mm:ss
	 */
	public static String DATE_PATTERN_01 = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 日期格式yyyy年MM月dd日 HH:mm:ss
	 */
	public static String DATE_PATTERN_02 = "yyyy年MM月dd日 HH:mm:ss";
	
	/**
	 * 日期格式yyyy-MM-dd
	 */
	public static String DATE_PATTERN_03 = "yyyy-MM-dd";
	
	/**
	 * 日期格式yyyy年MM月dd日
	 */
	public static String DATE_PATTERN_04 = "yyyy年MM月dd日";
	
	/**
	 * 日期格式MM月dd日
	 */
	public static String DATE_PATTERN_05 = "MM月dd日";
	
	/**
	 * 日期格式yyyy-MM-dd-HH-mm
	 */
	public static String DATE_PATTERN_06 = "yyyy-MM-dd-HH-mm";
	
	/**
	 * 日期格式yyyy-MM
	 */
	public static String DATE_PATTERN_07 = "yyyy-MM";
	
	/**
	 * 日期格式yyyyMMdd
	 */
	public static String DATE_PATTERN_08 = "yyyyMMdd";
	
	/**
	 * 日期格式yyyy/MM/dd
	 */
	public static String DATE_PATTERN_09 = "yyyy/MM/dd";
	
	
	/**
	 * 格式化时间格式
	 * 
	 * @param source
	 * @param form
	 * @return
	 */
	public static Date StringToDate(String source,String form){	
		Date date = null;
		if(source!=null&&!source.trim().equals("")){
			SimpleDateFormat sdf = new SimpleDateFormat(form);
			try {
				date = sdf.parse(source);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return date;
	}
	
	/**
	 * 格式化时间格式
	 * 
	 * @param source
	 * @param form
	 * @return
	 */
	public static String DateToString(Date source,String form){	
		String resultDate = "";
		if(source!=null){
			SimpleDateFormat sdf = new SimpleDateFormat(form);
			resultDate = sdf.format(source);
		}
		return resultDate;
	}
	
	/**
	 * 转换时间格式
	 * 
	 * @param source
	 * @param form
	 * @return
	 */
	public static Date DateToDate(Date source,String form){
		Date date = null;
		if(source!=null){
			SimpleDateFormat sdf = new SimpleDateFormat(form);
			String stringDate = sdf.format(source);
			try {
				date = sdf.parse(stringDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return date;
	}
	
	/**
	 * 格式化String时间
	 * @param source
	 * @param form
	 * @return
	 */
	public static String stringToString(String source,String form){
		String stringDate = "";
		if(source!=null&&!source.trim().equals("")){
			SimpleDateFormat sdf = new SimpleDateFormat(form);
			Date date = null;;
			try {
				date = sdf.parse(source);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			stringDate = sdf.format(date);
		}
		
		return stringDate;
	}
	
	
	
	/**
	 * 获取当前时间
	 * 
	 * @param form
	 * @return
	 */
	public static String getDatetimeStr(String form){	
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(form);
		return sdf.format(date);
	}

	
	/**
	 * 时间戳 格式化
	 * 
	 * @param source
	 * @param form
	 * @return
	 */
	public static String formatLongDate(Long source,String form){
		SimpleDateFormat sdf = new SimpleDateFormat(form);
		return sdf.format(source);
	}
	
	/**
	 * 获取当前时间前的时间点
	 * 
	 * @param time 时间间隔
	 * @return
	 */
	public static String getBeforeNowDateTime(Long time){
		long now = new Date().getTime();
		long result = now - time;
		String resultDate = DateUtil.formatLongDate(result, DateUtil.DATE_PATTERN_01);
		
		return resultDate;
	}
	
	
	/**
	 * 获取日期,例如20111001,格式：yyyyMMdd 
	 * 
	 * @return
	 */
	public static String getDateStr(){
		String year = String.valueOf(calendar.get(Calendar.YEAR ));
		String month = String.valueOf(calendar.get(Calendar.MONTH)+1);
		String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
		return year+month+day;
	}
	
	/**
	 * 获取时间，例如133040，格式：HHmmss
	 * 
	 * @return
	 */
	public static String getTimeStr(){		
		String HH = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
		String mm = String.valueOf(calendar.get(Calendar.MINUTE));
		String ss = String.valueOf(calendar.get(Calendar.SECOND));		
		return HH+mm+ss;
	}
	
	/**
	 * 获取当前LONG格式的时间
	 * @return
	 */
	public static Long getLongTime(){
		return new Date().getTime();
	}
	public static Long getLongTime(String pattern){
        return DateToDate(new Date(), pattern).getTime()/1000;
    }
	/**
     * 获取间隔时间日期
     * @param monthLag “-”：向前，“+”：向后
     * @return
     */
    public static String getLagMonthTime(int monthLag){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, monthLag);
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.DATE_PATTERN_07);
        return sdf.format(c.getTime());
    }
	
	/**
	 * 获取间隔时间日期
	 * @param dayLag “-”：向前，“+”：向后
	 * @return
	 */
	public static String getLagDateTime(int dayLag){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, dayLag);
		SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.DATE_PATTERN_01);
		return sdf.format(c.getTime());
	}
	
	public static void main(String args[]){
		String nowDate = DateUtil.getDatetimeStr(DateUtil.DATE_PATTERN_01);
		String targetDate = DateUtil.getLagDateTime(-7);
		System.out.println("nowDate:"+nowDate);
		System.out.println("targetDate:"+targetDate);
	}
}
