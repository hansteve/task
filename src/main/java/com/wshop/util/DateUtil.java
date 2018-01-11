package com.wshop.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Bruse.Wang
 * @date Jan 19, 2016 - 17:07:56 PM
 */

public class DateUtil
{

	private static DateFormat FORMAT_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static DateFormat FORMAT_SHORT_TIME = new SimpleDateFormat("yyyyMMddHHmmsss");
	private static DateFormat FORMAT_SHORT_DAY = new SimpleDateFormat("yyyyMMdd");
	private static DateFormat FORMAT_TIME_DAY = new SimpleDateFormat("yyyy-MM-dd");

	// StringתDate
	public static Date string2Date(String s)
	{
		Date date = null;
		try
		{
			date = FORMAT_TIME.parse(s);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		finally
		{
		}
		return date;
	}

	public static Date getDate()
	{
		Date date = new Date();
		return date;
	}

	public static Date string2SQLDate(String s)
	{
		Date date = null;
		date = java.sql.Date.valueOf(s);
		return date;
	}

	// DateתString
	public static String date2String(Date date)
	{
		String stringValue = null;
		date = new Date();
		try
		{
			stringValue = FORMAT_TIME.format(date);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
		}
		return stringValue;
	}

	// DateתString
	public static String date2str(Date date)
	{
		String stringValue = null;
		try
		{
			stringValue = FORMAT_TIME.format(date);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
		}
		return stringValue;
	}

	// DateתString
	// 长日期
	public static String getSysDate()
	{
		Date date = new Date();
		String stringValue = null;
		try
		{
			stringValue = FORMAT_TIME.format(date);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
		}
		return stringValue;
	}

	// 短日期
	public static String getShortSysDate(Date date)
	{
		// Date date = new Date();
		String stringValue = null;
		try
		{
			stringValue = FORMAT_TIME_DAY.format(date);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
		}
		return stringValue;
	}

	// 短日期
	public static String getShortDate(Date date)
	{
		String stringValue = null;
		try
		{
			stringValue = FORMAT_TIME_DAY.format(date);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
		}
		return stringValue;
	}

	// StringתDate
	public static Date string2ShortDate(String s)
	{
		Date date = null;
		try
		{
			date = FORMAT_TIME_DAY.parse(s);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		finally
		{
		}
		return date;
	}

	// DateתString
	// 长日期
	public static String getSysShortDate()
	{
		Date date = new Date();
		String stringValue = null;
		try
		{
			stringValue = FORMAT_SHORT_TIME.format(date);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
		}
		return stringValue;
	}

	// DateתString
	// 长日期
	public static String getSysShortDay()
	{
		Date date = new Date();
		String stringValue = null;
		try
		{
			stringValue = FORMAT_SHORT_DAY.format(date);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
		}
		return stringValue;
	}

	// DateתString
	// 短日期
	public static String getSysDateDay()
	{
		Date date = new Date();
		String stringValue = null;
		try
		{
			stringValue = FORMAT_TIME.format(date);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
		}
		return stringValue;
	}

	//
	public static Date changeDay(Date date, int offset)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR, (calendar.get(Calendar.DAY_OF_YEAR) + offset));
		return calendar.getTime();
	}

	/**
	 * 计算时间段内一共有几天，有多少工作日 数组第一个返回一共有几天，第二个返回有多少工作日
	 * 
	 * @param d1
	 * @param d2
	 * @param formatType
	 * @return
	 * @throws ParseException
	 */
	public static long[] dayOfWork(String d1, String d2, String formatType) throws ParseException
	{
		long[] strArray = new long[2];
		SimpleDateFormat sdf = new SimpleDateFormat(formatType);
		Calendar start = new GregorianCalendar();
		Calendar end = new GregorianCalendar();
		start.setTime(sdf.parse(d1));
		end.setTime(sdf.parse(d2));
		long day = 86400000;// 一天的MILLIS
		long mod = (end.getTimeInMillis() - start.getTimeInMillis()) / day;
		strArray[0] = mod;
		// System.out.println("一共是" + mod + "天");
		int n = start.get(Calendar.DAY_OF_WEEK);// START是周几
		/**
		 * 周日到周六分别是1~7
		 */
		long cnt = mod / 7;// 几个整周
		cnt = cnt * 2;
		long yushu = mod % 7;

		if (n + yushu > 7)
			cnt = cnt + 2;// 过了周六
		if (n + yushu == 7)
			cnt++;// 正好是周六
		strArray[1] = mod - cnt;
		// System.out.println("不算周六周日共" + (mod - cnt) + "天");
		return strArray;
	}

	// 得到指定日期的第一天的日期,如:2010-08-01
	public static String getMinDateByDate(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		String month = (calendar.get(Calendar.MONTH) + 1) < 10 ? "0" + (calendar.get(Calendar.MONTH) + 1)
				: (calendar.get(Calendar.MONTH) + 1) + "";
		int minDay = calendar.getActualMinimum(Calendar.DATE);
		String backMinDate = year + "-" + month + "-" + minDay;
		return backMinDate;
	}

	// 得到指定日期的最后一天日期,如:2010-08-31
	public static String getMaxDateByDate(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		String month = (calendar.get(Calendar.MONTH) + 1) < 10 ? "0" + (calendar.get(Calendar.MONTH) + 1)
				: (calendar.get(Calendar.MONTH) + 1) + "";
		int maxDay = calendar.getActualMaximum(Calendar.DATE);
		String backMaxDate = year + "-" + month + "-" + maxDay;
		return backMaxDate;
	}

