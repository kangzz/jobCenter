package com.jobCenter.util;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期工具类
 * @author liuys
 *
 */
public class DateUtil extends DateUtils {

	/**
	 * 日志对象
	 */
	private static Logger log = Logger.getLogger(DateUtil.class);

	private int weeks = 0;

	// 用来全局控制 上一周，本周，下一周的周数变化
	private int MaxDate;// 一月最大天数

	private int MaxYear;// 一年最大天数
	/**
	 * 日期格式:yyyy-mm-dd<br>
	 * 例如:2005-11-02
	 */
	public final static String DATE_PATTERN_LINE = "yyyy-MM-dd";
	/**
	 * 日期格式:yyyy/mm/dd<br>
	 * 例如:2005/11/02
	 */
	public final static String DATE_PATTERN_BIAS = "yyyy/MM/dd";
	/**
	 * 日期时间格式(24小时制):yyyy-mm-dd HH:mm:ss<br>
	 * 例如:2005-11-02 23:01:01
	 */
	public final static String DATETIME24_PATTERN_LINE = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 日期时间格式(12小时制):yyyy-mm-dd hh:mm:ss<br>
	 * 例如:2005-11-02 11:01:01
	 */
	public final static String DATETIME12_PATTERN_LINE = "yyyy-MM-dd hh:mm:ss";
	/**
	 * 日期时间格式(24小时制):yyyy/mm/dd HH:mm:ss<br>
	 * 例如:2005/11/02 23:01:01
	 */
	public final static String DATETIME24_PATTERN_BIAS = "yyyy/MM/dd HH:mm:ss";
	/**
	 * 日期时间格式(12小时制):yyyy/mm/dd hh:mm:ss<br>
	 * 例如:2005/11/02 11:01:01
	 */
	public final static String DATETIME12_PATTERN_BIAS = "yyyy/MM/dd hh:mm:ss";
	/**
	 * 日期时间格式(24小时制):yyyy-mm-dd HH:mm:ss<br>
	 * 例如:2005-11-02 23:01:01
	 */
	public final static String DATETIME24_NO_LINE = "yyyyMMddHHmmss";

	/**
	 * 日期时间格式(24小时制):yyyyMMdd<br>
	 * 例如:20151217
	 */
	public static final String DATE_PATTERN = "yyyyMMdd";

	/**
	 * 获取指定时间戳的偏移天数 (传-1 获取昨天的时间戳)
	 * @param day
	 * @param offset
	 * @return
	 */
	public static Timestamp getTimestampByOtherDay(Timestamp day, int offset) {
		Calendar c = Calendar.getInstance();
		c.setTime(day);
		c.add(Calendar.DAY_OF_MONTH, offset);
		return new Timestamp(c.getTimeInMillis());
	}

	/**
	 * 获取指定时间戳的偏移小时 (传-10 获取10小时的时间戳)
	 * @param day
	 * @param offset
	 * @return
	 */
	public static Timestamp getTimestampByOtherHour(Timestamp day, int offset) {
		Calendar c = Calendar.getInstance();
		c.setTime(day);
		c.add(Calendar.HOUR_OF_DAY, offset);
		return new Timestamp(c.getTimeInMillis());
	}

	/**
	 * 得到二个日期间的间隔天数
	 */
	public static String getTwoDay(String sj1, String sj2) {
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		long day = 0;
		try {
			Date date = myFormatter.parse(sj1);
			Date mydate = myFormatter.parse(sj2);
			day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		} catch (Exception e) {
			return "";
		}
		return day + "";
	}

