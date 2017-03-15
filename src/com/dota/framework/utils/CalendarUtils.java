package com.dota.framework.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalendarUtils {
	
	public static void main(String[] args) throws ParseException {

		/*String time="2015-01-30 24:00"; //1420387200000
		System.out.println(strToDateWithTime(time));*/
		String temp="";
		for (int i = 0; i < 101; i++) {
			temp+="我";
		}
		System.out.println(temp);
	}
	
	/**
	 * 生成xgcalendar所需要的时间格式
	 * @param date
	 * @return
	 */
	public static String makeCalendarTime(Date date){
		return "/Date("+date.getTime()+")/";
	}
	
	/**
	 * 格式化时间格式
	 * @param time
	 * @return
	 */
	public static String formatTime(int time){
		String res=time+"";
		return(res.length()==1?"0"+res:res);
	}
	
	/**
	 * 时间类型字符串 转换为 date类型
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	public static Date strToDate(String time){	
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式  
		Date date=null;
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
		 
			e.printStackTrace();
		}			
		return date;
	}
	
	/**
	 * 时间类型字符串 转换为 date类型
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	public static Date strToDateWithTime(String time){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm"); //设置时间格式  
		if(time.length()==10){
			sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式  
		}
		
		Date date=null;
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
		
			e.printStackTrace();
		}			
		return date;
	}
	
	/**
	 * 时间类型字符串 转换为 date类型
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	public static String formateDate(Date date,String formatStr){	
		SimpleDateFormat sdf=new SimpleDateFormat(formatStr); //设置时间格式  
		String res="";
		try {
			res = sdf.format(date);
		} catch (Exception e) {		
			e.printStackTrace();
		}			
		return res;
	}
	/**
	 * 时间类型字符串 转换为 date类型
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	public static String formateDate(Date date){	
	    return formateDate(date,"yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 计算日期为周几
	 * @param date
	 * @return
	 */
	public static String calWeekDay(Date date){
		 String[] week={"","7","1","2","3","4","5","6"};
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(date);
		 int num = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		 
		 return week[num];
	}
	
	/**
	 * 计算一周7天
	 * @param date
	 * @return
	 */
	public static String[] calWeek(Date date){
		 String[] week=new String[7];
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式  
		 week[0]=sdf.format(date); 
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(date);
		 for (int i = 1; i < 7; i++) {
			 cal.add(Calendar.DATE,1); // 周一日期再加6就是周日
			 week[i]=sdf.format(cal.getTime());
		 }		
		 return week;
	}
	
	/**
	 * 根据查询日期和视图类型   计算日历开始时间和结束时间
	 * @param showTime  查询时间
	 * @param viewtype  视图类型  month week day
	 * @return
	 */
	public static Date[] calDate(Date showdate,String viewtype){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置时间格式  
		Date[] dates=new Date[2];
		Date startTime = null;// 结束时间
		Date endTime = null;// 结束时间
		if ("month".equals(viewtype)) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(showdate);// 开始计算该时间所在周的时间范围
			cal.set(Calendar.DAY_OF_MONTH,1);
			
			//计算开始日期
			// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
			int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
			if (dayWeek==1) {
				cal.add(Calendar.DAY_OF_MONTH, -1);
			}
			cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
			int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
			cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
			startTime = cal.getTime();// 周一时间,开始时间
						
			//计算结束日期
			cal.setTime(showdate);// 开始计算该时间所在周的时间范围			
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH)); 
			// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
			dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
			if (dayWeek==1) {
				cal.add(Calendar.DAY_OF_MONTH, -1);
			}
			cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
			day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
			cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day+7);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
			endTime = cal.getTime();// 结束时间			
		} else if ("week".equals(viewtype)) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(showdate);// 开始计算该时间所在周的时间范围
			// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
			int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
			if (dayWeek==1) {
				cal.add(Calendar.DAY_OF_MONTH, -1);
			}
			cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
			int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
			cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
			startTime = cal.getTime();// 周一时间,开始时间
			String imptimeBegin = sdf.format(startTime);  
			System.out.println("所在周星期一的日期："+imptimeBegin);  
			cal.add(Calendar.DATE, 7); // 周一日期再加6就是周日
			endTime = cal.getTime();// 结束时间
			String imptimeEnd = sdf.format(endTime);  
			System.out.println("所在周星期日的日期："+imptimeEnd);  
		} else if ("day".equals(viewtype)) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(showdate);// 开始计算该时间所在周的时间范围
			startTime = showdate;
			cal.add(Calendar.DAY_OF_MONTH, -1);// 日期前 一天
			startTime = cal.getTime();
			cal.add(Calendar.DAY_OF_MONTH, 2);// 日期后一天
			endTime = cal.getTime();
		}
		dates[0]=startTime;
		dates[1]=endTime;
		return dates;
	}
	
	/**
	 * 计算使用了多少分钟
	 * @param startTime
	 * @param endTime
	 * @param ifDay 是否日期
	 * @return
	 */
	public static long calMinutes(Date startTime,Date endTime,boolean ifDay){
		long start=startTime.getTime();
		long end=endTime.getTime();
		
		long res=(end-start)/(1000*60); 
		
		return ifDay?res+60*24:res;
	}
	
	public static String addDate(Date now,int dayNum){
	    Calendar cal = Calendar.getInstance();
		cal.setTime(now);// 开始计算该时间所在周的时间范围
		// 1个月后
		cal.add(Calendar.DATE, dayNum); // 将当前日期加一个月
		return formateDate(cal.getTime(),"yyyy-MM-dd");
	}

	/**
	 * 获取开始时间到结束时间之间的日期
	 * @param startDay
	 * @param endDay
	 * @return
	 */
	public static List<String> getDays(String startDay,String endDay){
		List<String> days=new ArrayList<String>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式  
		Date startDate=null;
		try {
			startDate = sdf.parse(startDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		for (int i = 0; i < 7; i++) {			
			 cal.add(Calendar.DATE,i); 
			 String day=sdf.format(cal.getTime());
			 days.add(day); 
			 if(endDay.equals(day)){
				 break;
			 }			
			 cal.setTime(startDate);
		}	
		
		return days;
	}
	
	/**
	 * 计算起始时间结束时间之间的半小时个数
	 * @param startTime
	 * @param endTime
	 */
	public static long calHalfHourNum(String startTime,String endTime){
		SimpleDateFormat sdf=new SimpleDateFormat(startTime.length()==6?"HH:mm:ss":"HH:mm"); //设置时间格式  
		Date startDate=null;
		Date endDate=null;
		try {
			startDate = sdf.parse(startTime);
			endDate = sdf.parse(endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		long start=startDate.getTime();
		long end=endDate.getTime();
		
		return (end-start)/(1000*60*30);		
	}
	
}
