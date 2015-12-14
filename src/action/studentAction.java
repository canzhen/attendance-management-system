package action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;

import utils.Values;
import dbentity.Course;
import dbentity.CourseHome;
import dbentity.Sc;
import dbentity.ScHome;
import dbentity.ScId;

public class studentAction extends MyActionSupport{
	private Map session= getSession();//获取session
	private String sno;//学号
	private List<Course> courses;//课程链表，保存当前时间学生的课程信息
	
	/**
	 * 在返回页面之前，需要从数据库中比对，查找当前要上的课，
	 * 然后把课程信息放到request里传递过去。
	 * 若当前时间有课，那么还要判断老师是否已经上传点名图片，
	 * 若有，则也要放到request里传递到网页端。
	 * 若当前时间段没有课，那么就返回NOCURRENTCLASS，跳转到无课页面。
	 */
	@Override
	public String index(){
		session.put("identity", "student");
		sno = "13301085";
		
		//sno = (String) session.get("id");//获取学号
		int todayClass = 0;//此时的课程数目
		
		if ( !session.get("identity").equals("student") ){
			return ERROR;
		}else{
			List<String> coursesno = getCoursesno();
			/*
			 * 0为显示所有课程和信息
			 * 1为显示当前课程信息
			 * -1为这周不属于上课周，放假或者为自习周，无课
			 * 大于1为课程冲突
			 */
			todayClass = checkHasClasses(coursesno);
			session.put("classNum", todayClass);
			if ( todayClass == 0 )
				session.put("coursesInfo", coursesno);//传入所有课程编号
			else if ( todayClass == 1 )
				session.put("coursesInfo", courses.get(0));//传入当前课程的类，包含具体信息
			else if ( todayClass > 1)
				session.put("coursesInfo", courses);//课程冲突，将所有课传入，便于页面显示
			else if ( todayClass == -1 )
				session.put("coursesInfo", "这周不属于上课周，放假或者为自习周，无课");
			
			
			return SUCCESS;
		}
	}
	
	
	/**
	 * 获得当前学生上的所有的课程的编号
	 * @return 课程编号 String的列表List
	 */
	private List<String> getCoursesno(){
		List<String> temp = new ArrayList<String>();
		ScHome schome = new ScHome();
		Transaction tran = schome.createTransaction();
		List<Sc> result = schome.findBySno(sno);
		tran.commit();
		for (int i = 0; i < result.size(); i++){
			temp.add(result.get(i).getId().getCno());
		}
		HashSet<String> hs = new HashSet<String>(temp);
		temp = new ArrayList<String>(hs);
		return temp;
	}
	
	private int checkHasClasses(List<String> classesno){
		int week;
		String day,time;
		Date date = new Date();//获取当前日期
		int current_week = getCurrentWeek(new Date(115,10,1),date);
		String current_day = getCurrentDay(date);
		String current_time = getCurrentTime(date);
		courses = new ArrayList<Course>();
		/**
		 * 记录符合当前时间段的课程的数量，
		 * 若大于1，则报告错误，等于1则正常显示，0则显示所有的课程
		 */
		int count = 0;
		CourseHome courseHome = new CourseHome();
		Transaction tran = courseHome.createTransaction();
		Course course;
		
				
		for (int i = 0; i < classesno.size(); i++){
			//获取学生每一个课的具体信息
			course = courseHome.findById(classesno.get(i));
			week = course.getCweek();
			day = course.getCday()+"";
			time = course.getCtime()+"";
			/*
			 * 和当前时间进行比对，若符合，则在count上加一，
			 * 最后判断count是否大于1，大于1则返回错误并且报告错误信息
			 * 0表示1-16周，1表示1-8周，2表示9-16周
			 */
			if (current_week<Values.BeginEndWeek[0] || current_week>Values.BeginEndWeek[1] ) return -1;
			if(((week==1 && current_week>=Values.weekTypeOne[0] && current_week<=Values.weekTypeOne[1]) 
					||( week==2 && current_week>=Values.weekTypeTwo[0] && current_week<=Values.weekTypeTwo[1]))//符合周
					&& day.contains(current_day)//符合天
					&& time.contains(current_time)){//符合时间
				courses.add(course);
				count++;
			}
		}
		
		tran.commit();
		return count;
	}
	
	/**
	 * 通过起始日期算出今天是第几周
	 * @param date 今天的日期
	 * @return 这周是开学的第几周
	 */
	private int getCurrentWeek(Date start_date,Date current_date){
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
	private String getCurrentDay(Date date){
		
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
	private String getCurrentTime(Date date){
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