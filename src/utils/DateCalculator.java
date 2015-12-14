package utils;

import java.util.Calendar;
import java.util.Date;

public class DateCalculator {
	
	/**
	 * 通过起始日期算出今天是第几周
	 * @param date 今天的日期
	 * @return 这周是开学的第几周
	 */
	public static int getCurrentWeek(Date start_date,Date current_date){
		//开学日期
	    int startWeekday=Integer.parseInt(getCurrentDay(start_date));
	    int currentWeekday=Integer.parseInt(getCurrentDay(current_date));
		while(startWeekday!=1){
			start_date=new Date(start_date.getTime()+(long)(24 * 60 * 60 * 1000));
			startWeekday=Integer.parseInt(getCurrentDay(start_date));
		}
		while(currentWeekday!=1){
			current_date=new Date(current_date.getTime()-(long)(24 * 60 * 60 * 1000));
			currentWeekday=Integer.parseInt(getCurrentDay(current_date));
		}
		 int duringDay=(int) ((current_date.getTime()-start_date.getTime())/(long)(24 * 60 * 60 * 1000));
		 int duringWeek=duringDay/7;
		 return duringWeek+1;
	}
	
	/**
	 * 获得今天是这周的第几天
	 * @param date 当前日期
	 * @return 星期几，返回(1,7)的数字，String类型
	 */
	public static String getCurrentDay(Date date){
		
		//获取今天是星期几
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		//判断一周第一天是否为星期天 
		boolean isFirstSunday = (cal.getFirstDayOfWeek() == Calendar.SUNDAY);
		//若一周第一天为星期天，则-1
		if(isFirstSunday){
			day--;
		    if(day == 0) day = 7;
		}
		return day+"";
	}
	

	/**
	 * 获得现在时间是第几节课
	 * @param date 当前日期
	 * @return 当前时间是第几节课
	 */
	public static String getCurrentTime(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int hour=cal.HOUR_OF_DAY;
		int min=cal.MINUTE;
		if(hour>=8&&hour<10){
			if(hour==9&&min>50)
				return "";
			else
			return 1+"";
		}
		if(hour>=10&&hour<12){
			if(hour==10&&min<10)
				return "";
			return 2+"";
		}
		if(hour>=12&&hour<14){
			if(hour==12&&min<10)
				return "";
			return 3+"";
		}
		if(hour>=14&&hour<16){
			if(hour==14&&min<10)
				return "";
			return 4+"";
		}
		if(hour>=16&&hour<19){
			if(hour==16&&min<20)
				return "";
			if(hour==18&&min>10)
				return "";
			return 5+"";
		}
		if(hour>=19&&hour<21){
			if(hour==20&&min>50)
				return "";
			return 6+"";
		}
		if(hour>=21&&hour<22){
			if(hour==21&&min>50)
				return "";
			return 7+"";
		}
		return "";
	}	
	
}
