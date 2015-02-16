package se.rapid.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.Period;
import org.joda.time.ReadablePartial;
import org.joda.time.ReadablePeriod;
import org.joda.time.DateTime.Property;


public class DateTimeUtils
{
	DateTime dateTime = new DateTime();

	String onlyTime = dateTime.toString("HH:mm:ss");
	String formattedTime = dateTime.toString("yyyy-MM-dd HH:mm:ss");
	String onlyDate = dateTime.toString("yyyy-MM-dd");
	String dateAndTime = dateTime.toString("EEEE dd MMMM yyyy HH:mm:ss");

	public DateTimeUtils()
	{
	}

	public static String getDateNow()
	{
		return new DateTime().toString();
	}

	public static DateTime getDateNowToNextMonths(int numberMonths)
	{
		DateTime now = new DateTime();
		ReadablePeriod noUpdatePeriod = new Period().withMonths(numberMonths);
		return now.plus(noUpdatePeriod);
	}

	public static DateTime getDateNowToNextDays(int numberDays)
	{
		DateTime now = new DateTime();
		ReadablePeriod noUpdatePeriod = new Period().withDays(numberDays);
		return now.plus(noUpdatePeriod);
	}

	public static DateTime getDaysDateBeforeNow(int numberDays)
	{
		DateTime now = new DateTime();
		ReadablePeriod noUpdatePeriod = new Period().withDays(numberDays);
		return now.minus(noUpdatePeriod);
	}

	public static DateTime getDateNowToNextYears(int numberYears)
	{
		DateTime now = new DateTime();
		ReadablePeriod noUpdatePeriod = new Period().withYears(numberYears);
		return now.plus(noUpdatePeriod);
	}

	public static Date getDateFromString(String dateString, String pattern)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		sdf.setLenient(false);
		Date date = null;
		try
		{
			date = new Date(sdf.parse(dateString).getTime());
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		return date;
	}

	public static ReadablePartial getOnlyDate()
	{
		DateTime now = new DateTime();
		LocalDate date = now.toLocalDate();
		return date;
	}

	public static LocalTime getOnlyTime()
	{
		DateTime now = new DateTime();
		LocalTime date = now.toLocalTime();
		return date;
	}

	public static Property getDayOfTheWeek()
	{
		DateTime now = new DateTime();
		Property dayofweek = now.dayOfWeek();
		return dayofweek;
	}

	public static int weekNumber()
	{
		DateTime now = new DateTime();
		return now.weekOfWeekyear().get();
	}

	public static String getNameOfTheMonth()
	{
		DateTime dt = new DateTime(); // current time
		String monthName = dt.monthOfYear().getAsText();

		return monthName;
	}

	public static int getNumberOfTheMonth()
	{
		DateTime dt = new DateTime(); // current time

		return dt.monthOfYear().get();
	}

	public static int getDayNumber()
	{
		DateTime dt = new DateTime(); // current time

		return dt.getDayOfMonth();
	}

	public static String getOnlyYear()
	{
		int currentYear = DateTimeUtils.getOnlyDate().get(DateTimeFieldType.year());
		return Integer.toString(currentYear);
	}

	public static boolean dateCompare()
	{

		DateTime dt = new DateTime(); // current time

		return dt.isBeforeNow();

	}
	
	public static int getDayOfYear(){
		DateTime now = new DateTime();
		int dayofweek = now.getDayOfYear();
		return dayofweek;
	}
	
	public static String getShortTermOfTheMonth(){
		DateTime now = new DateTime();
		String month=now.monthOfYear().getAsShortText();
		return month;
	}
	public static String getDayOfTheWeekAsShortTerm()
	{
		DateTime now = new DateTime();
		String dayofweek = now.dayOfWeek().getAsShortText();
		return dayofweek;
	}
	
	public static int getYearAsShortTerm(){
		DateTime now = new DateTime();
		int year= now.getYearOfEra();
		return year;
	}

}