	/**
	 * 当前时间的Date
	 * 
	 * @return Date
	 */
	public static Date getNowDate()
	{
		Date timesDate = new Date(System.currentTimeMillis());
		return timesDate;
	}

	/**
	 * 加一天
	 * 
	 * @return Date
	 */
	public static Date getDateAddOne(Date endData)
	{
		Date timesDate = new Date(endData.getTime() + Long.parseLong("86400000"));
		return timesDate;
	}

	/**
	 * 加一天
	 * 
	 * @return Date
	 */
	public static Date getDateAddOne(Timestamp endData)
	{
		Date timesDate = new Date(endData.getTime() + Long.parseLong("86400000"));
		return timesDate;
	}

	/**
	 * 减一天
	 * 
	 * @return Date
	 */
	public static Date getDateMinusOne(Timestamp endData)
	{
		Date timesDate = new Date(endData.getTime() - Long.parseLong("86400000"));
		return timesDate;
	}

	/**
	 * timestamp转成date
	 * 
	 * @return Date
	 */
	public static Date getDate(Timestamp endData)
	{
		Date timesDate = new Date(endData.getTime());
		return timesDate;
	}

	/**
	 * 补齐时分秒
	 * 
	 * @param sourceDate
	 * @return Date
	 */
	public static Date makeHourMinSend(Date sourceDate)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sourceDate);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/**
	 * 补齐时分秒
	 * 
	 * @param sourceTimestamp
	 * @return Timestamp
	 */
	public static Timestamp makeHourMinSendTimestamp(Timestamp sourceTimestamp)
	{
		Timestamp timestamp = null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(sourceTimestamp.getTime());
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		timestamp = new Timestamp(calendar.getTimeInMillis());
		return timestamp;
	}

	/**
	 * 回去当前月份
	 * 
	 * @return
	 */
	public static int getCurrMonth()
	{
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.MONTH);
	}

	/**
	 * @param 获得两个日期的
	 *            天数集合
	 * @param start
	 *            ,格式"yyyy-MM"
	 * @param end
	 *            ,格式"yyyy-MM"
	 * @return List<String>
	 */
	public static List<String> getBetweenDateList(String begin, String end)
	{
		List<String> list = new ArrayList<String>();
		Date date_start = null;
		Date date_end = null;
		Date date = null;
		Calendar cd = null;
		try
		{
			date_start = FORMAT_TIME_DAY.parse(begin);
			date_end = FORMAT_TIME_DAY.parse(end);
			date = date_start;
			cd = Calendar.getInstance();

			while (date.getTime() <= date_end.getTime())
			{
				list.add(FORMAT_TIME_DAY.format(date));
				cd.setTime(date);
				cd.add(Calendar.DATE, 1);// 增加一天
				date = cd.getTime();
			}
		}
		catch (ParseException ex)
		{
			ex.printStackTrace();
		}
		return list;
	}

	public static Date getDateEnd(Date date)
	{
		if (date == null)
		{
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	public static Date getDateStart(Date date)
	{
		if (date == null)
		{
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 判断当前日期是星期几<br>
	 * <br>
	 * 
	 * @param date
	 *            要判断的时间<br>
	 * @return dayForWeek 判断结果<br>
	 * 
	 */
	public static int dayForWeek(Date date)
	{
		// SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int dayForWeek = 0;
		if (c.get(Calendar.DAY_OF_WEEK) == 1)
		{
			dayForWeek = 7;
		}
		else
		{
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return dayForWeek;
	}

	/**
	 * 获得String 类型时间
	 * 
	 * @param args
	 */
	public static String getDateStr(Timestamp timestamp)
	{
		String dateStr = FORMAT_TIME_DAY.format(timestamp);
		System.out.println(dateStr);
		// String dateString = formatter.format(date);
		return dateStr;

	}

	/**
	 * 获取当前年
	 * 
	 * @return
	 */
	public static int getNowYear()
	{
		Calendar c = new GregorianCalendar();
		return c.get(Calendar.YEAR);
	}

	/**
	 * 获取当前月
	 * 
	 * @return
	 */
	public static int getNowMonth()
	{
		Calendar c = new GregorianCalendar();
		return c.get(Calendar.MONTH) + 1;
	}

    /**
     * 获取上个月
     *
     * @return
     */
    public static int getBeforeMonth()
    {
        Calendar c = new GregorianCalendar();
        return c.get(Calendar.MONTH);
    }


    /**
	 * 获取当前日
	 * 
	 * @return
	 */
	public static int getNowDay()
	{
		Calendar c = new GregorianCalendar();
		return c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取当前时间（时间戳）
	 * 
	 * @return
	 */
	public static long getNowTime()
	{
		return System.currentTimeMillis();
	}

	/**
	 * 获取当前小时
	 * 
	 * @return
	 */
	public static int getNowHour()
	{
		Calendar c = new GregorianCalendar();
		return c.get(Calendar.HOUR_OF_DAY);
	}

	// FORMATTIME
	public static String date2StringOfSss(Date date)
	{
		String stringValue = null;
		date = new Date();
		try
		{
			stringValue = FORMAT_SHORT_TIME.format(date);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
		}
		return stringValue;
	}

	public static void main(String args[])
	{
		Timestamp time = Timestamp.valueOf("2010-10-07 14:30:30");
		// Date timesDate = new Date(time.getTime());
		getDateStr(time);
		System.out.println(date2StringOfSss(new Date()));
	}
}