	/**
	 * 根据一个日期，返回是星期几的字符串
	 * @param sdate
	 * @return
	 */
	public static String getWeek(String sdate) {
		// 再转换为时间
		Date date = DateUtil.strToDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		//int hour=c.get(Calendar.DAY_OF_WEEK);
		// hour中存的就是星期几了，其范围 1~7,1=星期日 7=星期六，其他类推
		return new SimpleDateFormat("EEEE").format(c.getTime());
	}
	public static String getWeekStr(String sdate) {
		String str = "";
		Date date = DateUtil.strToDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int hour=c.get(Calendar.DAY_OF_WEEK);
		if (1==hour){
			str = "星期日";
		} else if (2==hour) {
			str = "星期一";
		} else if (3==hour) {
			str = "星期二";
		} else if (4==hour) {
			str = "星期三";
		} else if (5==hour) {
			str = "星期四";
		} else if (6==hour) {
			str = "星期五";
		} else if (7==hour) {
			str = "星期六";
		}
		return str;
	}
	/**
	 * 将短时间格式字符串转换为时间 yyyy-MM-dd
	 * @param strDate
	 * @return
	 */
	public static Date strToDate(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	/**
	 * 两个时间之间的天数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getDays(String date1, String date2) {
		if (date1 == null || date1.equals(""))
			return 0;
		if (date2 == null || date2.equals(""))
			return 0;
		// 转换为标准时间
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		Date mydate = null;
		try {
			date = myFormatter.parse(date1);
			mydate = myFormatter.parse(date2);
		} catch (Exception e) {
		}
		long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		return day;
	}

	// 计算当月最后一天,返回字符串
	public String getDefaultDay() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号
		lastDate.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天
		str = sdf.format(lastDate.getTime());
		return str;
	}

	// 上月第一天
	public String getPreviousMonthFirst() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.add(Calendar.MONTH, -1);// 减一个月，变为下月的1号
		// lastDate.add(Calendar.DATE,-1);//减去一天，变为当月最后一天
		str = sdf.format(lastDate.getTime());
		return str;
	}

	// 获取当月第一天
	public String getFirstDayOfMonth() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		str = sdf.format(lastDate.getTime());
		return str;
	}

	// 获得本周星期日的日期
	public String getCurrentWeekday() {
		weeks = 0;
		int mondayPlus = this.getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonday = df.format(monday);
		return preMonday;
	}

	// 获取当天时间
	public static String getNowTime(String dateformat) {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);// 可以方便地修改日期格式
		String hehe = dateFormat.format(now);
		return hehe;
	}

	// 获得当前日期与本周日相差的天数
	private int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
		if (dayOfWeek == 1) {
			return 0;
		} else {
			return 1 - dayOfWeek;
		}
	}

	// 获得当前日期与本周日相差的天数
	private int getAmraMondayPlus() {
		Calendar cd = Calendar.getInstance();
		// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK); // 因为按中国礼拜一作为第一天所以这里减1
		if (dayOfWeek == 1) {
			return 0;
		} else {
			return 1 - dayOfWeek;
		}
	}

	// 获得本周一的日期
	public String getMondayOFWeek() {
		weeks = 0;
		int mondayPlus = this.getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonday = df.format(monday);
		return preMonday;
	}

	// 获得相应周的周六的日期
	public String getSaturday() {
		int mondayPlus = this.getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks + 6);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonday = df.format(monday);
		return preMonday;
	}

	// 获得美国上周星期日-1的日期
	public String getPreviousWeekSunday() {
		weeks = 0;
		weeks--;
		int mondayPlus = this.getAmraMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + weeks);
		Date monday = currentDate.getTime();
		SimpleDateFormat sf = new SimpleDateFormat("MM月dd日");
		String preMonday = sf.format(monday);
		return preMonday;
	}

	// 获得美国上周星期一-1的日期
	public String getPreviousWeekday() {
		weeks--;
		int mondayPlus = this.getAmraMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks);
		Date monday = currentDate.getTime();
		SimpleDateFormat sf = new SimpleDateFormat("MM月dd日");
		String preMonday = sf.format(monday);
		return preMonday;
	}

	// 获得下周星期一的日期
	public String getNextMonday() {
		weeks++;
		int mondayPlus = this.getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonday = df.format(monday);
		return preMonday;
	}

	// 获得下周星期日的日期
	public String getNextSunday() {
		int mondayPlus = this.getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 + 6);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonday = df.format(monday);
		return preMonday;
	}

	@SuppressWarnings("unused")
	private int getMonthPlus() {
		Calendar cd = Calendar.getInstance();
		int monthOfNumber = cd.get(Calendar.DAY_OF_MONTH);
		cd.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		cd.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		MaxDate = cd.get(Calendar.DATE);
		if (monthOfNumber == 1) {
			return -MaxDate;
		} else {
			return 1 - monthOfNumber;
		}
	}

	// 获得上月最后一天的日期
	public String getPreviousMonthEnd() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.MONTH, -1);// 减一个月
		lastDate.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		lastDate.roll(Calendar.DATE, -1);// 日期回滚一天，也就是本月最后一天
		str = sdf.format(lastDate.getTime());
		return str;
	}

	// 获得下个月第一天的日期
	public String getNextMonthFirst() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.MONTH, 1);// 减一个月
		lastDate.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		str = sdf.format(lastDate.getTime());
		return str;
	}

	// 获得下个月最后一天的日期
	public String getNextMonthEnd() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.MONTH, 1);// 加一个月
		lastDate.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		lastDate.roll(Calendar.DATE, -1);// 日期回滚一天，也就是本月最后一天
		str = sdf.format(lastDate.getTime());
		return str;
	}

	// 获得明年最后一天的日期
	public String getNextYearEnd() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.YEAR, 1);// 加一个年
		lastDate.set(Calendar.DAY_OF_YEAR, 1);
		lastDate.roll(Calendar.DAY_OF_YEAR, -1);
		str = sdf.format(lastDate.getTime());
		return str;
	}

	// 获得明年第一天的日期
	public String getNextYearFirst() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.YEAR, 1);// 加一个年
		lastDate.set(Calendar.DAY_OF_YEAR, 1);
		str = sdf.format(lastDate.getTime());
		return str;
	}

	// 获得本年有多少天
	@SuppressWarnings("unused")
	private int getMaxYear() {
		Calendar cd = Calendar.getInstance();
		cd.set(Calendar.DAY_OF_YEAR, 1);// 把日期设为当年第一天
		cd.roll(Calendar.DAY_OF_YEAR, -1);// 把日期回滚一天。
		int MaxYear = cd.get(Calendar.DAY_OF_YEAR);
		return MaxYear;
	}

	private int getYearPlus() {
		Calendar cd = Calendar.getInstance();
		int yearOfNumber = cd.get(Calendar.DAY_OF_YEAR);// 获得当天是一年中的第几天
		cd.set(Calendar.DAY_OF_YEAR, 1);// 把日期设为当年第一天
		cd.roll(Calendar.DAY_OF_YEAR, -1);// 把日期回滚一天。
		int MaxYear = cd.get(Calendar.DAY_OF_YEAR);
		if (yearOfNumber == 1) {
			return -MaxYear;
		} else {
			return 1 - yearOfNumber;
		}
	}

	// 获得本年第一天的日期
	public String getCurrentYearFirst() {
		int yearPlus = this.getYearPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, yearPlus);
		Date yearDay = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preYearDay = df.format(yearDay);
		return preYearDay;
	}

	// 获得本年最后一天的日期 *
	public String getCurrentYearEnd() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");// 可以方便地修改日期格式
		String years = dateFormat.format(date);
		return years + "-12-31";
	}

	// 获得上年第一天的日期 *
	public String getPreviousYearFirst() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");// 可以方便地修改日期格式
		String years = dateFormat.format(date);
		int years_value = Integer.parseInt(years);
		years_value--;
		return years_value + "-1-1";
	}

	// 获得上年最后一天的日期
	public String getPreviousYearEnd() {
		weeks--;
		int yearPlus = this.getYearPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, yearPlus + MaxYear * weeks
				+ (MaxYear - 1));
		Date yearDay = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preYearDay = df.format(yearDay);
		getThisSeasonTime(11);
		return preYearDay;
	}

	// 获得本季度
	public String getThisSeasonTime(int month) {
		int array[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } };
		int season = 1;
		if (month >= 1 && month <= 3) {
			season = 1;
		}
		if (month >= 4 && month <= 6) {
			season = 2;
		}
		if (month >= 7 && month <= 9) {
			season = 3;
		}
		if (month >= 10 && month <= 12) {
			season = 4;
		}
		int start_month = array[season - 1][0];
		int end_month = array[season - 1][2];
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");// 可以方便地修改日期格式
		String years = dateFormat.format(date);
		int years_value = Integer.parseInt(years);
		int start_days = 1;// years+"-"+String.valueOf(start_month)+"-1";//getLastDayOfMonth(years_value,start_month);
		int end_days = getLastDayOfMonth(years_value, end_month);
		String seasonDate = years_value + "-" + start_month + "-" + start_days + ";" + years_value + "-" + end_month + "-" + end_days;
		return seasonDate;
	}

	/**
	 * 获取某年某月的最后一天
	 * @param year	年
	 * @param month	月
	 * @return 最后一天
	 */
	private int getLastDayOfMonth(int year, int month) {
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
				|| month == 10 || month == 12) {
			return 31;
		}
		if (month == 4 || month == 6 || month == 9 || month == 11) {
			return 30;
		}
		if (month == 2) {
			if (isLeapYear(year)) {
				return 29;
			} else {
				return 28;
			}
		}
		return 0;
	}

	/**
	 * 是否闰年
	 * @param year	年
	 * @return
	 */
	public boolean isLeapYear(int year) {
		return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
	}

	/**
	 * 取得指定日期所在周的第一天
	 */
	public static Date getFirstDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
		return c.getTime();
	}

	/**
	 * 取得指定日期所在周的最后一天
	 */
	public static Date getLastDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
		return c.getTime();
	}

	public static List<String> dateToWeek(Date mdate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(mdate);
		int b = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (b == 0) {
			b = 7;
		}
		Date fdate;
		List<String> list = new ArrayList<String>();
		Long fTime = mdate.getTime() - b * 24 * 3600000;
		String datetemp = "2099-12-12";
		for (int a = 1; a < 8; a++) {
			fdate = new Date();
			fdate.setTime(fTime + (a * 24 * 3600000));
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			datetemp = sf.format(fdate);
			list.add(datetemp);
		}
		return list;
	}

	public static String getbz(Date date) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String datetemp = sf.format(date);
		return datetemp;
	}

	public static String getbz(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sf = new SimpleDateFormat("MM月dd日");
		String datetemp = "";
		try {
			datetemp = sf.format(sdf.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return datetemp;
	}

	public static String getDateString(Date date) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		String datetemp = sf.format(date);
		return datetemp;
	}

	/**
	 * 获取本周一日期
	 * @param current	当前时间
	 * @return
	 * @throws Exception
	 */
	public static Date getThisWeekMonday(Date current) throws Exception {
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(current);
			c.setFirstDayOfWeek(Calendar.MONDAY);
			c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);// // 本周1
			return c.getTime();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public static List<String> getWeekToDate(Date mdate) {
		Calendar cal=Calendar.getInstance();
		cal.setTime(mdate);
		int b= cal.get(Calendar.DAY_OF_WEEK)-1;
		if(b==0){
			b=7;
		}
		Date fdate;
		List<String> list = new ArrayList<String>();
		Long fTime = mdate.getTime() - b * 24 * 3600000;
		String datetemp = "2099-12-12";
		for (int a = 1; a < 8; a++) {
			fdate = new Date();
			fdate.setTime(fTime + (a * 24 * 3600000));
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
			datetemp = sf.format(fdate);
			list.add(datetemp);
		}
		return list;
	}

	/**
	 * 获得本周日时间
	 * @param current
	 * @return
	 * @throws Exception
	 */
	public static Date getThisWeekSunday(Date current) throws Exception {
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(current);
			c.setFirstDayOfWeek(Calendar.MONDAY);
			c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			return c.getTime();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 获得上周日时间
	 * @param current
	 * @return
	 * @throws Exception
	 */
	public static Date getLastWeekSunday(Date current) throws Exception {
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(getThisWeekMonday(current));
			c.setFirstDayOfWeek(Calendar.MONDAY);
			c.add(Calendar.DATE, -1);
			return c.getTime();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 获得上周一时间
	 * @param current
	 * @return
	 * @throws Exception
	 */
	public static Date getLastWeekMonday(Date current) throws Exception {
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(getThisWeekMonday(current));
			c.setFirstDayOfWeek(Calendar.MONDAY);
			c.add(Calendar.DATE, -7);
			return c.getTime();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * @param date
	 * @return
	 */
	public static Timestamp getTimestamp(Date date) {
		SimpleDateFormat dateFormate = new SimpleDateFormat(DateUtil.DATETIME24_PATTERN_LINE);
		return Timestamp.valueOf(dateFormate.format(date));
	}

	public static Timestamp getTimestamp() {
		return getTimestamp(new Date(System.currentTimeMillis()));
	}

	public static Timestamp getTimestamp(String time) {
		if (time == null || time.toLowerCase().equals("null")|| time.toLowerCase().length() == 0) {
			return new Timestamp(System.currentTimeMillis());
		} else {
			return Timestamp.valueOf(time);
		}
	}

	/**
	 * 获取当前日期所在月的第一天的日期
	 * @param current
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static Date getFirstDate(Date current) throws Exception {
		try {
			return new Date(current.getYear(), current.getMonth(), 1);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 获取当前日期所在月的最后一天的日期
	 * @param current
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public static Date getLastDate(Date current) throws Exception {
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(new Date(current.getYear(), current.getMonth(), 1));
			c.roll(Calendar.MONTH, true);
			if (current.getMonth() == 11) {
				c.roll(Calendar.YEAR, true);
			}
			c.add(Calendar.DATE, -1);
			return c.getTime();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 得到当前系统日期
	 *
	 * @return 得到系统当前日期
	 */
	public static Date getCurrentDate() {
		return new Date(System.currentTimeMillis());
	}

	/**
	 * 将字符串转化为日期型数据<br>
	 * 字符串必须是yyyy-MM-dd格式
	 *
	 * @param src
	 *            日期数据字符串
	 * @return java.util.Date型日期类型数据
	 */
	public static Date parseDate(String src) {
		if (src == null || src.trim().length() <= 0) {
			return null;
		}
		try {
			return parse(src,0);
		} catch (ParseException pe) {
			throw new RuntimeException(pe);
		}
	}

	/**
	 * 根据日期、小时、分钟、秒组合成日期时间
	 * @param date	日期
	 * @param hour	小时
	 * @param
	 * @param
	 * @return 组合后的日期时间
	 * @throws
	 */
	public static Date parseDate(String date, int hour, int minute,int second) throws Exception {
		Calendar cal = Calendar.getInstance();
		Date dateObj = parseDate(date);
		cal.set(getYear(dateObj), getMonth(dateObj), getDay(dateObj), hour,minute, second);
		return cal.getTime();
	}

	/**
	 * 根据年、月、日生成日志对象
	 * @param year	年
	 * @param month	月(1~12之间)
	 * @param day	日
	 * @return 日期对象
	 * @throws
	 */
	public static Date parseDate(int year, int month, int day)
			throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);
		return cal.getTime();
	}

	/**
	 * 得到日期中的年份
	 * @param date	日期
	 * @return 年份
	 */
	public static int getYear(Date date) {
		if (date == null) {
			return 0;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}

	/**
	 * 得到日期中的月份
	 * @param date	日期
	 * @return 月份
	 */
	public static int getMonth(Date date) {
		if (date == null) {
			return 0;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MONTH);
	}

	/**
	 * 得到日期中的天
	 * @param date	日期
	 * @return 天
	 */
	public static int getDay(Date date) {
		if (date == null) {
			return 0;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 得到日期中的小时
	 * @param date	日期
	 * @return 小时
	 */
	public static int getHour(Date date) {
		if (date == null) {
			return 0;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 得到日期中的分钟数
	 * @param date	日期
	 * @return 分钟
	 */
	public static int getMinute(Date date) {
		if (date == null) {
			return 0;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MINUTE);
	}

	/**
	 * 根据指定的格式化模式,格式化日历数据<br>
	 * 默认使用yyyy-MM-dd HH:mm:ss
	 * @param now	 给定日期
	 * @return 被格式化后的字符串
	 */
	public static String formatDate(Calendar now) {
		return formatDate(now, DateUtil.DATETIME24_PATTERN_LINE);
	}

	/**
	 * 根据指定的格式化模式,格式化日历数据<br>
	 * 如果格式化模式为null或者为空,则默认使用yyyy-MM-dd HH:mm:ss
	 * @param now	给定日期
	 * @param formatePattern	格式化模式
	 * @return 被格式化后的字符串<br>
	 */
	public static String formatDate(Calendar now,
									String formatePattern) {
		if (now == null) {
			return null;
		}
		if (formatePattern == null || formatePattern.trim().length() <= 0) {
			formatePattern = DateUtil.DATETIME24_PATTERN_LINE;
		}
		Date tempDate = now.getTime();
		SimpleDateFormat dateFormate = new SimpleDateFormat(formatePattern);
		return dateFormate.format(tempDate);
	}

	/**
	 * 将java.util.Date数据转换为指定格式的字符串<br>
	 * 如果格式化模式为null或者为空,则默认使用yyyy-MM-dd HH:mm:ss
	 * @param date	java.util.Date类型数据
	 * @param formatePattern	指定的日期格式化模式
	 * @return 格式化后的日期的字符串形式<br>
	 *
	 */
	public static String formatDate(Date date, String formatePattern) {
		if (formatePattern == null || formatePattern.trim().length() <= 0) {
			formatePattern = DateUtil.DATETIME24_PATTERN_LINE;
		}
		if (date == null) {
			return "";
		} else {
			SimpleDateFormat dateFormate = new SimpleDateFormat(formatePattern);
			return dateFormate.format(date);
		}
	}

	/**
	 * 将java.sql.Timestamp数据转换为指定格式的字符串<br>
	 * 如果格式化模式为null或者为空,则默认使用yyyy-MM-dd HH:mm:ss
	 * @param date	Timestamp数据
	 * @param formatePattern	日期格式化模式
	 * @return 格式化后的日期的字符串形式
	 */
	public static String formatDate(Timestamp date,
									String formatePattern) {
		if (formatePattern == null || formatePattern.trim().length() <= 0) {
			formatePattern = DateUtil.DATETIME24_PATTERN_LINE;
		}
		if (date == null) {
			return "";
		} else {
			SimpleDateFormat dateFormate = new SimpleDateFormat(formatePattern);
			return dateFormate.format(date);
		}
	}

	/**
	 * 将java.util.Date数据转换为指定格式的字符串<br>
	 * 如果格式化模式为null或者为空,则默认使用yyyy-MM-dd HH:mm:ss
	 *
	 * @param date
	 *            java.util.Date类型数据
	 * @return 格式化后的日期的字符串形式<br>
	 */
	public static String formatDate(Date date) {
		return formatDate(date, DateUtil.DATETIME24_PATTERN_LINE);
	}

	/**
	 * 将代表日期的长整形数值转换为yyyy-MM-dd HH:mm:ss格式的字符串<br>
	 *
	 * @param datetime
	 *            需要转换的日期的长整形数值
	 * @return 格式化后的日期字符串
	 */
	public static String formatDate(long datetime) {
		return formatDate(datetime, DateUtil.DATETIME24_PATTERN_LINE);
	}

	/**
	 * 将代表日期的字符串转换yyyy-MM-dd HH:mm:ss格式的字符串
	 *
	 * @param datetime
	 *            需要转换的日期
	 * @return 格式化后的日期字符串
	 */
	public static String formate(String datetime) {
		return formatDate(datetime, DateUtil.DATETIME24_PATTERN_LINE);
	}

	/**
	 * 将代表日期的字符串转换未指定格式的字符串<br>
	 * 如果格式化模式为null或者为空,则默认使用yyyy-MM-dd HH:mm:ss
	 * @param datetime	需要转换的日期的字符串
	 * @param formatePattern	指定的日期格式
	 * @return 格式化后的日期字符串
	 */
	public static String formatDate(String datetime, String formatePattern) {
		if (datetime == null || datetime.trim().length() <= 0) {
			return "";
		}
		Date date = null;
		try {
			if (formatePattern != null&& (formatePattern.equals(DateUtil.DATE_PATTERN_BIAS) || formatePattern.equals(DateUtil.DATE_PATTERN_LINE))) {
				date = parseDate(datetime);
			} else {
				date = parseDateTime(datetime);
			}


		} catch (Exception ex) {
			log.error("日期转换失败:", ex);
		}
		return formatDate(date, formatePattern);
	}

	/**
	 * 将代表日期的长整形数值转换为y指定格式的字符串<br>
	 * 如果格式化模式为null或者为空,则默认使用yyyy-MM-dd HH:mm:ss
	 * @param datetime	需要转换的日期的长整形数值
	 * @param formatePattern	指定的日期格式
	 * @return 格式化后的日期字符串
	 */
	public static String formatDate(long datetime, String formatePattern) {
		if (datetime <= 0) {
			return "";
		}
		Date date = new Date(datetime);
		return formatDate(date, formatePattern);
	}

	/**
	 * 将java.sql.Date数据转换为指定格式的字符串<br>
	 * 默认使用yyyy-MM-dd HH:mm:ss
	 * @param date	java.sql.Date类型数据
	 * @return 格式化后的日期的字符串形式<br>
	 */
	public static String formatDate(java.sql.Date date) {
		return formatDate(toUtilDate(date));
	}

	/**
	 * 将字符串转化为日期型数据<br>
	 * 字符串必须是yyyy-MM-dd HH:mm:ss格式
	 * @param src	日期数据字符串
	 * @return java.util.Date型日期时间型数据
	 */
	public static Date parseDateTime(String src) {
		if (src == null || src.equals("")) {
			return null;
		}

		try {
			return parse(src,1);
		} catch (ParseException pe) {
			throw new RuntimeException(pe);
		}
	}

	/**
	 * 将java.sql.Date转换为java.util.Date数据类型
	 * @param date	需要转换的java.sql.Date数据
	 * @return 转换后的java.util.Date数据
	 */
	public static Date toUtilDate(java.sql.Date date) {
		if (date == null) {
			return null;
		} else {
			return new Date(date.getTime());
		}
	}

	/**
	 * 某日期上添加x时间段
	 *
	 * @param date
	 * @param iType 如Calendar.DAY_OF_MONTH
	 * @param iValue 增加的时间
	 * @author xwang
	 * @return
	 */
	public static Date add(Date date, int iType, int iValue) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(iType, iValue);
		return calendar.getTime();
	}

	/**
	 * 某日期上添加x时间段
	 *
	 * @param timestamp
	 * @param iType 如Calendar.DAY_OF_MONTH
	 * @param iValue 增加的时间
	 * @author xwang
	 * @return
	 */
	public static Timestamp add(Timestamp timestamp, int iType, int iValue) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(timestamp);
		calendar.add(iType, iValue);
		return new Timestamp(calendar.getTimeInMillis());
	}

	public static void main(String[] args) {
		DateUtil dateUtil = new DateUtil();
//		System.out.println(dateToWeek(new Date()));
//		System.out.println("获取当天日期:" + dateUtil.getNowTime("yyyy-MM-dd"));
//		System.out.println("获取本周一日期:" + dateUtil.getMondayOFWeek());
//		System.out.println("获取本周日的日期~:" + dateUtil.getCurrentWeekday());
//		System.out.println("获取上周一-1日期:" + dateUtil.getPreviousWeekday());
//		System.out.println("获取上周日-1日期:" + dateUtil.getPreviousWeekSunday());
//		System.out.println("获取下周一日期:" + dateUtil.getNextMonday());
//		System.out.println("获取下周日日期:" + dateUtil.getNextSunday());
//		System.out.println("获得相应周的周六的日期:" + dateUtil.getNowTime("yyyy-MM-dd"));
//		System.out.println("获取本月第一天日期:" + dateUtil.getFirstDayOfMonth());
//		System.out.println("获取本月最后一天日期:" + dateUtil.getDefaultDay());
//		System.out.println("获取上月第一天日期:" + dateUtil.getPreviousMonthFirst());
//		System.out.println("获取上月最后一天的日期:" + dateUtil.getPreviousMonthEnd());
//		System.out.println("获取下月第一天日期:" + dateUtil.getNextMonthFirst());
//		System.out.println("获取下月最后一天日期:" + dateUtil.getNextMonthEnd());
//		System.out.println("获取本年的第一天日期:" + dateUtil.getCurrentYearFirst());
//		System.out.println("获取本年最后一天日期:" + dateUtil.getCurrentYearEnd());
//		System.out.println("获取去年的第一天日期:" + dateUtil.getPreviousYearFirst());
//		System.out.println("获取去年的最后一天日期:" + dateUtil.getPreviousYearEnd());
//		System.out.println("获取明年第一天日期:" + dateUtil.getNextYearFirst());
//		System.out.println("获取明年最后一天日期:" + dateUtil.getNextYearEnd());
//		System.out.println("获取本季度第一天到最后一天:" + dateUtil.getThisSeasonTime(11));
//		System.out.println("获取两个日期之间间隔天数2008-12-1~2008-9.29:" +
//		DateUtil.getTwoDay("2008-12-1", "2008-9-29")); 1970-01-17 03:21:10
//		long in = Long.valueOf("1305278619") ;
//		System.out.println(DateUtil.formatDate(in, "yyyy-MM-dd HH:mm:ss"));
//		System.out.println(dateUtil.getWeekStr(DateUtil.formatDate(DateUtil.getTimestamp(DateUtil.getCurrentDate()), "yyyy-MM-dd HH:mm:ss")));


		Date nowTime = new Date();
		Date time2 = DateUtil.addDays(nowTime, 10);
		System.out.println(getTimeDiff(time2, nowTime, 1));
	}

	public static Date strToDate(String dateStr, String format) throws ParseException
	{
		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(format);
		try {
			date = df.parse(dateStr);
		} catch (ParseException pe) {
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}

		return date;
	}

	public static Long getLongByStr(String str, String format)
	{
		if (str == null) {
			return null;
		}
		SimpleDateFormat f = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = f.parse(str);
		} catch (ParseException e) {
			log.error(e.getMessage());
		}
		Long time = Long.valueOf(date.getTime());
		return time;
	}



	/**
	 * 获取两个时间差值
	 * @param timest 开始时间
	 * @param timeed 结束时间
	 * @param type 1 天，2 时， 3 分， 4 秒， 5 毫秒（ms）
	 * @return 成功返回时间差值，不成功返回null
	 */
	public static Long getTimeDiff(Date timest, Date timeed, int type) {
		try {
			if(timest == null) {
				log.info("getTimeDiff timest 为空！");
				return null;
			}
			if (timeed == null) {
				log.info("getTimeDiff timeed 为空！");
				return null;
			}

			Long result= null;
			switch (type) {
				case 1:
					result = getDiffDays(timest, timeed);
					break;
				case 2:
					result = getDiffHours(timest, timeed);
					break;
				case 3:
					result = getDiffMinutes(timest, timeed);
					break;
				case 4:
					result = getDiffSeconds(timest, timeed);
					break;
				case 5:
					result = getDiffStamp(timest, timeed);
					break;
				default:
					log.info("getTimeDiff type未定义！");
					break;
			}
			return result;

		} catch (Exception e) {
			log.error("getTimeDiff 异常：" + e.getMessage());
		}
		return null;
	}


	/**
	 * 获取两个时间相差的天数
	 * @param timest 开始时间
	 * @param timeed 结束时间
	 * @return 成功返回相差天数，不成功返回null
	 */
	private static Long getDiffDays(Date timest, Date timeed) {
		try {
			Calendar calst = Calendar.getInstance();
			Calendar caled = Calendar.getInstance();
			calst.setTime(timest);
			caled.setTime(timeed);
			log.info("LongDiffDays 开始时间：" + formatDate(timest, "yyyy-MM-dd"));
			log.info("LongDiffDays 结束时间：" + formatDate(timeed, "yyyy-MM-dd"));

			//设置时间为0时
			calst.set(Calendar.HOUR_OF_DAY, 0);
			calst.set(Calendar.MINUTE, 0);
			calst.set(Calendar.SECOND, 0);

			caled.set(Calendar.HOUR_OF_DAY, 0);
			caled.set(Calendar.MINUTE, 0);
			caled.set(Calendar.SECOND, 0);
			//得到两个日期相差的天数
			Long days = ((Long) (caled.getTime().getTime() / 1000) - (Long) (calst.getTime().getTime() / 1000)) / 3600 / 24;

			return days;
		} catch (Exception e) {
			log.error("LongDiffDays 异常：" + e.getMessage());
		}
		return null;
	}
	/**
	 * 获取两个时间相差的分钟数
	 * @param timest 开始时间
	 * @param timeed 结束时间
	 * @return 成功返回相差分钟数，不成功返回null
	 */
	private static Long getDiffHours(Date timest, Date timeed) {
		try {
			Calendar calst = Calendar.getInstance();
			Calendar caled = Calendar.getInstance();
			calst.setTime(timest);
			caled.setTime(timeed);
			log.info("LongDiffDays 开始时间：" + formatDate(timest, "yyyy-MM-dd HH:mm:ss"));
			log.info("LongDiffDays 结束时间：" + formatDate(timeed, "yyyy-MM-dd HH:mm:ss"));

			//设置时间为0时
//			calst.set(java.util.Calendar.HOUR_OF_DAY, 0);
			calst.set(Calendar.MINUTE, 0);
			calst.set(Calendar.SECOND, 0);

//			caled.set(java.util.Calendar.HOUR_OF_DAY, 0);
			caled.set(Calendar.MINUTE, 0);
			caled.set(Calendar.SECOND, 0);
			//得到两个日期相差的天数
			Long hoerss = ((Long) (caled.getTime().getTime() / 1000) - (Long) (calst.getTime().getTime() / 1000)) / 3600;

			return hoerss;
		} catch (Exception e) {
			log.error("getDiffHours 异常：" + e.getMessage());
		}
		return null;
	}

	/**
	 * 获取两个时间相差的分钟数
	 * @param timest 开始时间
	 * @param timeed 结束时间
	 * @return 成功返回相差分钟数，不成功返回null
	 */
	private static Long getDiffMinutes(Date timest, Date timeed) {
		try {
			Calendar calst = Calendar.getInstance();
			Calendar caled = Calendar.getInstance();
			calst.setTime(timest);
			caled.setTime(timeed);
			log.info("LongDiffDays 开始时间：" + formatDate(timest, "yyyy-MM-dd HH:mm:ss"));
			log.info("LongDiffDays 结束时间：" + formatDate(timeed, "yyyy-MM-dd HH:mm:ss"));

			//设置时间为0时
//			calst.set(java.util.Calendar.HOUR_OF_DAY, 0);
//			calst.set(java.util.Calendar.MINUTE, 0);
			calst.set(Calendar.SECOND, 0);

//			caled.set(java.util.Calendar.HOUR_OF_DAY, 0);
//			caled.set(java.util.Calendar.MINUTE, 0);
			caled.set(Calendar.SECOND, 0);
			//得到两个日期相差的天数
			Long minutess = ((Long) (caled.getTime().getTime() / 1000) - (Long) (calst.getTime().getTime() / 1000)) / 60;

			return minutess;
		} catch (Exception e) {
			log.info("getDiffMinutes 异常：" + e.getMessage());
		}
		return null;
	}

	/**
	 * 获取两个时间相差的数据刻度
	 * @param timest 开始时间
	 * @param timeed 结束时间
	 * @return 成功返回时间刻度，不成功返回null
	 */
	private static Long getDiffSeconds(Date timest, Date timeed) {
		try {
			Calendar calst = Calendar.getInstance();
			Calendar caled = Calendar.getInstance();
			calst.setTime(timest);
			caled.setTime(timeed);
			log.info("LongDiffDays 开始时间：" + formatDate(timest, "yyyy-MM-dd HH:mm:ss"));
			log.info("LongDiffDays 结束时间：" + formatDate(timeed, "yyyy-MM-dd HH:mm:ss"));

			//设置时间为0时
//			calst.set(java.util.Calendar.HOUR_OF_DAY, 0);
//			calst.set(java.util.Calendar.MINUTE, 0);
//			calst.set(java.util.Calendar.SECOND, 0);

//			caled.set(java.util.Calendar.HOUR_OF_DAY, 0);
//			caled.set(java.util.Calendar.MINUTE, 0);
//			caled.set(java.util.Calendar.SECOND, 0);
			//得到两个日期相差的天数
			Long minutess = ((Long) (caled.getTime().getTime() / 1000) - (Long) (calst.getTime().getTime() / 1000));

			return minutess;
		} catch (Exception e) {
			log.error("getDiffSeconds 异常：" + e.getMessage());
		}
		return null;
	}
	/**
	 * 获取两个时间的相差时间刻度
	 * @param timest 开始时间
	 * @param timeed 结束时间
	 * @return 成功返回时间刻度，不成功返回null
	 */
	private static Long getDiffStamp(Date timest, Date timeed) {
		try {
			Calendar calst = Calendar.getInstance();
			Calendar caled = Calendar.getInstance();
			calst.setTime(timest);
			caled.setTime(timeed);
			log.info("LongDiffDays 开始时间：" + formatDate(timest, "yyyy-MM-dd HH:mm:ss"));
			log.info("LongDiffDays 结束时间：" + formatDate(timeed, "yyyy-MM-dd HH:mm:ss"));

			//设置时间为0时
//			calst.set(java.util.Calendar.HOUR_OF_DAY, 0);
//			calst.set(java.util.Calendar.MINUTE, 0);
//			calst.set(java.util.Calendar.SECOND, 0);

//			caled.set(java.util.Calendar.HOUR_OF_DAY, 0);
//			caled.set(java.util.Calendar.MINUTE, 0);
//			caled.set(java.util.Calendar.SECOND, 0);
			//得到两个日期相差的天数
			Long stamps = ((Long) (caled.getTime().getTime()) - (Long) (calst.getTime().getTime()));

			return stamps;
		} catch (Exception e) {
			log.error("getDiffStamp 异常：" + e.getMessage());
		}
		return null;
	}

	public static Integer compareDate(Date date1, Date date2, int stype) {
		if (null == date1) {
			return null;
		}
		int n = 0;
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		try {
			c1.setTime(date1);
			c2.setTime(date2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		while (!c1.after(c2)) { // 循环对比，直到相等，n 就是所要的结果
			n++;
			if (stype == 1) {
				c1.add(Calendar.MONTH, 1); // 比较月份，月份+1
			} else {
				c1.add(Calendar.DATE, 1); // 比较天数，日期+1
			}
		}
		n = n - 1;
		if (stype == 2) {
			n = (int) n / 365;
		}
		return n;
	}
	public static Date monthChange(Date date, Integer month) {
		//return DateUtil.addMonths(date,month);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		calendar.add(Calendar.MONTH, month);

		Date lastdate = DateUtil.getLastDayOfMonth(date, 0);

		Date dates = DateUtil.addMonths(date, month);
		if(DateUtil.formatDate(lastdate).equals(DateUtil.formatDate(date)) ){
			if(Calendar.MONTH != 2){
				dates = DateUtil.getLastDayOfMonth(dates, 0);
			}
		}
		return dates;
	}
	public static Date getLastDayOfMonth(Date fiducialDate, int offset)
	{
		Calendar cal = Calendar.getInstance();
		if (fiducialDate == null)
			fiducialDate = new Date();
		cal.setTime(fiducialDate);
		cal.add(2, offset + 1);
		cal.set(5, 1);
		cal.add(5, -1);
		return cal.getTime();
	}


	/**
	 * 日期格式化对象,日期型（yyyy-MM-dd）
	 */
	private static ThreadLocal<SimpleDateFormat> DATE_FORMAT = new ThreadLocal<SimpleDateFormat>() {
		protected synchronized SimpleDateFormat initialValue() {
			return new SimpleDateFormat(DATE_PATTERN_LINE);
		}
	};

	/**
	 * 日期时间格式化对象,日期时间型（yyyy-MM-dd HH:mm:ss）
	 */
	private static ThreadLocal<SimpleDateFormat> DATE_TIME_FORMAT = new ThreadLocal<SimpleDateFormat>() {
		protected synchronized SimpleDateFormat initialValue() {
			return new SimpleDateFormat(DATETIME24_PATTERN_LINE);
		}
	};

	/**
	 * 日期时间格式化对象,日期时间型（yyyyMMddHHmmss）
	 */
	private static ThreadLocal<SimpleDateFormat> DATE_TIME_FORMAT2 = new ThreadLocal<SimpleDateFormat>() {
		protected synchronized SimpleDateFormat initialValue() {
			return new SimpleDateFormat(DATETIME24_NO_LINE);
		}
	};

	public static SimpleDateFormat getDateFormat(int sl) {
		switch (sl) {
			case 0:
				return DATE_FORMAT.get();
			case 1:
				return DATE_TIME_FORMAT.get();
			case 2:
				return DATE_TIME_FORMAT2.get();
		}
		return null;
	}

	//通用时间转换方法
	public static Date parse(String textDate, int sl) throws ParseException {
		switch (sl) {
			case 0://日期转换
				return getDateFormat(0).parse(textDate);
			case 1://日期+时间转换
				return getDateFormat(1).parse(textDate);
			case 2://日期+时间转换（yyyyMMddHHmmss 形式）
				return getDateFormat(2).parse(textDate);
		}
		return null;
	}

	/**
	 * @description: 获取相对于本月第n月第一天的Long类型的值  例如2016-05-01 00:00:00的Long类型
	 * 例如今天2016-04-24  getMonthFirstDay(-1)代表上月	2016-03-01 00:00:00
	 * 					 getMonthFirstDay(0)代表本月    2016-04-01 00:00:00
	 * 					 getMonthFirstDay(1)代表下月	2016-05-01 00:00:00
	 * @param n
	 * @return Long
	 * @author :ZWenBO
	 */
	public static Long getMonthFirstDay(int n){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH,n);// 设置为本月   考虑辩解问题需要用add
		calendar.set(Calendar.DATE, 1);// 设置为本月第一天
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTimeInMillis();
	}
	/**
	 * @description: 获取相对于本周第n周的周一Long类型的值  例如2016-05-01 00:00:00的Long类型
	 * 例如今天2016-04-21  getWeekFirstDay(-1)代表上周1	2016-04-11 00:00:00
	 * 					 getWeekFirstDay(0)代表本周1   2016-04-18 00:00:00
	 * 					 getWeekFirstDay(1)代表下周1	2016-04-25 00:00:00
	 * @param n
	 * @return Long
	 * @author :ZWenBO
	 */
	public static Long getWeekFirstDay(int n){
		Calendar currentDate = Calendar.getInstance();
		currentDate.add(Calendar.WEEK_OF_MONTH,n);//设置为本月   考虑边界问题需要用add
		currentDate.set(Calendar.DAY_OF_WEEK,2);//默认是星期六最后一天，往后推两天
		currentDate.set(Calendar.HOUR_OF_DAY, 0);
		currentDate.set(Calendar.MINUTE, 0);
		currentDate.set(Calendar.SECOND, 0);
		return currentDate.getTimeInMillis();
	}
	/**
	 * @desc :计算两个时间差并返回差多少月
	 * [与资产计算相同]
	 * @param s
	 * @param e
     * @return m
     */
	public static int getMonthDiff(Date s, Date e) {
		if (s.after(e)) {
			Date t = s;
			s = e;
			e = t;
		}
		Calendar start = Calendar.getInstance();
		start.setTime(s);
		Calendar end = Calendar.getInstance();
		end.setTime(e);
		Calendar temp = Calendar.getInstance();
		temp.setTime(e);
		// temp.add(Calendar.DATE, 1);

		int y = end.get(Calendar.YEAR) - start.get(Calendar.YEAR);
		int endM = end.get(Calendar.MONTH), startM = start.get(Calendar.MONTH);
		int m = endM - startM + y * 12;

		if (start.get(Calendar.DAY_OF_MONTH) > end.get(Calendar.DAY_OF_MONTH) + 1) {
			m -= 1;
		}

		return m;
	}
}
